package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Onibus;
import persistence.GenericDAO;
import persistence.OnibusDAO;

@WebServlet("/onibus")
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
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("onibus", o);
			request.setAttribute("onibuss", onibus);
			
			RequestDispatcher rd = request.getRequestDispatcher("onibus.jsp");
			rd.forward(request, response);
		}
	}
	private void cadastrarOnibus(Onibus o) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		OnibusDAO oDao = new OnibusDAO(gDao);
		oDao.inserir(o);
		listarOnibus();
	}
	private void alterarOnibus(Onibus o) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		OnibusDAO oDao = new OnibusDAO(gDao);
		oDao.atualizar(o);
		listarOnibus();
	}
	private void excluirOnibus(Onibus o) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		OnibusDAO oDao = new OnibusDAO(gDao);
		oDao.excluir(o);
		listarOnibus();
	}
	private Onibus buscarOnibus(Onibus o) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		OnibusDAO oDao = new OnibusDAO(gDao);
		o = oDao.consultar(o);
		return o;
	}
	private List<Onibus> listarOnibus() throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		OnibusDAO oDao = new OnibusDAO(gDao);
		List<Onibus> onibus = oDao.listar();
		return onibus;
	}
}
