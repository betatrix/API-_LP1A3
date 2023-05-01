package br.com.ifsp.projetolinguagens.model;

public class Funcionario extends Usuario{
    public Funcionario(String nome, String cpf, String endereco, String email, String senha) {
        super(nome, cpf, endereco, email, senha);
    }

    public void realizarEmprestimo(Emprestimo emprestimo){
        //realiza um novo emprestimo
    }

    public void realizarDevolucao(Emprestimo emprestimo){
        //realiza uma devolucao
    }
}
