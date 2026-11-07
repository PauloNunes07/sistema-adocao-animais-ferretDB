package org.model;

import org.bson.codecs.pojo.annotations.BsonId;
import org.util.enums.StatusAdocao;

import java.util.Date;

public class Adocao {
    @BsonId
    private String id;
    private String adotanteId;
    private String animalId;
    private Date dataAdocao;
    private StatusAdocao statusAdocao;

    public Adocao() {}

    public Adocao(String adotanteId, String animalId) {
        this.adotanteId = adotanteId;
        this.animalId = animalId;
    }

    public String getAdotanteId() {
        return adotanteId;
    }

    public void setAdotanteId(String adotanteId) {
        this.adotanteId = adotanteId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnimalId() {
        return animalId;
    }

    public void setAnimalId(String animalId) {
        this.animalId = animalId;
    }

    public Date getDataAdocao() {
        return dataAdocao;
    }

    public void setDataAdocao(Date dataAdocao) {
        this.dataAdocao = dataAdocao;
    }

    public StatusAdocao getStatusAdocao() {
        return statusAdocao;
    }

    public void setStatusAdocao(StatusAdocao statusAdocao) {
        this.statusAdocao = statusAdocao;
    }
}
