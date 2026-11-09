package org.view;

import org.controller.AdocaoController;
import org.model.Adocao;

import java.util.List;

public class AdocaoView {

    private final ConsoleInput input;
    private final AdocaoController adocaoController = new AdocaoController();

    public AdocaoView(ConsoleInput input) {
        this.input = input;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n========== ADOÇÕES ==========");
            System.out.println("1 - Cadastrar adoção");
            System.out.println("2 - Listar adoções");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Aprovar adoção");
            System.out.println("5 - Cancelar adoção");
            System.out.println("6 - Remover adoção");
            System.out.println("7 - Listar por animal");
            System.out.println("8 - Listar por adotante");
            System.out.println("0 - Voltar");

            opcao = input.lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAdocao();
                    break;
                case 2:
                    listarAdocoes();
                    break;
                case 3:
                    buscarAdocaoPorId();
                    break;
                case 4:
                    aprovarAdocao();
                    break;
                case 5:
                    cancelarAdocao();
                    break;
                case 6:
                    removerAdocao();
                    break;
                case 7:
                    listarAdocoesPorAnimal();
                    break;
                case 8:
                    listarAdocoesPorAdotante();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarAdocao() {
        System.out.println("\n--- CADASTRAR ADOÇÃO ---");

        String adotanteId = input.lerTexto("ID do adotante: ");
        String animalId = input.lerTexto("ID do animal: ");

        Adocao adocao = new Adocao(adotanteId, animalId);

        adocaoController.cadastrarAdocao(adocao);
    }

    private void listarAdocoes() {
        List<Adocao> adocoes = adocaoController.listarAdocoes();

        for (Adocao adocao : adocoes) {
            System.out.println("ID: " + adocao.getId());
            System.out.println("ID do adotante: " + adocao.getAdotanteId());
            System.out.println("ID do animal: " + adocao.getAnimalId());
            System.out.println("Data da adoção: " + adocao.getDataAdocao());
            System.out.println("Status: " + adocao.getStatusAdocao());
            System.out.println("-------------------------");
        }
    }

    private void listarAdocoesPorAnimal() {
        String animalId = input.lerTexto("ID do animal: ");

        List<Adocao> adocoes = adocaoController.listarPorAnimal(animalId);

        if (adocoes.isEmpty()) {
            System.out.println("Nenhuma adoção encontrada para esse animal.");
            return;
        }

        for (Adocao adocao : adocoes) {
            System.out.println("ID: " + adocao.getId());
            System.out.println("ID do adotante: " + adocao.getAdotanteId());
            System.out.println("ID do animal: " + adocao.getAnimalId());
            System.out.println("Data da adoção: " + adocao.getDataAdocao());
            System.out.println("Status: " + adocao.getStatusAdocao());
            System.out.println("-------------------------");
        }
    }

    private void listarAdocoesPorAdotante() {
        String adotanteId = input.lerTexto("ID do adotante: ");

        List<Adocao> adocoes = adocaoController.listarPorAdotante(adotanteId);

        if (adocoes.isEmpty()) {
            System.out.println("Nenhuma adoção encontrada para esse adotante.");
            return;
        }

        for (Adocao adocao : adocoes) {
            System.out.println("ID: " + adocao.getId());
            System.out.println("ID do adotante: " + adocao.getAdotanteId());
            System.out.println("ID do animal: " + adocao.getAnimalId());
            System.out.println("Data da adoção: " + adocao.getDataAdocao());
            System.out.println("Status: " + adocao.getStatusAdocao());
            System.out.println("-------------------------");
        }
    }

    private void buscarAdocaoPorId() {
        String id = input.lerTexto("ID da adoção: ");

        Adocao adocao = adocaoController.buscarAdocaoPorId(id);

        if (adocao != null) {
            exibirAdocao(adocao);
        }
    }

    private void exibirAdocao(Adocao adocao) {
        System.out.println("\nAdoção encontrada!");
        System.out.println("ID: " + adocao.getId());
        System.out.println("ID do adotante: " + adocao.getAdotanteId());
        System.out.println("ID do animal: " + adocao.getAnimalId());
        System.out.println("Data: " + adocao.getDataAdocao());
        System.out.println("Status: " + adocao.getStatusAdocao());
    }

    private void aprovarAdocao() {
        String id = input.lerTexto("ID da adoção que deseja aprovar: ");
        adocaoController.aprovarAdocao(id);
    }

    private void cancelarAdocao() {
        String id = input.lerTexto("ID da adoção que deseja cancelar: ");
        adocaoController.cancelarAdocao(id);
    }

    private void removerAdocao() {
        String id = input.lerTexto("ID da adoção que deseja remover: ");
        adocaoController.removerAdocao(id);
    }
}