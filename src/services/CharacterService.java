package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

import databaseobjects.Game;
import databaseobjects.GameCharacter;
import databaseobjects.SelectedCharacter;

public class CharacterService {

	DatabaseConnection dbConnection;
  
	public CharacterService(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public ArrayList<GameCharacter> getCharacters(String game) {
		 ArrayList<GameCharacter> characters = new ArrayList<GameCharacter>();
		 try {
			Statement characterST = dbConnection.getConnection().createStatement();
			String characterQuery = "SELECT * FROM Character WHERE GameName = "+"'"+game+"'";
			ResultSet characterRS = characterST.executeQuery(characterQuery);
			
			int nameIndex = characterRS.findColumn("Name");
			int imageIndex = characterRS.findColumn("Image");
			
			while (characterRS.next()) {
				characters.add(new GameCharacter(characterRS.getString(nameIndex), null));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return characters;
	}
	
	public SelectedCharacter getCharacter(MoveService moveService, String game, String character) {
		 try {
			Statement characterST = dbConnection.getConnection().createStatement();
			String characterQuery = "SELECT * FROM Character WHERE GameName = "+"'"+game+"' and Name= '"+character+"'";
			ResultSet characterRS = characterST.executeQuery(characterQuery);
			
			int nameIndex = characterRS.findColumn("Name");
			int originIndex = characterRS.findColumn("Origin");
			int speedIndex = characterRS.findColumn("Speed");
			int weightIndex = characterRS.findColumn("Weight");
			
			characterRS.next();
			return new SelectedCharacter(moveService, game, characterRS.getString(nameIndex), characterRS.getString(originIndex),characterRS.getInt(speedIndex),characterRS.getInt(weightIndex));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addCharacter(String game, String name, String origin, int speed, int weight) {

		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.insert_Character(?,?,?,?,?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, game);
			str.setNString(3, name);
			str.setNString(4, origin);
			str.setInt(5, speed);
			str.setInt(6, weight);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Character is in current Game");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in adding the character to the database.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateCharacter(String game, String name, String origin, int speed, int weight) {

		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.update_Character(?,?,?,?,?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, game);
			str.setNString(3, name);
			str.setNString(4, origin);
			str.setInt(5, speed);
			str.setInt(6, weight);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The character was succesfully updated to the database.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Character is in current Game");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in adding the character to the database.");
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteCharacter(String game, String name) {
		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.delete_Character(?,?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, game);
			str.setNString(3, name);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The character was succesfully removed from the database.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Character currently does not exist in this database or is not in this game");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in deleting the character from the database.");
			e.printStackTrace();
		}
		return false;
	}

	public HashMap<SelectedCharacter, String> getCompare(MoveService moveService, String characterName) {
		HashMap<SelectedCharacter, String> characters = new HashMap<SelectedCharacter, String>();
				
		 try {
			Statement ST = dbConnection.getConnection().createStatement();
			String Query = "SELECT Name FROM Game";
			ResultSet RS = ST.executeQuery(Query);
			
			int nameIndex = RS.findColumn("Name");
			
			while (RS.next()) {
				
				Statement characterST = dbConnection.getConnection().createStatement();
				String characterQuery = "SELECT * FROM Character WHERE GameName = "+"'"+RS.getString(nameIndex)+"' and Name = '"+characterName+"'";
				ResultSet characterRS = characterST.executeQuery(characterQuery);
				
				int characterNameIndex = characterRS.findColumn("Name");
				int originIndex = characterRS.findColumn("Origin");
				int speedIndex = characterRS.findColumn("Speed");
				int weightIndex = characterRS.findColumn("Weight");
				
				while(characterRS.next()) {
					characters.put( new SelectedCharacter(moveService,RS.getString(nameIndex), characterRS.getString(characterNameIndex), characterRS.getString(originIndex),characterRS.getInt(speedIndex),characterRS.getInt(weightIndex)),RS.getString(nameIndex));
				}

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		 
		return characters;
	}
	


}
