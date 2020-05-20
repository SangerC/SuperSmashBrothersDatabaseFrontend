package utils;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.record.DBCellRecord;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import services.DatabaseConnection;
import services.ItemService;
import services.StageService;

/*
 * This program reads from an Excel file and imports that data into the database.
 */
public class DataImporter {

	public static final String FILE_PATH = "C:\\Users\\scottjm1\\Documents\\CSSE\\CSSE 333\\SuperSmashData.xlsx";
	DatabaseConnection dbConnection = new DatabaseConnection();
	StageService stageServices;
	ItemService itemServices;

	public DataImporter() {
		dbConnection = new DatabaseConnection();
		connect();
		stageServices = new StageService(dbConnection);
		itemServices = new ItemService(dbConnection);
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
					System.out.println("Used for Characters");
					break;
					
				case 1:
					System.out.println("Used for Characters");
					break;
					
				case 2:
					System.out.println("Used for Characters");
					break;
					
				case 3:
					System.out.println("Used for Characters");
					break;
					
				case 4:
					stageServices.addStage(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							row.getCell(2).getStringCellValue(), row.getCell(3).getStringCellValue());
					break;
				case 5:
					itemServices.addItem(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(),
							(int)row.getCell(2).getNumericCellValue(), row.getCell(3).getStringCellValue());
					break;
				default:
					System.out.print("No clear sheet picked. Stopping Import");
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
		for (int i = 0; i < 6; i++) {
			dataImporter.dataimport(i);
		}
		System.out.println("All data has successfully been imported into the Database");
	}

}
