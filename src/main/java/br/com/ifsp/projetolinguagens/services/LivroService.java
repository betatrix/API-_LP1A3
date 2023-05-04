package br.com.ifsp.projetolinguagens.services;

import br.com.ifsp.projetolinguagens.exceptions.LivroExceptions;
import br.com.ifsp.projetolinguagens.model.Livro;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LivroService {

    private List<Livro> livros = new ArrayList<>();

    //Cria algumas instâncias de livros quando o programa é executado para facilitar a visualizacao

    {
        Livro livro1 = new Livro(1, "O Senhor dos Anéis", "J.R.R. Tolkien", "tralala", 1990, 20, 20, false);
        Livro livro2 = new Livro(2, "Harry Potter e a Pedra Filosofal", "J.K. Rowling", "tralalaaaa", 1997, 30, 10, true);
        livros.add(livro1);
        livros.add(livro2);
    }


    public Livro adicionarLivro(Livro livro) {
        if (livros.contains(livro)) {
            throw new LivroExceptions("Nao foi possivel adicionar, esse livro ja existe.");
        }
        livros.add(livro);
        return livro;
    }


    public List<Livro> listarLivros() {
        if (livros.size() == 0) {
            throw new LivroExceptions("Nao foi possivel listar, pois nao existem livros cadastrados.");
        }
        return livros;
    }


    public Livro buscarLivro(Integer id){
        Livro livro = livros.stream()
                .filter(l -> l.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (livro == null) {
            throw new LivroExceptions(id);
            //throw new LivroExceptions("Não foi possível encontrar um livro com o ID fornecido.");
        }
        return livro;
    }
/* O método recebe o id do livro que estamos buscando, depois disso é criada uma instância de livro ao
    qual será atribuído o livro correspondente. O método stream() do objeto livros para criar um fluxo
    de objetos Livro e o método filter() restringe o fluxo apenas aos objetos Livro que têm o mesmo ID
    que o valor do parâmetro id.
    O método findFirst() é usado para retornar o primeiro objeto Livro que atende aos critérios definidos
    pelo filter(). Caso nenhum objeto seja encontrado, o método findFirst() retorna um objeto Optional vazio.
    O método orElse(null) é usado para retornar o objeto Livro encontrado pelo findFirst(), ou null se nenhum
    objeto for encontrado.

    Se o valor de livro for null, o método lança uma exceção LivroExceptions com uma mensagem de erro indicando
    que nenhum livro com o ID fornecido foi encontrado. Caso contrário, o método retorna o objeto Livro encontrado.
* */

    public void excluirLivro(Integer id) {
        boolean livroRemovido = livros.removeIf(l -> l.getId().equals(id));
            if (!livroRemovido) {
                throw new LivroExceptions(id);
            }
    }
    // verificar

    public Livro alterarLivro(Integer id, Livro livro) {
        Livro livroAtualizado = buscarLivro(id);
        if (livroAtualizado == null) {
            return null;
        }

        if (livro.getTitulo() == null) {
            throw new LivroExceptions("Título não pode ser nulo");
        }

        if (livro.getAutor() == null) {
            throw new LivroExceptions("Autor não pode ser nulo");
        }

        if (livro.getEditora() == null) {
            throw new LivroExceptions("Editora não pode ser nula");
        }

        if (livro.getAnoPublicacao() == 0) {
            throw new LivroExceptions("Ano de publicação não pode ser nulo");
        }

        if (livro.getNumExemplares() == 0) {
            throw new LivroExceptions("Número de exemplares não pode ser nulo");
        }

        if (livro.getNumExemplaresDisponiveis() == 0) {
            throw new LivroExceptions("Número de exemplares disponíveis não pode ser nulo");
        }

        livroAtualizado.setTitulo(livro.getTitulo());
        livroAtualizado.setAutor(livro.getAutor());
        livroAtualizado.setEditora(livro.getEditora());
        livroAtualizado.setAnoPublicacao(livro.getAnoPublicacao());
        livroAtualizado.setNumExemplares(livro.getNumExemplares());
        livroAtualizado.setNumExemplaresDisponiveis(livro.getNumExemplaresDisponiveis());
        livroAtualizado.setReservado(livro.isReservado());

        return livroAtualizado;
    }
}

