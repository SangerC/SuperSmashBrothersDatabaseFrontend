package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import databaseobjects.SelectedItem;

public class ProfileService {
	
	DatabaseConnection dbConnection;

	public ProfileService(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public String getFavGame(String username) {
		 try {
			Statement fgST = dbConnection.getConnection().createStatement();
			String fgQuery = "SELECT Game FROM [User Favorite] WHERE Username = "+"'"+username+"'";
			ResultSet fgRS = fgST.executeQuery(fgQuery);
			
			int gameIndex = fgRS.findColumn("Game");
			
			fgRS.next();
			return fgRS.getString(gameIndex);
			
		} catch (SQLException e) {
		}
		 return "No favorite game";
	}
	
	public String getFavChar(String username) {
		 try {
			Statement fcST = dbConnection.getConnection().createStatement();
			String fcQuery = "SELECT Character FROM [User Favorite] WHERE Username = "+"'"+username+"'";
			ResultSet fcRS = fcST.executeQuery(fcQuery);
			
			int charIndex = fcRS.findColumn("Character");
			
			fcRS.next();
			return fcRS.getString(charIndex);
			
		} catch (SQLException e) {
		}
		return "No favorite character";
	}

}
