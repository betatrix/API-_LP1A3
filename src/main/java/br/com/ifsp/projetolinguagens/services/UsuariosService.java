package br.com.ifsp.projetolinguagens.services;

import br.com.ifsp.projetolinguagens.exceptions.LivroExceptions;
import br.com.ifsp.projetolinguagens.exceptions.UsuarioExceptions;
import br.com.ifsp.projetolinguagens.model.Administrador;
import br.com.ifsp.projetolinguagens.model.Cliente;
import br.com.ifsp.projetolinguagens.model.Funcionario;
import br.com.ifsp.projetolinguagens.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuariosService {

    static List<Usuario> usuarios = new ArrayList<>();
    static List<Cliente> clientes = new ArrayList<>();
    List<Funcionario> funcionarios = new ArrayList<>();
    List<Administrador> administradores = new ArrayList<>();


    //Cria algumas instâncias de usuarios (clientes, funcionarios e adms) quando o programa é executado

    {
        //Cria cliente, funcionario e administrador
        Cliente cliente1 = new Cliente("Raul Seixas", "19532569840", "Rua Felipe dos Santos", "raul@email", "123456");
        Funcionario funcionario1 = new Funcionario("Paula Souza", "19532579840", "Rua Felipe dos Santos", "paula@email", "230978");
        Administrador administrador1 = new Administrador("Igor de Moraes", "18732579840", "Rua Pedro Vicente", "igor@email", "23092003");

        //Adiciona cada tipo de objeto numa lista correspondente
        clientes.add(cliente1);
        funcionarios.add(funcionario1);
        administradores.add(administrador1);

        //Adiciona todos os usuarios, independente do tipo, na lista de usuarios
        usuarios.add(cliente1);
        usuarios.add(funcionario1);
        usuarios.add(administrador1);
    }

    /*  ADICIONA NOVOS OBJETOS NAS LISTAS */

    public Cliente adicionarCliente(Cliente cliente) {
        if (clientes.contains(cliente)) {
            throw new LivroExceptions("Nao foi possivel adicionar, esse cliente ja existe.");
        }
        usuarios.add(cliente);
        clientes.add(cliente);
        return cliente;
    }

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        if (funcionarios.contains(funcionario)) {
            throw new LivroExceptions("Nao foi possivel adicionar, ja existe um funcionario com esse cpf " +
                    "e com esse email cadastrados.");
        }
        usuarios.add(funcionario);
        funcionarios.add(funcionario);
        return funcionario;
    }

    /*  LISTA OS OBJETOS DAS LISTAS */
    public List<Usuario> listarUsuarios() {
        if (usuarios.size() == 0) {
            throw new UsuarioExceptions("Nao foi possivel listar, pois nao existem usuarios cadastrados.");
        }
        return usuarios;
    }


    public List<Cliente> listarClientes() {
        if (clientes.size() == 0) {
            throw new UsuarioExceptions("Nao foi possivel listar, pois nao existem clientes cadastrados.");
        }
        return clientes;
    }


    public List<Funcionario> listarFuncionarios() {
        if (funcionarios.size() == 0) {
            throw new UsuarioExceptions("Nao foi possivel listar, pois nao existem funcionarios cadastrados.");
        }
        return funcionarios;
    }


    /*  BUSCA OS OBJETOS DAS LISTAS PELO CPF  */


    public static Usuario buscarUsuario(String cpf) {
        Usuario usuario = usuarios.stream()
                .filter(l -> l.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (usuario == null) {
            throw new UsuarioExceptions("Não foi possível encontrar um usuario com o CPF fornecido.");
        }
        return usuario;
    }


    public static Cliente buscarCliente(String cpf) {
        Cliente cliente = clientes.stream()
                .filter(l -> l.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (cliente == null) {
            throw new UsuarioExceptions("Não foi possível encontrar um cliente com o CPF fornecido.");
        }
        return cliente;
    }


    public Funcionario buscarFuncionario(String cpf) {
        Funcionario funcionario = funcionarios.stream()
                .filter(l -> l.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);

        if (funcionario == null) {
            throw new UsuarioExceptions("Não foi possível encontrar um funcionario com o CPF fornecido.");
        }
        return funcionario;
    }


    // testar

    /*  MÉTODO QUE VAI SER USADO EM BIBLIOTECA PARA BUSCAR USUARIO PELO NOME */
    public Usuario buscarUsuarioNome(String nome) {
        Usuario usuario = usuarios.stream()
                .filter(l -> l.getNome().equals(nome))
                .findFirst()
                .orElse(null);
        if (usuario == null) {
            throw new UsuarioExceptions("Não foi possível encontrar um usuario com o nome fornecido.");
        }
        return usuario;
    }



    public void excluirUsuario(String cpf) {
        Usuario usuario = usuarios.stream().filter(u -> u.getCpf().equals(cpf)).findFirst().orElse(null);
        if (usuario != null) {
            usuarios.remove(usuario);
            if (usuario instanceof Cliente) {
                clientes.remove(usuario);
            } else if (usuario instanceof Funcionario) {
                funcionarios.remove(usuario);
            }
        }
        if (usuario == null) {
            throw new UsuarioExceptions("Nao existe um usuario com este CPF.");
        }
    }


    /*  METODO PARA ATUALIZAR UM USUARIO GENERICO
     *   ESSE MÉTODO ESTÁ RELACIONADO COM A MANIULAÇÃO DO JSON, FEITA NA CLASSE DO USUARIO,
     *   SEM ISSO, O MÉTODO NÃO FUNCIONA
     * */
    public <T extends Usuario> T atualizarUsuario(String cpf, T usuario) {
        Usuario usuarioAtualizado = buscarUsuario(cpf);
        if (usuarioAtualizado == null) {
            return null;
        }

        if (usuario.getCpf() == null) {
            throw new UsuarioExceptions("CPF nao pode ser nulo.");
        }

        if (usuario.getNome() == null) {
            throw new UsuarioExceptions("Nome nao pode ser nulo.");
        }

        if (usuario.getEndereco() == null) {
            throw new UsuarioExceptions("Endereço nao pode ser nulo");
        }

        if (usuario.getEmail() == null) {
            throw new UsuarioExceptions("Email nao pode ser nulo");
        }

        if (usuario.getSenha() == null) {
            throw new UsuarioExceptions("Senha nao pode ser nula.");
        }

        int index = usuarios.indexOf(usuarioAtualizado);
        if (index != -1) {
            usuarios.set(index, usuario);
            if (usuario instanceof Cliente) {
                int indexCliente = clientes.indexOf(usuarioAtualizado);
                if (indexCliente != -1) {
                    clientes.set(indexCliente, (Cliente) usuario);
                }
            } else if (usuario instanceof Funcionario) {
                int indexFuncionario = funcionarios.indexOf(usuarioAtualizado);
                if (indexFuncionario != -1) {
                    funcionarios.set(indexFuncionario, (Funcionario) usuario);
                }
            }
            return usuario;
        }
        return null;
    }
}







