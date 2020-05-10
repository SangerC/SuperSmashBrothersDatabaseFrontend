package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import databaseobjects.Game;

public class GameService {
	
	DatabaseConnection dbConnection;

	public GameService(DatabaseConnection dbConnection) {
		this.dbConnection=dbConnection;
	}
	
	public ArrayList<Game> getGames(){
		 ArrayList<Game> games = new ArrayList<Game>();
		 try {
			Statement ST = dbConnection.getConnection().createStatement();
			String Query = "SELECT * FROM Game";
			ResultSet RS = ST.executeQuery(Query);
			
			int nameIndex = RS.findColumn("Name");
			int madeIndex = RS.findColumn("Made");
			int consolesIndex = RS.findColumn("Consoles");
			
			while (RS.next()) {
				games.add(new Game(RS.getString(nameIndex), RS.getDate(madeIndex), RS.getString(consolesIndex), null));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return games;
	}

}
