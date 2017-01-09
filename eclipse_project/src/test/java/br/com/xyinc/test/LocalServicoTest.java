package br.com.xyinc.test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import br.com.xyinc.servico.LocalServico;

public class LocalServicoTest extends JerseyTest {
	
	@Override
    public Application configure() {
        enable(TestProperties.LOG_TRAFFIC);
        enable(TestProperties.DUMP_ENTITY);
        return new ResourceConfig(LocalServico.class);
    }
	
	@Test
    public void testListarTodos() {
        Response output = target("/local/listar").request().get();
        assertEquals("Precisa retornar status 200", 200, output.getStatus());
        assertNotNull("Precisa retornar uma lista de locais", output.getEntity());
    }
	
    @Test
    public void testCreate(){
    	MultivaluedMap<String, String> params = new MultivaluedHashMap<>();
    	params.add("nomePOI", "Bar da esquina n." + new Double(Math.random()).intValue());
    	params.add("pontoX",new Double(Math.random()).intValue()+"");
    	params.add("pontoY",new Double(Math.random()).intValue()+"");
        Response output = target("/local")
                .request()
                .post(Entity.form(params));

        assertEquals("Precisa retornar status 200", 200, output.getStatus());
        assertNotNull("Precisa retornar um local", output.getEntity());
    }	
	
    @Test
    public void testListaProx(){
    	MultivaluedMap<String, String> params = new MultivaluedHashMap<>();
    	params.add("dmax", "6");
    	params.add("pontoX",new Double(Math.random()).intValue()+"");
    	params.add("pontoY",new Double(Math.random()).intValue()+"");
        Response output = target("/local/listarProximidade")
                .request()
                .post(Entity.form(params));

        assertEquals("Precisa retornar status 200", 200, output.getStatus());
        assertNotNull("Precisa retornar uma lista de locais", output.getEntity());
    }	
	
}
