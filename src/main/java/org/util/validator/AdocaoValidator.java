package org.util.validator;

import org.exception.CampoInvalidoException;
import org.model.Adocao;
import org.model.Animal;
import org.repository.AdocaoRepository;
import org.repository.AdotanteRepository;
import org.repository.AnimalRepository;
import org.util.enums.StatusAdocao;
import org.util.enums.StatusAnimal;

import java.time.LocalDate;
import java.util.List;

public class AdocaoValidator extends Validator {

    public static void validarAnimalExistente(String animalId, AnimalRepository animalRepository) {
        if (animalRepository.buscarPorId(animalId) == null) {
            throw new CampoInvalidoException("Não é possível realizar a adoção: animal não encontrado.");
        }
    }

    public static void validarAnimalDisponivel(Animal animal) {
        if (animal.getStatusAnimal() != StatusAnimal.DISPONIVEL) {
            throw new CampoInvalidoException("Este animal não está disponível para adoção.");
        }
    }

    public static void validarAdocaoExistente(String animalId, AdocaoRepository adocaoRepository) {

        List<Adocao> adocoes = adocaoRepository.listarPorAnimal(animalId);

        for (Adocao adocao : adocoes) {
            if (adocao.getStatusAdocao() == StatusAdocao.PENDENTE ||
                    adocao.getStatusAdocao() == StatusAdocao.APROVADA) {

                throw new CampoInvalidoException("Este animal já possui uma adoção pendente ou aprovada.");
            }
        }
    }

    public static void validarAdocaoEncontrada(Adocao adocao) {
        if (adocao == null) {
            throw new RuntimeException("Adoção não encontrada!");
        }
    }


}