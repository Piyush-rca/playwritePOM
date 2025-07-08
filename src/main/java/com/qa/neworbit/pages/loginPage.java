package com.qa.neworbit.pages;

import java.util.Properties;

import com.microsoft.playwright.Page;

public class loginPage {

	Page page;
	Properties prop;

	//1 String locators- OR

	//String login = "(//button[@type='button'])[1]";
	String email = "//input[@id='login_form_email']";
	String passwrd = "//input[@id='login_form_password']";
	String login = "//button[span[text()='Login']]";
			
//	String email = "//input[@name='uidField']";
//	String passwrd = "//input[@id='ak-stage-password-input']";;
//	String login = "//button[@type='submit']";
	String search = "//button[@type='submit']";

	
	//2. constructor of login class
	public loginPage(Page page){
		this.page=page;
	}

	//3. page action/methods:
	public String getloginPageTitle() {
		return page.title();
	}

	public boolean dologin(String UserName, String Password){
		System.out.println("Login Credential: " + UserName + " : " + Password);
		//page.click(login);
		page.fill(email, UserName);
		page.fill(passwrd, Password);
		page.click(login);;
		//page.click(search);
		//page.waitForTimeout(20000);
		page.click(search);
		if (page.isVisible(search)) {
			System.out.println("You are logged in successfully....");
			return true;
		}
		else {
			System.out.println("Please provide a correct credentials");
			return false;
		}
	}
	
	public DashboardPage navigatetodashboard(String UserName, String Password){
			System.out.println("Login Credential: " + UserName + " : " + Password);
			//page.click(login);
			page.fill(email, UserName);
			//page.click(passwrd);
			page.fill(passwrd, Password);
			page.click(login);
						
			return new DashboardPage(page); 
	}
}
