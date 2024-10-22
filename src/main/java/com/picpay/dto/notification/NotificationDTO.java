package com.picpay.dto.notification;

import java.math.BigDecimal;


public class NotificationDTO {

    public NotificationDTO() {
    }

    public NotificationDTO(String email, String mensagem, BigDecimal valor) {
        this.email = email;
        this.mensagem = mensagem;
        this.valor = valor;
    }

    private String email;

    private String mensagem;

    private BigDecimal valor;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
