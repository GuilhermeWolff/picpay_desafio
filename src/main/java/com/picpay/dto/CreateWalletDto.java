package com.picpay.dto;

import com.picpay.entity.Wallet;

public record CreateWalletDto(String nome,
							  String cpfCnpj,
							  String email,
							  String password){
	

	public Wallet toWallet() {
		return new Wallet(
				nome,
				cpfCnpj,
				email,
				password
				
						 );
  }

}
