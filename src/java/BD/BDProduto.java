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
import model.Produto;

/**
 *
 * @author Fabiano
 */
public class BDProduto {

    public ArrayList listaProdutos() {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        ArrayList lProduto = new ArrayList<>();

        try {

            PreparedStatement ps = c.prepareStatement("select codigo, precovenda, descricao, datacriacao, dataexclusao from tbproduto where dataexclusao is null order by datacriacao");

            ResultSet rs = ps.executeQuery();

            try {
                while (rs.next()) {
                    Produto prod = new Produto();
                    prod.setCodigo(rs.getInt("codigo"));
                    prod.setPrecovenda(rs.getFloat("precovenda"));
                    prod.setDescricao(rs.getString("descricao"));

                    lProduto.add(prod);
                }

                return lProduto;

            } catch (SQLException ex) {
                System.out.println("Ocorreu o erro ao listar produtos: " + ex.getMessage());
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

    public void insereProduto(Produto prod) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            String InsertSQL = "INSERT INTO tbproduto (codigo, precovenda, descricao, datacriacao, dataexclusao) "
                    + "values (?,?,?, now(), null)";

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ps.setInt(1, prod.getCodigo());
            ps.setFloat(2, prod.getPrecovenda());
            ps.setString(3, prod.getDescricao());

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

    public Produto buscaPorCodigo(int cod) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        Produto prod = new Produto();

        try {

            String InsertSQL = "select codigo, precovenda, descricao, datacriacao, dataexclusao from tbproduto where codigo = " + cod ;

            PreparedStatement ps = c.prepareStatement(InsertSQL);

            ResultSet rs = ps.executeQuery();

            try {
                if (rs.next()) {
                    prod.setCodigo(rs.getInt("codigo"));
                    prod.setPrecovenda(rs.getFloat("precovenda"));
                    prod.setDescricao(rs.getString("descricao"));
                    return prod;
                }else{
                    System.out.println("Não há retorno...");
                    prod.setCodigo(cod);
                    prod.setDescricao("Produto não localizado!");
                    return prod;
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

    public Produto excluirProduto(Produto prod) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();
        int cod = prod.getCodigo();

        try {

            String UpdateSQL = "update tbproduto set dataexclusao = now() where codigo = " + cod;

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

    public Produto atualizaProduto(Produto prod) {

        BDConnection conn = new BDConnection();
        Connection c = conn.Conexao();

        try {

            String UpdateSQL = "update tbproduto set precovenda = " + prod.getPrecovenda() + " , descricao = '" + prod.getDescricao() + "' where codigo = " + prod.getCodigo();

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
