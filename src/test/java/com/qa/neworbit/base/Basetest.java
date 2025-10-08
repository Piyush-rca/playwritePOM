package com.qa.neworbit.base;

import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.neworbit.factory.PlaywrightFactory;
import com.qa.neworbit.pages.DashboardPage;
import com.qa.neworbit.pages.HotelAlldetailPage;
import com.qa.neworbit.pages.SearchResultPage;
import com.qa.neworbit.pages.loginPage;

public class Basetest {
	
	PlaywrightFactory pf;
	protected Page page;
	protected Properties prop;
	
	protected loginPage loginpage;
	protected DashboardPage dashboardpage;
	protected SearchResultPage searchresultpage;
	protected HotelAlldetailPage hotelalldetailpage;
	
	@BeforeTest
	public void setup() {
		//System.out.println("testbase clss enter");
		pf = new PlaywrightFactory();
		prop = pf.init_prop();
		page =pf.initBrowser(prop);
		
		loginpage = new loginPage(page);
	}

	@AfterTest
	public void tearDown() {
		page.context().browser().close();
	}
}
