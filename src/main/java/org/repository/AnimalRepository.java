package org.repository;

import com.mongodb.client.model.Filters;
import org.model.Adocao;
import org.model.Animal;
import org.util.enums.StatusAnimal;

import java.util.ArrayList;
import java.util.List;

public class AnimalRepository extends BaseRepository<Animal> {

    public AnimalRepository() {
        super("Animal", Animal.class);
    }

    public Animal buscarPorNome(String nome) {
        return colecao.find(Filters.eq("nome", nome)).first();
    }

    public List<Animal> listarPorStatus(StatusAnimal statusAnimal) {
        return colecao.find(Filters.eq("statusAnimal", statusAnimal)).into(new ArrayList<>());
    }

}
