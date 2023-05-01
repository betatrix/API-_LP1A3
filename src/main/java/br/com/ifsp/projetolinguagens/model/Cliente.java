

package br.com.ifsp.projetolinguagens.model;

public class Cliente extends Usuario {

    public Cliente(String nome, String cpf, String endereco, String email, String senha) {
        super(nome, cpf, endereco, email, senha);
    }

    @Override
    public void reservarLivro(Livro livro) {
        super.reservarLivro(livro);
    }

    @Override
    public void cancelarReserva(Livro livro) {
        super.cancelarReserva(livro);
    }
}
