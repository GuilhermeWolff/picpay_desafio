package com.picpay.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class User {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "cpfCnpj", unique = true)
	private String cpfCnpj;
	 
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "saldo")
	private BigDecimal saldo = BigDecimal.ZERO;
	
	@Column(name = "perfil")
	private int perfil;
}
