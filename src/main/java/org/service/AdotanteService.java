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

        Adotante adotante = adotanteRepository.buscarPorNome(nome.toLowerCase().trim());
        AdotanteValidator.validarAdotanteEncontrado(adotante);

        return adotante;
    }

    public List<Adotante> listarAdotantes() {
        return adotanteRepository.listarTodos();
    }


    public Adotante buscarAdotantePorId(String id) {

        Validator.validarId(id);
        Adotante adotante = adotanteRepository.buscarPorId(id);

        AdotanteValidator.validarAdotanteEncontrado(adotante);

        return adotante;
    }

    public void atualizarAdotante(String id, Adotante adotante) {

        Validator.validarId(id);
        Validator.validarObjeto(adotante, "adotante");

        buscarAdotantePorId(id);

        Validator.validarApenasLetras(adotante.getNome(), "nome");

        AdotanteValidator.validarContato(adotante.getContato());
        AdotanteValidator.validarEndereco(adotante.getContato().getEndereco());

        Adotante encontrado = adotanteRepository.buscarPorEmail(adotante.getContato().getEmail());

        if (encontrado != null && !encontrado.getId().equals(id)) {
            throw new CampoInvalidoException("Já existe outro adotante cadastrado com esse e-mail.");
        }

        adotante.setId(id);

        adotanteRepository.atualizar(id, adotante);
    }

    public void removerAdotante(String id) {

        Validator.validarId(id);

        buscarAdotantePorId(id);

        adotanteRepository.remover(id);
    }




}
