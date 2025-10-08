package com.qa.neworbit.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.neworbit.base.Basetest;
import com.qa.neworbit.constants.AppConstans;

public class SearchResultPageTest extends Basetest{

	@Test(priority=1)
	public void navigate() throws InterruptedException {
		dashboardpage = loginpage.navigatetodashboard(prop.getProperty("email"), prop.getProperty("password"));
		searchresultpage = dashboardpage.navigatetosearchresult(prop.getProperty("city"), prop.getProperty("day"), prop.getProperty("month") ,prop.getProperty("year"), prop.getProperty("nationality"));
	}
	@Test(priority=2)
	public void titleverify() {
		String actualtitle = searchresultpage.SearchresultTitle();
		System.out.println("Actaual title of Search result page : " + actualtitle);
		Assert.assertEquals(actualtitle, AppConstans.SEARCHRESULT_PAGE_TITLE);
	}
	
	@Test(priority=3)
	public void hotelcount() {
		String count = searchresultpage.Searchresultcount();
		System.out.println("Total no of hotels found : " + count);
	}
	
	@Test(priority=4)
	public void selecthotel() {
		Assert.assertTrue(searchresultpage.Hotelselect());
	}
}
