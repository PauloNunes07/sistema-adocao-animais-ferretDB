package org.model;

import org.bson.codecs.pojo.annotations.BsonId;

public class Adotante {

    @BsonId
    private String id;

    private String nome;

    private Contato contato;

    public Adotante(){}

    public Adotante(String nome, Contato contato) {
        this.nome = nome;
        this.contato = contato;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
