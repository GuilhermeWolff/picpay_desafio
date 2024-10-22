package com.picpay.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import java.math.BigDecimal;

public class TransactionDTO {

    @JsonAlias("value")
    private BigDecimal valor;

    @JsonAlias("payer")
    private Long clienteId;

    @JsonAlias("payee")
    private Long lojistaId;

    public BigDecimal getValor() {
        return valor;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public Long getLojistaId() {
        return lojistaId;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public void setLojistaId(Long lojistaId) {
        this.lojistaId = lojistaId;
    }

    @Override
    public String toString() {
        return "TransactionDTO{" +
                "valor=" + valor +
                ", clienteId=" + clienteId +
                ", lojistaId=" + lojistaId +
                '}';
    }
}
