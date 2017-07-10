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
import model.Cliente;
import service.ClienteServico;

/**
 * REST Web Service
 *
 * @author Fabiano
 */
@Path("cliente")
public class ClienteWS {

    @EJB
    private ClienteServico clienteServico;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ClienteWS
     */
    public ClienteWS() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarCliente(Cliente c,
            @Context final HttpServletResponse response) {
        clienteServico.novoCli(c);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try {
            response.flushBuffer();
        } catch (IOException e) {
            throw new InternalServerErrorException();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Cliente> getCliente() {
        return clienteServico.getCliente();
    }
    
    @GET
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente clientePorCPF(@PathParam("cpf") String cpf) {
        return clienteServico.buscaPorCPF(cpf);
    }
    
    @DELETE
    @Path("/{cpf}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente removerCliente(@PathParam("cpf") String cpf){
        Cliente c = clienteServico.buscaPorCPF(cpf);
        ClienteServico cs = new ClienteServico();
        cs.excluirCliente(c);
        return c;
    }     
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizaCliente(Cliente c, 
            @Context final HttpServletResponse response){
        clienteServico.atualizaCliente(c);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        }catch (IOException e){
            throw new InternalServerErrorException();
        }
    }    

}
