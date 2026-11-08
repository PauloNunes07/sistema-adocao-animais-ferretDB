package org.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.config.ConfiguracaoFerretDB;
import org.model.Adotante;

import java.util.ArrayList;
import java.util.List;

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

    public T buscarPorId(String id) {
        return colecao.find(Filters.eq("_id", id)).first();
    }

    public List<T> listarTodos() {
        return colecao.find().into(new ArrayList<>());
    }

    public void atualizar(String id, T objeto) {
        colecao.replaceOne(Filters.eq("_id", id), objeto);
    }


}
