package br.com.ifsp.projetolinguagens.dto;

import java.util.Date;

public class EmprestimoDTO {

    private Integer iDlivro;
    private String cpfCliente;
    private String cpfFuncionario;
    private Date dataEmprestimo;
    private Date dataDevolucao;

    public EmprestimoDTO(Integer iDlivro, String cpfCliente, String cpfFuncionario, Date dataEmprestimo, Date dataDevolucao) {
        this.iDlivro = iDlivro;
        this.cpfCliente = cpfCliente;
        this.cpfFuncionario = cpfFuncionario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
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

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
