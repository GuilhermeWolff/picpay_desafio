package com.picpay.controller;


import com.picpay.dto.TransactionDTO;
import com.picpay.entity.Transaction;
import com.picpay.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/transacion")
@Slf4j
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(TransactionDTO dto){
        log.info("Iniciando o processamento da transacao: {}", dto);
        Transaction transaction = service.createTransaction(dto);
        /*
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();

         */
        log.info("Fim do processamento User{}", transaction);
        return ResponseEntity.ok().build();

    }




}
