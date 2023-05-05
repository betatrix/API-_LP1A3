package br.com.ifsp.projetolinguagens.model;

import br.com.ifsp.projetolinguagens.services.LivroService;

import java.util.ArrayList;

public class Biblioteca {

    private ArrayList<Livro> livros = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private ArrayList<Emprestimo> emprestimos = new ArrayList<>();

}
