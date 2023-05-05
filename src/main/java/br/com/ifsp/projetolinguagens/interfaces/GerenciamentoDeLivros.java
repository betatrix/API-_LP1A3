package br.com.ifsp.projetolinguagens.interfaces;

import br.com.ifsp.projetolinguagens.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface GerenciamentoDeLivros {

    // Método Post
    ResponseEntity<?> adicionarLivro(@RequestBody Livro livro);

    //Método GET
    ResponseEntity<?> listarLivros();

    //Método GET por id
    ResponseEntity<?> buscarLivro(@PathVariable Integer id);

    // Método DELETE
    ResponseEntity<?> excluirLivro(@PathVariable Integer id);

    // Método PUT
    ResponseEntity<?> alterarLivro(@PathVariable Integer id, @RequestBody Livro livro);
}
