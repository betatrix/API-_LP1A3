package br.com.ifsp.projetolinguagens.interfaces;

import br.com.ifsp.projetolinguagens.model.Funcionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/*
*   ESSA INTERFACE É RESPONSÁVEL POR DEFINIR OS METODOS DE ADICIONAR, LISTAR E BUSCAR FUNCIONARIOS PELO CPF,
*   ESSE MÉTODOS DEVEM SER IMPLEMENTADOS EM ADMINISTRADORCONTROLLER
* */
public interface GerenciamentoDeFuncionarios {

    ResponseEntity<Funcionario> adicionarFuncionario(@RequestBody Funcionario funcionario);

    ResponseEntity<List<Funcionario>> listarFuncionario();

    ResponseEntity<Funcionario> buscarFuncionario(@PathVariable String cpf);

    /*
    ResponseEntity<Void> excluirFuncionario(@PathVariable String cpf);

    ResponseEntity<Usuario> alterarFuncionario(@PathVariable String cpf, @RequestBody Funcionario funcionario);
*/
}
