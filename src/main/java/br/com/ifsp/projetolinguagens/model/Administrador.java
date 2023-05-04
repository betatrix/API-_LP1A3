package br.com.ifsp.projetolinguagens.model;

import br.com.ifsp.projetolinguagens.controller.AdministradorController;

public class Administrador extends Usuario{

    public Administrador(String nome, String cpf, String endereco, String email, String senha) {
        super(nome, cpf, endereco, email, senha);
    }

    public void cadastrarFuncionario(Funcionario funcionario){
        //cadastra um novo funcionario
    }

    public void removeFuncionario(Funcionario funcionario){
        //remove um funcionario
    }

    public void buscarfuncionario(String cpf){
        //faz a busca do funcionario pelo cpf
    }

}
