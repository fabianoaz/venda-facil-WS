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
import model.Usuario;

/**
 *
 * @author Fabiano
 */
public class BDUsuario {

    public ArrayList listaUsuarios() {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        ArrayList lUsuario = new ArrayList<>();

        try {

            PreparedStatement ps = c.prepareStatement("select cpf,nome,endereco,telefone,usuario,senha from tbusuario");

            ResultSet rs = ps.executeQuery();

            try {
                while (rs.next()) {
                    Usuario usu = new Usuario();
                    usu.setCpf(rs.getString("cpf"));
                    usu.setNome(rs.getString("nome"));
                    usu.setEndereco(rs.getString("endereco"));
                    usu.setTelefone(rs.getInt("telefone"));
                    usu.setUsuario(rs.getString("usuario"));
                    usu.setSenha(rs.getInt("senha"));
                    
                    lUsuario.add(usu);
                }

                return lUsuario;

            } catch (SQLException ex) {
                System.out.println("Ocorreu o erro ao listar usuarios: " + ex.getMessage());
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

    public void insereUsuario(Usuario usu) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            String InsertSQL = "INSERT INTO tbusuario (cpf,nome,endereco,telefone,usuario,senha) "
                    + "values (?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ps.setString(1, usu.getCpf());
            ps.setString(2, usu.getNome());
            ps.setString(3, usu.getEndereco());
            ps.setInt(4, usu.getTelefone());
            ps.setString(5, usu.getUsuario());
            ps.setInt(6, usu.getSenha());

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
