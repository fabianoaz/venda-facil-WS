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
import model.Estabelecimento;

/**
 *
 * @author Fabiano
 */
public class BDEstabelecimento {

    public ArrayList listaEstabelecimentos() {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        ArrayList lEstabelecimento = new ArrayList<>();

        try {

            PreparedStatement ps = c.prepareStatement("select cnpj, ie, telefone, razaosocial, nomefantasia, endereco, uf from tbEstabelecimento");

            ResultSet rs = ps.executeQuery();

            try {
                while (rs.next()) {
                    Estabelecimento estab = new Estabelecimento();
                    estab.setCnpj(rs.getString("cnpj"));
                    estab.setIe(rs.getString("ie"));
                    estab.setRazaosocial(rs.getString("razaosocial"));
                    estab.setNomefantasia(rs.getString("nomefantasia"));
                    estab.setEndereco(rs.getString("endereco"));
                    estab.setTelefone(rs.getInt("telefone"));
                    estab.setUf(rs.getString("uf"));

                    lEstabelecimento.add(estab);
                }

                return lEstabelecimento;

            } catch (SQLException ex) {
                System.out.println("Ocorreu o erro ao listar estabelecimentos: " + ex.getMessage());
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

    public void insereEstabelecimento(Estabelecimento estab) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            String InsertSQL = "INSERT INTO tbestabelecimento (cnpj,ie,telefone,razaosocial,nomefantasia,endereco,uf) "
                    + "values (?,?,?,?,?,?,?)";

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ps.setString(1, estab.getCnpj());
            ps.setString(2, estab.getIe());
            ps.setInt(3, estab.getTelefone());
            ps.setString(4, estab.getRazaosocial());
            ps.setString(5, estab.getNomefantasia());
            ps.setString(6, estab.getEndereco());
            ps.setString(7, estab.getUf());

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
