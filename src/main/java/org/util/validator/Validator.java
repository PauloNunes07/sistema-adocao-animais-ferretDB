package org.util.validator;

import org.exception.CampoInvalidoException;
import org.exception.ObjetoNuloException;

public class Validator {

    protected Validator() {}

    public static void validarObjeto(Object objeto, String nomeObjeto) {
        if(objeto == null) {
            throw new ObjetoNuloException("O " + nomeObjeto + " não pode ser nulo.");
        }
    }

    public static void validarString(String texto, String campo) {
        if (texto == null) {
            throw new CampoInvalidoException("O campo " + campo + " é obrigatório.");
        }
        if (texto.isBlank()) {
            throw new CampoInvalidoException("O campo " + campo + " não pode estar vazio ou conter apenas espaços.");
        }
    }

    public static void validarApenasLetras(String texto, String campo) {
        validarString(texto, campo);

        if (!texto.matches("^[\\p{L}]+(?:\\s+[\\p{L}]+)*$")) {
            throw new CampoInvalidoException("O campo " + campo + " deve conter apenas letras");
        }

        if (texto.length() < 2) {
            throw new CampoInvalidoException("O campo " + campo + " deve possuir pelo menos 2 caracteres");
        }
    }


}
