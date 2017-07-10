/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BD.BDProduto;
import java.util.ArrayList;
import javax.ejb.Stateless;
import model.Produto;

/**
 *
 * @author Fabiano
 */
@Stateless
public class ProdutoServico {

    public void novoProd(Produto prod) {
        BDProduto bdprod = new BDProduto();
        bdprod.insereProduto(prod);
    }

    public ArrayList getProd() {
        BDProduto bdprod = new BDProduto();
        return bdprod.listaProdutos();
    }
    
    public Produto buscaPorCodigo(int cod){
        BDProduto bdprod = new BDProduto();
        Produto p = bdprod.buscaPorCodigo(cod);
        return p;
    }
    
    public void excluirProduto(Produto p){
        BDProduto bdprod = new BDProduto();
        bdprod.excluirProduto(p);
    }
    
    public void atualizaProduto(Produto p){
        BDProduto bdprod = new BDProduto();
        bdprod.atualizaProduto(p);
    }

}
