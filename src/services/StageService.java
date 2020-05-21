package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databaseobjects.GameCharacter;
import databaseobjects.GameStage;
import databaseobjects.SelectedCharacter;
import databaseobjects.SelectedStage;

public class StageService {

	DatabaseConnection dbConnection;
	
	public StageService(DatabaseConnection dbConnection) {
		this.dbConnection=dbConnection;
	}
	
	// Returns all the stages of the database
	public ArrayList<GameStage> getStages(String game) {
		 ArrayList<GameStage> stages = new ArrayList<GameStage>();
		 try {
			Statement stageST = dbConnection.getConnection().createStatement();
			String stageQuery = "SELECT * FROM Stage WHERE GameName = "+"'"+game+"'";
			ResultSet stageRS = stageST.executeQuery(stageQuery);
			
			int nameIndex = stageRS.findColumn("Name");
			int imageIndex = stageRS.findColumn("Image");
			
			while (stageRS.next()) {
				stages.add(new GameStage(stageRS.getString(nameIndex), null));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return stages;
	}
	
	public SelectedStage getStage(String game, String character) {
		 try {
			Statement stageST = dbConnection.getConnection().createStatement();
			String stageQuery = "SELECT * FROM Stage WHERE GameName = "+"'"+game+"' and Name= '"+character+"'";
			ResultSet stageRS = stageST.executeQuery(stageQuery);
			
			int nameIndex = stageRS.findColumn("Name");
			int originIndex = stageRS.findColumn("Origin");
			int musicIndex = stageRS.findColumn("Music");
			
			stageRS.next();
			return new SelectedStage(stageRS.getString(nameIndex), stageRS.getString(originIndex),stageRS.getString(musicIndex));
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean addStage(String name, String origin, String music, String gameName) {
		try {
			CallableStatement addStageST = dbConnection.getConnection().prepareCall("{? = call dbo.insert_Stage(?,?,?,?)}");
			addStageST.registerOutParameter(1, Types.INTEGER);
			addStageST.setNString(2, name);
			addStageST.setNString(3, origin);
			addStageST.setNString(4, music);
			addStageST.setNString(5, gameName);
			addStageST.executeUpdate();
			int returnValue = addStageST.getInt(1);

			if (returnValue == 0) {
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the database.");
			}

			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Stage is already in the database");
			}
			
			if (returnValue == 3) {
				JOptionPane.showMessageDialog(null, "ERROR: Name cannot be left empty");
			}
			
			if (returnValue == 4) {
				JOptionPane.showMessageDialog(null, "ERROR: Game Name cannot be left empty");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in adding the stage to the database.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteStage(String name, String gameName) {
		try {
			CallableStatement deleteStageST = dbConnection.getConnection().prepareCall("{? = call dbo.delete_Stage(?,?)}");
			deleteStageST.registerOutParameter(1, Types.INTEGER);
			deleteStageST.setNString(2, name);
			deleteStageST.setNString(3, gameName);
			deleteStageST.executeUpdate();
			int returnValue = deleteStageST.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The stage was succesfully removed from the database.");
				return true;
			}
			
			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the database.");
			}

			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Stage currently does not exist in the database.");
			}  
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in deleting the stage from the database.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateStages(String name, String origin, String music, String gameName) {
		try {
			CallableStatement updateStageST = dbConnection.getConnection().prepareCall("{? = call dbo.update_Stage(?,?,?,?)}");
			updateStageST.registerOutParameter(1, Types.INTEGER);
			updateStageST.setNString(2, name);
			updateStageST.setNString(3, origin);
			updateStageST.setNString(4, music);
			updateStageST.setNString(5, gameName);
			updateStageST.executeUpdate();
			int returnValue = updateStageST.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The stage was succesfully updated in the database.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the database.");
			}
			
			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Stage currently does not exist in the database.");
			}
			
			if (returnValue == 3) {
				JOptionPane.showMessageDialog(null, "ERROR: Cannot leave Origin and Music empty");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in updating the stage from the database.");
			e.printStackTrace();
		}
		return false;
	}
	
}
