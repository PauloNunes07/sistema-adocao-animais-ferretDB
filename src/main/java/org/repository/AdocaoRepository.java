package org.repository;

import com.mongodb.client.model.Filters;
import org.model.Adocao;
import org.util.enums.StatusAdocao;

import java.util.ArrayList;
import java.util.List;

public class AdocaoRepository extends BaseRepository<Adocao>{

    public AdocaoRepository() {
        super("Adocao", Adocao.class);
    }

    public List<Adocao> listarPorAdotante(String adotanteId) {
        return colecao.find(Filters.eq("adotanteId", adotanteId)).into(new ArrayList<>());
    }

    public List<Adocao> listarPorStatus(StatusAdocao statusAdocao) {
        return colecao.find(Filters.eq("statusAdocao", statusAdocao)).into(new ArrayList<>());
    }

    public Adocao buscarPorAnimal(String animalId) {
        return colecao.find(Filters.eq("animalId", animalId)).first();
    }
}
