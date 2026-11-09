package org.view;

import org.util.enums.TipoSexo;

import java.util.Scanner;

public class ConsoleInput {

    private final Scanner scanner = new Scanner(System.in);

    public String lerTexto(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine();
    }

    public int lerInteiro(String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Informe um número válido!");
            }
        }
    }

    public TipoSexo lerSexo() {
        while (true) {
            String sexo = lerTexto("Sexo (MACHO ou FEMEA): ").trim().toUpperCase();

            try {
                return TipoSexo.valueOf(sexo);
            } catch (IllegalArgumentException e) {
                System.out.println("Sexo inválido! Informe MACHO ou FEMEA.");
            }
        }
    }

    public void fechar() {
        scanner.close();
    }
}