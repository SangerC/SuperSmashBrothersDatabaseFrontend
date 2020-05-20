package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import javax.swing.JOptionPane;

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
	
	public int getRank(String username) {
		 try {
			Statement rST = dbConnection.getConnection().createStatement();
			String rQuery = "SELECT Rank FROM [User] WHERE Username = "+"'"+username+"'";
			ResultSet rRS = rST.executeQuery(rQuery);
			
			int rankIndex = rRS.findColumn("Rank");
			
			rRS.next();
			return rRS.getInt(rankIndex);
			
		} catch (SQLException e) {
		}
		 return 0;
	}
	
	public boolean updateFav(String username, String game, String character) {
		try {
			if (username.equals("appUserSuperSmash")) {
				JOptionPane.showMessageDialog(null, "Cannot edit favorites when login is bypassed");
				return false;
			}
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.update_Fav(?,?,?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, username);
			str.setNString(3, game);
			str.setNString(4, character);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "Favorites were successfully updated");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the Database");
			}
			
			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Character currently does not exist in the Database.");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in updating favorites");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteProfile(String username) {
		if (username.equals("appUserSuperSmash")) {
			JOptionPane.showMessageDialog(null, "Cannot delete profile when login is bypassed");
			return false;
		}
		try {
			CallableStatement str = dbConnection.getConnection().prepareCall("{? = call dbo.delete_User(?)}");
			str.registerOutParameter(1, Types.INTEGER);
			str.setNString(2, username);
			str.executeUpdate();
			int returnValue = str.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "Profile deleted");
				return true;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in deleting profile");
			e.printStackTrace();
		}
		return false;
	}

}
