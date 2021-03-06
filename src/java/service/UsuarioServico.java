/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import BD.BDUsuario;
import java.util.ArrayList;
import javax.ejb.Stateless;
import model.Usuario;

/**
 *
 * @author Fabiano
 */
@Stateless
public class UsuarioServico {

    public void novoUsu(Usuario usu) {
        BDUsuario bdusu = new BDUsuario();
        bdusu.insereUsuario(usu);
    }

    public ArrayList getUsu() {
        BDUsuario bdusu = new BDUsuario();
        return bdusu.listaUsuarios();
    }

    public Usuario buscaPorCPF(String cpf){
        BDUsuario bdusu = new BDUsuario();
        Usuario usu = bdusu.buscaPorCodigo(cpf);
        return usu;
    }
    
    public void excluirUsuario(Usuario usu){
        BDUsuario bdusu = new BDUsuario();
        bdusu.excluirUsuario(usu);
    } 
    
    public void atualizaUsuario(Usuario usu){
        BDUsuario bdusu = new BDUsuario();
        bdusu.atualizaUsuario(usu);
    }    
}
