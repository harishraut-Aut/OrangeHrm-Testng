package com.orangehrm.qa.util;

public class CommonUtilities
	{

		//////program to extract data from Excel Files 
		//"D:\\Classwork\\OrangeHRM_Test_V2\\src\\main\\java\\com\\orangehrm\\qa\\testdata\\logindata.xlsx",
		//						"Sheet1"

		String excelfilepath = "D:\\Classwork\\OrangeHRM_Test_V2\\src\\main\\java\\com\\orangehrm\\qa\\testdata\\logindata.xlsx";
		String Sheetname = "Sheet1";

		public Object[][] extractor(String excelfilepath, String Sheetname)
			{
				//if there are 5 rows 1st must be for columnnames so 
				//we have to neglect 

				ExcelUtil eu = new ExcelUtil(excelfilepath, Sheetname);
				int rows = eu.getRowcount();

				int cols = eu.getColcount();
				Object data[][] = new Object[rows - 1][cols];
				//row starts from 1 ok 0th row we dont consider
				//starting loop from 1st row 

				//			 col 
				//		  __|_ 	  0	  		 1  	  2   3  
				//		  0 | username	|password	|	|	|	//0th row not conisdering 
				//		  1	|			|			|	|	|	//1st row 
				//		  2	|			|			|	|	|	
				//		  3	|			|			|	|	|
				//		  4	|			|			|	|	|

				//if you want data of position 1st row 0th column
				//now we want data like this 
				//				1st row 0th column then
				//				1st row 1st column then
				//				1st row 2nd column 
				//so we have to keep 1 for loop at start to get data 

				////if there are 5 rows it will return rows as 5 but 
				//our rows in excel starts from 1st position and we are interested in rows from 
				//1 to 4 so we have to write for loop like this only
				for (int i = 1; i < rows; i++)
					{
						//now inside we have to keep 1 for loop to change this column number 
						// 1 (i) will remain constant and from 1st row to as many columns
						for (int j = 0; j < cols; j++)
							{
								String maindata = eu.getCelldata(i, j);
								//	System.out.println(maindata);
								//store this maindata 1 by 1 to data object array
								data[i - 1][j] = maindata;
							}

					}
				//String s=eu.getCelldata(1, 0);

				return data;

			}

	}
