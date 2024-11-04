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

import java.math.BigDecimal;
import java.util.Optional;

@Service
@Slf4j
public class TransactionService {

    private final TransactionRepository repository;
    private final UserRepository userRepository;
    private final IntegrationApiService integrationApiService;

    private final static String CLIENTE_STR = "Cliente";
    private final static String LOJISTA_STR = "Lojista";
    private final static String NOTIFICATION_MSG_STR = "Você recebeu uma transferencia";


    public TransactionService(TransactionRepository repository, UserRepository userRepository, IntegrationApiService integrationApiService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.integrationApiService = integrationApiService;
    }

    public Transaction createTransaction(TransactionDTO dto) throws TransactionBusinessException {
        log.info("iniciando o processamento da transacao");

        //validacao da existencia do usuario e do tipo de perfil
        log.info("Iniciando validacao de usuario");
        User customer = checkCustomer(dto.getClienteId());

        validateBalance(customer, dto.getValor());

        //Validacao da existencia do lojsta e do tipo de perfil
        log.info("Iniciando validacao de lojista");
        User shopKeeper = checkShopkeeper(dto.getLojistaId());

        //Autorizando a transacao
        authorizeTransaction(customer.getEmail(), customer.getPassword());

        //Enviando a notificacao
        notificationTransaction(shopKeeper.getEmail(), dto.getValor());

        return persistTransaction(customer, shopKeeper, dto);
    }

    private Transaction persistTransaction(User customer, User shopKeeper, TransactionDTO dto) throws TransactionBusinessException {
        Transaction transaction = new Transaction();
        transaction.setCliente(customer);
        transaction.setLojista(shopKeeper);
        transaction.setValor(dto.getValor());
        transaction.setStatus("DONE");

         return repository.save(transaction);
    }

    private void validateBalance(User customer, BigDecimal balance) throws TransactionBusinessException {

        if(customer.getSaldo().compareTo(balance)> 0){
            throw new TransactionBusinessException("Saldo insuficiente");
        }

    }


    private User checkCustomer(Long id) throws TransactionBusinessException {
        User customer = getUserById(id);
        if (customer == null) {
            throw new TransactionBusinessException("Cliente nao encontrado!");
        }
        Perfil payer = Perfil.toEnum(customer.getPerfil());
        if (!payer.getDesc().equalsIgnoreCase(CLIENTE_STR)) {
            throw new TransactionBusinessException("Somente um cliente poderá transferir dinheiro para um lojista!");
        }
        return customer;
    }


    private User checkShopkeeper(Long id) throws TransactionBusinessException {
        User shopKeeper = getUserById(id);
        if (shopKeeper == null) {
            throw new TransactionBusinessException("Lojista nao encontrado!");
        }
        Perfil payee = Perfil.toEnum(shopKeeper.getPerfil());

        if (!payee.getDesc().equalsIgnoreCase(LOJISTA_STR)) {
            throw new TransactionBusinessException("Somente um lojista poderá receber dinheiro de um cliente!");
        }
        return shopKeeper;
    }


    private Boolean authorizeTransaction(String email, String pass) throws TransactionBusinessException {

        AuthorizationDTO authorizationDTO = new AuthorizationDTO(email, pass);

        boolean resultAuthorization = integrationApiService.authorizationRequest(authorizationDTO);

        if (!resultAuthorization) {
            throw new TransactionBusinessException("A autorização ao cliente para a transação nao foi concedida!");
        }
        return resultAuthorization;
    }


    private Boolean notificationTransaction(String shopKeeperEmail, BigDecimal value) throws TransactionBusinessException {

        NotificationDTO notificationDTO = new NotificationDTO(shopKeeperEmail, NOTIFICATION_MSG_STR, value);

        boolean resultNotification = integrationApiService.sendNotificationRequest(notificationDTO);
        if (!resultNotification) {
            throw new TransactionBusinessException("Falha ao notificar o lojista!");
        }
        return resultNotification;
    }


    private User getUserById(Long id) {
        Optional<User> opUser = userRepository.findById(id);
        return opUser.orElse(null);

    }


}
