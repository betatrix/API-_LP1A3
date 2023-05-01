package br.com.ifsp.projetolinguagens.controller;

import br.com.ifsp.projetolinguagens.interfaces.GerenciamentoDeUsuarios;
import br.com.ifsp.projetolinguagens.model.Cliente;
import br.com.ifsp.projetolinguagens.model.Livro;
import br.com.ifsp.projetolinguagens.interfaces.GerenciamentoDeLivros;
import br.com.ifsp.projetolinguagens.model.Usuario;
import br.com.ifsp.projetolinguagens.services.LivroService;
import br.com.ifsp.projetolinguagens.services.UsuariosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        @PostMapping("/livros")
        public ResponseEntity<Livro> adicionarLivro(@RequestBody Livro livro) {
            Livro livroAdicionado = livroService.adicionarLivro(livro);
            return new ResponseEntity<>(livroAdicionado, HttpStatus.CREATED);
        }

        @GetMapping("/livros")
        public ResponseEntity<List<Livro>> listarLivros() {
            List<Livro> livros = livroService.listarLivros();
            return new ResponseEntity<>(livros, HttpStatus.OK);
        }

        @GetMapping("/livros/{id}")
        public ResponseEntity<Livro> buscarLivro(@PathVariable Integer id) {
            Livro livro = livroService.buscarLivro(id);
            if (livro == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(livro, HttpStatus.OK);
        }


        @DeleteMapping("/livros/{id}")
        public ResponseEntity<Void> excluirLivro(@PathVariable Integer id) {
            Livro livro = livroService.buscarLivro(id);
            if (livro == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            livroService.excluirLivro(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    // Método PUT

    @PutMapping("/livros/{id}")
    public ResponseEntity<Livro> alterarLivro(@PathVariable Integer id, @RequestBody Livro livro) {
        Livro livroAtualizado = livroService.alterarLivro(id, livro);
        if (livroAtualizado == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livroAtualizado, HttpStatus.OK);
    }

    /*###################################   USUARIOS  ##############################333*/

//METODO POST
    @PostMapping("/clientes")
    public ResponseEntity<Cliente> adicionarCliente(@RequestBody Cliente cliente) {
        Cliente novoCliente = usuariosService.adicionarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
    }


    // METODO GET
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> usuarios = usuariosService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarCliente() {
        List<Cliente> clientes = usuariosService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }




    // METOGO GET PELO CPF
    @GetMapping("/usuarios/{cpf}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String cpf) {
        Usuario usuario = usuariosService.buscarUsuario(cpf);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @GetMapping("/clientes/{cpf}")
    public ResponseEntity<Cliente> buscarCliente(String cpf) {
        Cliente cliente = usuariosService.buscarCliente(cpf);
        if(cliente == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }


    // METODO DELETE - UM METODO DE DELETE E DE PUT PARA TODOS OS USUARIOS
    @DeleteMapping("/usuarios/{cpf}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable String cpf) {
        Usuario usuario = usuariosService.buscarUsuario(cpf);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        usuariosService.excluirUsuario(cpf);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // METODO PUT - Essa rota utiliza o método alterarUsuario da classe de serviços, que permite que
    // possa ser utilizada também para excluir funcionarios e clientes
    @PutMapping("/usuarios/{cpf}")
    public ResponseEntity<Usuario> alterarUsuario(@PathVariable String cpf, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = usuariosService.atualizarUsuario(cpf, usuario);
        if(usuarioAtualizado == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
    }
}