package br.com.ifsp.projetolinguagens.services;

import br.com.ifsp.projetolinguagens.exceptions.EmprestimosExceptions;
import br.com.ifsp.projetolinguagens.exceptions.UsuarioExceptions;
import br.com.ifsp.projetolinguagens.model.Cliente;
import br.com.ifsp.projetolinguagens.model.Emprestimo;
import br.com.ifsp.projetolinguagens.model.Funcionario;
import br.com.ifsp.projetolinguagens.model.Livro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Date;


@Service
public class EmprestimoService {

    @Autowired
    private LivroService livroService;

    private static List<Emprestimo> emprestimos = new ArrayList<>();


    public static Emprestimo realizarEmprestimo(Emprestimo emprestimo) throws EmprestimosExceptions {
        if (emprestimo == null) {
            throw new EmprestimosExceptions("O emprestimo enviado e nulo. Nao foi possivel criar.");
        }
        if(emprestimos.contains(emprestimo)){
            throw new EmprestimosExceptions("Ja existe um emprestimo com esse ID.");
        }
        emprestimos.add(emprestimo);
        return emprestimo;
    }


    public static List<Emprestimo> listarEmprestimos() throws EmprestimosExceptions {
        if (emprestimos.isEmpty()) {
            throw new EmprestimosExceptions("A lista esta vazia. Adicione emprestimos para listar");
        }
        return emprestimos;
    }


    public static List<Emprestimo> listarEmprestimosPorCpfCliente(String cpfCliente) {
        if (cpfCliente == null) {
            throw new EmprestimosExceptions("O cpf enviado e nulo. Nao foi possivel listar.");
        }
        List<Emprestimo> emprestimosPorCpfCliente = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCliente().getCpf().equals(cpfCliente)) {
                emprestimosPorCpfCliente.add(emprestimo);
            }
        }
        if (emprestimosPorCpfCliente.size() == 0) {
            throw new EmprestimosExceptions("Nao existe emprestimo feito por um cliente com esse cpf.");
        }
        return emprestimosPorCpfCliente;
    }

    public static List<Emprestimo> listarEmprestimosPorCpfFuncionario(String cpfFuncionario) {
        if (cpfFuncionario == null) {
            throw new EmprestimosExceptions("O cpf enviado e nulo. Nao foi possivel listar.");
        }
        List<Emprestimo> emprestimosPorCpfFuncionario = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getCpf().equals(cpfFuncionario)) {
                emprestimosPorCpfFuncionario.add(emprestimo);
            }
        }
        if (emprestimosPorCpfFuncionario.size() == 0) {
            throw new EmprestimosExceptions("Nao existe emprestimo feito por um funcionario com esse cpf.");
        }
        return emprestimosPorCpfFuncionario;
    }


    public ArrayList<Emprestimo> buscarPorData(LocalDate data){
        ArrayList<Emprestimo> emprestimosPorData = new ArrayList<>();

        for (Emprestimo e: emprestimos) {
            if (e.getDataEmprestimo().isEqual(data)){
                emprestimosPorData.add(e);
            }
        }

        if(emprestimosPorData.size() == 0){
            throw new EmprestimosExceptions("Nao existe um emprestimo feito na data fornecida.");
        }

        return emprestimosPorData;
    }


    public Emprestimo buscarPorId(Integer idEmp) {
        Emprestimo emprestimo = emprestimos.stream()
                .filter(l -> l.getIdEmp().equals(idEmp))
                .findFirst()
                .orElse(null);

        return emprestimo;
    }


    public Emprestimo atualizarEmprestimo(Integer idEmp) {

        Emprestimo emprestimoAtualizado = buscarPorId(idEmp);
        if(emprestimoAtualizado == null){
            throw new EmprestimosExceptions("Nao existe um emprestimo com esse ID");
        }

        Livro livro = livroService.devolverLivro(emprestimoAtualizado.getLivro().getId());

        emprestimoAtualizado.setDevolvido(true);
        int index = emprestimos.indexOf(emprestimoAtualizado);
        if (index != -1) {
            emprestimos.set(index, emprestimoAtualizado);
        }
        return emprestimoAtualizado;
    }






}
