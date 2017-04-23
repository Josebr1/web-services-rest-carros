package br.com.livro.rest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.jettison.JettisonFeature;

/*
 * Neste código, o método getSingletons() é responsável por adicionar funcionalidades ao 
 * Jersey.
 */
public class MyApplication extends Application{
	
	@Override
	public Set<Object> getSingletons(){
		Set<Object> singletons = new HashSet<>();
		// Driver do Jettison para gerar o JSON
		singletons.add(new JettisonFeature());
		return singletons;
	}
	
	@Override
	public Map<String, Object> getProperties(){
		Map<String, Object> properties = new HashMap<>();
		// Configura o pacote para fazer scan das classes com anotação REST.
		properties.put("jersey.config.server.provider.packages", "br.com.livro");
		return properties;
	}

}
