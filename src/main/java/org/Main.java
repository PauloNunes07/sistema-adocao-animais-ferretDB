
package org;

import org.model.Adocao;
import org.repository.AdocaoRepository;
import org.util.enums.StatusAdocao;

import java.util.List;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        AdocaoRepository repository = new AdocaoRepository();

        // CADASTRO
        System.out.println("===== CADASTRO =====");

        Adocao adocao = new Adocao("ad001", "an001");

        adocao.setId("ado001");
        adocao.setDataAdocao(new Date());
        adocao.setStatusAdocao(StatusAdocao.PENDENTE);

        repository.salvar(adocao);

        System.out.println("Adoção cadastrada com sucesso!");

        // BUSCA POR ID
        System.out.println("\n===== BUSCA POR ID =====");

        Adocao encontrada = repository.buscarPorId("ado001");

        if (encontrada != null) {
            System.out.println("Adoção encontrada: " + encontrada.getId());
            System.out.println("Adotante: " + encontrada.getAdotanteId());
            System.out.println("Animal: " + encontrada.getAnimalId());
        }

        // LISTAGEM
        System.out.println("\n===== LISTAGEM =====");

        List<Adocao> adocoes = repository.listarTodos();

        for (Adocao a : adocoes) {
            System.out.println("ID: " + a.getId());
            System.out.println("Adotante: " + a.getAdotanteId());
            System.out.println("Animal: " + a.getAnimalId());
            System.out.println("Status: " + a.getStatusAdocao());
            System.out.println("--------------------");
        }

        // BUSCA POR ADOTANTE
        System.out.println("\n===== BUSCA POR ADOTANTE =====");

        List<Adocao> porAdotante =
                repository.listarPorAdotante("ad001");

        for (Adocao a : porAdotante) {
            System.out.println("Adoção: " + a.getId());
            System.out.println("Animal: " + a.getAnimalId());
        }

        // BUSCA POR STATUS
        System.out.println("\n===== BUSCA POR STATUS =====");

        List<Adocao> porStatus =
                repository.listarPorStatus(StatusAdocao.PENDENTE);

        for (Adocao a : porStatus) {
            System.out.println("Adoção: " + a.getId());
            System.out.println("Status: " + a.getStatusAdocao());
        }

        // BUSCA POR ANIMAL
        System.out.println("\n===== BUSCA POR ANIMAL =====");

        Adocao porAnimal = repository.buscarPorAnimal("an001");

        if (porAnimal != null) {
            System.out.println("Adoção encontrada: " + porAnimal.getId());
            System.out.println("Adotante: " + porAnimal.getAdotanteId());
        }

        // ATUALIZAÇÃO
        System.out.println("\n===== ATUALIZAÇÃO =====");

        adocao.setStatusAdocao(StatusAdocao.APROVADA);

        repository.atualizar(adocao.getId(), adocao);

        System.out.println("Adoção atualizada com sucesso!");

        // EXCLUSÃO
        System.out.println("\n===== EXCLUSÃO =====");

        repository.remover(adocao.getId());

        System.out.println("Adoção removida com sucesso!");
    }
}
