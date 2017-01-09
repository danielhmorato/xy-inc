package br.com.xyinc.servico;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/geo")
public class ServiceConfig extends Application {

	@Override
	public Map<String, Object> getProperties() {
	    Map<String, Object> properties = new HashMap<String, Object>();
	    properties.put("jersey.config.server.provider.packages", "br.com.xyinc.servico");
	    return properties;
	}
	

}
