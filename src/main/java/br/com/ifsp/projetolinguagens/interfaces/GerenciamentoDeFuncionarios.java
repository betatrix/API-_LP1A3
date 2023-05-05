package br.com.ifsp.projetolinguagens.interfaces;

import br.com.ifsp.projetolinguagens.model.Funcionario;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/*
*   ESSA INTERFACE É RESPONSÁVEL POR DEFINIR OS METODOS DE ADICIONAR, LISTAR E BUSCAR FUNCIONARIOS PELO CPF,
*   ESSE MÉTODOS DEVEM SER IMPLEMENTADOS EM ADMINISTRADORCONTROLLER
* */
public interface GerenciamentoDeFuncionarios {

    ResponseEntity<?> adicionarFuncionario(@RequestBody Funcionario funcionario);

    ResponseEntity<?> listarFuncionario();

    ResponseEntity<?> buscarFuncionario(@PathVariable String cpf);

}
