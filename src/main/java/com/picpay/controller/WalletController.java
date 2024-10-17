package com.picpay.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.picpay.dto.CreateWalletDto;
import com.picpay.entity.Wallet;
import com.picpay.service.WalletService;

@RestController
public class WalletController {
	
	private final WalletService walletService;
	
	public WalletController(WalletService walletService) {
		this.walletService = walletService;
	}
	
	
	@PostMapping("/wallets")
	public ResponseEntity<Wallet> createWallet(@RequestBody CreateWalletDto dto){
		
	  var wallet =	walletService.createWallet(dto);
	  return ResponseEntity.ok(wallet);
		
	}

}

