package org.controller;

import org.exception.GlobalExceptionHandler;
import org.model.Adocao;
import org.service.AdocaoService;

import java.util.List;

public class AdocaoController {

    private final AdocaoService adocaoService;

    public AdocaoController() {
        this.adocaoService = new AdocaoService();
    }

    public void cadastrarAdocao(Adocao adocao) {
        try {
            adocaoService.cadastrarAdocao(adocao);
            System.out.println("Adoção cadastrada com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }

    public List<Adocao> listarAdocoes() {
        try {
            return adocaoService.listarAdocoes();
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return List.of();
        }
    }

    public List<Adocao> listarPorAnimal(String animalId) {
        try {
            return adocaoService.listarPorAnimal(animalId);
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return List.of();
        }
    }

    public Adocao buscarAdocaoPorId(String id) {
        try {
            return adocaoService.buscarAdocaoPorId(id);
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return null;
        }
    }

    public List<Adocao> listarPorAdotante(String adotanteId) {
        try {
            return adocaoService.listarPorAdotante(adotanteId);
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return List.of();
        }
    }

    public void aprovarAdocao(String id) {
        try {
            adocaoService.aprovarAdocao(id);
            System.out.println("Adoção aprovada com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }

    public void cancelarAdocao(String id) {
        try {
            adocaoService.cancelarAdocao(id);
            System.out.println("Adoção cancelada com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }

    public void removerAdocao(String id) {
        try {
            adocaoService.removerAdocao(id);
            System.out.println("Adoção removida com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }
}