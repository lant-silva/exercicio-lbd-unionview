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
import model.Motorista;
import persistence.GenericDAO;
import persistence.MotoristaDAO;

@WebServlet("/motorista")
public class MotoristaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public MotoristaServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("botao");
		String codigo = request.getParameter("codigo");
		String nome = request.getParameter("nome");
		String naturalidade = request.getParameter("naturalidade");
		
		String saida="";
		String erro="";
		Motorista m = new Motorista();
		List<Motorista> motoristas = new ArrayList<>();
		
		if(!cmd.contains("Listar")) {
			m.setCodigo(Integer.parseInt(codigo));
		}
		if(cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
			m.setNome(nome);
			m.setNaturalidade(naturalidade);
		}
		try {
			if(cmd.contains("Cadastrar")) {
				if(nome != null || naturalidade != null) {
					cadastrarMotorista(m);
					saida = "Motorista cadastrado.";
					m = null;					
				}else {
					erro = "Insira as informações em todos os campos";
				}
			}
			if(cmd.contains("Alterar")) {
				alterarMotorista(m);
				saida = "Motorista alterado.";
				m = null;
			}
			if(cmd.contains("Excluir")) {
				excluirMotorista(m);
				saida = "Motorista excluido";
				m = null;
			}
			if(cmd.contains("Buscar")) {
				m = buscarMotorista(m);
			}
			if(cmd.contains("Listar")) {
				motoristas = listarMotorista();
			}
		} catch(SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("motorista", m);
			request.setAttribute("motoristas", motoristas);
			
			RequestDispatcher rd = request.getRequestDispatcher("motorista.jsp");
			rd.forward(request, response);
		}
	}
	private void cadastrarMotorista(Motorista m) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		MotoristaDAO mDao = new MotoristaDAO(gDao);
		mDao.inserir(m);
		listarMotorista();
	}
	private void alterarMotorista(Motorista m) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		MotoristaDAO mDao = new MotoristaDAO(gDao);
		mDao.atualizar(m);
		listarMotorista();
	}
	private void excluirMotorista(Motorista m) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		MotoristaDAO mDao = new MotoristaDAO(gDao);
		mDao.excluir(m);
		listarMotorista();
		
	}
	private Motorista buscarMotorista(Motorista m) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		MotoristaDAO mDao = new MotoristaDAO(gDao);
		m = mDao.consultar(m);
		return m;
	}
	private List<Motorista> listarMotorista() throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		MotoristaDAO mDao = new MotoristaDAO(gDao);
		List<Motorista> motoristas = mDao.listar();
		return motoristas;
	}
}
