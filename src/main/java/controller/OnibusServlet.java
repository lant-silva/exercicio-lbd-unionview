package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Onibus;

public class OnibusServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public OnibusServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("botao");
		String placa = request.getParameter("placa");
		String marca = request.getParameter("marca");
		String ano = request.getParameter("ano");
		String descricao = request.getParameter("descricao");
		
		String saida="";
		String erro="";
		Onibus o = new Onibus();
		List<Onibus> onibus = new ArrayList<>();
			
		if(!cmd.contains("Listar")) {
			o.setPlaca(placa);
		}
		if(cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			o.setMarca(marca);
			o.setAno(Integer.parseInt(ano));
			o.setDescricao(descricao);
		}
		try {
			if(cmd.contains("Cadastrar")) {
				cadastrarOnibus(o);
				saida = "Ônibus cadastrado.";
				o = null;
			}
			if(cmd.contains("Alterar")) {
				alterarOnibus(o);
				saida = "Ônibus alterado.";
				o = null;
			}
			if(cmd.contains("Excluir")) {
				excluirOnibus(o);
				saida = "Ônibus excluido.";
				o = null;
			}
			if(cmd.contains("Buscar")) {
				o = buscarOnibus(o);
			}
			if(cmd.contains("Listar")) {
				onibus = listarOnibus();
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			
		} finally {
			
		}
		
	}
	private void cadastrarOnibus(Onibus o) {
		
		
	}
	private void alterarOnibus(Onibus o) {
		
		
	}
	private void excluirOnibus(Onibus o) {
		
		
	}
	private Onibus buscarOnibus(Onibus o) {
		
		return null;
	}
	private List<Onibus> listarOnibus() {
		
		return null;
	}
}
