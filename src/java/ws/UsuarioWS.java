/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import java.io.IOException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import model.Usuario;
import service.UsuarioServico;

/**
 * REST Web Service
 *
 * @author Fabiano
 */
@Path("usuario")
public class UsuarioWS {
    
    @EJB
    private UsuarioServico usuarioServico;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioWS
     */
    public UsuarioWS() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarUsuario(Usuario u, 
            @Context final HttpServletResponse response){
        usuarioServico.novoUsu(u);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        }catch (IOException e){
            throw new InternalServerErrorException();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Usuario> getUsuario() {
        return usuarioServico.getUsu();
    }
    
    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario clientePorCodigo(@PathParam("cpf") String cpf){
        return usuarioServico.buscaPorCPF(cpf);
    }
    
    @DELETE
    @Path("{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario removerCliente(@PathParam("cpf") String cpf){
        Usuario u = usuarioServico.buscaPorCPF(cpf);
        usuarioServico.excluirUsuario(u);
        return u;
    }       
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizaProduto(Usuario u, 
            @Context final HttpServletResponse response){
        usuarioServico.atualizaUsuario(u);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        }catch (IOException e){
            throw new InternalServerErrorException();
        }
    }
    
}
