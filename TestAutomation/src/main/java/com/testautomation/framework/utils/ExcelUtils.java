package com.testautomation.framework.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @Author Bharath
 * @Date 19-March-2018
 * @Updated 23-Oct-2018
 */
public class ExcelUtils
{
	private static XSSFSheet xlsxWorkSheet;
	private static XSSFWorkbook xlsxWorkBook;
	private static XSSFCell xlsxCell;
	@SuppressWarnings("unused")
	private static XSSFRow xlsxRow;
	public static int obtainedRow=0;

	private static HSSFSheet xlsWorkSheet;
	private static HSSFWorkbook xlsWorkBook;
	private static HSSFCell xlsCell;
	@SuppressWarnings("unused")
	private static HSSFRow xlsRow;

	public static String cellTextVal = null;
	public static String cellIntegerVal = null;
	public static int colIndex = 0;
	public static String excel_Path;
	public static FileInputStream strInputdataExtractorFile = null;
	public static XSSFWorkbook objdataExtractorWKB = null;
	public static XSSFSheet objdataExtractorSheet = null;
	public static Row objdataExtractorRow = null;
	private static String sheetName = null;

	/** To get the Excel-XLSX File with Path and SheetName */
	public static void getExcelFile(String Path,String SheetName)
	{
		try
		{
			File file = new File(Path);
			if(file.getAbsolutePath().endsWith(".xlsx"))
			{
				FileInputStream fis = new FileInputStream(file);
				xlsxWorkBook = new XSSFWorkbook(fis);
				xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);
			}
			else if(file.getAbsolutePath().endsWith(".xls"))
			{
				FileInputStream fis = new FileInputStream(file);
				xlsWorkBook = new HSSFWorkbook(fis);
				xlsWorkSheet = xlsWorkBook.getSheet(SheetName);
			}

			excel_Path = Path;
			sheetName= SheetName;

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}


