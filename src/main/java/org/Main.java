package org;

import org.controller.AnimalController;
import org.controller.AdotanteController;
import org.controller.AdocaoController;
import org.model.Animal;
import org.model.Adotante;
import org.model.Contato;
import org.model.Adocao;
import org.util.enums.TipoSexo;

import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static final AnimalController animalController = new AnimalController();
    private static final AdotanteController adotanteController = new AdotanteController();
    private static final AdocaoController adocaoController = new AdocaoController();

    public static void main(String[] args) {

        int opcao;

        do {
            System.out.println("\n========== PET ADOTE ==========");
            System.out.println("1 - Animais");
            System.out.println("2 - Adotantes");
            System.out.println("3 - Adoções");
            System.out.println("0 - Sair");
            System.out.println("===============================");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    menuAnimais();
                    break;
                case 2:
                    menuAdotantes();
                    break;
                case 3:
                    menuAdocoes();
                    break;
                case 0:
                    System.out.println("\nSistema encerrado!");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        scanner.close();
    }

    // ==================== MENU ANIMAIS ====================

    private static void menuAnimais() {
        int opcao;

        do {
            System.out.println("\n========== ANIMAIS ==========");
            System.out.println("1 - Cadastrar animal");
            System.out.println("2 - Listar animais");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Buscar por nome");
            System.out.println("5 - Atualizar animal");
            System.out.println("6 - Remover animal");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAnimal();
                    break;
                case 2:
                    List<Animal> animais = animalController.listarAnimais();

                    for (Animal animal : animais) {
                        System.out.println("ID: " + animal.getId());
                        System.out.println("Nome: " + animal.getNome());
                        System.out.println("Raça: " + animal.getRaca());
                        System.out.println("Idade: " + animal.getIdade());
                        System.out.println("Sexo: " + animal.getSexo());
                        System.out.println("Descrição de saúde: " + animal.getDescricaoSaude());
                        System.out.println("Porte: " + animal.getPorte());
                        System.out.println("Status: " + animal.getStatusAnimal());
                        System.out.println("-------------------------");
                    }
                    break;
                case 3:
                    buscarAnimalPorId();
                    break;
                case 4:
                    buscarAnimalPorNome();
                    break;
                case 5:
                    atualizarAnimal();
                    break;
                case 6:
                    removerAnimal();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private static void cadastrarAnimal() {
        System.out.println("\n--- CADASTRAR ANIMAL ---");

        String nome = lerTexto("Nome: ");
        String raca = lerTexto("Raça: ");
        int idade = lerInteiro("Idade: ");
        TipoSexo sexo = lerSexo();
        String descricaoSaude = lerTexto("Descrição de saúde: ");
        String porte = lerTexto("Porte (PEQUENO, MEDIO ou GRANDE): ");

        Animal animal = new Animal(
                nome, raca, idade, sexo, descricaoSaude, porte, null
        );

        animalController.cadastrarAnimal(animal);
    }

    private static void buscarAnimalPorId() {
        String id = lerTexto("ID do animal: ");

        Animal animal = animalController.buscarAnimalPorId(id);

        if (animal != null) {
            System.out.println("\nAnimal encontrado!");
            System.out.println("Nome: " + animal.getNome());
            System.out.println("Raça: " + animal.getRaca());
            System.out.println("Idade: " + animal.getIdade());
            System.out.println("Sexo: " + animal.getSexo());
            System.out.println("Porte: " + animal.getPorte());
            System.out.println("Saúde: " + animal.getDescricaoSaude());
            System.out.println("Status: " + animal.getStatusAnimal());
        }
    }

    private static void buscarAnimalPorNome() {
        String nome = lerTexto("Nome do animal: ");

        Animal animal = animalController.buscarAnimalPorNome(nome);

        if (animal != null) {
            System.out.println("\nAnimal encontrado!");
            System.out.println("ID: " + animal.getId());
            System.out.println("Nome: " + animal.getNome());
            System.out.println("Raça: " + animal.getRaca());
            System.out.println("Idade: " + animal.getIdade());
            System.out.println("Sexo: " + animal.getSexo());
            System.out.println("Porte: " + animal.getPorte());
            System.out.println("Status: " + animal.getStatusAnimal());
        }
    }

    private static void atualizarAnimal() {
        String id = lerTexto("ID do animal que deseja atualizar: ");

        Animal animalExistente = animalController.buscarAnimalPorId(id);

        if (animalExistente == null) {
            return;
        }

        System.out.println("\n--- NOVOS DADOS DO ANIMAL ---");

        String nome = lerTexto("Nome: ");
        String raca = lerTexto("Raça: ");
        int idade = lerInteiro("Idade: ");
        TipoSexo sexo = lerSexo();
        String descricaoSaude = lerTexto("Descrição de saúde: ");
        String porte = lerTexto("Porte: ");

        Animal animal = new Animal(
                nome,
                raca,
                idade,
                sexo,
                descricaoSaude,
                porte,
                animalExistente.getStatusAnimal()
        );

        animalController.atualizarAnimal(id, animal);
    }

    private static void removerAnimal() {
        String id = lerTexto("ID do animal que deseja remover: ");
        animalController.removerAnimal(id);
    }

    // ==================== MENU ADOTANTES ====================

    private static void menuAdotantes() {
        int opcao;

        do {
            System.out.println("\n========== ADOTANTES ==========");
            System.out.println("1 - Cadastrar adotante");
            System.out.println("2 - Listar adotantes");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Buscar por nome");
            System.out.println("5 - Remover adotante");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAdotante();
                    break;
                case 2:
                    List<Adotante> adotantes = adotanteController.listarAdotantes();

                    for (Adotante adotante : adotantes) {
                        System.out.println("ID: " + adotante.getId());
                        System.out.println("Nome: " + adotante.getNome());
                        System.out.println("Email: " + adotante.getContato().getEmail());
                        System.out.println("Telefone: " + adotante.getContato().getTelefone());
                        System.out.println("Endereço: " + adotante.getContato().getEndereco());
                        System.out.println("-------------------------");
                    }
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
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private static void cadastrarAdotante() {
        System.out.println("\n--- CADASTRAR ADOTANTE ---");

        String nome = lerTexto("Nome: ");
        String email = lerTexto("E-mail: ");
        String telefone = lerTexto("Telefone: ");
        String endereco = lerTexto("Endereço: ");

        Contato contato = new Contato(email, telefone, endereco);

        Adotante adotante = new Adotante(nome, contato);

        adotanteController.cadastrarAdotante(adotante);
    }

    private static void buscarAdotantePorId() {
        String id = lerTexto("ID do adotante: ");

        Adotante adotante = adotanteController.buscarAdotantePorId(id);

        if (adotante != null) {
            exibirAdotante(adotante);
        }
    }

    private static void buscarAdotantePorNome() {
        String nome = lerTexto("Nome do adotante: ");

        Adotante adotante = adotanteController.buscarAdotantePorNome(nome);

        if (adotante != null) {
            exibirAdotante(adotante);
        }
    }

    private static void exibirAdotante(Adotante adotante) {
        System.out.println("\nAdotante encontrado!");
        System.out.println("ID: " + adotante.getId());
        System.out.println("Nome: " + adotante.getNome());
        System.out.println("E-mail: " + adotante.getContato().getEmail());
        System.out.println("Telefone: " + adotante.getContato().getTelefone());
        System.out.println("Endereço: " + adotante.getContato().getEndereco());
    }

    private static void removerAdotante() {
        String id = lerTexto("ID do adotante que deseja remover: ");
        adotanteController.removerAdotante(id);
    }

    // ==================== MENU ADOÇÕES ====================

    private static void menuAdocoes() {
        int opcao;

        do {
            System.out.println("\n========== ADOÇÕES ==========");
            System.out.println("1 - Cadastrar adoção");
            System.out.println("2 - Listar adoções");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Aprovar adoção");
            System.out.println("5 - Cancelar adoção");
            System.out.println("6 - Remover adoção");
            System.out.println("0 - Voltar");

            opcao = lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAdocao();
                    break;
                case 2:
                    List<Adocao> adocoes = adocaoController.listarAdocoes();

                    for (Adocao adocao : adocoes) {
                        System.out.println("ID: " + adocao.getId());
                        System.out.println("ID do adotante: " + adocao.getAdotanteId());
                        System.out.println("ID do animal: " + adocao.getAnimalId());
                        System.out.println("Data da adoção: " + adocao.getDataAdocao());
                        System.out.println("Status: " + adocao.getStatusAdocao());
                        System.out.println("-------------------------");
                    }
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
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
            }

        } while (opcao != 0);
    }

    private static void cadastrarAdocao() {
        System.out.println("\n--- CADASTRAR ADOÇÃO ---");

        String adotanteId = lerTexto("ID do adotante: ");

        Adotante adotante = adotanteController.buscarAdotantePorId(adotanteId);


        String animalId = lerTexto("ID do animal: ");

        Adocao adocao = new Adocao(adotanteId, animalId);

        adocaoController.cadastrarAdocao(adocao);
    }

    private static void buscarAdocaoPorId() {
        String id = lerTexto("ID da adoção: ");

        Adocao adocao = adocaoController.buscarAdocaoPorId(id);

        if (adocao != null) {
            exibirAdocao(adocao);
        }
    }

    private static void exibirAdocao(Adocao adocao) {
        System.out.println("\nAdoção encontrada!");
        System.out.println("ID: " + adocao.getId());
        System.out.println("ID do adotante: " + adocao.getAdotanteId());
        System.out.println("ID do animal: " + adocao.getAnimalId());
        System.out.println("Data: " + adocao.getDataAdocao());
        System.out.println("Status: " + adocao.getStatusAdocao());
    }

    private static void aprovarAdocao() {
        String id = lerTexto("ID da adoção que deseja aprovar: ");
        adocaoController.aprovarAdocao(id);
    }

    private static void cancelarAdocao() {
        String id = lerTexto("ID da adoção que deseja cancelar: ");
        adocaoController.cancelarAdocao(id);
    }

    private static void removerAdocao() {
        String id = lerTexto("ID da adoção que deseja remover: ");
        adocaoController.removerAdocao(id);
    }

    // ==================== MÉTODOS AUXILIARES ====================

    private static String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    private static int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Informe um número válido!");
            }
        }
    }

    private static TipoSexo lerSexo() {
        while (true) {
            String sexo = lerTexto("Sexo (MACHO ou FEMEA): ").trim().toUpperCase();

            try {
                return TipoSexo.valueOf(sexo);
            } catch (IllegalArgumentException e) {
                System.out.println("Sexo inválido! Informe MACHO ou FEMEA.");
            }
        }
    }
}