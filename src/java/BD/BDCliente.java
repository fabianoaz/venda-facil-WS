/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cliente;

/**
 *
 * @author Fabiano
 */
public class BDCliente {

    public ArrayList listaClientes() {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        ArrayList lCliente = new ArrayList<>();

        try {

            PreparedStatement ps = c.prepareStatement("select cpf, nome, endereco, telefone,datacriacao,dataexclusao from tbcliente where dataexclusao is null");

            ResultSet rs = ps.executeQuery();

            try {
                while (rs.next()) {
                    Cliente cli = new Cliente();
                    cli.setCpf(rs.getString("cpf"));
                    cli.setNome(rs.getString("nome"));
                    cli.setEndereco(rs.getString("endereco"));
                    cli.setTelefone(rs.getInt("telefone"));

                    lCliente.add(cli);
                }

                return lCliente;

            } catch (SQLException ex) {
                System.out.println("Ocorreu o erro ao listar clientes: " + ex.getMessage());
            } finally {
                try {
                    c.close();
                } catch (SQLException ex) {
                    System.out.println("Ocorreu o erro ao encerrar a conexão com o banco de dados: " + ex.getMessage());
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDCliente.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                System.out.println("Ocorreu o erro ao encerrar a conexão com o banco de dados: " + ex.getMessage());
            }
        }

        return null;
    }

    public void insereCliente(Cliente cli) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            String InsertSQL = "INSERT INTO tbcliente (cpf,nome,endereco,telefone, datacriacao, dataexclusao) "
                    + "values (?,?,?,?, now(), null)";

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ps.setString(1, cli.getCpf());
            ps.setString(2, cli.getNome());
            ps.setString(3, cli.getEndereco());
            ps.setInt(4, cli.getTelefone());

            ps.execute();

            System.out.println("Executou o comando: " + ps.toString());

        } catch (Exception e) {
            System.out.println("Ocorreu erro ao inserir o registro: " + e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao fechar a conexão: " + ex.getMessage());
            }
        }
    }

    public Cliente buscaPorCodigo(String cpf) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        Cliente cli = new Cliente();

        try {

            String InsertSQL = "select cpf, nome, endereco, telefone,datacriacao,dataexclusao from tbcliente where cpf = '" + cpf + "'";

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    cli.setCpf(rs.getString("cpf"));
                    cli.setNome(rs.getString("nome"));
                    cli.setEndereco(rs.getString("endereco"));
                    cli.setTelefone(rs.getInt("telefone"));
                    return cli;
                }else{
                    System.out.println("Não há retorno...");
                    cli.setCpf(cpf);
                    cli.setNome("Cliente não localizado!");
                    return cli;
                }                   
            } catch (SQLException ex) {
                System.out.println("Ocorreu o erro ao listar produtos: " + ex.getMessage());
            } finally {
                try {
                    c.close();
                } catch (SQLException ex) {
                    System.out.println("Ocorreu o erro ao encerrar a conexão com o banco de dados: " + ex.getMessage());
                }
            }

            System.out.println("Executou o comando: " + ps.toString());

        } catch (Exception e) {
            System.out.println("Ocorreu erro ao inserir o registro: " + e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao fechar a conexão: " + ex.getMessage());
            }
        }

        return null;
    }
	
    public Cliente excluirCliente(Cliente cli) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        String cpf = cli.getCpf();

        try {

            String UpdateSQL = "update tbcliente set dataexclusao = now() where cpf = '" + cpf + "'";

            PreparedStatement ps = c.prepareStatement(UpdateSQL);

            ps.executeUpdate();

            System.out.println("\nExecutou o comando: " + ps.toString());

        } catch (Exception e) {
            System.out.println("\nOcorreu erro ao remover o registro: " + e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao fechar a conexão: " + ex.getMessage());
            }
        }

        return null;
    }		
	
    public Cliente atualizaCliente(Cliente cli) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            String UpdateSQL = "update tbcliente set nome = '" + cli.getNome() + "' , endereco = '" + cli.getEndereco() + "' , telefone = '" + cli.getTelefone() + "' where cpf = '" + cli.getCpf() + "'";

            PreparedStatement ps = c.prepareStatement(UpdateSQL);

            ps.executeUpdate();

            System.out.println("\nExecutou o comando: " + ps.toString());

        } catch (Exception e) {
            System.out.println("\nOcorreu erro ao atualizar o registro: " + e.getMessage());
        } finally {
            try {
                c.close();
            } catch (SQLException ex) {
                System.out.println("Ocorreu um erro ao fechar a conexão: " + ex.getMessage());
            }
        }

        return null;
    }
    
}
