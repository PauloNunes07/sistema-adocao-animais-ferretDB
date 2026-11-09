package org.service;

import org.exception.CampoInvalidoException;
import org.model.Adotante;
import org.repository.AdotanteRepository;
import org.util.validator.AdotanteValidator;
import org.util.validator.Validator;

import java.util.List;
import org.util.GeradorId;

public class AdotanteService {
    private final AdotanteRepository adotanteRepository;

    public AdotanteService() {
        this.adotanteRepository = new AdotanteRepository();
    }

    public void cadastrarAdotante(Adotante adotante) {

        Validator.validarObjeto(adotante, "adotante");

        Validator.validarApenasLetras(adotante.getNome(), "nome");

        AdotanteValidator.validarContato(adotante.getContato());
        AdotanteValidator.validarEndereco(adotante.getContato().getEndereco());


        if (adotanteRepository.buscarPorEmail(adotante.getContato().getEmail()) != null) {
            throw new CampoInvalidoException("Já existe um adotante cadastrado com esse e-mail.");
        }

        adotante.setId(GeradorId.gerar(10));

        adotanteRepository.salvar(adotante);
    }

    public Adotante buscarAdotantePorNome(String nome) {
        Validator.validarApenasLetras(nome, "nome");

        Adotante adotante = adotanteRepository.buscarPorNome(nome);
        AdotanteValidator.validarAdotanteEncontrado(adotante);

        return adotante;
    }

    public List<Adotante> listarAdotantes() {
        return adotanteRepository.listarTodos();
    }


    public Adotante buscarAdotantePorId(String id) {

        Validator.validarString(id, "ID");

        Adotante adotante = adotanteRepository.buscarPorId(id);

        AdotanteValidator.validarAdotanteEncontrado(adotante);

        return adotante;
    }

    public void removerAdotante(String id) {

        Validator.validarString(id, "ID");

        buscarAdotantePorId(id);

        adotanteRepository.remover(id);
    }




}
