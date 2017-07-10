/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtro;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Fabiano
 */
@WebFilter(filterName = "FiltroCORS", urlPatterns = {"/*"})
public class FiltroCORS implements Filter {
    
    public FiltroCORS() {
    }    
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        System.out.println(request.getMethod());
        response.addHeader("Access-Control-Allow-Origin", "*");
        
        if(request.getMethod().equals("OPTIONS")){
            response.addHeader("Access-Control-Allow-Methods","GET, POST, OPTIONS, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers","Content-Type");
            response.setStatus(HttpServletResponse.SC_OK);
        }
            
        chain.doFilter(req, resp);
        
        System.out.println("Status: "+response.getStatus());
    }
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }
    
}
