package com.picpay.entity;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/YYYY 24H:mm:ss")
    @Column(name="data_transacao")
    private LocalDate dataTransacao = LocalDate.now();

    @Column(name="valor")
    private BigDecimal valor;

    @JoinColumn(name = "id_cliente")
    @OneToOne
    private User cliente;

    @JoinColumn(name="id_lojista")
    @OneToOne
    private User lojista;

    @Column(name="status")
    private String status;

    public Long getId() {
        return id;
    }

    public LocalDate getDataTransacao() {
        return dataTransacao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public User getCliente() {
        return cliente;
    }

    public User getLojista() {
        return lojista;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataTransacao(LocalDate dataTransacao) {
        this.dataTransacao = dataTransacao;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setCliente(User cliente) {
        this.cliente = cliente;
    }

    public void setLojista(User lojista) {
        this.lojista = lojista;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", dataTransacao=" + dataTransacao +
                ", valor=" + valor +
                ", cliente=" + cliente +
                ", lojista=" + lojista +
                ", status='" + status + '\'' +
                '}';
    }
}
