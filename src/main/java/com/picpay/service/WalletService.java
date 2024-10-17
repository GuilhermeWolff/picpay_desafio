package com.picpay.service;

import org.springframework.stereotype.Service;

import com.picpay.dto.CreateWalletDto;
import com.picpay.entity.Wallet;
import com.picpay.exception.WalletDataException;
import com.picpay.repository.WalletRepository;


@Service
public class WalletService {
	
	private final WalletRepository walletRepository;
	
	
	public WalletService(WalletRepository walletRepository) {
		this.walletRepository = walletRepository;
	}

	public Wallet createWallet(CreateWalletDto dto) {
		
	var walletCpfCnpj =	walletRepository.findByCpfCnpj(dto.cpfCnpj());
	var walletEmail =	walletRepository.findByEmail(dto.email());
	if(walletCpfCnpj.isPresent()) {
		throw new WalletDataException("Cpf ou CPNJ ja cadastrado");
	}
	if(walletEmail.isPresent()) {
		throw new WalletDataException("Email ja cadastrado");
	}
		
		return walletRepository.save(dto.toWallet());
		
		
		
	}
	


}
