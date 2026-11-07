package org.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.config.ConfiguracaoFerretDB;

public class BaseRepository<T> {

    protected final MongoCollection<T> colecao;
    public BaseRepository(String nomeColecao, Class<T> classe) {
        this.colecao = ConfiguracaoFerretDB.getDatabase().getCollection(nomeColecao, classe);
    }

    public void salvar(T objeto) {
        colecao.insertOne(objeto);
    }

    public void remover(String id) {
        colecao.deleteOne(Filters.eq("_id", id));
    }
}
