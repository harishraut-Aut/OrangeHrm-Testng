package com.orangehrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.Main_Base;
import com.orangehrm.qa.pages.HomePage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.util.CommonUtilities;
import com.orangehrm.qa.util.Utility1;

@Listeners(Utility1.class)
public class Ddtests extends Main_Base
	{
		LoginPage lp;
		HomePage hp;

		public Ddtests()
			{
				super();
			}

		//if you have 100 test classes 
		//you cant just right 100 separate dataproviders classes for them so here is wat i found
		CommonUtilities cu = new CommonUtilities();
		//our extractor method is coming from this class
		//this excelpath you can put in properties file also 
		//which is 
		//your object repo :
		String excelfilepath = "D:\\Classwork\\OrangeHRM_Test_V2\\src\\main\\java\\com\\orangehrm\\qa\\testdata\\logindata.xlsx";

		//change sheetname for diffrent test classes and test data
		String Sheetname = "loginpg";

		@BeforeMethod
		public void init2()
			{
				log.info("==================================================================");
				lp = new LoginPage();
				hp = new HomePage();
			}

		@DataProvider(name = "test2data")
		public Object[][] getdata()
			{
				//	String excelPath = "D:\\Classwork\\OrangeHRMTest\\src\\main\\java\\com\\orangehrm\\qa\\testdata\\OrangeHRM_DD_approach.xlsx";
				//	String sheetName = "";
				Object data[][] = testdata(excelfilepath, Sheetname);
				return data;
			}

		private Object[][] testdata(String excelfilepath, String Sheetname)
			{
				log.info("fetching data..");
				return cu.extractor(excelfilepath, Sheetname);

			}

		@Test(dataProvider = "test2data") //if your data provider coming from same class 
		//no need to pass dtaproviderclass  parameter
		public void temp(String usernames, String passwords) throws InterruptedException
			{
				log.info("Entering valid invalid credentials..");

				lp.clearUsernamefield().enterUserName(usernames).clearPasswordfield().enterPassword(passwords)
						.clickonLoginBtn();
				Thread.sleep(1000);

				try
					{
						boolean flag = hp.verifyWelcomebutton();
						Assert.assertTrue(flag);
						hp.Logout();
						log.info("Credentials Matched logging out..");
					}
				catch (Exception e)
					{
						log.info("unmatched credentials failing..");
						System.out.println(e.getMessage());
					}

				//				System.out.println(usernames + " " + passwords);
			}

	}
