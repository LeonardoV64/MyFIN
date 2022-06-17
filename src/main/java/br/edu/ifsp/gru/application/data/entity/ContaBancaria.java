package br.edu.ifsp.gru.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class ContaBancaria extends AbstractEntity {

    @NotEmpty
    private String contaBancaria = "";

    private double saldo;

    @NotNull
    @ManyToOne
    private TipoConta tipoConta;

    public TipoConta getStatus() {
        return tipoConta;
    }

    public void setStatus(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public String getConta() {
        return contaBancaria;
    }

    public void setConta(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
