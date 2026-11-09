package org.controller;

import org.exception.GlobalExceptionHandler;
import org.model.Animal;
import org.repository.AnimalRepository;
import org.service.AnimalService;

import java.util.List;

public class AnimalController {

    private final AnimalService animalService;

    public AnimalController() {
        this.animalService = new AnimalService(new AnimalRepository());
    }

    public void cadastrarAnimal(Animal animal) {
        try {
            animalService.cadastrarAnimal(animal);
            System.out.println("Animal cadastrado com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }

    public List<Animal> listarAnimais() {
        try {
            return animalService.listarAnimais();
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return List.of();
        }
    }

    public Animal buscarAnimalPorId(String id) {
        try {
            return animalService.buscarAnimalPorId(id);
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return null;
        }
    }

    public Animal buscarAnimalPorNome(String nome) {
        try {
            return animalService.buscarAnimalPorNome(nome);
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return null;
        }
    }

    public void atualizarAnimal(String id, Animal animal) {
        try {
            animalService.atualizarAnimal(id, animal);
            System.out.println("Animal atualizado com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }

    public void removerAnimal(String id) {
        try {
            animalService.removerAnimal(id);
            System.out.println("Animal removido com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }
}