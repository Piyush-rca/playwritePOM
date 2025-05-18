package com.qa.neworbit.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.neworbit.base.Basetest;
import com.qa.neworbit.constants.AppConstans;

public class DashboardPageTest extends Basetest{
	
	@Test(priority = 1)
	public void NavigateToDashboardPage() {
		dashboardpage = loginpage.navigatetodashboard(prop.getProperty("email"), prop.getProperty("password"));
	}
	
	@Test(priority = 2)
	public void TitleverifyDashboard() {
		String actualtitle = dashboardpage.DashboardTitle();
		System.out.println("Actaual title of dashboard page : " + actualtitle);
		Assert.assertEquals(actualtitle, AppConstans.DASHBOARD_PAGE_TITLE);
	}
	
	@Test(priority = 3)
	public void Dosearch() {
	Assert.assertTrue(dashboardpage.search(prop.getProperty("city"), prop.getProperty("day"), prop.getProperty("month") ,prop.getProperty("year"), prop.getProperty("nationality")));
	}

}
