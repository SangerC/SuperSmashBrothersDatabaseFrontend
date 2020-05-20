package utils;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/*
 * This program reads from an Excel file and imports that data into the database.
 */
public class DataImporter {
	public static final String FILE_PATH = "C:\\Users\\scottjm1\\Documents\\CSSE\\CSSE 333\\SuperSmashData.xlsx";

	public static void main(String[] args) throws IOException, InvalidFormatException {

		// Creating a Workbook from an Excel File
		Workbook workbook = WorkbookFactory.create(new File(FILE_PATH));

		// Retrieve the # of sheets in the workbook
		System.out.println("Workbook has " + workbook.getNumberOfSheets() + " Sheets : ");

		// This is who we iterate through all sheets.
		Iterator<Sheet> sheetIterator = workbook.sheetIterator();
		System.out.println("Retrieving Sheets using Iterator");
		Sheet sheet = sheetIterator.next();
		System.out.println("=> " + sheet.getSheetName());

		// Iterating through rows and columns in a sheet

		// Gets the first sheet
		Sheet theSheet = workbook.getSheetAt(0);

		// Create a DataFormatter to format and get each cell's value as String
		DataFormatter dataFormatter = new DataFormatter();

		// Obtain a rowIterator and columnIterator
		System.out.println("\n\nIterating over Rows and Columns using Iterator\n");
		Iterator<Row> rowIterator = theSheet.rowIterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			if (row.getRowNum() > 0) {
//				sodaService.addSoda(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue());
				
//				// Now let's iterate over the columns of the current row
//				Iterator<Cell> cellIterator = row.cellIterator();
//				while (cellIterator.hasNext()) {
//					Cell cell = cellIterator.next();
//					//printCellValue(cell);
//				}
				System.out.println();
			}
			
			// We are not going to use data formatter however.
			/*
			 * while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); String
			 * cellValue = dataFormatter.formatCellValue(cell); System.out.print(cellValue +
			 * "\t"); } System.out.println();
			 */ }

		// Close the workbook

		workbook.close();

	}

	private static void printCellValue(Cell cell) {
		switch (cell.getCellType()){
		case BOOLEAN:
			System.out.print(cell.getBooleanCellValue());
			break;
		case STRING:
			System.out.print(cell.getRichStringCellValue().getString());
			break;
		case NUMERIC:
			if (DateUtil.isCellDateFormatted(cell)) {
				System.out.print(cell.getDateCellValue());
			} else {
				System.out.print(cell.getNumericCellValue());
			}
			break;
		case FORMULA:
			System.out.print(cell.getCellFormula());
			break;
		case BLANK:
			System.out.print("");
			break;
		default:
			System.out.print("");
		}

	    System.out.print("\t");
	}

}

