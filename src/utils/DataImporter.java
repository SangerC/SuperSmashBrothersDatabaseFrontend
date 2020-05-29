package utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.DBCellRecord;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import services.CharacterService;
import services.DatabaseConnection;
import services.GameService;
import services.ItemService;
import services.MoveService;
import services.ProfileService;
import services.StageService;
import services.UserServices;

/*
 * This program reads from an Excel file and imports that data into the database.
 */
public class DataImporter {

	public static final String FILE_PATH = "SuperSmashData.xlsx";
	DatabaseConnection dbConnection = new DatabaseConnection();
	StageService stageServices;
	ItemService itemServices;
	CharacterService characterServices;
	GameService gameServices;
	MoveService moveServices;
	UserServices userServices;
	ProfileService profileServices;

	public DataImporter() {
		dbConnection = new DatabaseConnection();
		connect();
		stageServices = new StageService(dbConnection);
		itemServices = new ItemService(dbConnection);
		characterServices = new CharacterService(dbConnection);
		gameServices = new GameService(dbConnection);
		moveServices = new MoveService(dbConnection);
		userServices = new UserServices(dbConnection);
		profileServices = new ProfileService(dbConnection);
	}
	
	public void importGames(Iterator<Row> games) {
		
		while(games.hasNext()) {
			Row row = games.next();
			if(row.getCell(0)==null)break;
			gameServices.addGame(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), 
					             row.getCell(2).getStringCellValue());
		}
		
	}

	public void importCharacters(Iterator<Row> characters) {
		
		while(characters.hasNext()) {
			Row row = characters.next();
			if(row.getCell(0)==null)break;
			characterServices.addCharacter(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), 
					                       row.getCell(2).getStringCellValue(), (int)row.getCell(3).getNumericCellValue(), (int)row.getCell(4).getNumericCellValue());
		}
		
	}
	
	public void importMoves(Iterator<Row> moves) {
		
		while(moves.hasNext()) {
			Row row = moves.next();
			if(row.getCell(0)==null)break;
			moveServices.updateMove(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), 
					                row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(), 
					                String.valueOf((int)row.getCell(4).getNumericCellValue()), String.valueOf((int)row.getCell(5).getNumericCellValue()));
		}
		
	}
	
	public void importItems(Iterator<Row> items) {
		
		while(items.hasNext()) {
			Row row = items.next();
			if(row.getCell(0)==null)break;
			itemServices.addItem(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
					       (int) row.getCell(2).getNumericCellValue(), row.getCell(3).getStringCellValue());
		}
		
	}
	
	public void importStages(Iterator<Row> stages) {
		
		while(stages.hasNext()) {
			Row row = stages.next();
			if(row.getCell(0)==null)break;
			stageServices.addStage(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
			                       row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue());
		}
		
	}
	
	public void importUsers(Iterator<Row> users) {
		
		while(users.hasNext()) {
			Row row = users.next();
			if(row.getCell(0)==null)break;
			userServices.register(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
		}
		
	}
	
	public void importUserFavorites(Iterator<Row> users) {
		
		while(users.hasNext()) {
			Row row = users.next();
			if(row.getCell(0)==null)break;
			profileServices.updateFav(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), row.getCell(2).getStringCellValue());
		}
		
	}
	
	public void importData() throws EncryptedDocumentException, IOException {
		Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));

		// Gets the first sheet
		Sheet theSheet = workbook.getSheetAt(0);

		// Obtain a rowIterator and columnIterator
		Iterator<Row> rowIterator = theSheet.rowIterator();
		
		rowIterator.next();
		importGames(rowIterator);
		
		theSheet = workbook.getSheetAt(1);
		rowIterator = theSheet.rowIterator();
	
		rowIterator.next();
		importCharacters(rowIterator);
		
		theSheet = workbook.getSheetAt(2);
		rowIterator = theSheet.rowIterator();
		
		rowIterator.next();
		importMoves(rowIterator);
		
		theSheet = workbook.getSheetAt(3);
		rowIterator = theSheet.rowIterator();
		
		rowIterator.next();
		importStages(rowIterator);
		
		theSheet = workbook.getSheetAt(4);
		rowIterator = theSheet.rowIterator();
		
		rowIterator.next();
		importItems(rowIterator);
		
		theSheet = workbook.getSheetAt(5);
		rowIterator = theSheet.rowIterator();
		
		rowIterator.next();
		importUsers(rowIterator);
		
		theSheet = workbook.getSheetAt(6);
		rowIterator = theSheet.rowIterator();
		
		rowIterator.next();
		importUserFavorites(rowIterator);
		
		workbook.close();
	}
	

	private void connect() {
		dbConnection.connect(Reader.getAttribute("serverName"), Reader.getAttribute("databaseName"),
				Reader.getAttribute("defaultUsername"), Reader.getAttribute("defaultPassword"));
	}

	public static void main(String[] args) throws IOException, InvalidFormatException {
		DataImporter dataImporter = new DataImporter();
		dataImporter.importData();
		JOptionPane.showMessageDialog(null, "All data has successfully been imported into the Database");
	}

}
