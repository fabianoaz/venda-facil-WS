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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Estabelecimento;

/**
 *
 * @author Fabiano
 */
public class BDEstabelecimento {

    public Estabelecimento listaEstabelecimentos() {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            PreparedStatement ps = c.prepareStatement("select cnpj, ie, telefone, razaosocial, nomefantasia, endereco, uf from tbestabelecimento where dataexclusao is null");

            ResultSet rs = ps.executeQuery();

            try {
                
                Estabelecimento estab = new Estabelecimento();
                
                if (rs.next()) {
                    
                    estab.setCnpj(rs.getString("cnpj"));
                    estab.setIe(rs.getString("ie"));
                    estab.setTelefone(rs.getString("telefone"));
                    estab.setRazaosocial(rs.getString("razaosocial"));
                    estab.setNomefantasia(rs.getString("nomefantasia"));
                    estab.setEndereco(rs.getString("endereco"));
                    estab.setUf(rs.getString("uf"));
                }

                return estab;

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

            String InsertSQL = "INSERT INTO tbestabelecimento (cnpj,ie,telefone,razaosocial,nomefantasia,endereco,uf, datacriacao, dataexclusao) "
                    + "values (?,?,?,?,?,?,?, now(), null)";

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ps.setString(1, estab.getCnpj());
            ps.setString(2, estab.getIe());
            ps.setString(3, estab.getTelefone());
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

    public Estabelecimento buscaPorCodigo(String cnpj) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        Estabelecimento estab = new Estabelecimento();

        try {

            String InsertSQL = "select cnpj, ie, telefone, razaosocial, nomefantasia, endereco, uf,datacriacao,dataexclusao from tbEstabelecimento where cnpj = " + cnpj;

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    estab.setCnpj(rs.getString("cnpj"));
                    estab.setIe(rs.getString("ie"));
                    estab.setRazaosocial(rs.getString("razaosocial"));
                    estab.setNomefantasia(rs.getString("nomefantasia"));
                    estab.setEndereco(rs.getString("endereco"));
                    estab.setTelefone(rs.getString("telefone"));
                    estab.setUf(rs.getString("uf"));
                    return estab;
                }else{
                    System.out.println("Não há retorno...");
                    estab.setCnpj(cnpj);
                    estab.setRazaosocial("Estabelecimento não localizado!");
                    return estab;
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
	
    public Estabelecimento excluirEstabelecimento(Estabelecimento estab) {
        
        System.out.println("excluirEstabelecimento em BDEstabelecimento");

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        String cnpj = estab.getCnpj();

        try {

            String UpdateSQL = "update tbestabelecimento set dataexclusao = now() where cnpj = " + cnpj;

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

    public Estabelecimento atualizaEstabelecimento(Estabelecimento estab) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            String UpdateSQL = "update tbestabelecimento set cnpj = '" + estab.getCnpj() + "' , ie = '" + estab.getIe() + "' , nomefantasia = '" + estab.getNomefantasia() + "' , razaosocial = '" + estab.getRazaosocial() + "' , endereco = '" + estab.getEndereco() + "'";

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
