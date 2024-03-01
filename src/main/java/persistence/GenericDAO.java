package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GenericDAO {
	private Connection c;
	
	public Connection getConnection() throws ClassNotFoundException, SQLException{
		String hostname = "localhost";
		String dbname = "rodoviaria";
		String user = "sa";
		String pass = "luiz2002";
		Class.forName("net.sourceforge.jtds.jdbc.Driver");
		c = DriverManager.getConnection(String.format(
				"jdbc:jtds:sqlserver://%s:1433;databaseName=%s;user=%s;password=%s",
				hostname, dbname, user, pass)
			);
		return c;
	}
}