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

            PreparedStatement ps = c.prepareStatement("select cpf, nome, endereco, telefone from tbcliente");

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

            String InsertSQL = "INSERT INTO tbcliente (cpf,nome,endereco,telefone) "
                    + "values (?,?,?,?)";

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

}
