package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JOptionPane;

public class MoveService {

	DatabaseConnection dbConnection;
	
	public MoveService(DatabaseConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
	public String getFrameData(String character, String game, String type, String direction) {
		int typeInt;
		switch(type) {
		
		 case "strong": typeInt = 1;
		 break;
		 
		 case "smash": typeInt = 2;
		 break;
		 
		 case "special": typeInt = 3;
		 break;
		 
		 case "aerial": typeInt = 4;
		 break;
		 
		 default: return "Invalid move type";
		}
		
		int directionInt;
		switch(direction) {
		
		 case "up": directionInt = 1;
		 break;
		 
		 case "down": directionInt = 2;
		 break;
		 
		 case "forward": directionInt = 3;
		 break;
		 
		 case "back": directionInt = 4;
		 break;
		 
		 default: return "Invalid move direction";
		}
		
		try {
			Statement st = dbConnection.getConnection().createStatement();
			String query = "SELECT DamageStartFrame, DamageEndFrame FROM Move"+
					" WHERE CharacterName = '"+character+
					"' AND GameName = '"+game+
					"' AND Type = '"+typeInt+
					"' AND Direction = '"+directionInt+"'";
			ResultSet rs = st.executeQuery(query);
			
			int sIndex = rs.findColumn("DamageStartFrame");
			int eIndex = rs.findColumn("DamageEndFrame");
			
			rs.next();
			return "Damage from frames "+rs.getString(sIndex)+" to "+rs.getString(eIndex);
			
		} catch (SQLException e) {
			}
		return "Move is currently not tracked";
	}
	
	public boolean updateMove(String character, String game, String type, String direction, String start, String end) {

		switch(type) {
		
		 case "strong": type = "1";
		 break;
		 
		 case "smash": type = "2";
		 break;
		 
		 case "special": type = "3";
		 break;
		 
		 case "aerial": type = "4";
		 break;
		 
		 default: JOptionPane.showMessageDialog(null, "Invalid move type");
		 return false;
		}
		
		switch(direction) {
		
		 case "up": direction = "1";
		 break;
		 
		 case "down": direction = "2";
		 break;
		 
		 case "forward": direction = "3";
		 break;
		 
		 case "back": direction = "4";
		 break;
		 
		 default: JOptionPane.showMessageDialog(null, "Invalid move direction");
		 return false;
		}
		
		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.insert_Move(?, ?, ?, ?, ?, ?, ?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, null);
			str.setNString(3, character);
			str.setNString(4, game);
			str.setNString(5, type);
			str.setNString(6, direction);
			str.setNString(7, start);
			str.setNString(8, end);
			str.executeUpdate();
			
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "Move added");
				return true;
			}
			
			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "Move updated");
				return true;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in adding the move to the database");
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean deleteMove(String character, String game, String type, String direction) {
		switch(type) {
		
		 case "strong": type = "1";
		 break;
		 
		 case "smash": type = "2";
		 break;
		 
		 case "special": type = "3";
		 break;
		 
		 case "aerial": type = "4";
		 break;
		 
		 default: JOptionPane.showMessageDialog(null, "Invalid move type");
		 return false;
		}
		
		switch(direction) {
		
		 case "up": direction = "1";
		 break;
		 
		 case "down": direction = "2";
		 break;
		 
		 case "forward": direction = "3";
		 break;
		 
		 case "back": direction = "4";
		 break;
		 
		 default: JOptionPane.showMessageDialog(null, "Invalid move direction");
		 return false;
		}
		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.delete_Move(?, ?, ?, ?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, character);
			str.setNString(3, game);
			str.setNString(4, type);
			str.setNString(5, direction);
			str.executeUpdate();
			
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "Move deleted");
				return true;
			}
			
			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "Move does not exist in database");
				return false;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in deleting move");
			e.printStackTrace();
		}
		
		return true;
	}
	
	
}