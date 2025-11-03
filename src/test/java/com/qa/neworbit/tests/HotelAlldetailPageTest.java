package com.qa.neworbit.tests;

import java.net.UnknownHostException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.neworbit.base.Basetest;
import com.qa.neworbit.constants.AppConstans;

public class HotelAlldetailPageTest extends Basetest{
	
	@Test(priority=1)
	public void navigate() throws InterruptedException, UnknownHostException {
		dashboardpage = loginpage.navigatetodashboard(prop.getProperty("email"), prop.getProperty("password"));
		searchresultpage = dashboardpage.navigatetosearchresult(prop.getProperty("city"), prop.getProperty("day"), prop.getProperty("month") ,prop.getProperty("year"), prop.getProperty("nationality"));
		hotelalldetailpage = searchresultpage.navigatetohotelalldetail();
	}
	
	
	@Test(priority=2)
	public void titleverify() {
		String actualtitle = hotelalldetailpage.HotelAllDetailTitle();
		//System.out.println("Actaual title of Search result page : " + actualtitle);
		Assert.assertEquals(actualtitle, AppConstans.HOTELALLDETAIL_PAGE_TITLE);
	}
	
	@Test(priority=3)
	public void roomcount() {
		String count = hotelalldetailpage.NoOfRoomsCount();
		System.out.println("Total no of rooms in hotels found : " + count);
	}
	
	@Test(priority=4)
	public void lowestroomselection() {
		String clicked = hotelalldetailpage.roomSelect();
		System.out.println("room clicked " + clicked);
	}
	
	
	@Test(priority=5)
	public void hotelbooking() {
		
		String bookingid = hotelalldetailpage.booking();
		System.out.println("booking id:- " + bookingid);
		Assert.assertTrue(bookingid.contains("Confirmed"));

	}

	
	
	
}
