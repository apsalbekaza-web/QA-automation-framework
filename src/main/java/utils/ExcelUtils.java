package utils;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static Workbook workbook;
    private static Sheet sheet;

    // Load Excel file . Im creating a function to get our Excel file
    public static void loadExcel(String filePath, String sheetName) throws IOException {

        FileInputStream file = new FileInputStream(filePath); // use FileInputStream to get file path. it may throw
                                                              // FileNotFoundException if the file path is invalid.
        workbook = new XSSFWorkbook(file); // for .xlsx
        // workbook = new HSSFWorkbook(file); // for .xls
        sheet = workbook.getSheet(sheetName);
    }

    // Get Cell data as String
    public static String getCellData(int row, int column) {

        Cell cell = sheet.getRow(row).getCell(column);
        if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            return String.valueOf((int) cell.getNumericCellValue()); // if cell is intigers then convert to string. use
                                              
        }
        return ""; // else return null string
    }

    // Get total rows
    public static int getRowCount() {
        return sheet.getPhysicalNumberOfRows();
    }

    // Close workbook
    public static void closeExcel() throws IOException {
        workbook.close();
    }
}
