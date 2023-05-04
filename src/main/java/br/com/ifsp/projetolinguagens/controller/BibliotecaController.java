package br.com.ifsp.projetolinguagens.controller;

import br.com.ifsp.projetolinguagens.dto.LoginDTO;
import br.com.ifsp.projetolinguagens.model.*;
import br.com.ifsp.projetolinguagens.services.EmprestimoService;
import br.com.ifsp.projetolinguagens.services.LivroService;
import br.com.ifsp.projetolinguagens.services.UsuariosService;
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

    // Listar livros
    @GetMapping("/livros")
    public ResponseEntity<List<Livro>> listarLivros() {
        List<Livro> livros = livroService.listarLivros();
        return new ResponseEntity<>(livros, HttpStatus.OK);
    }

    //buscar pelo id
    @GetMapping("/livros/{id}")
    public ResponseEntity<Livro> buscarLivro(@PathVariable Integer id) {
        Livro livro = livroService.buscarLivro(id);
        if (livro == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(livro, HttpStatus.OK);
    }

    // Lista usuarios
    @GetMapping("/usuarios")
    public ResponseEntity<List<Usuario>> listarUsuario() {
        List<Usuario> usuarios = usuariosService.listarUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    // buscar usuarios por nome
    @GetMapping("/usuarios/{nome}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String nome) {
        Usuario usuario = usuariosService.buscarUsuarioNome(nome);
        if(usuario == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    //Lista os clientes
    @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarCliente() {
        List<Cliente> clientes = usuariosService.listarClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    //Lista os funcionarios
    @GetMapping("/funcionarios")
    public ResponseEntity<List<Funcionario>> listarFuncionario() {
        List<Funcionario> funcionarios = usuariosService.listarFuncionarios();
        return new ResponseEntity<>(funcionarios, HttpStatus.OK);
    }



    @GetMapping("cliente/{cpfCliente}")
    public ResponseEntity<List<Emprestimo>> listarEmprestimosPorCpfCliente(@PathVariable String cpfCliente) {
        List<Emprestimo> emprestimosPorCpfCliente = EmprestimoService.listarEmprestimosPorCpfCliente(cpfCliente);
        return new ResponseEntity<>(emprestimosPorCpfCliente, HttpStatus.OK);
    }

    @GetMapping("funcionario/{cpfFuncionario}")
    public ResponseEntity<List<Emprestimo>> listarEmprestimosPorCpfFuncionario(@PathVariable String cpfFuncionario) {
        List<Emprestimo> emprestimosPorCpfFuncionario = EmprestimoService.listarEmprestimosPorCpfFuncionario(cpfFuncionario);
        return new ResponseEntity<>(emprestimosPorCpfFuncionario, HttpStatus.OK);
    }

//    @GetMapping("/data/{dataEmprestimo}")
//    public ResponseEntity<List<Emprestimo>> listarEmprestimosPorData(@PathVariable Date data) {
//        List<Emprestimo> emprestimosPorData = EmprestimoService.listarEmprestimosPorData(data);
//        return new ResponseEntity<>(emprestimosPorData, HttpStatus.OK);
//    }

//    @GetMapping("/data/{dataEmprestimo}")
//    public ResponseEntity<List<Emprestimo>> listarEmprestimosPorData(@PathVariable String dataEmprestimo) {
//        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date data = dateFormat.parse(dataEmprestimo);
//            List<Emprestimo> emprestimosPorData = EmprestimoService.listarEmprestimosPorData(data);
//            return new ResponseEntity<>(emprestimosPorData, HttpStatus.OK);
//        } catch (java.text.ParseException e) {
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }

//    @GetMapping("dataEmprestimo/{dataEmprestimo}")
//    public ResponseEntity<List<Emprestimo>> listarEmprestimosPorDataEmprestimo(@PathVariable Date dataEmprestimo) {
//        List<Emprestimo> emprestimosPorDataEmprestimo = EmprestimoService.listarEmprestimosPorDataEmprestimo(dataEmprestimo);
//        return new ResponseEntity<>(emprestimosPorDataEmprestimo, HttpStatus.OK);
//    }

    //Precisa arrumar
//    @GetMapping("dataEmprestimo/{dataEmprestimo}")
//    public ResponseEntity<List<Emprestimo>> buscarEmprestimoPorData(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date dataEmprestimo) {
//        List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorDataEmprestimo(dataEmprestimo);
//        return ResponseEntity.ok().body(emprestimos);
//    }

//    @GetMapping("/dataEmprestimo")
//    public ResponseEntity<List<Emprestimo>> buscarEmprestimoPorData(@RequestBody String dataEmprestimo) {
//        try {
//            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dataEmprestimo);
//            List<Emprestimo> emprestimos = emprestimoService.listarEmprestimosPorDataEmprestimo(date);
//            return ResponseEntity.ok().body(emprestimos);
//        } catch (java.text.ParseException e) {
//            throw new RuntimeException(e);
//        }
//    }

    @GetMapping("/emprestimo/{data}")
    public ResponseEntity<List<Emprestimo>> buscarPorData(@PathVariable String data) throws ParseException {
        List<Emprestimo> emprestimos = emprestimoService.buscarPorData(data);
        return ResponseEntity.ok(emprestimos);
    }

    @PostMapping("/usuario/login")
    public ResponseEntity<Usuario> realizarLogin(@RequestBody LoginDTO loginDTO) {
        String cpf = loginDTO.getCpf();
        String senha = loginDTO.getSenha();

        // Buscar o usuário com o CPF fornecido
        Usuario usuario = usuariosService.buscarUsuario(cpf);

        // Verificar se o usuário existe e se a senha fornecida corresponde à senha do usuário
        if (usuario != null && usuario.getSenha().equals(senha)) {
            return ResponseEntity.ok().body(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}






















    //@GetMapping("/login/usuario")




