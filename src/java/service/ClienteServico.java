/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BD.BDCliente;
import java.util.ArrayList;
import javax.ejb.Stateless;
import model.Cliente;

/**
 *
 * @author Fabiano
 */
@Stateless
public class ClienteServico {

    public void novoCli(Cliente cli) {
        BDCliente bdcli = new BDCliente();
        bdcli.insereCliente(cli);
    }

    public ArrayList getCliente() {
        BDCliente bdcli = new BDCliente();
        return bdcli.listaClientes();
    }

}
