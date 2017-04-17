package br.com.livro.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

/* 
 * ServletUtil faz o trabalho de escrever um XML ou JSON.
 * Note que o mime-type correto do XML ou do JSON é configurado em cada método.
 * */

public class ServletUtil {

	public static void writeXML(HttpServletResponse response, String xml) throws IOException{
		if(xml != null){
			PrintWriter writer = response.getWriter();
			response.setContentType("application/xml;charset=UTF-8");
			writer.write(xml);
			writer.close();
		}
	}
	
	public static void writeJSON(HttpServletResponse response, String json) throws IOException{
		if(json != null){
			PrintWriter writer = response.getWriter();
			response.setContentType("application/json;charset=UTF-8");
			writer.write(json);
			writer.close();
		}
	}
	
}
