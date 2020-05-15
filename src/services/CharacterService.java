package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databaseobjects.GameCharacter;

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
				JOptionPane.showMessageDialog(null, "The character was succesfully added to the database.");
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
	


}
