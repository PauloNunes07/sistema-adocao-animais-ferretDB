package org.util.validator;

import org.exception.CampoInvalidoException;
import org.model.Contato;

import java.util.regex.Pattern;

public class AdotanteValidator extends Validator {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9]+([._+-][A-Za-z0-9]+)*@[A-Za-z0-9]+([.-][A-Za-z0-9]+)*\\.[A-Za-z]{2,}$");

    private static final Pattern TELEFONE_PATTERN = Pattern.compile("^[1-9][1-9]9\\d{8}$");

    private static final Pattern ENDERECO_PATTERN = Pattern.compile("^[\\p{L}\\p{N} .,'\\-/ºª#]+$");

    public static void validarEndereco(String endereco) {
        validarString(endereco, "endereço");

        String limpo = endereco.trim();

        if (limpo.length() < 10 || limpo.length() > 150) {
            throw new CampoInvalidoException("O endereço deve ter entre 10 e 150 caracteres.");
        }
        if (!ENDERECO_PATTERN.matcher(limpo).matches()) {
            throw new CampoInvalidoException("O endereço contém caracteres inválidos.");
        }
    }

    public static void validarTelefone(String telefone) {
        validarString(telefone, "telefone");

        if (telefone.length() != 11) {
            throw new CampoInvalidoException("O telefone deve ter 11 dígitos (DDD + 9 + número).");
        }
        if (!TELEFONE_PATTERN.matcher(telefone).matches()) {
            throw new CampoInvalidoException("Telefone inválido: " + telefone +
                    ". Use apenas números, com DDD e começando com 9 (ex.: 83999999999).");
        }
    }

    public static void validarEmail(String email) {
        validarString(email, "e-mail");

        String emailLimpo = email.trim();

        if (emailLimpo.length() > 254) {
            throw new CampoInvalidoException("O e-mail deve ter no máximo 254 caracteres.");
        }
        if (!EMAIL_PATTERN.matcher(emailLimpo).matches()) {
            throw new CampoInvalidoException("E-mail em formato inválido: " + email);
        }
    }
    public static void validarContato(Contato contato) {
       validarObjeto(contato, "Contato");
       validarEmail(contato.getEmail());
       validarTelefone(contato.getTelefone());
    }


}
