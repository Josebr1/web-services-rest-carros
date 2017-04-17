package br.com.livro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.domain.ListaCarros;
import br.com.livro.util.JAXBUtil;

@WebServlet("/carros/*")
public class CarrosServlets extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CarroService carroService = new CarroService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Carro> carros = carroService.getCarros();
		ListaCarros lista = new ListaCarros();
		lista.setCarros(carros);
		// Gera o JSON
		/* O método setPrettyPrinting() é utilizado para exibir o JSON em um formato mais amigável com 
		 * quebra de linhas. */
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String json = gson.toJson(lista);
		// Escreve o JSON na responde do servlet com application/json
		ServletUtil.writeJSON(resp, json);
	}

}
