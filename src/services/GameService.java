package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
	
	public boolean addGame(String name, String made, String consoles) {
		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.insert_Game(?,?,?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, name);
			str.setNString(3, made);
			str.setNString(4, consoles);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game is already in the database.");
			}

			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Game name cannot be null");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in adding the game to the database.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteGame(String name) {
		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.delete_Game(?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, name);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The game was succesfully removed from the database.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the database.");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in deleting the game from the database.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateGame(String name, String made, String consoles) {
		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.update_Game(?,?,?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, name);
			str.setNString(3, made);
			str.setNString(4, consoles);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The game was succesfully updated in the database.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the Database.");
			}
			
			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Game name cannot be null");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in updating the game from the database.");
			e.printStackTrace();
		}
		return false;
	}

}
