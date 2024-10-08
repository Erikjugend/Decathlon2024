package excel;

import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelPrinter {

	private XSSFWorkbook workbook;
	private String excelName;

	public ExcelPrinter(String name) throws IOException {
		workbook = new XSSFWorkbook();
		excelName = name;
	}

	public void add(Object[][] data, String sheetName) {

		XSSFSheet sheet = workbook.createSheet(sheetName);

		int rowCount = 0;

		for (Object[] aBook : data) {
			Row row = sheet.createRow(rowCount);
			rowCount++;
			int columnCount = 0;

			for (Object field : aBook) {
				Cell cell = row.createCell(columnCount);
				columnCount++;
				
				if (field instanceof String) {
					cell.setCellValue((String) field);
					
				} else if (field instanceof Integer) {
					cell.setCellValue((Integer) field);
					
				} else if (field instanceof Double) {
					cell.setCellValue((Double) field);

				}
			}
		}
	}

	public void write() throws IOException {
		// Create the folder if it doesn't exist
		File directory = new File("C:/Eclipse");
		if (!directory.exists()) {
			directory.mkdirs();  // Create the directory
		}

		File excelFile = new File(directory + "/resultat_" + excelName + ".xlsx");
		FileOutputStream out = new FileOutputStream(excelFile);

		workbook.write(out);
		workbook.close();
		out.close();  // Close the output stream after writing

		// Open the Excel file using the system default application
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (excelFile.exists()) {
				desktop.open(excelFile);  // Open the file
			}
		}
	}

}
