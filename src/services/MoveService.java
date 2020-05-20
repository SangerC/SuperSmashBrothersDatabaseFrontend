package services;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	
	
}