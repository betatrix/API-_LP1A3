package br.com.ifsp.projetolinguagens.services;

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
    public static Emprestimo realizarEmprestimo(Emprestimo emprestimo) {
        emprestimos.add(emprestimo);
        return emprestimo;
    }


    public static List<Emprestimo> listarEmprestimos() {
        return emprestimos;
    }

    public static List<Emprestimo> listarEmprestimosPorCpfCliente(String cpfCliente) {
        List<Emprestimo> emprestimosPorCpfCliente = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo. getCliente().getCpf().equals(cpfCliente)) {
                emprestimosPorCpfCliente.add(emprestimo);
            }
        }
        return emprestimosPorCpfCliente;
    }

    public static List<Emprestimo> listarEmprestimosPorCpfFuncionario(String cpfFuncionario) {
        List<Emprestimo> emprestimosPorCpfFuncionario = new ArrayList<>();
        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getUsuario().getCpf().equals(cpfFuncionario)) {
                emprestimosPorCpfFuncionario.add(emprestimo);
            }
        }
        return emprestimosPorCpfFuncionario;
    }

//
//    public static List<Emprestimo> listarEmprestimosPorData(Date dataEmprestimo) {
//        List<Emprestimo> emprestimosPorData = new ArrayList<>();
//        for (Emprestimo emprestimo : emprestimos) {
//            if (emprestimo.getDataEmprestimo().compareTo(dataEmprestimo) == 0) {
//                emprestimosPorData.add(emprestimo);
//            }
//        }
//        return emprestimosPorData;
//    }
//
//    public static List<Emprestimo> listarEmprestimosPorData(Date dataEmprestimo) {
//        List<Emprestimo> emprestimosPorData = new ArrayList<>();
//        for (Emprestimo emprestimo : emprestimos) {
//            if (emprestimo.getDataEmprestimo().compareTo(dataEmprestimo) == 0) {
//                emprestimosPorData.add(emprestimo);
//            }
//        }
//        return emprestimosPorData;
//    }
//
//    public static List<Emprestimo> listarEmprestimosPorDataEmprestimo(Date dataEmprestimo) {
//        List<Emprestimo> emprestimosPorDataEmprestimo = new ArrayList<>();
//        for (Emprestimo e : emprestimos) {
//            if (e.getDataEmprestimo().equals(dataEmprestimo)) {
//                emprestimosPorDataEmprestimo.add(e);
//            }
//        }
//        return emprestimosPorDataEmprestimo;
//    }
//
//    public static List<Emprestimo> listarEmprestimosPorDataEmprestimo(Date dataEmprestimo) {
//        List<Emprestimo> emprestimosPorDataEmprestimo = new ArrayList<>();
//        for (Emprestimo e : emprestimos) {
//            Date emprestimoData = truncateTime(e.getDataEmprestimo());
//            Date filtroData = truncateTime(dataEmprestimo);
//            if (emprestimoData.equals(filtroData)) {
//                emprestimosPorDataEmprestimo.add(e);
//            }
//        }
//        return emprestimosPorDataEmprestimo;
//    }

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


    private static Date truncateTime(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }


}
