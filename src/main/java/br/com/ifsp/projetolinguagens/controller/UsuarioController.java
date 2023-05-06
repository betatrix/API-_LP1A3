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

import java.time.LocalDate;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private LivroService livroService;

    @Autowired
    private EmprestimoService emprestimoService;

    /*###########################################   LIVROS  ############################################*/

    //RESERVAR LIVRO
    @PostMapping("/reservarLivro")
    public ResponseEntity<?> reservarLivro(@PathVariable Integer idlivro){
        try {
            Livro livroAtualizado = livroService.reservarLivro(idlivro);
            return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
        }catch (LivroExceptions l){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(l.getMessage());
        }
    }

    //CANCELAR RESERVA DE LIVRO
    @PostMapping("/cancelarReservar")
    public ResponseEntity<?> cancelarReserva(@PathVariable Integer idLivro){
        try{
            Livro livroAtualizado = livroService.cancelarReserva(idLivro);
            return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
        }catch (LivroExceptions l){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(l.getMessage());
        }
    }

    /*                          REALIZAR EMPRESTIMO E DEVOLVER LIVRO           */

    // FAZER EMPRESTIMO DE LIVRO
    @PostMapping("/emprestimo")
    public ResponseEntity<?> RealizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            Integer idEmp = emprestimoDTO.getIdEmp();
            String cpfcliente = emprestimoDTO.getCpfCliente();
            String cpffunc = emprestimoDTO.getCpfFuncionario();
            Integer idlivro = emprestimoDTO.getiDlivro();
            LocalDate dataEmprestimo = emprestimoDTO.getDataEmprestimo();
            LocalDate dataDevolucaoPrevista = emprestimoDTO.getDataDevolucaoPrevista();
            Cliente cliente = usuariosService.buscarCliente(cpfcliente);
            Funcionario funcionario = usuariosService.buscarFuncionario(cpffunc);
            Livro livro = livroService.buscarLivro(idlivro);

            Emprestimo emp = new Emprestimo(idEmp, cliente, funcionario, livro, dataEmprestimo, dataDevolucaoPrevista);

            Emprestimo novoEmprestimo = EmprestimoService.realizarEmprestimo(emp);

            return new ResponseEntity<>(novoEmprestimo, HttpStatus.CREATED);
        } catch (EmprestimosExceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // DEVOLVER LIVRO
    @PutMapping("/devolver/{idEmp}")
    public ResponseEntity<?> devolverLivro(@PathVariable Integer idEmp){
        try {
            Emprestimo emprestimoDevolvido = emprestimoService.atualizarEmprestimo(idEmp);
            return new ResponseEntity<>(emprestimoDevolvido, HttpStatus.OK);
        }catch (EmprestimosExceptions e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
