package br.com.livro.util;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLStreamWriter;

import org.codehaus.jettison.mapped.MappedNamespaceConvention;
import org.codehaus.jettison.mapped.MappedXMLStreamWriter;

import br.com.livro.domain.Carro;
import br.com.livro.domain.ListaCarros;

public class JAXBUtil {

	private static JAXBUtil instance;
	private static JAXBContext context;

	public static JAXBUtil getInstance() {
		return instance;
	}

	static {
		try {
			// Informa ao JAXB que � para gerar XML destas classe
			context = JAXBContext.newInstance(ListaCarros.class, Carro.class);
		} catch (JAXBException e) {
			throw new RuntimeException();
		}
	}

	/*
	 * Depois o m�todo toXML(Object) converte o objeto para xml, processo
	 * conhecido no JAXB por Marshaller, por isso o pr�prio c�digo cria um
	 * objeto Marshaller.
	 */
	public static String toXML(Object object) throws IOException {
		try {
			StringWriter writer = new StringWriter();

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			m.marshal(object, writer);
			String xml = writer.toString();
			return xml;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* 
	 * Mas o que isso tem a ver com XML? Bem, � porque qualquer biblioteca pode implementar o StAX, por�m
	 * o Jettison � famoso por implement�-la, por�m, em vez de gerar XML, ele gera JSON no final.
	 *  */
	public static String toJSON(Object object) throws IOException {
		try {
			StringWriter writer = new StringWriter();

			Marshaller m = context.createMarshaller();
			MappedNamespaceConvention con = new MappedNamespaceConvention();
			XMLStreamWriter xmlStreamWriter = new MappedXMLStreamWriter(con, writer);

			m.marshal(object, xmlStreamWriter);
			String json = writer.toString();
			return json;
		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}

}
