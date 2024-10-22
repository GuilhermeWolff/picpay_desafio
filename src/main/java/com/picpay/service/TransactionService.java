package com.picpay.service;


import com.picpay.dto.TransactionDTO;
import com.picpay.dto.authorization.AuthorizationDTO;
import com.picpay.dto.notification.NotificationDTO;
import com.picpay.entity.Transaction;
import com.picpay.entity.User;
import com.picpay.entity.enums.Perfil;
import com.picpay.exception.TransactionBusinessException;
import com.picpay.repository.TransactionRepository;
import com.picpay.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository repository;
    private final UserRepository userRepository;
    private final IntegrationApiService integrationApiService;

    private final static String CLIENTE_STR="Cliente";
    private final static String LOJISTA_STR="Lojista";
    private final static String NOTIFICATION_MSG_STR="Você recebeu uma transferencia";



    public TransactionService(TransactionRepository repository,UserRepository userRepository, IntegrationApiService integrationApiService){
        this.repository = repository;
        this.userRepository = userRepository;
        this.integrationApiService = integrationApiService;
    }

    public Transaction createTransaction(TransactionDTO dto) {
        log.info("iniciando o processamento da transacao");

        User cliente = getUserById(dto.getClienteId());
        log.info("Validando cliente enviado na transacao: {}", cliente);

        Perfil payer = Perfil.toEnum(cliente.getPerfil());

        if(!payer.getDesc().equalsIgnoreCase(CLIENTE_STR)){
            throw new TransactionBusinessException("Somente um cliente poderá transferir dinheiro para um lojista!");
        }

        if(dto.getValor().compareTo(cliente.getSaldo()) > 0) {
            throw new TransactionBusinessException("O cliente precisa ter saldo para a realizar a transferencia");
        }

        User lojista = getUserById(dto.getLojistaId());
        Perfil payee = Perfil.toEnum(lojista.getPerfil());

        if(!payee.getDesc().equalsIgnoreCase(LOJISTA_STR)){
            throw new TransactionBusinessException("Somente um lojista poderá receber dinheiro de um cliente!");
        }

        AuthorizationDTO authorizationDTO = new AuthorizationDTO(cliente.getEmail(), cliente.getPassword());

        boolean resultAuthorization = integrationApiService.authorizationRequest(authorizationDTO);
        if(!resultAuthorization){
            throw new TransactionBusinessException("A autorização ao cliente para a transação nao foi concedida!");
        }


        NotificationDTO notificationDTO = new NotificationDTO(lojista.getEmail(), NOTIFICATION_MSG_STR, dto.getValor());
        boolean resultNotification = integrationApiService.sendNotificationRequest(notificationDTO);
        if(!resultNotification){
            throw new TransactionBusinessException("Falha ao notificar o lojista!");
        }



        return null;

    }

    private User getUserById(Long id){
        Optional<User> opUser = userRepository.findById(id);
        return opUser.orElse(null);

    }


}
