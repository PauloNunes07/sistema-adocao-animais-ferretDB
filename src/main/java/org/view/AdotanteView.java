package org.view;

import org.controller.AdotanteController;
import org.model.Adotante;
import org.model.Contato;

import java.util.List;

public class AdotanteView {

    private final ConsoleInput input;
    private final AdotanteController adotanteController = new AdotanteController();

    public AdotanteView(ConsoleInput input) {
        this.input = input;
    }

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n========== ADOTANTES ==========");
            System.out.println("1 - Cadastrar adotante");
            System.out.println("2 - Listar adotantes");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Buscar por nome");
            System.out.println("5 - Remover adotante");
            System.out.println("6 - Atualizar adotante");
            System.out.println("0 - Voltar");

            opcao = input.lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAdotante();
                    break;
                case 2:
                    listarAdotantes();
                    break;
                case 3:
                    buscarAdotantePorId();
                    break;
                case 4:
                    buscarAdotantePorNome();
                    break;
                case 5:
                    removerAdotante();
                    break;
                case 6:
                    atualizarAdotante();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private void cadastrarAdotante() {
        System.out.println("\n--- CADASTRAR ADOTANTE ---");

        String nome = input.lerTexto("Nome: ").trim();
        String email = input.lerTexto("E-mail: ").trim();
        String telefone = input.lerTexto("Telefone: ").trim();
        String endereco = input.lerTexto("Endereço: ").trim();

        Contato contato = new Contato(email, telefone, endereco);

        Adotante adotante = new Adotante(nome, contato);

        adotanteController.cadastrarAdotante(adotante);
    }

    private void listarAdotantes() {
        List<Adotante> adotantes = adotanteController.listarAdotantes();

        for (Adotante adotante : adotantes) {
            System.out.println("ID: " + adotante.getId());
            System.out.println("Nome: " + adotante.getNome());
            System.out.println("Email: " + adotante.getContato().getEmail());
            System.out.println("Telefone: " + adotante.getContato().getTelefone());
            System.out.println("Endereço: " + adotante.getContato().getEndereco());
            System.out.println("-------------------------");
        }
    }

    private void buscarAdotantePorId() {
        String id = input.lerTexto("ID do adotante: ").trim();

        Adotante adotante = adotanteController.buscarAdotantePorId(id);

        if (adotante != null) {
            exibirAdotante(adotante);
        }
    }

    private void buscarAdotantePorNome() {
        String nome = input.lerTexto("Nome do adotante: ").trim();

        Adotante adotante = adotanteController.buscarAdotantePorNome(nome);

        if (adotante != null) {
            exibirAdotante(adotante);
        }
    }

    private void atualizarAdotante() {
        System.out.println("\n--- ATUALIZAR ADOTANTE ---");

        String id = input.lerTexto("ID do adotante: ").trim();

        Adotante adotante = adotanteController.buscarAdotantePorId(id);

        if (adotante == null) {
            return;
        }

        System.out.println("\nDados atuais:");
        exibirAdotante(adotante);

        System.out.println("\nInforme os novos dados:");

        String nome = input.lerTexto("Novo nome: ").trim();
        String email = input.lerTexto("Novo e-mail: ").trim();
        String telefone = input.lerTexto("Novo telefone: ").trim();
        String endereco = input.lerTexto("Novo endereço: ").trim();

        Contato contato = new Contato(email, telefone, endereco);

        Adotante adotanteAtualizado = new Adotante(nome, contato);

        adotanteController.atualizarAdotante(id, adotanteAtualizado);
    }

    private void exibirAdotante(Adotante adotante) {
        System.out.println("\nAdotante encontrado!");
        System.out.println("ID: " + adotante.getId());
        System.out.println("Nome: " + adotante.getNome());
        System.out.println("E-mail: " + adotante.getContato().getEmail());
        System.out.println("Telefone: " + adotante.getContato().getTelefone());
        System.out.println("Endereço: " + adotante.getContato().getEndereco());
    }

    private void removerAdotante() {
        String id = input.lerTexto("ID do adotante que deseja remover: ").trim();
        adotanteController.removerAdotante(id);
    }
}
