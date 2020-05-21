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
import services.StageService;

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

	public DataImporter() {
		dbConnection = new DatabaseConnection();
		connect();
		stageServices = new StageService(dbConnection);
		itemServices = new ItemService(dbConnection);
		characterServices = new CharacterService(dbConnection);
		gameServices = new GameService(dbConnection);
		moveServices = new MoveService(dbConnection);

	}

	public void dataimport(int i) throws EncryptedDocumentException, IOException {
		// // Creating a Workbook from an Excel File
		Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));

		// Gets the first sheet
		Sheet theSheet = workbook.getSheetAt(i);

		// Obtain a rowIterator and columnIterator
		Iterator<Row> rowIterator = theSheet.rowIterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() > 0) {
				switch (i) {
				case 0:
					gameServices.addGame(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue());
					break;

				case 1:
					characterServices.addCharacter(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(), 2, 2);
					break;

				case 2:
					moveServices.updateMove(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue(),
							row.getCell(4).getStringCellValue(), row.getCell(5).getStringCellValue());
					break;

				case 3:
					stageServices.addStage(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue());
					break;
					
				case 4:
					itemServices.addItem(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							(int) row.getCell(2).getNumericCellValue(), row.getCell(3).getStringCellValue());
					break;
					
				default:
					JOptionPane.showMessageDialog(null, "ERROR: No sheet picked. Stopping Import");
					return;
				}
			}
		}
		// Close the workbook
		workbook.close();
	}

	private void connect() {
		dbConnection.connect(Reader.getAttribute("serverName"), Reader.getAttribute("databaseName"),
				Reader.getAttribute("defaultUsername"), Reader.getAttribute("defaultPassword"));
	}

	public static void main(String[] args) throws IOException, InvalidFormatException {
		DataImporter dataImporter = new DataImporter();
		for (int i = 3; i < 5; i++) {
			dataImporter.dataimport(i);
		}
		JOptionPane.showMessageDialog(null, "All data has successfully been imported into the Database");
	}

}
