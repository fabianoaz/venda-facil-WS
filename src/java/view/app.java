/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import BD.BDCliente;
import model.Cliente;
import BD.BDConnection;
import BD.BDProduto;
import java.util.ArrayList;
import model.Produto;
import service.ClienteServico;
import service.ProdutoServico;
import ws.ProdutoWS;

/**
 *
 * @author Fabiano
 */
public class app {

    public static void main(String[] args) {
        
//          BDConnection bdc = new BDConnection();
//          bdc.Conexao();


//        BDCliente bdcli = new BDCliente();
//        Cliente cli = bdcli.consultaSQL("select * from tbcliente");
//        System.out.println("Cliente: " + cli.getNome());
//        ClienteServico cs = new ClienteServico();
//        ArrayList lCli = new ArrayList<Cliente>();
//        Cliente cli = new Cliente();
//        lCli = cs.getCliente();
//        for(int i=0;i<lCli.size();i++){
//        System.out.println("Cliente: " + lCli.get(i).toString());
//        }

//        BDProduto bdp = new BDProduto();
//        Produto p1 = bdp.buscaPorCodigo(3);
//        System.out.println("Produto em BD: " + p1.getDescricao());
        
// ------ Este teste funcionou
//        ProdutoServico ps = new ProdutoServico();
//        Produto p = ps.buscaPorCodigo(3);
//        ps.excluirProduto(p);

        
        ProdutoWS pws = new ProdutoWS();
        pws.removerProduto(3);




    }
}
