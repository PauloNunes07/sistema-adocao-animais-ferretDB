package org.service;

import org.model.Animal;
import org.repository.AnimalRepository;
import org.util.enums.StatusAnimal;
import org.util.validator.AnimalValidator;
import org.util.validator.Validator;

import java.util.List;
import org.util.GeradorId;

public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public void cadastrarAnimal(Animal animal) {
        Validator.validarObjeto(animal, "animal");
        Validator.validarApenasLetras(animal.getNome(), "nome");
        Validator.validarApenasLetras(animal.getRaca(), "raça");

        AnimalValidator.validarIdade(animal.getIdade());
        Validator.validarObjeto(animal.getSexo(), "sexo");
        AnimalValidator.validarSexo(animal.getSexo().name());

        Validator.validarObjeto(animal.getPorte(), "porte");
        AnimalValidator.validarPorte(animal.getPorte());

        Validator.validarString(animal.getDescricaoSaude(), "descrição de saúde");


        animal.setStatusAnimal(StatusAnimal.DISPONIVEL);

        animal.setId(GeradorId.gerar(10));
        animalRepository.salvar(animal);
    }


    public List<Animal> listarAnimais() {
        return animalRepository.listarTodos();
    }

    public Animal buscarAnimalPorNome(String nome) {
        Validator.validarApenasLetras(nome, "nome");

        Animal animal = animalRepository.buscarPorNome(nome);
        AnimalValidator.validarAnimalEncontrado(animal);

        return animal;
    }


    public Animal buscarAnimalPorId(String id) {

        Validator.validarId(id);

        Animal animal = animalRepository.buscarPorId(id);

        AnimalValidator.validarAnimalEncontrado(animal);

        return animal;
    }


    public void atualizarAnimal(String id, Animal animal) {

        Validator.validarId(id);
        Validator.validarObjeto(animal, "animal");

        buscarAnimalPorId(id);

        Validator.validarApenasLetras(animal.getNome(), "nome");
        Validator.validarApenasLetras(animal.getRaca(), "raça");

        AnimalValidator.validarIdade(animal.getIdade());
        Validator.validarObjeto(animal.getSexo(), "sexo");
        AnimalValidator.validarSexo(animal.getSexo().name());
        AnimalValidator.validarPorte(animal.getPorte());

        Validator.validarString(animal.getDescricaoSaude(), "descrição de saúde");

        animalRepository.atualizar(id, animal);
    }


    public void removerAnimal(String id) {

        Validator.validarId(id);

        buscarAnimalPorId(id);

        animalRepository.remover(id);
    }


}
