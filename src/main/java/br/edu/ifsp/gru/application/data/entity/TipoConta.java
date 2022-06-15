package br.edu.ifsp.gru.application.data.entity;

import javax.persistence.Entity;

@Entity
public class TipoConta extends AbstractEntity {
    private String tipo;

    public TipoConta() {

    }

    public TipoConta(String tipo) {
        this.tipo = tipo;
    }

    public String getName() {
        return tipo;
    }

    public void setName(String tipo) {
        this.tipo = tipo;
    }

}