	/** To Return the Excel-XLSX Values given Path to the File and Sheet Name */
	public static Object[][] getTableArray(String FilePath, String SheetName) throws Exception
	{
		Object[][] tabArray = null;
		try
		{
			File file = new File(FilePath);
			if(file.getAbsolutePath().endsWith(".xlsx"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsxWorkBook = new XSSFWorkbook(ExcelFile);
				xlsxWorkSheet = xlsxWorkBook.getSheet(SheetName);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = ExcelUtils.xlsxRowCount();
				int totalCols = ExcelUtils.xlsxColumnCount();
				tabArray=new Object[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLSX(i,j);
						cj++;
					}
					ci++;
				}
			}
			else if(file.getAbsolutePath().endsWith(".xls"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsWorkBook = new HSSFWorkbook(ExcelFile);
				xlsWorkSheet = xlsWorkBook.getSheet(SheetName);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = ExcelUtils.xlsRowCount();
				int totalCols = ExcelUtils.xlsColumnCount();
				tabArray=new Object[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLS(i,j);
						cj++;
					}
					ci++;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			throw new Exception("Could not Find the Excel File/Sheet");
		}
		catch (Exception e)
		{
			throw new Exception("Could not Open the Excel File");
		}
		return(tabArray);
	}


	/** To Return the Excel-XLSX Values given Path to the File */
	public static Object[][] getTableArray(String FilePath) throws Exception
	{
		Object[][] tabArray = null;
		try
		{
			File file = new File(FilePath);
			if(file.getAbsolutePath().endsWith(".xlsx"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsxWorkBook = new XSSFWorkbook(ExcelFile);
				xlsxWorkSheet = xlsxWorkBook.getSheetAt(0);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = ExcelUtils.xlsxRowCount();
				int totalCols = ExcelUtils.xlsxColumnCount();
				tabArray=new Object[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLSX(i,j);
						cj++;
					}
					ci++;
				}
			}
			else if(file.getAbsolutePath().endsWith(".xls"))
			{
				FileInputStream ExcelFile = new FileInputStream(file);
				xlsWorkBook = new HSSFWorkbook(ExcelFile);
				xlsWorkSheet = xlsWorkBook.getSheetAt(0);

				int startRow = 1;
				int startCol = 0;
				int ci,cj;
				int totalRows = ExcelUtils.xlsRowCount();
				int totalCols = ExcelUtils.xlsColumnCount();
				tabArray=new Object[totalRows-1][totalCols];
				ci=0;
				for (int i=startRow;i<totalRows;i++)
				{
					cj=0;
					for (int j=startCol;j<totalCols;j++)
					{
						tabArray[ci][cj]=getCellData_XLS(i,j);
						cj++;
					}
					ci++;
				}
			}
		}
		catch (FileNotFoundException e)
		{
			throw new Exception("Could not Find the Excel File/Sheet");
		}
		catch (Exception e)
		{
			throw new Exception("Could not Open the Excel File");
		}
		return(tabArray);
	}




	/** To get cell data from Excel-XLSX */
	public static String getCellData_XLSX(int RowNum, int ColNum) throws Exception
	{
		String CellData = null;
		try
		{
			xlsxCell = xlsxWorkSheet.getRow(RowNum).getCell(ColNum);
			if(xlsxCell.getCellType() == Cell.CELL_TYPE_STRING )
			{
				String stringCellData = xlsxCell.getStringCellValue();
				CellData = stringCellData;
			}
			if(xlsxCell.getCellType() == Cell.CELL_TYPE_FORMULA )
			{
				String stringCellData = xlsxCell.getRawValue();
				CellData = stringCellData;
			}

			if(xlsxCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
			{
				double numericCellData =  xlsxCell.getNumericCellValue();
				CellData = String.valueOf(numericCellData);

			}
			else if(xlsxCell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
			{
				boolean booleanCellData =  xlsxCell.getBooleanCellValue();
				CellData = String.valueOf(booleanCellData);
			}
			return CellData;
		}
		catch (Exception e)
		{
			return"";
		}
	}

	/** To get cell data from Excel-XLS */
	public static String getCellData_XLS(int RowNum, int ColNum) throws Exception
	{
		String CellData = null;
		try
		{
			xlsCell = xlsWorkSheet.getRow(RowNum).getCell(ColNum);
			if(xlsCell.getCellType() == Cell.CELL_TYPE_STRING )
			{
				String stringCellData = xlsCell.getStringCellValue();
				CellData = stringCellData;
			}
			/*		else if(xlsCell.getCellType() == Cell.CELL_TYPE_NUMERIC)
			{
				double numericCellData =  xlsCell.getNumericCellValue();
				CellData = numericCellData;
			}
			else if(xlsCell.getCellType() == Cell.CELL_TYPE_BOOLEAN)
			{
				boolean booleanCellData =  xlsCell.getBooleanCellValue();
				CellData = booleanCellData;
			}	*/
			return CellData;
		}
		catch (Exception e)
		{
			return"";
		}
	}

	/** To get Excel-XLSX Row Count */
	public static int xlsxRowCount()
	{
		int rowNum = xlsxWorkSheet.getLastRowNum()+1;
		return rowNum;
	}
	public static int getrowIndex(String rowVal,String colName) throws Exception{

		int rows = xlsxRowCount();

		String cellTextVal;
		//while (rows.hasNext()) {
		for (int i=1;i<=rows;i++){

			cellTextVal = getCellData_XLSX(i,colName);

			if (cellTextVal.equals(rowVal)) {
				obtainedRow = i;
				break;
			}

		}
		return obtainedRow;
	}

	/** To get Excel-XLS Row Count */
	public static int xlsRowCount()
	{
		int rowNum = xlsWorkSheet.getLastRowNum()+1;
		return rowNum;
	}

	/** To get Excel-XLSX Column Count */
	public static int xlsxColumnCount()
	{
		int rowNum = xlsxWorkSheet.getRow(0).getLastCellNum();
		return rowNum;
	}

	/** To get cell data from Excel-XLS */
	public static String getCellData_XLSX(int RowNum, String ColName) throws Exception{
		return getCellData_XLSX(RowNum,getcolIndex(ColName));
	}
	/** To get Excel-XLS Column Count */
	public static int xlsColumnCount()
	{
		int rowNum = xlsWorkSheet.getRow(0).getLastCellNum();
		return rowNum;
	}
	/** To get Excel-XLXS Column Index */
	public static int getcolIndex(String colName)throws Exception{
		ArrayList<Row> testscriptColumnList = extractTestScriptColumns();
		Iterator coulmns = testscriptColumnList.iterator();
		int breakVal=0;
		while (coulmns.hasNext()) {
			XSSFRow currentRow = (XSSFRow) coulmns.next();
			Iterator cells = currentRow.cellIterator();
			while (cells.hasNext()) {
				// rowIncrementer=0;
				breakVal++;
				XSSFCell cell = (XSSFCell) cells.next();
				cellTextVal = cell.getRichStringCellValue().toString();
				// objectArrayList.add(cellTextVal);

				if(cellTextVal.equals(colName)) {
					colIndex = breakVal;
					break;
				}
			}
			if(colIndex>0)
				break;
		}
		colIndex = colIndex - 1;
		return colIndex;
	}

	public static ArrayList<Row> extractTestScriptColumns() throws Exception {
		//Path to Excel filename
		File strExecutiondataExtractorFile = new File(excel_Path);

		strInputdataExtractorFile = new FileInputStream(strExecutiondataExtractorFile);
		objdataExtractorWKB = new XSSFWorkbook(strInputdataExtractorFile);
		objdataExtractorSheet = objdataExtractorWKB.getSheet(sheetName);

		ArrayList<Row> targetTestScriptColumns = new ArrayList<Row>();
		objdataExtractorRow = objdataExtractorSheet.getRow(0);
		targetTestScriptColumns.add(objdataExtractorRow);
		return targetTestScriptColumns;
	}
}