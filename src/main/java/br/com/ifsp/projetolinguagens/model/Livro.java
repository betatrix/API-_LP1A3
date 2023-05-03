package br.com.ifsp.projetolinguagens.model;

import java.util.Objects;

public class Livro {

    private Integer id;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private int numExemplares;
    private int numExemplaresDisponiveis;
    private boolean reservado = false;


    public Livro(Integer id) {
        this.id = id;
    }
    public Livro(Integer id, String titulo, String autor, String editora, int anoPublicacao, int numExemplares, int numExemplaresDisponiveis, boolean reservado) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.numExemplares = numExemplares;
        this.numExemplaresDisponiveis = numExemplaresDisponiveis;
        this.reservado = reservado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livro livro = (Livro) o;
        return anoPublicacao == livro.anoPublicacao && numExemplares == livro.numExemplares && numExemplaresDisponiveis == livro.numExemplaresDisponiveis && Objects.equals(id, livro.id) && Objects.equals(titulo, livro.titulo) && Objects.equals(autor, livro.autor) && Objects.equals(editora, livro.editora);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, autor, editora, anoPublicacao, numExemplares, numExemplaresDisponiveis);
    }

    // método responsável por decrementar o numero de exemplares
    // disponiveis, em caso de emprestimo ou reserva de livros
    public void decrementarExemplaresDisponiveis(){
        this.numExemplaresDisponiveis--;
    }

    // método responsável por incrementar o numero de exemplares
    // disponiveis, em caso de devolucao ou cancelamento de reserva de livros
    public void incrementarExemplaresDisponiveis(){
        this.numExemplaresDisponiveis++;
    }

    public void emprestar(){
        //emprestimo de livros
        decrementarExemplaresDisponiveis();
        //Podemos criar uma verificação para caso o numero de exemplares
        // disponiveis seja 0, envie uma exceção
    }

    public void devolver(){
        //devolucao de livros
        incrementarExemplaresDisponiveis();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public boolean isReservado() {
        return reservado;
    }

    public void setReservado(boolean reservado) {
        this.reservado = reservado;
    }
}
