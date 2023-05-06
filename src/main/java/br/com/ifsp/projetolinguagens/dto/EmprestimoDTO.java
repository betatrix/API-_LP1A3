package br.com.ifsp.projetolinguagens.dto;

import java.time.LocalDate;
import java.util.Date;

public class EmprestimoDTO {

    private Integer idEmp;
    private Integer iDlivro;
    private String cpfCliente;
    private String cpfFuncionario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucaoPrevista;
    private boolean devolvido = false;



    public EmprestimoDTO(Integer idEmp, Integer iDlivro, String cpfCliente, String cpfFuncionario, LocalDate dataEmprestimo, LocalDate dataDevolucaoPrevista) {
        this.idEmp = idEmp;
        this.iDlivro = iDlivro;
        this.cpfCliente = cpfCliente;
        this.cpfFuncionario = cpfFuncionario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        //this.devolvido = devolvido;
    }

//    public EmprestimoDTO(Integer iDlivro, String cpfCliente, String cpfFuncionario, Date dataEmprestimo, Date dataDevolucaoPrevista, Date dataDevolucao) {
//        this.iDlivro = iDlivro;
//        this.cpfCliente = cpfCliente;
//        this.cpfFuncionario = cpfFuncionario;
//        this.dataEmprestimo = dataEmprestimo;
//        this.dataDevolucaoPrevista = dataDevolucao;
//        this.dataDevolucao = dataDevolucao;
//    }
    public LocalDate getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public Integer getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(Integer idEmp) {
        this.idEmp = idEmp;
    }

    public void setDataDevolucaoPrevista(LocalDate dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    public Integer getiDlivro() {
        return iDlivro;
    }

    public void setiDlivro(Integer iDlivro) {
        this.iDlivro = iDlivro;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getCpfFuncionario() {
        return cpfFuncionario;
    }

    public void setCpfFuncionario(String cpfFuncionario) {
        this.cpfFuncionario = cpfFuncionario;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
}
