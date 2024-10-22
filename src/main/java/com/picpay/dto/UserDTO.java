package com.picpay.dto;

import java.math.BigDecimal;

public class UserDTO {

    private String nome;

    private String cpfCnpj;

    private String email;

    private String password;

    private BigDecimal saldo = BigDecimal.ZERO;

    private int perfil;

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

    @Override
    public String toString() {
        return "UserDTO{" +
                "nome='" + nome + '\'' +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", saldo=" + saldo +
                ", perfil=" + perfil +
                '}';
    }
}
