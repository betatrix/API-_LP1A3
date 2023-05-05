package br.com.ifsp.projetolinguagens.controller;

import br.com.ifsp.projetolinguagens.dto.EmprestimoDTO;
import br.com.ifsp.projetolinguagens.exceptions.EmprestimosExceptions;
import br.com.ifsp.projetolinguagens.exceptions.LivroExceptions;
import br.com.ifsp.projetolinguagens.model.Cliente;
import br.com.ifsp.projetolinguagens.model.Emprestimo;
import br.com.ifsp.projetolinguagens.model.Funcionario;
import br.com.ifsp.projetolinguagens.model.Livro;
import br.com.ifsp.projetolinguagens.services.EmprestimoService;
import br.com.ifsp.projetolinguagens.services.LivroService;
import br.com.ifsp.projetolinguagens.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoService emprestimoService;

    //Reservar livros
    @PostMapping("/reservarLivro")
    public ResponseEntity<?> reservarLivro(@PathVariable Integer idlivro){
        try {
            Livro livroAtualizado = livroService.reservarLivro(idlivro);
            return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
        }catch (LivroExceptions l){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(l.getMessage());
        }
    }

    @PostMapping("/cancelarReservar")
    public ResponseEntity<?> cancelarReserva(@PathVariable Integer idLivro){
        try{
            Livro livroAtualizado = livroService.cancelarReserva(idLivro);
            return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
        }catch (LivroExceptions l){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(l.getMessage());
        }
    }

    @PostMapping("/emprestimo")
    public ResponseEntity<?> RealizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            String cpfcliente = emprestimoDTO.getCpfCliente();
            String cpffunc = emprestimoDTO.getCpfFuncionario();
            Integer idlivro = emprestimoDTO.getiDlivro();
            Date dataEmprestimo = emprestimoDTO.getDataEmprestimo();
            Date dataDevolucao = emprestimoDTO.getDataDevolucao();
            Date dataDevolucaoPrevista = emprestimoDTO.getDataDevolucaoPrevista();

            Cliente cliente = usuariosService.buscarCliente(cpfcliente);
            Funcionario funcionario = usuariosService.buscarFuncionario(cpffunc);
            Livro livro = livroService.buscarLivro(idlivro);

            Emprestimo emp = new Emprestimo(cliente, funcionario, livro, dataEmprestimo, dataDevolucaoPrevista);

            Emprestimo novoEmprestimo = EmprestimoService.realizarEmprestimo(emp);

            return new ResponseEntity<>(novoEmprestimo, HttpStatus.CREATED);
        } catch (EmprestimosExceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }





}
