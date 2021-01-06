package com.orangehrm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.orangehrm.qa.base.Main_Base;

public class LoginPage extends Main_Base
	{
		HomePage hp;
		// Page Factory - OR:
		@FindBy(id = "txtUsername")
		WebElement username;

		@FindBy(name = "txtPassword")
		WebElement password;

		@FindBy(xpath = "//input[@id='btnLogin']")
		WebElement loginBtn;

		@FindBy(xpath = "//span[@id='spanMessage']")
		WebElement invalidcredentialmsg;

		@FindBy(tagName = "img")
		WebElement Ohrmlogo;

		@FindBy(xpath = "//a[contains(text(),'Forgot your password?')]")
		WebElement forgotpasswordlink;
		// Initializing the Page Objects:

		public LoginPage()
			{
				PageFactory.initElements(driver, this);
			}

		// Actions:
		public LoginPage clearUsernamefield()
			{
				username.clear();
				return this;
			}

		public LoginPage clearPasswordfield()
			{
				password.clear();
				return this;
			}

		public HomePage clickonLoginBtn()
			{
				loginBtn.click();
				return new HomePage();
			}

		//		public void VerifLogindetails()
		//			{
		//				//				boolean isPresent = driver.findElements(By.xpath("//a[@id='welcome']")).size() > 0;
		//				//				if (isPresent == true)
		//				//					{
		//				//						hp.wecomebtn.click();
		//				//						hp.logoutbtn.click();
		//				//						System.out.println("password matched");
		//				//
		//				//					}
		//				//				else
		//				//					{
		//				//						System.out.println("Pasword not matched");
		//				//						driver.close();
		//				//					}
		//				String Actualurl = driver.getCurrentUrl();
		//				String Expectedurl = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
		//				if (Actualurl.equalsIgnoreCase(Expectedurl))
		//					{
		//						System.out.println("password matched");
		//						driver.findElement(By.xpath("//a[@id='welcome']")).click();
		//						driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		//
		//					}
		//				else
		//					{
		//
		//						System.out.println("Pasword not matched");
		//
		//					}
		//			}

		public LoginPage enterUserName(String username)
			{
				this.username.sendKeys(username);
				return this;
			}

		public LoginPage enterPassword(String password)
			{
				this.password.sendKeys(password);
				return this;
			}

		public HomePage login(String un, String pwd)
			{
				username.sendKeys(un);
				password.sendKeys(pwd);
				loginBtn.click();
				return new HomePage();
			}

		public boolean verify_logo()
			{
				return Ohrmlogo.isDisplayed();
			}

		public boolean verify_invalidmsg()
			{
				return invalidcredentialmsg.isDisplayed();
			}

		public boolean Forgotpasswordlink()
			{
				forgotpasswordlink.click();
				String currenturl = driver.getCurrentUrl();
				String expectedurl = "https://opensource-demo.orangehrmlive.com/index.php/auth/requestPasswordResetCode";
				Assert.assertEquals(currenturl, expectedurl);
				return true;
			}

		public String verify_title()
			{

				return driver.getTitle();

			}

	}
