package br.com.livro.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.com.livro.domain.Carro;
import br.com.livro.domain.ListaCarros;

public class JAXBUtil {

	private static JAXBUtil instance;
	private static JAXBContext context;
	
	public static JAXBUtil getInstance(){
		return instance;
	}
	
	static{
		try{
			// Informa ao JAXB que é para gerar XML destas classe
			context = JAXBContext.newInstance(ListaCarros.class, Carro.class);
		} catch (JAXBException e) {
			throw new RuntimeException();
		}
	}
	
	/*
	 * Depois o método toXML(Object) converte o objeto para xml, processo conhecido
	 * no JAXB por Marshaller, por isso o próprio código cria um objeto Marshaller.
	 *  */
	public static String toXML(Object object) throws IOException{
		try{
			StringWriter writer = new StringWriter();
			
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, writer);
			String xml = writer.toString();
			return xml;
		}catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
