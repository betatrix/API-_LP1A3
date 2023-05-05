package br.com.ifsp.projetolinguagens.services;

import br.com.ifsp.projetolinguagens.exceptions.EmprestimosExceptions;
import br.com.ifsp.projetolinguagens.model.Emprestimo;
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

    private static List<Emprestimo> emprestimos = new ArrayList<>();

    public static Date parseDate(String data) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false);
        return sdf.parse(data);
    }
/*
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dataEmprestimo = dateFormat.parse("2021-12-22");
        Date dataDevolucao = dateFormat.parse("2021-12-30");

        Emprestimo e1 = new Emprestimo(1, "19532569840", "19532579840", dataEmprestimo, dataDevolucao);

    }
*/
    public static Emprestimo realizarEmprestimo(Emprestimo emprestimo) throws EmprestimosExceptions{
        if(emprestimo == null){
            throw new EmprestimosExceptions("O emprestimo enviado e nulo. Nao foi possivel criar.");
        }
        emprestimos.add(emprestimo);
        return emprestimo;
    }


//    public static Emprestimo atualizarEmprestimo(Emprestimo emprestimo) throws EmprestimosExceptions {
//        if (emprestimo == null) {
//            throw new EmprestimosExceptions("O emprestimo enviado e nulo. Nao foi possivel atualizar.");
//        }
//
//        int index = emprestimos.indexOf(emprestimo);
//        if (index == -1) {
//            throw new EmprestimosExceptions("O emprestimo informado nao foi encontrado na lista.");
//        }
//
//        Emprestimo emprestimoAntigo = emprestimos.get(index);
//
//        emprestimoAntigo.atualizarDataDevolucao(emprestimo.getDataDevolucao());
//
//        return emprestimoAntigo;
//    }


    public List<Emprestimo> buscarPorData(LocalDate data){
        List<Emprestimo> emprestimosPorData = new ArrayList<>();

        for (Emprestimo e: emprestimos) {
            if (e.getDataDevolucaoPrevista().equals(data)){
                emprestimosPorData.add(e);
            }
        }

        return emprestimosPorData;
    }

    public static List<Emprestimo> listarEmprestimos() throws EmprestimosExceptions {
        if(emprestimos.isEmpty()){
            throw new EmprestimosExceptions("A lista esta vazia. Adicione emprestimos para listar");
        }
        return emprestimos;
    }

    public static List<Emprestimo> listarEmprestimosPorCpfCliente(String cpfCliente) {
        if(cpfCliente == null){
            throw new EmprestimosExceptions("O cpf enviado e nulo. Nao foi possivel listar.");
        }
        List<Emprestimo> emprestimosPorCpfCliente = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getCliente().getCpf().equals(cpfCliente)) {
                emprestimosPorCpfCliente.add(emprestimo);
            }
        }
        if(emprestimosPorCpfCliente.size() == 0){
            throw new EmprestimosExceptions("Nao existe emprestimo feito por um cliente com esse cpf.");
        }
        return emprestimosPorCpfCliente;
    }

    public static List<Emprestimo> listarEmprestimosPorCpfFuncionario(String cpfFuncionario) {
        if(cpfFuncionario == null){
            throw new EmprestimosExceptions("O cpf enviado e nulo. Nao foi possivel listar.");
        }
        List<Emprestimo> emprestimosPorCpfFuncionario = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getCpf().equals(cpfFuncionario)) {
                emprestimosPorCpfFuncionario.add(emprestimo);
            }
        }
        if(emprestimosPorCpfFuncionario.size() == 0){
            throw new EmprestimosExceptions("Nao existe emprestimo feito por um funcionario com esse cpf.");
        }
        return emprestimosPorCpfFuncionario;
    }


    // Nao está funcionando
    public ArrayList<Emprestimo> buscarPorData(String data) throws ParseException {
        ArrayList<Emprestimo> emprestimosPorData = new ArrayList<Emprestimo>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(data);
        for (Emprestimo e : emprestimos) {
            if (e.getDataEmprestimo().equals(date)) {
                emprestimosPorData.add(e);
            }
        }
        return emprestimosPorData;
    }


//    private static Date truncateTime(Date date) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(date);
//        calendar.set(Calendar.HOUR_OF_DAY, 0);
//        calendar.set(Calendar.MINUTE, 0);
//        calendar.set(Calendar.SECOND, 0);
//        calendar.set(Calendar.MILLISECOND, 0);
//        return calendar.getTime();
//    }


}
