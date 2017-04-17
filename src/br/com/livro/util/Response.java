package br.com.livro.util;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Response {

	private String status;
	private String msg;
	
	public Response() {
	}
	
	public static Response oK(String string){
		Response r = new Response();
		r.setStatus("OK");
		r.setMsg(string);
		return r;
	}
	
	public static Response error(String string){
		Response r = new Response();
		r.setStatus("ERROR");
		r.setMsg(string);
		return r;
	}

	public String getStatus() {
		return status;
	}

	public String getMsg() {
		return msg;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
	
}
