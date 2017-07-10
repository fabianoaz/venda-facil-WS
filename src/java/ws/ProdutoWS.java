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
import model.Produto;
import service.ProdutoServico;

/**
 * REST Web Service
 *
 * @author Fabiano
 */
@Path("produto")
public class ProdutoWS {
    
    @EJB
    private ProdutoServico produtoServico;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ProdutoWS
     */
    public ProdutoWS() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarProduto(Produto p, 
            @Context final HttpServletResponse response){
        
        produtoServico.novoProd(p);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        }catch (IOException e){
            throw new InternalServerErrorException();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Produto> getProduto() {
        return produtoServico.getProd();
    }
    
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto produtoPorCodigo(@PathParam("codigo") int cod){
        return produtoServico.buscaPorCodigo(cod);
    }
    
    @DELETE
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Produto removerProduto(@PathParam("codigo") int cod){
        Produto p = produtoServico.buscaPorCodigo(cod);
        produtoServico.excluirProduto(p);
        return p;
    }  
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void atualizaProduto(Produto p, 
            @Context final HttpServletResponse response){
        produtoServico.atualizaProduto(p);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        }catch (IOException e){
            throw new InternalServerErrorException();
        }
    }
}
