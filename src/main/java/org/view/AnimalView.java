package org.view;

import org.controller.AnimalController;
import org.model.Animal;
import org.util.enums.TipoSexo;

import java.util.List;

public class AnimalView {

    private final ConsoleInput input;
    private final AnimalController animalController = new AnimalController();

    public AnimalView(ConsoleInput input) {
        this.input = input;
    }

    public void exibirMenu() {
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

            opcao = input.lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    cadastrarAnimal();
                    break;
                case 2:
                    listarAnimais();
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

    private void cadastrarAnimal() {
        System.out.println("\n--- CADASTRAR ANIMAL ---");

        String nome = input.lerTexto("Nome: ");
        String raca = input.lerTexto("Raça: ");
        int idade = input.lerInteiro("Idade: ");
        TipoSexo sexo = input.lerSexo();
        String descricaoSaude = input.lerTexto("Descrição de saúde: ");
        String porte = input.lerTexto("Porte (PEQUENO, MEDIO ou GRANDE): ");

        Animal animal = new Animal(
                nome, raca, idade, sexo, descricaoSaude, porte, null
        );

        animalController.cadastrarAnimal(animal);
    }

    private void listarAnimais() {
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
    }

    private void buscarAnimalPorId() {
        String id = input.lerTexto("ID do animal: ");

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

    private void buscarAnimalPorNome() {
        String nome = input.lerTexto("Nome do animal: ");

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

    private void atualizarAnimal() {
        String id = input.lerTexto("ID do animal que deseja atualizar: ");

        Animal animalExistente = animalController.buscarAnimalPorId(id);

        if (animalExistente == null) {
            return;
        }

        System.out.println("\n--- NOVOS DADOS DO ANIMAL ---");

        String nome = input.lerTexto("Nome: ");
        String raca = input.lerTexto("Raça: ");
        int idade = input.lerInteiro("Idade: ");
        TipoSexo sexo = input.lerSexo();
        String descricaoSaude = input.lerTexto("Descrição de saúde: ");
        String porte = input.lerTexto("Porte: ");

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

    private void removerAnimal() {
        String id = input.lerTexto("ID do animal que deseja remover: ");
        animalController.removerAnimal(id);
    }
}