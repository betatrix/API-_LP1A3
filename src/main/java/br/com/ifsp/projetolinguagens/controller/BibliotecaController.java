package br.com.ifsp.projetolinguagens.controller;

import br.com.ifsp.projetolinguagens.dto.LoginDTO;
import br.com.ifsp.projetolinguagens.exceptions.EmprestimosExceptions;
import br.com.ifsp.projetolinguagens.exceptions.LivroExceptions;
import br.com.ifsp.projetolinguagens.exceptions.UsuarioExceptions;
import br.com.ifsp.projetolinguagens.model.*;
import br.com.ifsp.projetolinguagens.services.EmprestimoService;
import br.com.ifsp.projetolinguagens.services.LivroService;
import br.com.ifsp.projetolinguagens.services.UsuariosService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaController {

    @Autowired
    private LivroService livroService;
    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private EmprestimoService emprestimoService;

    /*###################################   LIVROS  ############################################*/
    @GetMapping("/livros")
    public ResponseEntity<?> listarLivros() {
        try {
            List<Livro> livros = livroService.listarLivros();
            return new ResponseEntity<>(livros, HttpStatus.OK);
        }catch (LivroExceptions l){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(l.getMessage());
        }
    }

    //buscar livro pelo id
    @GetMapping("/livros/{id}")
    public ResponseEntity<?> buscarLivro(@PathVariable Integer id) {
        try {
            Livro livro = livroService.buscarLivro(id);
            return new ResponseEntity<>(livro, HttpStatus.OK);
        }catch (LivroExceptions l){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(l.getMessage());
        }
    }

    /*####################################  USUARIOS    ###################################################*/
    // Lista usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuarios() {
        try {
            List<Usuario> usuarios = usuariosService.listarUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u.getMessage());
        }
    }

    // buscar usuarios por nome
    @GetMapping("/usuario/{nome}")
    public ResponseEntity<?> buscarUsuario(@PathVariable String nome) {
        try {
            Usuario usuario = usuariosService.buscarUsuarioNome(nome);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
    }

    // buscar usuario por cpf
    @GetMapping("/usuario/{cpf}")
    public ResponseEntity<?> buscarUsuarioCpf(@PathVariable String cpf) {
        try {
            Usuario usuario = usuariosService.buscarUsuario(cpf);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
    }

    //Lista os clientes
    @GetMapping("/clientes")
    public ResponseEntity<?> listarCliente() {
        try {
            List<Cliente> clientes = usuariosService.listarClientes();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(u.getMessage());
        }
    }

    //Lista os funcionarios
    @GetMapping("/funcionarios")
    public ResponseEntity<?> listarFuncionario() {
        try {
            List<Funcionario> funcionarios = usuariosService.listarFuncionarios();
            return new ResponseEntity<>(funcionarios, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(u.getMessage());
        }
    }


    /*################################# EMPRESTIMOS ##########################################3*/

    // LISTA EMPRESTIMOS POR CLIENTE
    @GetMapping("/cliente/{cpfCliente}")
    public ResponseEntity<?> listarEmprestimosPorCpfCliente(@PathVariable String cpfCliente) {
        try {
            List<Emprestimo> emprestimosPorCpfCliente = EmprestimoService.listarEmprestimosPorCpfCliente(cpfCliente);
            return new ResponseEntity<>(emprestimosPorCpfCliente, HttpStatus.OK);
        } catch (EmprestimosExceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // LISTA EMPRESTIMOS POR FUNCIONARIO
    @GetMapping("/funcionario/{cpfFuncionario}")
    public ResponseEntity<?> listarEmprestimosPorCpfFuncionario(@PathVariable String cpfFuncionario) {
        try {
            List<Emprestimo> emprestimosPorCpfFuncionario = EmprestimoService.listarEmprestimosPorCpfFuncionario(cpfFuncionario);
            return new ResponseEntity<>(emprestimosPorCpfFuncionario, HttpStatus.OK);
        } catch (EmprestimosExceptions e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // LISTA EMPRESTIMOS POR DATA
    @GetMapping("/emprestimo/{data}")
    public ResponseEntity<?> getData(@PathVariable String data) {
        try {
            List<Emprestimo> emprestimos = emprestimoService.buscarPorData(LocalDate.parse(data));
            return ResponseEntity.ok(emprestimos);
        } catch (EmprestimosExceptions e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    // LISTA TODOS OS EMPRESTIMOS FEITOS
    @GetMapping("/emprestimos")
    public ResponseEntity<?> listarEmprestimos() throws ParseException {
        try {
            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimos();
            return ResponseEntity.ok(emprestimos);
        } catch (EmprestimosExceptions e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
    }

    /*########################################  LOGIN   ###################################################*/

    @PostMapping("/usuario/login")
    public ResponseEntity<?> realizarLogin(@RequestBody LoginDTO loginDTO) {
        String cpf = loginDTO.getCpf();
        String senha = loginDTO.getSenha();
        try {
            // Buscar o usuário com o CPF fornecido
            Usuario usuario = usuariosService.buscarUsuario(cpf);

            // Verificar se o usuário existe e se a senha fornecida corresponde à senha do usuário
            if (usuario != null && usuario.getSenha().equals(senha)) {
                return ResponseEntity.ok().body(usuario);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
            }
        } catch (UsuarioExceptions u) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u.getMessage());
        }
    }



}




