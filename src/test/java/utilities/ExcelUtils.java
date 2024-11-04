package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    /***
     * This is a utility for reading from writing to excel files.
     *
     */


    public static FileInputStream fi;
    public static FileOutputStream fo;
    private static XSSFWorkbook workbook;
    private static XSSFSheet sheet;
    private static XSSFRow row;
    public static XSSFCell cell;
    public static CellStyle style;
    private static String path;

    public ExcelUtils(String path, String sheetName) {
        this.path = path;
        try {
            // Open the Excel file
            fi = new FileInputStream(path);
            workbook = new XSSFWorkbook(fi);
            sheet = workbook.getSheet(sheetName);
            // check if sheet is null or not. null means  sheetname was wrong
            Assert.assertNotNull(sheet, "Sheet: \"" + sheetName + "\" does not exist\n");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getCellData(int rowNum, int colNum) {
        Cell cell;
        try {
            cell = sheet.getRow(rowNum).getCell(colNum);
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // get all the data into 2D array
    public String[][] getDataArray() {

        String[][] data = new String[rowCount()][columnCount()];

        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i][j] = value;
            }
        }
        return data;

    }

    //this method will return data table as 2d array
    //so we need this format because of data provider.
    public String[][] getDataArrayWithoutFirstRow() {

        String[][] data = new String[rowCount() - 1][columnCount()];

        for (int i = 1; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i, j);
                data[i - 1][j] = value;
            }
        }
        return data;

    }

    public List<Map<String, String>> getDataList() {
        // get all columns
        List<String> columns = getColumnsNames();
        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();

        for (int i = 1; i < rowCount(); i++) {
            // get each row
            Row row = sheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value --> map bvalue
            Map<String, String> rowMap = new HashMap<String, String>();
            for (Cell cell : row) {
                int columnIndex = cell.getColumnIndex();
                rowMap.put(columns.get(columnIndex), cell.toString());
            }

            data.add(rowMap);
        }

        return data;
    }


    public List<String> getColumnsNames() {
        List<String> columns = new ArrayList<>();

        for (Cell cell : sheet.getRow(0)) {
            columns.add(cell.toString());
        }
        return columns;
    }

    public void setCellData(int rowNum, int colNum, String value) {

        try {
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            if (cell == null) {
                cell = row.createCell(colNum);
                cell.setCellValue(value);
            } else {
                cell.setCellValue(value);
            }
            fo = new FileOutputStream(path);
            workbook.write(fo);
            workbook.close();
            fo.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setCellData(String columnName, int row, String value) {
        int column = getColumnsNames().indexOf(columnName);
        setCellData(row, column, value);
    }


    public void fillCellGreen(int rowNum, int colNum) {

        try {
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);
            fo = new FileOutputStream(path);
            workbook.write(fo);
            workbook.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void fillCellRed(int rowNum, int colNum) {

        try {
            row = sheet.getRow(rowNum);
            cell = row.getCell(colNum);

            style = workbook.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            cell.setCellStyle(style);
            fo = new FileOutputStream(path);
            workbook.write(fo);
            workbook.close();
            fo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public int columnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    public int rowCount() {
        return sheet.getLastRowNum() + 1;
    }


}
