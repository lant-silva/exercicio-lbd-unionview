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
import model.Onibus;
import model.Viagem;
import persistence.GenericDAO;
import persistence.MotoristaDAO;
import persistence.OnibusDAO;
import persistence.ViagemDAO;

@WebServlet("/viagem")
public class ViagemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ViagemServlet() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("botao");
		String codigo = request.getParameter("codigo");
		String onibus = request.getParameter("onibus");
		String motorista = request.getParameter("motorista");
		String horaSaida = request.getParameter("hora_saida");
		String horaChegada = request.getParameter("hora_chegada");
		String partida = request.getParameter("partida");
		String destino = request.getParameter("destino");
		
		String saida="";
		String erro="";
		Viagem v = new Viagem();
		List<Viagem> viagens = new ArrayList<>();
		List<Onibus> vOnibus = new ArrayList<>();
		List<Motorista> vMotoristas = new ArrayList<>();
		
		if(!cmd.contains("Listar") && !cmd.contains("View Onibus") && !cmd.contains("View Viagens")) {
			v.setCodigo(Integer.parseInt(codigo));
		}
		try {
			vOnibus = listarOnibus();
			vMotoristas = listarMotorista();
			
			if(cmd.contains("Cadastrar") || cmd.contains("Alterar")) {
				Onibus o = new Onibus();
				o.setPlaca(onibus);
				o = buscarOnibus(o);
				v.setOnibus(o.getPlaca());
				Motorista m = new Motorista();
				m.setCodigo(Integer.parseInt(motorista));
				m = buscarMotorsita(m);
				v.setMotorista(m.getCodigo());
				v.setHoraSaida(Integer.parseInt(horaSaida));
				v.setHoraChegada(Integer.parseInt(horaChegada));
				v.setPartida(partida);
				v.setDestino(destino);
			}
			if(cmd.contains("Cadastrar")) {
				cadastrarViagem(v);
				saida = "Viagem cadastrada.";
				v = null;
			}
			if(cmd.contains("Alterar")) {
				alterarViagem(v);
				saida = "Viagem alterada.";
				v = null;
			}
			if(cmd.contains("Excluir")) {
				excluirViagem(v);
				saida = "Viagem excluida.";
				v = null;
			}
			if(cmd.contains("Buscar")) {
				v = buscarViagem(v);
			}
			if(cmd.contains("Listar")) {
				viagens = listarViagens();
				request.setAttribute("tabela", "listar");
			}
			if(cmd.contains("View Onibus")) {
				vOnibus = listarOnibus();
				request.setAttribute("tabela", "vonibus");
			}
			if(cmd.contains("View Viagens")) {
				vMotoristas = listarMotorista();
				request.setAttribute("tabela", "vviagens");
			}
			
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			request.setAttribute("saida", saida);
			request.setAttribute("erro", erro);
			request.setAttribute("viagem", v);
			request.setAttribute("viagens", viagens);
			request.setAttribute("vonibus", vOnibus);
			request.setAttribute("vmotoristas", vMotoristas);;
			RequestDispatcher rd = request.getRequestDispatcher("viagem.jsp");
			rd.forward(request, response);
		}
	}
	private Motorista buscarMotorsita(Motorista m) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		MotoristaDAO mDao = new MotoristaDAO(gDao);
		m = mDao.consultar(m);
		return m;
	}
	private Onibus buscarOnibus(Onibus o) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		OnibusDAO oDao = new OnibusDAO(gDao);
		o = oDao.consultar(o);
		return o;
	}
	private void cadastrarViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		ViagemDAO vDao = new ViagemDAO(gDao);
		vDao.inserir(v);
		listarViagens();
	}
	private void alterarViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		ViagemDAO vDao = new ViagemDAO(gDao);
		vDao.atualizar(v);
		listarViagens();
	}
	private void excluirViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		ViagemDAO vDao = new ViagemDAO(gDao);
		vDao.excluir(v);
		listarViagens();
	}
	private Viagem buscarViagem(Viagem v) throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		ViagemDAO vDao = new ViagemDAO(gDao);
		v = vDao.consultar(v);
		return v;
	}
	private List<Viagem> listarViagens() throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		ViagemDAO vDao = new ViagemDAO(gDao);
		List<Viagem> viagens = vDao.listar();
		return viagens;
	}
	private List<Onibus> listarOnibus() throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		OnibusDAO vDao = new OnibusDAO(gDao);
		List<Onibus> vO = vDao.listar();
		return vO;
	}
	private List<Motorista> listarMotorista() throws ClassNotFoundException, SQLException {
		GenericDAO gDao = new GenericDAO();
		MotoristaDAO vDao = new MotoristaDAO(gDao);
		List<Motorista> vV = vDao.listar();
		return vV;
	}
}
