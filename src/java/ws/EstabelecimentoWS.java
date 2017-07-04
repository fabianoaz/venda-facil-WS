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
import javax.ws.rs.GET;
import javax.ws.rs.InternalServerErrorException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import model.Estabelecimento;
import service.EstabelecimentoServico;

/**
 * REST Web Service
 *
 * @author Fabiano
 */
@Path("estabelecimento")
public class EstabelecimentoWS {
    
    @EJB
    private EstabelecimentoServico estabelecimentoServico;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of EstabelecimentoWS
     */
    public EstabelecimentoWS() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void adicionarEstabelecimento(Estabelecimento e, 
            @Context final HttpServletResponse response){
        
        estabelecimentoServico.novoEstab(e);
        response.setStatus(HttpServletResponse.SC_CREATED);
        try{
            response.flushBuffer();
        }catch (IOException x){
            throw new InternalServerErrorException();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ArrayList<Estabelecimento> getEstabelecimento() {
        return estabelecimentoServico.getEstab();
    }
}
