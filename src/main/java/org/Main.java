package org;

import com.mongodb.client.model.Filters;
import org.config.ConfiguracaoFerretDB;
import org.model.Adotante;
import org.model.Contato;
import org.repository.AdotanteRepository;

public class Main {

    public static void main(String[] args) {

        AdotanteRepository repository = new AdotanteRepository();

        try {

            // 1. CADASTRAR ADOTANTE
            System.out.println("===== CADASTRO =====");

            Contato contato = new Contato();
            contato.setEmail("joao@gmail.com");
            contato.setTelefone("83999999999");
            contato.setEndereco("Rua Central");

            Adotante adotante = new Adotante();
            adotante.setId("a001");
            adotante.setNome("Joao Silva");
            adotante.setContato(contato);

            repository.salvar(adotante);

            System.out.println("Adotante cadastrado com sucesso!");

            // 2. BUSCAR POR ID
            System.out.println("\n===== BUSCA POR ID =====");

            Adotante porId = repository.buscarPorId("a001");

            if (porId != null) {
                System.out.println("Adotante encontrado: " + porId.getNome());
            } else {
                System.out.println("Adotante não encontrado!");
            }

            // 3. BUSCAR POR NOME
            System.out.println("\n===== BUSCA POR NOME =====");

            Adotante porNome = repository.buscarPorNome("Joao Silva");

            if (porNome != null) {
                System.out.println("Adotante encontrado: " + porNome.getNome());
            } else {
                System.out.println("Adotante não encontrado!");
            }

            // 4. BUSCAR POR EMAIL
            System.out.println("\n===== BUSCA POR EMAIL =====");

            Adotante porEmail = repository.buscarPorEmail("joao@gmail.com");

            if (porEmail != null) {
                System.out.println("Adotante encontrado: " + porEmail.getNome());
                System.out.println("Email: " + porEmail.getContato().getEmail());
            } else {
                System.out.println("Adotante não encontrado!");
            }

            // 5. LISTAR TODOS
            System.out.println("\n===== LISTAGEM =====");

            for (Adotante a : repository.listarTodos()) {
                System.out.println("ID: " + a.getId());
                System.out.println("Nome: " + a.getNome());
                System.out.println("Email: " + a.getContato().getEmail());
                System.out.println("--------------------");
            }

            Adotante teste = repository.buscarPorId("a001");

            System.out.println("Email armazenado: " +
                    teste.getContato().getEmail());


            // 6. ATUALIZAR ADOTANTE
            System.out.println("\n===== ATUALIZAÇÃO =====");

            Contato novoContato = new Contato();
            novoContato.setEmail("joao.silva@gmail.com");
            novoContato.setTelefone("83888888888");
            novoContato.setEndereco("Avenida Brasil");

            Adotante atualizado = new Adotante();
            atualizado.setId("a001");
            atualizado.setNome("Joao Silva Atualizado");
            atualizado.setContato(novoContato);

            repository.atualizar("a001", atualizado);

            System.out.println("Adotante atualizado!");

            // 7. VERIFICAR ATUALIZAÇÃO
            System.out.println("\n===== VERIFICAR ATUALIZAÇÃO =====");

            Adotante depoisAtualizacao = repository.buscarPorId("a001");

            if (depoisAtualizacao != null) {
                System.out.println("Nome: " + depoisAtualizacao.getNome());
                System.out.println("Email: " + depoisAtualizacao.getContato().getEmail());
                System.out.println("Telefone: " + depoisAtualizacao.getContato().getTelefone());
                System.out.println("Endereço: " + depoisAtualizacao.getContato().getEndereco());
            }

            // 8. REMOVER ADOTANTE
            System.out.println("\n===== REMOÇÃO =====");

            repository.remover("a001");

            System.out.println("Adotante removido!");

            // 9. VERIFICAR REMOÇÃOB
            System.out.println("\n===== VERIFICAR REMOÇÃO =====");

            Adotante depoisRemocao = repository.buscarPorId("a001");

            if (depoisRemocao == null) {
                System.out.println("Adotante não encontrado. Remoção confirmada!");
            } else {
                System.out.println("O adotante ainda existe!");
            }

        } finally {
            ConfiguracaoFerretDB.fecharConexao();
        }
    }
}