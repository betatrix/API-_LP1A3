package br.com.ifsp.projetolinguagens.interfaces;

import br.com.ifsp.projetolinguagens.model.Livro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface GerenciamentoDeLivros {

    // Método Post
    ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro);

    //Método GET
    ResponseEntity<List<Livro>> listarLivros();

    //Método GET por id
    ResponseEntity<Livro> buscarLivro(@PathVariable Integer id);

    // Método DELETE
    ResponseEntity<Void> excluirLivro(@PathVariable Integer id);

    // Método PUT
    ResponseEntity<Livro> alterarLivro(@PathVariable Integer id, @RequestBody Livro livro);
}
