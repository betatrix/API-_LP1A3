package br.com.ifsp.projetolinguagens.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipo"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cliente.class, name = "cliente"),
        @JsonSubTypes.Type(value = Funcionario.class, name = "funcionario")
})

public abstract class Usuario {

    private String nome;
    private String cpf;
    private String endereco;
    private String email;
    private String senha;

    public Usuario(String nome, String cpf, String endereco, String email, String senha) {
        this.nome = nome;
        this.cpf = cpf;
        this.endereco = endereco;
        this.email = email;
        this.senha = senha;
    }

    public void emprestarLivro(Livro livro){
        livro.emprestar();
        //empresta um exemplar do livro
    }

    public void devolverLivro(Livro livro){
        //devolve um exemplar do livro
        livro.devolver();
    }

    public void reservarLivro(Livro livro){
        //reserva um livro
        livro.decrementarExemplaresDisponiveis();
        livro.setReservado(true);
        // Podemos criar uma verificação para caso o numero de exemplares
        // disponiveis seja 0, envie uma exceção,
    }

    public void cancelarReserva(Livro livro){
        //cancela a reserva do livro
        livro.incrementarExemplaresDisponiveis();
        livro.setReservado(false);
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
