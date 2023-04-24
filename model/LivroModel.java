package br.com.projeto1LP.projeto.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
@Table(name="Livro")
public class LivroModel {

	@Id
	@GeneratedValue
	private int id;
	
	@column("titulo")
	private String titulo;
	
	@column("autor")
	private String  autor;
	
	@column("editora")
	private String editora;
	
	@column("ano_publicacao")
	private int anoPublicacao;
	
	@column("num_exemplares")
	private int numExemplares;
	
	@column("num_exemplares_disponiveis")
	private int numExemplaresDisponiveis;
	
	@column("disponivel")
	private boolean disponivel;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getAutor() {
		return autor;
	}
	
	public void setAutor(String autor) {
		this.autor = autor;
	}
	
	public String getEditora() {
		return editora;
	}
	
	public void setEditora(String editora) {
		this.editora = editora;
	}
	
	public int getAnoPublicacao() {
		return anoPublicacao;
	}
	
	public void setAnoPublicacao(int anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	
	public int getNumExemplares() {
		return numExemplares;
	}
	
	public void setNumExemplares(int numExemplares) {
		this.numExemplares = numExemplares;
	}
	
	public int getNumExemplaresDisponiveis() {
		return numExemplaresDisponiveis;
	}
	
	public void setNumExemplaresDisponiveis(int numExemplaresDisponiveis) {
		this.numExemplaresDisponiveis = numExemplaresDisponiveis;
	}
	
	
	public void emprestar(int idLivro) {
		if(this.isDisponivel() == false) { //Verifica se já não foi emprestado
			System.out.println("O livro não está disponível pois já foi emprestado");
		} else {
		this.disponivel = false;
	}
	}
	
	
	public void devolver (int idLivro) {
		if(this.isDisponivel() == false) { //verifica se já não foi devolvido
			this.disponivel = true;
	} else {
		System.out.println("Livro já foi devolvido");
	}
}
	

	public boolean isDisponivel() {
		return disponivel;
	}
}
