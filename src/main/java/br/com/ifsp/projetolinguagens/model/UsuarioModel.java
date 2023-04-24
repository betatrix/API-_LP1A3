package br.com.ifsp.projetolinguagens.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class UsuarioModel {

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    /*
    public UsuarioModel(String nome, String cpf, String endereco, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

     */
    public void emprestarLivro(LivroModel livro){
        livro.emprestar(livro.getId());
        //empresta um exemplar do livro
    }

    public void devolverLivro(LivroModel livro){
        //devolve um exemplar do livro
        livro.devolver(livro.getId());
    }

    public void reservarLivro(LivroModel livro){
        livro.setReservado(true);
        //reserva um livro
    }

    public void cancelarReserva(LivroModel livro){
        livro.setReservado(false);
        //cancela a reserva do livro
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
