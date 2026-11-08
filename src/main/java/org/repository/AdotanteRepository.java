package org.repository;

import com.mongodb.client.model.Filters;
import org.model.Adotante;

public class AdotanteRepository extends BaseRepository<Adotante> {

    public AdotanteRepository() {
        super("Adotante", Adotante.class);
    }

    public Adotante buscarPorNome(String nome) {
        return colecao.find(Filters.eq("nome", nome)).first();
    }

    public Adotante buscarPorEmail(String email) {
        return colecao.find(Filters.eq("contato.email", email)).first();
    }


}