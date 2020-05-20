package services;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import databaseobjects.GameItem;
import databaseobjects.SelectedItem;
import databaseobjects.SelectedStage;

public class ItemService {

	DatabaseConnection dbConnection;

	public ItemService(DatabaseConnection dbConnection) {
		this.dbConnection=dbConnection;
	}
		public ArrayList<GameItem> getItems(String game) {
			 ArrayList<GameItem> items = new ArrayList<GameItem>();
			 try {
				Statement itemST = dbConnection.getConnection().createStatement();
				String itemQuery = "SELECT * FROM Item WHERE GameName = "+"'"+game+"'";
				ResultSet itemRS = itemST.executeQuery(itemQuery);
				
				int nameIndex = itemRS.findColumn("Name");
				int imageIndex = itemRS.findColumn("Image");
				
				while (itemRS.next()) {
					items.add(new GameItem(itemRS.getString(nameIndex), null));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			return items;
		}
		
		public SelectedItem getItem(String game, String character) {
			 try {
				Statement itemST = dbConnection.getConnection().createStatement();
				String itemQuery = "SELECT * FROM Item WHERE GameName = "+"'"+game+"' and Name= '"+character+"'";
				ResultSet itemRS = itemST.executeQuery(itemQuery);
				
				int nameIndex = itemRS.findColumn("Name");
				int originIndex = itemRS.findColumn("Origin");
				int musicIndex = itemRS.findColumn("Type");
				
				itemRS.next();
				return new SelectedItem(itemRS.getString(nameIndex), itemRS.getString(originIndex),itemRS.getString(musicIndex));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	public boolean addItem(String name, String origin, int type, String gameName) {
		try {
			CallableStatement addItemST = dbConnection.getConnection().prepareCall("{? = call dbo.insert_Item(?,?,?,?)}");
			addItemST.registerOutParameter(1, Types.INTEGER);
			addItemST.setNString(2, name);
			addItemST.setNString(3, origin);
			addItemST.setInt(4, type);
			addItemST.setNString(5, gameName);
			addItemST.executeUpdate();
			int returnValue = addItemST.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The Item was succesfully added to the database.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the database.");
			}

			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Item is already in the database");
			}
			
			if (returnValue == 3) {
				JOptionPane.showMessageDialog(null, "ERROR: Name cannot be left empty");
			}
			
			if (returnValue == 4) {
				JOptionPane.showMessageDialog(null, "ERROR: Game Name cannot be left empty");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in adding the Item to the database.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteItem(String name, String gameName) {
		try {
			CallableStatement deleteItemsST = dbConnection.getConnection().prepareCall("{? = call dbo.delete_Item(?,?)}");
			deleteItemsST.registerOutParameter(1, Types.INTEGER);
			deleteItemsST.setNString(2, name);
			deleteItemsST.setNString(3, gameName);
			deleteItemsST.executeUpdate();
			int returnValue = deleteItemsST.getInt(1);

			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The Item was succesfully removed from the database.");
				return true;
			}
			
			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the database.");
			}

			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Item currently does not exist in the database.");
			}  
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in deleting the Item from the database.");
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateItem(String name, String origin, int type, String gameName) {
		try {
			CallableStatement updateItemST = dbConnection.getConnection().prepareCall("{? = call dbo.update_Item(?,?,?,?)}");
			updateItemST.registerOutParameter(1, Types.INTEGER);
			updateItemST.setNString(2, name);
			updateItemST.setNString(3, origin);
			updateItemST.setInt(4, type);
			updateItemST.setNString(5, gameName);
			updateItemST.executeUpdate();
			int returnValue = updateItemST.getInt(1);
			
			if (returnValue == 0) {
				JOptionPane.showMessageDialog(null, "The Item was succesfully updated in the database.");
				return true;
			}

			if (returnValue == 1) {
				JOptionPane.showMessageDialog(null, "ERROR: Game currently does not exist in the database.");
			}
			
			if (returnValue == 2) {
				JOptionPane.showMessageDialog(null, "ERROR: Item currently does not exist in the database.");
			}
			
			if (returnValue == 3) {
				JOptionPane.showMessageDialog(null, "ERROR: Cannot leave Origin, Projectile, and Type empty");
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Was unsuccessful in updating the Item from the database.");
			e.printStackTrace();
		}
		return false;
	}
	

}
