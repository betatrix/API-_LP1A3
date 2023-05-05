package br.com.ifsp.projetolinguagens.model;

import java.util.Date;

public class Emprestimo {
    private Cliente cliente;
    private Usuario usuario;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucao;

    public Emprestimo(Cliente cliente, Usuario usuario, Livro livro, Date dataEmprestimo, Date dataDevolucao) {
        this.cliente = cliente;
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucao;
    }

//    public Emprestimo(Cliente cliente, Usuario usuario, Livro livro, Date dataEmprestimo, Date dataDevolucaoPrevista, Date dataDevolucao) {
//        this.cliente = cliente;
//        this.usuario = usuario;
//        this.livro = livro;
//        this.dataEmprestimo = dataEmprestimo;
//        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
//        this.dataDevolucao = dataDevolucao;
//    }

    public void atualizarDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }

    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
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
