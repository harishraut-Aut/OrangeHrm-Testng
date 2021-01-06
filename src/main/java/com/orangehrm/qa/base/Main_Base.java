package com.orangehrm.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class Main_Base
	{
		public static WebDriver driver;
		public static Properties prop;
		public static Logger log;

		public Main_Base()
			{

				//Sometimes mistakenly you will type System.getproperty to get values from properties file 
				//use prop object like 				 prop.getProperty
				try
					{
						prop = new Properties();
						FileInputStream inStream = new FileInputStream(
								"D:\\Classwork\\OrangeHRM_Test_V2\\src\\main\\java\\com\\orangehrm\\qa\\config\\config.properties");
						prop.load(inStream);
					}
				catch (FileNotFoundException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}

		@BeforeClass
		public static void init()
			{
				log = Logger.getLogger(Main_Base.class);
				String browsername = prop.getProperty("browser");
				System.out.println(browsername);
				if (browsername.equalsIgnoreCase("chrome"))
					{
						System.setProperty("webdriver.chrome.driver",
								"C:\\Users\\admin\\Desktop\\Selessentials\\chromedriver.exe");
						driver = new ChromeDriver();

					}

				else if (browsername.equalsIgnoreCase("ff"))
					{
						System.setProperty("webdriver.chrome.driver",
								System.getProperty("user.dir") + "/Drivers/geckodriver.exe");
						driver = new FirefoxDriver();
					}

				else if (browsername.equalsIgnoreCase("ie"))
					{
						System.setProperty("webdriver.chrome.driver",
								System.getProperty("user.dir") + "/Drivers/IEDriverServer.exe");
						driver = new InternetExplorerDriver();
					}
				else if (browsername.isEmpty())
					{
						System.out.println("Give a proper Browser Name ");
					}
				log.info("browser started :");
				driver.get(prop.getProperty("baseURL"));

			}

		public static void takeScreenshot(String methodName)
			{
				SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss a");
				Date d = new Date();
				String ampm = sd.format(d);
				String dateappend = ampm.toString().replaceAll(":", "_").replaceAll(" ", "_");

				File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
				try
					{
						FileUtils.copyFile(src, new File("D:\\Classwork\\OrangeHRM_Test_V2\\Screenshots\\failed\\"
								+ dateappend + "_" + methodName + ".png"));
					}
				catch (IOException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}

		@AfterClass
		public static void teardown()
			{

				driver.close();

			}

	}
