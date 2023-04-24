package br.com.ifsp.projetolinguagens.model;

import jakarta.persistence.*;


@Entity
@Table(name = "livro")

public class LivroModel {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "titulo")
    private String titulo;

    @Column(name = "autor")
    private String  autor;

    @Column(name = "editora")
    private String editora;

    @Column(name = "ano_publicacao")
    private int anoPublicacao;

    @Column(name = "num_exemplares")
    private int numExemplares;

    @Column(name = "num_exemplares_disponiveis")
    private int numExemplaresDisponiveis;

    @Column(name = "disponivel")
    private boolean disponivel;

    @Column(name = "reservado")
    private boolean reservado;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public int getNumExemplares() {
        return numExemplares;
    }

    public void setNumExemplares(int numExemplares) {
        this.numExemplares = numExemplares;
    }

    public int getNumExemplaresDisponiveis() {
        return numExemplaresDisponiveis;
    }

    public void setNumExemplaresDisponiveis(int numExemplaresDisponiveis) {
        this.numExemplaresDisponiveis = numExemplaresDisponiveis;
    }


    public void emprestar(int idLivro) {
        if(this.isDisponivel() == false) { //Verifica se já não foi emprestado
            System.out.println("O livro não está disponível pois já foi emprestado");
        } else {
            this.disponivel = false;
        }
    }


    public void devolver (int idLivro) {
        if(this.isDisponivel() == false) { //verifica se já não foi devolvido
            this.disponivel = true;
        } else {
            System.out.println("Livro já foi devolvido");
        }
    }


    public boolean isDisponivel() {

        return disponivel;
    }

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
}
