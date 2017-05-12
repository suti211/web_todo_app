package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnector {
		
	private void loadDriver(){
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error: could not load jdbc driver!");
			System.exit(1);
		}	
		
	}
	
	public Connection getConnection() throws SQLException{
		loadDriver();
		String unicode = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "root";
		String pass = "admin";
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/todo" + unicode + "&useSSL=true", user, pass);
	}
}
