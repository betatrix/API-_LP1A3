package br.com.ifsp.projetolinguagens.interfaces;

import br.com.ifsp.projetolinguagens.model.Cliente;
import br.com.ifsp.projetolinguagens.model.Funcionario;
import br.com.ifsp.projetolinguagens.model.Livro;
import br.com.ifsp.projetolinguagens.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GerenciamentoDeUsuarios {
    // Método Post

   //  ResponseEntity<Usuario> adicionarUsuario(@RequestBody Usuario usuario);

    ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente);


    //Método GET
    ResponseEntity<List<Usuario>> listarUsuario();

    ResponseEntity<List<Cliente>> listarCliente();


    //Método GET por id
    ResponseEntity<Usuario> buscarUsuario(@PathVariable String cpf);

    ResponseEntity<Cliente> buscarCliente(@PathVariable String cpf);


    // Método DELETE
    ResponseEntity<Void> excluirUsuario(@PathVariable String cpf);

    // Método PUT
    <T extends Usuario> ResponseEntity<T> alterarUsuario(@PathVariable String cpf, @RequestBody T usuario);

    //ResponseEntity<Usuario> alterarUsuario(@PathVariable String cpf, @RequestBody Usuario usuario);
}
