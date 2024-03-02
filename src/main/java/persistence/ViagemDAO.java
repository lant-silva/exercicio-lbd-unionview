package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Viagem;

public class ViagemDAO implements ICrud<Viagem>{
	private GenericDAO gDao;
	
	public ViagemDAO(GenericDAO gDao) {
		this.gDao = gDao;
	}

	@Override
	public void inserir(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "INSERT INTO viagem VALUES (?,?,?,?,?,?,?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.setString(2, v.getOnibus());
		ps.setInt(3, v.getMotorista());
		ps.setInt(4, v.getHoraSaida());
		ps.setInt(5, v.getHoraChegada());
		ps.setString(6, v.getPartida());
		ps.setString(7, v.getDestino());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void atualizar(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "UPDATE viagem SET onibus = ?, motorista = ?, hora_saida = ?, hora_chegada = ?, partida = ?, destino = ? WHERE codigo = ?)";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setString(2, v.getOnibus());
		ps.setInt(3, v.getMotorista());
		ps.setInt(4, v.getHoraSaida());
		ps.setInt(5, v.getHoraChegada());
		ps.setString(6, v.getPartida());
		ps.setString(7, v.getDestino());
		ps.setInt(1, v.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public void excluir(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "DELETE viagem WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ps.execute();
		ps.close();
		c.close();
	}

	@Override
	public Viagem consultar(Viagem v) throws SQLException, ClassNotFoundException {
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino FROM viagem WHERE codigo = ?";
		PreparedStatement ps = c.prepareStatement(sql);
		ps.setInt(1, v.getCodigo());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			v.setCodigo(rs.getInt("codigo"));
			v.setOnibus(rs.getString("onibus"));
			v.setMotorista(rs.getInt("motorista"));
			v.setHoraSaida(rs.getInt("hora_saida"));
			v.setHoraChegada(rs.getInt("hora_chegada"));
			v.setPartida(rs.getString("partida"));
			v.setDestino(rs.getString("destino"));
		}
		rs.close();
		ps.close();
		c.close();
		return v;
	}

	@Override
	public List<Viagem> listar() throws SQLException, ClassNotFoundException {
		List<Viagem> viagens = new ArrayList<>();
		Connection c = gDao.getConnection();
		String sql = "SELECT codigo, onibus, motorista, hora_saida, hora_chegada, partida, destino FROM viagem";
		PreparedStatement ps = c.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Viagem v = new Viagem();
			v.setCodigo(rs.getInt("codigo"));
			v.setOnibus(rs.getString("onibus"));
			v.setMotorista(rs.getInt("motorista"));
			v.setHoraSaida(rs.getInt("hora_saida"));
			v.setHoraChegada(rs.getInt("hora_chegada"));
			v.setPartida(rs.getString("partida"));
			v.setDestino(rs.getString("destino"));
			viagens.add(v);
		}
		rs.close();
		ps.close();
		c.close();
		return viagens;
	}
}
