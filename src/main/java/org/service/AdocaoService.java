package org.service;

import org.exception.CampoInvalidoException;
import org.model.Adocao;
import org.model.Adotante;
import org.model.Animal;
import org.repository.AdocaoRepository;
import org.repository.AdotanteRepository;
import org.repository.AnimalRepository;
import org.util.GeradorId;
import org.util.enums.StatusAdocao;
import org.util.enums.StatusAnimal;
import org.util.validator.AdocaoValidator;
import org.util.validator.AdotanteValidator;
import org.util.validator.AnimalValidator;
import org.util.validator.Validator;

import java.util.Date;
import java.util.List;

public class AdocaoService {

    private final AdocaoRepository adocaoRepository;
    private final AdotanteRepository adotanteRepository;
    private final AnimalRepository animalRepository;

    public AdocaoService() {
        this.adocaoRepository = new AdocaoRepository();
        this.adotanteRepository = new AdotanteRepository();
        this.animalRepository = new AnimalRepository();
    }

    public void cadastrarAdocao(Adocao adocao) {
        Validator.validarObjeto(adocao, "adoção");
        Validator.validarString(adocao.getAnimalId(), "ID do animal");
        Validator.validarString(adocao.getAdotanteId(), "ID do adotante");

        Animal animal = animalRepository.buscarPorId(adocao.getAnimalId());
        AnimalValidator.validarAnimalEncontrado(animal);

        Adotante adotante = adotanteRepository.buscarPorId(adocao.getAdotanteId());
        AdotanteValidator.validarAdotanteEncontrado(adotante);

        AdocaoValidator.validarAnimalDisponivel(animal);
        AdocaoValidator.validarAdocaoExistente(adocao.getAnimalId(), adocaoRepository);

        adocao.setId(GeradorId.gerar(10));
        adocao.setDataAdocao(new Date());
        adocao.setStatusAdocao(StatusAdocao.PENDENTE);

        adocaoRepository.salvar(adocao);
    }

    public List<Adocao> listarAdocoes() {
        return adocaoRepository.listarTodos();
    }

    public Adocao buscarAdocaoPorId(String id) {
        Validator.validarString(id, "ID");

        Adocao adocao = adocaoRepository.buscarPorId(id);
        AdocaoValidator.validarAdocaoEncontrada(adocao);

        return adocao;
    }

    public void aprovarAdocao(String id) {
        Adocao adocao = buscarAdocaoPorId(id);

        if (adocao.getStatusAdocao() != StatusAdocao.PENDENTE) {
            throw new CampoInvalidoException("Somente adoções pendentes podem ser aprovadas.");
        }

        Animal animal = animalRepository.buscarPorId(adocao.getAnimalId());
        AnimalValidator.validarAnimalEncontrado(animal);

        AdocaoValidator.validarAnimalDisponivel(animal);

        adocao.setStatusAdocao(StatusAdocao.APROVADA);
        animal.setStatusAnimal(StatusAnimal.ADOTADO);

        adocaoRepository.atualizar(id, adocao);
        animalRepository.atualizar(animal.getId(), animal);
    }

    public void cancelarAdocao(String id) {
        Adocao adocao = buscarAdocaoPorId(id);

        if (adocao.getStatusAdocao() == StatusAdocao.CANCELADA) {
            throw new CampoInvalidoException("Esta adoção já está cancelada.");
        }

        if (adocao.getStatusAdocao() == StatusAdocao.APROVADA) {
            Animal animal = animalRepository.buscarPorId(adocao.getAnimalId());
            AnimalValidator.validarAnimalEncontrado(animal);
            animal.setStatusAnimal(StatusAnimal.DISPONIVEL);
            animalRepository.atualizar(animal.getId(), animal);
        }

        adocao.setStatusAdocao(StatusAdocao.CANCELADA);
        adocaoRepository.atualizar(id, adocao);
    }

    public void removerAdocao(String id) {
        Adocao adocao = buscarAdocaoPorId(id);

        if (adocao.getStatusAdocao() == StatusAdocao.APROVADA) {
            throw new CampoInvalidoException("Não é possível remover uma adoção aprovada. Cancele-a primeiro.");
        }

        adocaoRepository.remover(id);
    }

    public List<Adocao> listarPorAnimal(String animalId) {
        Validator.validarString(animalId, "ID do animal");

        Animal animal = animalRepository.buscarPorId(animalId);
        AnimalValidator.validarAnimalEncontrado(animal);

        return adocaoRepository.listarPorAnimal(animalId);
    }

    public List<Adocao> listarPorAdotante(String adotanteId) {
        Validator.validarString(adotanteId, "ID do adotante");

        Adotante adotante = adotanteRepository.buscarPorId(adotanteId);
        AdotanteValidator.validarAdotanteEncontrado(adotante);

        return adocaoRepository.listarPorAdotante(adotanteId);
    }



}