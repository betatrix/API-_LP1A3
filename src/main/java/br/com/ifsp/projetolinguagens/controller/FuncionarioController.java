package br.com.ifsp.projetolinguagens.controller;

import br.com.ifsp.projetolinguagens.dto.EmprestimoDTO;
import br.com.ifsp.projetolinguagens.exceptions.EmprestimosExceptions;
import br.com.ifsp.projetolinguagens.exceptions.LivroExceptions;
import br.com.ifsp.projetolinguagens.exceptions.UsuarioExceptions;
import br.com.ifsp.projetolinguagens.interfaces.GerenciamentoDeUsuarios;
import br.com.ifsp.projetolinguagens.model.*;
import br.com.ifsp.projetolinguagens.interfaces.GerenciamentoDeLivros;
import br.com.ifsp.projetolinguagens.services.EmprestimoService;
import br.com.ifsp.projetolinguagens.services.LivroService;
import br.com.ifsp.projetolinguagens.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/func")
public class FuncionarioController implements GerenciamentoDeLivros, GerenciamentoDeUsuarios {

    /*
     Essa classe pode realizar o CRUD nos livros e nos Clientes/Usuarios
    * */

        // Faz a injeção das dependências do serviço para que seja possível usar os métodos
        @Autowired
        private LivroService livroService;

        @Autowired
        private UsuariosService usuariosService;

        @Autowired
        private EmprestimoService emprestimoService;


    @PostMapping("/emprestimo")
    public ResponseEntity<?> RealizarEmprestimo(@RequestBody EmprestimoDTO emprestimoDTO) {
        try {
            String cpfcliente = emprestimoDTO.getCpfCliente();
            String cpffunc = emprestimoDTO.getCpfFuncionario();
            Integer idlivro = emprestimoDTO.getiDlivro();
            Date dataEmprestimo = emprestimoDTO.getDataEmprestimo();
            //Date dataDevolucao = emprestimoDTO.getDataDevolucao();
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

//    @PostMapping("/emprestimo/devolucao")
//    public ResponseEntity<?> realizarDevolucao(@RequestBody EmprestimoDTO emprestimoDTO){
//         try {
//            String cpfcliente = emprestimoDTO.getCpfCliente();
//            String cpffunc = emprestimoDTO.getCpfFuncionario();
//            Integer idlivro = emprestimoDTO.getiDlivro();
//            Date dataEmprestimo = emprestimoDTO.getDataEmprestimo();
//            Date dataDevolucaoPrevista = emprestimoDTO.getDataDevolucaoPrevista();
//            Date dataDevolucao = emprestimoDTO.getDataDevolucao();
//
//
//            Cliente cliente = usuariosService.buscarCliente(cpfcliente);
//            Funcionario funcionario = usuariosService.buscarFuncionario(cpffunc);
//            Livro livro = livroService.buscarLivro(idlivro);
//
//            Emprestimo emp = new Emprestimo(cliente, funcionario, livro, dataEmprestimo, dataDevolucaoPrevista, dataDevolucao);
//
//            Emprestimo emprestimoAtualizado = EmprestimoService.atualizarEmprestimo(emp);
//
//            return new ResponseEntity<>(emprestimoAtualizado, HttpStatus.CREATED);
//        } catch (EmprestimosExceptions e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }



    /*  MANIPULAÇÃO DOS LIVROS */

        @PostMapping("/livros")
        public ResponseEntity<?> adicionarLivro(@RequestBody Livro livro) {
            try {
                Livro livroAdicionado = livroService.adicionarLivro(livro);
                return new ResponseEntity<>(livroAdicionado, HttpStatus.CREATED);
            }catch(LivroExceptions l){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(l.getMessage());
            }
        }


    @GetMapping("/livros")
        public ResponseEntity<?> listarLivros() {
            try {
                List<Livro> livros = livroService.listarLivros();
                return new ResponseEntity<>(livros, HttpStatus.OK);
            }catch(LivroExceptions l){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(l.getMessage());
            }
        }

        @GetMapping("/livros/{id}")
        public ResponseEntity<?> buscarLivro(@PathVariable Integer id) {
            try {
                Livro livro = livroService.buscarLivro(id);
                return new ResponseEntity<>(livro, HttpStatus.OK);
            }catch(LivroExceptions l){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(l.getMessage());
            }
        }

        @DeleteMapping("/livros/{id}")
        public ResponseEntity<?> excluirLivro(@PathVariable Integer id) {
            try {
                Livro livro = livroService.buscarLivro(id);
                livroService.excluirLivro(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch(LivroExceptions l){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(l.getMessage());
            }
        }

    // Método PUT

    @PutMapping("/livros/{id}")
    public ResponseEntity<?> alterarLivro(@PathVariable Integer id, @RequestBody Livro livro) {
            try {
                Livro livroAtualizado = livroService.alterarLivro(id, livro);
                return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
            }catch(LivroExceptions l){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(l.getMessage());
            }
    }

    /*###################################   USUARIOS  ##############################333*/

//METODO POST
    @PostMapping("/clientes")
    public ResponseEntity<?> adicionarCliente(@RequestBody Cliente cliente) {
        try {
            Cliente novoCliente = usuariosService.adicionarCliente(cliente);
            return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(u.getMessage());
        }
    }

    // METODO GET
    @GetMapping("/usuarios")
    public ResponseEntity<?> listarUsuario() {
        try {
            List<Usuario> usuarios = usuariosService.listarUsuarios();
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(u.getMessage());
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity<?> listarCliente() {
        try {
            List<Cliente> clientes = usuariosService.listarClientes();
            return new ResponseEntity<>(clientes, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
    }


    // METOGO GET PELO CPF
    @GetMapping("/usuarios/{cpf}")
    public ResponseEntity<?> buscarUsuario(@PathVariable String cpf) {
        try {
            Usuario usuario = usuariosService.buscarUsuario(cpf);
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
    }

    @GetMapping("/clientes/{cpf}")
    public ResponseEntity<?> buscarCliente(String cpf) {
        try {
            Cliente cliente = usuariosService.buscarCliente(cpf);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
    }


    // METODO DELETE - UM METODO DE DELETE E DE PUT PARA TODOS OS USUARIOS
    @DeleteMapping("/usuarios/{cpf}")
    public ResponseEntity<?> excluirUsuario(@PathVariable String cpf) {
        try {
            Usuario usuario = usuariosService.buscarUsuario(cpf);
            usuariosService.excluirUsuario(cpf);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
    }

    // METODO PUT - Essa rota utiliza o método alterarUsuario da classe de serviços, que permite que
    // possa ser utilizada também para excluir funcionarios e clientes
    @PutMapping("/usuarios/{cpf}")
    public ResponseEntity<?> alterarUsuario(@PathVariable String cpf, @RequestBody Usuario usuario) {
        try {
            Usuario usuarioAtualizado = usuariosService.atualizarUsuario(cpf, usuario);
            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
        }catch (UsuarioExceptions u){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(u.getMessage());
        }
    }
}