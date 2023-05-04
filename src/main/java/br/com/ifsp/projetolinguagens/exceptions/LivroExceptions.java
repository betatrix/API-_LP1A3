package br.com.ifsp.projetolinguagens.exceptions;


public class LivroExceptions extends RuntimeException {

    public LivroExceptions(String message) {
        super(message);
    }

    public LivroExceptions(Integer id) {
        super("Livro não encontrado para o id " + id);
    }
}



