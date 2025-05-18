package com.qa.neworbit.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.neworbit.base.Basetest;
import com.qa.neworbit.constants.AppConstans;

public class LoginPageTest extends Basetest {
		
	@Test
	public void Title() {
		//System.out.println("testint");
		String actualtitle = loginpage.getloginPageTitle();
		System.out.println("Actual title of login page : " + actualtitle);
		Assert.assertEquals(actualtitle, AppConstans.LOGIN_PAGE_TITLE);
	}
	
	@Test
	public void Testlogin() {
		 Assert.assertTrue(loginpage.dologin(prop.getProperty("email"), prop.getProperty("password")));
	}
}

