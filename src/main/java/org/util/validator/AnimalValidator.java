package org.util.validator;

import org.exception.CampoInvalidoException;
import org.model.Animal;
import org.util.enums.TipoPorte;
import org.util.enums.TipoSexo;

public class AnimalValidator extends Validator {

    public static void validarIdade(int idade){
        if(idade<0 || idade > 80){
            throw new CampoInvalidoException("A idade do animal deve estar entre 0 e 80 anos!");
        }
    }

    public static void validarSexo(String sexo) {

        Validator.validarString(sexo, "sexo");

        String sexoFormatado = sexo.trim().toUpperCase();

        if (!sexoFormatado.equals(TipoSexo.MACHO.name()) && !sexoFormatado.equals(TipoSexo.FEMEA.name())) {
            throw new CampoInvalidoException("Sexo inválido! Informe MACHO ou FEMEA.");
        }
    }

    public static void validarPorte(String porte) {
        Validator.validarString(porte, "porte");

        String porteFormatado = porte.trim().toUpperCase();

        if (!porteFormatado.equals(TipoPorte.PEQUENO.name()) && !porteFormatado.equals(TipoPorte.MEDIO.name()) && !porteFormatado.equals(TipoPorte.GRANDE.name())) {
            throw new CampoInvalidoException("Porte inválido! Informe PEQUENO, MEDIO ou GRANDE.");
        }
    }

    public static void validarAnimalEncontrado(Animal animal) {
        if (animal == null) {
            throw new RuntimeException("Animal não encontrado!");
        }
    }

}
