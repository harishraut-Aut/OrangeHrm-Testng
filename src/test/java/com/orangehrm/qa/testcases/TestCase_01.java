package com.orangehrm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.orangehrm.qa.base.Main_Base;
import com.orangehrm.qa.pages.HomePage;
import com.orangehrm.qa.pages.LoginPage;
import com.orangehrm.qa.util.Utility1;

@Listeners(Utility1.class)
public class TestCase_01 extends Main_Base
	{
		LoginPage lp;
		HomePage hp;

		public TestCase_01()
			{
				super();//to call parent class constructor first so prop file will get loaded 
			}

		@BeforeMethod
		void throwq()
			{
				lp = new LoginPage();//this if you
				hp = new HomePage();
			}

		@Test
		public void ValidLogin()
			{
				log.info("Checking credentials");

				lp.enterUserName(prop.getProperty("username")).enterPassword(prop.getProperty("password"))
						.clickonLoginBtn();
				boolean flag = hp.verifyWelcomebutton();
				//this is why you should return this so that you can do method chaining 
				//	once you are on homepage controll will be on homepages objects 
				if (flag == true)
					{
						log.info("User logged in");
					}
				else
					{
						log.info("Invalid credentials ");
					}
				Assert.assertTrue(flag);
				//				log.info("LoginPage Opened :");
				//				lp.enterUserName(prop.getProperty("username"));
				//				lp.enterPassword(prop.getProperty("password"));
				//				hp = lp.clickonLoginBtn();//welcome button is on homepage ok so 
				//				//on hitting login button we are landing onto homepage so
				//				//again through hopepages object (same) we can access homepages methods aswell 
				//				boolean flag = hp.verifyWelcomebutton();
				//				Assert.assertTrue(flag);
			}

	}
