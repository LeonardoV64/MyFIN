package br.edu.ifsp.gru.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Contas extends AbstractEntity {

    @NotEmpty
    private String conta = "";

    private double saldo;

    @NotNull
    @ManyToOne
    private Tipo tipo;

    public Tipo getStatus() {
        return tipo;
    }

    public void setStatus(Tipo tipo) {
        this.tipo = tipo;
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}
