package com.orangehrm.qa.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporting extends TestListenerAdapter
	{
		/*
		 * Open Declaration org.testng.TestListenerAdapter
		 * 
		 * 
		 * A simple ITestListener adapter that stores all the tests that were run. You
		 * can retrieve these results with the following methods: getPassedTests()
		 * getFailedTests() getSkippedTests() If you extend this class in order to
		 * override any of these methods, remember to call their super equivalent if you
		 * want this list of tests to be maintained.
		 * 
		 * 
		 */
		public ExtentHtmlReporter htmlReporter;
		public ExtentReports extent;
		public ExtentTest logger;

		public void onStart(ITestContext testContext)
			{
				//Short way to get date like we did in main_base
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());//have added dots instead of replacing again and again
				String repName = "Ext-Report-" + timeStamp + ".html";

				htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/" + repName);//specifying location of the report
				htmlReporter.loadXMLConfig(System.getProperty("user.dir") + "/extent-config.xml");

				extent = new ExtentReports();

				extent.attachReporter(htmlReporter);
				extent.setSystemInfo("Host name", "localhost");
				extent.setSystemInfo("Environemnt", "QA");
				extent.setSystemInfo("user", "Harish");

				htmlReporter.config().setDocumentTitle("OrangeHRM Test Project"); // Tile of report
				htmlReporter.config().setReportName("Functional Test Automation Report"); // name of the report
				htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //location of the chart
				htmlReporter.config().setTheme(Theme.DARK);
			}

		public void onTestSuccess(ITestResult tr)
			{
				logger = extent.createTest(tr.getName()); // create new entry in th report
				logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed information to the report with GREEN color highlighted
			}

		public void onTestFailure(ITestResult tr)
			{
				logger = extent.createTest(tr.getName()); // create new entry in th report
				logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information to the report with GREEN color highlighted

			}

		public void onTestSkipped(ITestResult tr)
			{
				logger = extent.createTest(tr.getName()); // create new entry in th report
				logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));//setting orange colour for skipped tests
			}

		public void onFinish(ITestContext testContext)
			{
				extent.flush();//when all tests finished simple we will call this flush method to generate reports..
			}

	}
