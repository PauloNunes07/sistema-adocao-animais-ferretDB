package org.service;

import org.repository.AdotanteRepository;

public class AdotanteService {
    private final AdotanteRepository adotanteRepository;

    public AdotanteService() {
        this.adotanteRepository = new AdotanteRepository();
    }

}
