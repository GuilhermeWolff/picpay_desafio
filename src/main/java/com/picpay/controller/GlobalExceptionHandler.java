package com.picpay.controller;

import com.picpay.exception.TransactionBusinessException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
//	@ExceptionHandler(WalletException.class)
//	public ProblemDetail handleException(WalletException exception) {
//		return exception.toProblemDetail();
//
//	}

	//TODO: Criar metodo que trate todas as exceptions criadas

}
