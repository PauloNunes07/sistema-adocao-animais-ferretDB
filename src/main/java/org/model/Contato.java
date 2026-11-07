package org.model;

public class Contato {
    private String email;
    private String telefone;
    private String endereco;


    public Contato(){}
    public Contato(String email, String telefone, String endereco) {
        this.email = email;
        this.telefone = telefone;
        this.endereco = endereco;
    }
}
