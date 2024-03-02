package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO {
	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String hostname = "localhost";
		String port = ":1433;";
		String dbname = "databaseName=rodoviaria;";
		String user = "user=sa;";
		String pass = "password=luiz2002";
		String uri = "jdbc:jtds:sqlserver://"+hostname+port+dbname+user+pass;
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(uri);
		return c;
	}
}