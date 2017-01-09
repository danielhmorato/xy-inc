package br.com.xyinc.servico;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.com.xyinc.dao.LocalDao;
import br.com.xyinc.modelo.Local;
import br.com.xyinc.modelo.LocalProximo;

/**
 * Classe do servico WEBSERVICE
 * @author Daniel
 *
 */
@Path("/local")
public class LocalServico {
    @Context UriInfo uriInfo;
    
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Local> listar() throws SQLException {
        LocalDao dao = new LocalDao();
        List<Local> locais = dao.listarTodos();
        return locais;
    }
    
    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response local(@PathParam("codigo") long codigo) { 
        LocalDao dao = new LocalDao();      
        try {           
            Local local = dao.consultarLocal(codigo);      
            return Response.ok(local).build();                
        } catch (SQLException e) {
            e.printStackTrace();
        }       
        return Response.serverError().build();
    }     
     
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response criar(@FormParam("nomePOI") String nomePOI, 
    					  @FormParam("pontoX") int pontoX, 
    					  @FormParam("pontoY") int pontoY) { 
    	if (validaPontos(pontoX, pontoY)) {
    		LocalDao dao = new LocalDao();
	        try {
	            int id = dao.inserirLocal(new Local(nomePOI, pontoX, pontoY));         
	            return Response.ok(dao.consultarLocal(new Long(id))).build();               
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }      
        }
        return Response.serverError().build();
    } 

	@POST
    @Path("/listarProximidade")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public List<LocalProximo> listar(@FormParam("pontoX") int pontoX, 
    						  @FormParam("pontoY") int pontoY,
    						  @FormParam("dmax") double distanciaMaxima) throws SQLException {
		List<LocalProximo> locais = new ArrayList<>();
    	if (validaPontos(pontoX, pontoY)) {
	    	Local localRef = new Local("PontoReferencia", pontoX, pontoY);
	        LocalDao dao = new LocalDao();
	        for (Local loc : dao.listarTodos()) {
	        	double distancia = calcularDistanciaEntreLocais(loc, localRef);
	        	if (distanciaMaxima >= distancia) {
	        		locais.add(new LocalProximo(loc, distancia));
	        	}
	        }
    	}
    	Collections.sort(locais, new Comparator<LocalProximo>() {
    		@Override
    		public int compare(LocalProximo o1, LocalProximo o2) {    			
    			return o1.getDistancia().compareTo(o2.getDistancia());
    		}
		});
        return locais;
    }
    
    private double calcularDistanciaEntreLocais(Local l1, Local l2) {
    	return Math.sqrt(Math.pow(l1.getLatitude()-l2.getLatitude(), 2)+Math.pow(l1.getLongitude()-l2.getLongitude(), 2));
    }
    
    private boolean validaPontos(int pontoX, int pontoY) {    	
		return (pontoX >= 0 && pontoY >= 0);
	}
     
}
