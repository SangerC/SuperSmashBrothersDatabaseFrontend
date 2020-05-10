package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private Connection connection = null;

	public boolean connect(String serverName, String databaseName, String username, String password) {
		String connectionString = "jdbc:sqlserver://"+serverName+";databaseName="+ databaseName +";user=" + username + ";password=" + password+";";
		try {
			connection = DriverManager.getConnection(connectionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection != null ? true : false;
	}
	
	public Connection getConnection() {
		return this.connection;
	}
	
	public boolean closeConnection() {
		if(connection !=null) {
			try {
				connection.close();
				if(connection.isClosed()) {
					System.out.println("The connection has successfully closed.");
					return true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		else {
			System.out.println("No Connection Active");
		}
		return false;
	}	
}
