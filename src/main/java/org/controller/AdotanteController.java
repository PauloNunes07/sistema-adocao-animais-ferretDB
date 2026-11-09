package org.controller;

import org.exception.GlobalExceptionHandler;
import org.model.Adotante;
import org.service.AdotanteService;

import java.util.List;

public class AdotanteController {

    private final AdotanteService adotanteService;

    public AdotanteController() {
        this.adotanteService = new AdotanteService();
    }

    public void cadastrarAdotante(Adotante adotante) {
        try {
            adotanteService.cadastrarAdotante(adotante);
            System.out.println("Adotante cadastrado com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }

    public List<Adotante> listarAdotantes() {
        try {
            return adotanteService.listarAdotantes();
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return List.of();
        }
    }

    public Adotante buscarAdotantePorId(String id) {
        try {
            return adotanteService.buscarAdotantePorId(id);
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return null;
        }
    }

    public Adotante buscarAdotantePorNome(String nome) {
        try {
            return adotanteService.buscarAdotantePorNome(nome);
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
            return null;
        }
    }

    public void atualizarAdotante(String id, Adotante adotante) {
        try {
            adotanteService.atualizarAdotante(id, adotante);
            System.out.println("Adotante atualizado com sucesso!");

        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }

    public void removerAdotante(String id) {
        try {
            adotanteService.removerAdotante(id);
            System.out.println("Adotante removido com sucesso!");
        } catch (Exception e) {
            GlobalExceptionHandler.tratar(e);
        }
    }
}
