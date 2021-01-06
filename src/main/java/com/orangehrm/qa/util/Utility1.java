package com.orangehrm.qa.util;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.orangehrm.qa.base.Main_Base;

public class Utility1 extends Main_Base implements ITestListener
	{

		public void onFinish(ITestContext arg0)
			{

			}

		public void onStart(ITestResult result)
			{

			}

		public void onTestFailedButWithinSuccessPercentage(ITestResult arg0)
			{
				// TODO Auto-generated method stub

			}

		public void onTestFailure(ITestResult result)
			{
				log.info(result.getMethod().getMethodName() + " failed");
				//takeScreenshot(result.getStatus(),result.getMethod().getMethodName());
				takeScreenshot(result.getMethod().getMethodName());

			}

		public void onTestSkipped(ITestResult arg0)
			{
				// TODO Auto-generated method stub

			}

		public void onTestStart(ITestResult arg0)
			{
				log.info("Started test named : " + arg0.getMethod().getMethodName());

			}

		public void onTestSuccess(ITestResult arg0)
			{
				// TODO Auto-generated method stub

			}

		@Override
		public void onStart(ITestContext context)
			{
				// TODO Auto-generated method stub

			}

	}
