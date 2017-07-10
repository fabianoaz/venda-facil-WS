/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import BD.BDCliente;
import model.Cliente;
import BD.BDConnection;
import BD.BDEstabelecimento;
import BD.BDProduto;
import java.util.ArrayList;
import model.Estabelecimento;
import model.Produto;
import service.ClienteServico;
import service.EstabelecimentoServico;
import service.ProdutoServico;
import ws.ClienteWS;
import ws.EstabelecimentoWS;
import ws.ProdutoWS;

/**
 *
 * @author Fabiano
 */
public class app {

    public static void main(String[] args) {

//        BDConnection bdc = new BDConnection();
//        bdc.Conexao();
        
          ClienteWS cws = new ClienteWS();
          cws.getCliente();



    }
}
