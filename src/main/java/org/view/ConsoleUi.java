package org.view;

public class ConsoleUi {

    private final ConsoleInput input = new ConsoleInput();

    private final AnimalView animalView = new AnimalView(input);
    private final AdotanteView adotanteView = new AdotanteView(input);
    private final AdocaoView adocaoView = new AdocaoView(input);

    public void iniciar() {
        int opcao;

        do {
            System.out.println("\n========== PET ADOTE ==========");
            System.out.println("1 - Animais");
            System.out.println("2 - Adotantes");
            System.out.println("3 - Adoções");
            System.out.println("0 - Sair");
            System.out.println("===============================");

            opcao = input.lerInteiro("Escolha uma opção: ");

            switch (opcao) {
                case 1:
                    animalView.exibirMenu();
                    break;
                case 2:
                    adotanteView.exibirMenu();
                    break;
                case 3:
                    adocaoView.exibirMenu();
                    break;
                case 0:
                    System.out.println("\nSistema encerrado!");
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }

        } while (opcao != 0);

        input.fechar();
    }
}