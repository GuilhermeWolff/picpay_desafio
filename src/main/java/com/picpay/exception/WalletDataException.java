package com.picpay.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class WalletDataException extends WalletException{
	
	private String detail;
	
	public WalletDataException(String detail) {
		this.detail = detail;
	}
	
	@Override
	public ProblemDetail toProblemDetail() {
		var pb = ProblemDetail.forStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		
		pb.setTitle("Data already exists");
		pb.setDetail(detail);
		
		
		return pb;
		
		
		
	}

}
