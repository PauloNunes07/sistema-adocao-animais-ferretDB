package org.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.util.enums.StatusAnimal;

public class Animal {

    @BsonId
    private String id;
    private String nome;
    private String raca;
    private int idade;
    private String sexo;
    private String descricaoSaude;
    private String porte;
    private StatusAnimal statusAnimal;

    public Animal(){}

    public Animal(String nome, String raca, int idade, String sexo, String descricaoSaude, String porte, StatusAnimal statusAnimal) {
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.sexo = sexo;
        this.descricaoSaude = descricaoSaude;
        this.porte = porte;
        this.statusAnimal = statusAnimal;
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

    public StatusAnimal getStatusAnimal() {
        return statusAnimal;
    }

    public void setStatusAnimal(StatusAnimal statusAnimal) {
        this.statusAnimal = statusAnimal;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getDescricaoSaude() {
        return descricaoSaude;
    }

    public void setDescricaoSaude(String descricaoSaude) {
        this.descricaoSaude = descricaoSaude;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
