package org.exception;

public class GlobalExceptionHandler {
    public static void tratar(Exception e) {
        System.out.println("\nErro: " + e.getMessage());
    }
}
