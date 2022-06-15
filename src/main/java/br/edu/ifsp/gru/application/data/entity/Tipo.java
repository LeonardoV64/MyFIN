package br.edu.ifsp.gru.application.data.entity;

import javax.persistence.Entity;

@Entity
public class Tipo extends AbstractEntity {
    private String name;

    public Tipo() {

    }

    public Tipo(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
