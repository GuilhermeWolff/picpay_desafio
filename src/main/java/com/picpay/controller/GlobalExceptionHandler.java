package com.picpay.controller;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.picpay.exception.WalletException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(WalletException.class)
	public ProblemDetail handleException(WalletException exception) {
		return exception.toProblemDetail();
		
	}

}
