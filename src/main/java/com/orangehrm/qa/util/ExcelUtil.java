package com.orangehrm.qa.util;

import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil
	{
		static XSSFWorkbook workbook;
		static XSSFSheet sheet;

		///whenever we will create object of this class we have to give excelpath and sheetname from there 
		public ExcelUtil(String Excelpath, String Sheetname)
			{
				try
					{
						//there is constructor in XSSFWorkbook class which accepts this excelpath 
						workbook = new XSSFWorkbook(Excelpath);
						//now give which sheet to read from workbook we can get that 
						sheet = workbook.getSheet(Sheetname);
					}
				catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}

		public int getRowcount()
			{
				int rows = 0;
				rows = sheet.getPhysicalNumberOfRows();
				return rows;

			}

		public int getColcount()
			{
				//				there isnt any method to count columns directly but 1 trick is there 
				//			  	first get the 0th row and then get physicalnumber of cell present in that particular row
				//			  	|0th row|	|	|	|	//
				//			  	|cell 1	| 2	| 3	| 4	|
				//			  	|		|	|	|	|
				//			  

				int cols = 0;
				cols = sheet.getRow(0).getPhysicalNumberOfCells();
				return cols;

			}

		public String getCelldata(int rowNum, int colNum)
			{
				String data = null;
				data = sheet.getRow(rowNum).getCell(colNum).getStringCellValue();
				return data;

			}
	}
