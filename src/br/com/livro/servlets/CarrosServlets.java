package br.com.livro.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import br.com.livro.domain.Carro;
import br.com.livro.domain.CarroService;
import br.com.livro.util.RegexUtil;

@WebServlet("/carros/*")
public class CarrosServlets extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private CarroService carroService = new CarroService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String requestUri = req.getRequestURI();
		Long id = null;
		try {
			id = RegexUtil.matchId(requestUri);
		} catch (SerialException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(id != null){
			// Informou o Id
			Carro carro = carroService.getCarro(id);
			if(carro != null){
				// Gera o JSON
				/* O m�todo setPrettyPrinting() � utilizado para exibir o JSON em um formato mais amig�vel com 
				 * quebra de linhas. */
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(carro);
				// Escreve o JSON na responde do servlet com application/json
				ServletUtil.writeJSON(resp, json);
			}else{
				resp.sendError(404, "Carro n�o encontrado");
			}
		}else{
			// Lista de carros
			List<Carro> carros = carroService.getCarros();
			// Gera o JSON
			/* O m�todo setPrettyPrinting() � utilizado para exibir o JSON em um formato mais amig�vel com 
			 * quebra de linhas. */
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String json = gson.toJson(carros);
			// Escreve o JSON na responde do servlet com application/json
			ServletUtil.writeJSON(resp, json);
		}
		
		
	}

}
