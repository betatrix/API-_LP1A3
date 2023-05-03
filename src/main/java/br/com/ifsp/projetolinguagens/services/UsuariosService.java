package br.com.ifsp.projetolinguagens.services;

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
        usuarios.add(cliente);
        clientes.add(cliente);
        return cliente;
    }

    public Funcionario adicionarFuncionario(Funcionario funcionario) {
        usuarios.add(funcionario);
        funcionarios.add(funcionario);
        return funcionario;
    }

    /*  LISTA OS OBJETOS DAS LISTAS */

    public List<Usuario> listarUsuarios() {
        return usuarios;
    }

    public List<Cliente> listarClientes() {
        return clientes;
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    /*  BUSCA OS OBJETOS DAS LISTAS PELO CPF  */

    public static Usuario buscarUsuario(String cpf) {
        return usuarios.stream()
                .filter(l -> l.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    public static Cliente buscarCliente(String cpf) {
        return clientes.stream()
                .filter(l -> l.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }


    public Funcionario buscarFuncionario(String cpf) {
        return funcionarios.stream()
                .filter(l -> l.getCpf().equals(cpf))
                .findFirst()
                .orElse(null);
    }

    /*  MÉTODO QUE VAI SER USADO EM BIBLIOTECA PARA BUSCAR USUARIO PELO NOME */
    public Usuario buscarUsuarioNome(String nome) {
        return usuarios.stream()
                .filter(l -> l.getCpf().equals(nome))
                .findFirst()
                .orElse(null);
    }



    /*  APAGA USUARIOS/CLIENTES/FUNCIONARIOS */

    // MODIFICAÇÃO DO METODO PARA VERIFICAR QUE TIPO DE OBJETO É E APAGAR DA LISTA CORRESPONDENTE

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








