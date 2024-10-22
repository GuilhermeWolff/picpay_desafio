package com.picpay.entity;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="user")
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

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public int getPerfil() {
		return perfil;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	public void setPerfil(int perfil) {
		this.perfil = perfil;
	}

	@Override
	public String toString() {
		return "User{" +
				"perfil=" + perfil +
				", saldo=" + saldo +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", cpfCnpj='" + cpfCnpj + '\'' +
				", nome='" + nome + '\'' +
				", id=" + id +
				'}';
	}
}
