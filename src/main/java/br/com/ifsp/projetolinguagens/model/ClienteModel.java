package br.com.ifsp.projetolinguagens.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cliente")
public class ClienteModel extends UsuarioModel{

    @Override
    public void reservarLivro(LivroModel livro) {
        super.reservarLivro(livro);
    }

    @Override
    public void cancelarReserva(LivroModel livro) {
        super.cancelarReserva(livro);
    }
}
