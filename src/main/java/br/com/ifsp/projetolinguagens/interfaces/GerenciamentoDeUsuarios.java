package br.com.ifsp.projetolinguagens.interfaces;

import br.com.ifsp.projetolinguagens.model.Cliente;
import br.com.ifsp.projetolinguagens.model.Usuario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface GerenciamentoDeUsuarios {
    // Método Post
    ResponseEntity<?> adicionarCliente(@RequestBody Cliente cliente);


    //Método GET
    ResponseEntity<?> listarUsuario();

    ResponseEntity<?> listarCliente();


    //Método GET por id
    ResponseEntity<?> buscarUsuario(@PathVariable String cpf);

    ResponseEntity<?> buscarCliente(@PathVariable String cpf);


    // Método DELETE
    ResponseEntity<?> excluirUsuario(@PathVariable String cpf);

    // Método PUT
    <T extends Usuario> ResponseEntity<T> alterarUsuario(@PathVariable String cpf, @RequestBody T usuario);

}
