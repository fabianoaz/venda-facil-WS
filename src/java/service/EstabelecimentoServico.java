/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import javax.ejb.Stateless;
import BD.BDEstabelecimento;
import java.util.ArrayList;
import model.Estabelecimento;

/**
 *
 * @author Fabiano
 */
@Stateless
public class EstabelecimentoServico {
    
    public void novoEstab(Estabelecimento estab) {
        BDEstabelecimento bdestab = new BDEstabelecimento();
        bdestab.insereEstabelecimento(estab);
    }

    public ArrayList getEstab() {
        BDEstabelecimento bdestab = new BDEstabelecimento();
        return bdestab.listaEstabelecimentos();
    }
    
}
