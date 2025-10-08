package com.qa.neworbit.pages;

import com.microsoft.playwright.Page;

public class SearchResultPage {
	private Page page;

	//1. locators
	
	private String summary = "//span[@class='output-text-notes']/span[@class='f-w-700'][1]";
	
	private String fhotel = "(//button[@type='button'][span[text()='SHOW ROOMS']])[2]";

	
	private String hotelname = "(//div[@class='booking-name-type ant-flex css-1v5z42l'])[1]/span";
	private String initprice ="//div[@class='ant-col right-details-section css-1v5z42l']//div[@class='price-section']//span[@class='m-r-10']";
	private String currency = "//div[@class='ant-col right-details-section css-1v5z42l']//div[@class='price-section']//span[@class='m-r-10']//span[@class='currency-text']";
	private String showrates = "(//div[@class='ant-col right-details-section css-1v5z42l'])[1]//button[@class='ant-btn css-1v5z42l ant-btn-primary ant-btn-color-primary ant-btn-variant-solid grn-booking-custom-pink-btn grn-common-btn']";
	//private String noofroom ="//div[@class='information ant-flex css-1v613y0']//span[@class='ant-typography css-1v613y0']//span";
	private String noofroom = "//div[contains(@class, 'information')]//span//span";
	
	//2. creating constructor
	
	public SearchResultPage(Page page) {
		this.page = page;
	}
	
	//3. implement page actions/methods
	
	public String SearchresultTitle() {
		String actualtitle = page.title();
		return actualtitle;
	}
	
	public String Searchresultcount() {
		page.waitForTimeout(20000);
		page.click(summary);
		System.out.println("hotel count");
		if(page.isVisible(summary)) {
		String noofhotels = page.locator(summary).first().innerText();
		return noofhotels;
		}
		else return "no hotel found";
	}
	
	public boolean Hotelselect() {
		System.out.println("Enter into hotelselect");
		page.click(summary);
		String noofhotels = page.locator(summary).first().textContent();
		System.out.println("we found " + noofhotels + "hotels in search");
		if(noofhotels!=null) {
			System.out.println("hotels available");
//			page.click(filter);
//			System.out.println("Filter clicked");
//			page.locator(filteroption).first().click();
//			System.out.println("filter option clicked");
//			String hname = page.locator(hotelname).first().textContent();
//		System.out.println("Selected hotel name : " + hname);
//			String hprice = page.locator(initprice).first().textContent();
//			System.out.println("Initial price : " + hprice );
//			String hcurrency = page.locator(currency).first().textContent();
//			System.out.println("Selected hotel price : " + hcurrency + " " + hprice);
			//page.click(showrates);
			//page.click(fhotel);
			//System.out.println("first hotel clicked");
			//new popup opened
			
			Page page1 = page.waitForPopup(() -> {
				System.out.println("inside the clause");
				page.click(fhotel);
				System.out.println("click on first hotel");
				page.waitForTimeout(20000);
			});
			page1.waitForTimeout(20000);
			System.out.println("waiting for noofrooms available");
			page1.click(noofroom);
			String availroom = page1.innerText(noofroom);
			System.out.println("No of rooms available : " + availroom);
			page1.waitForTimeout(20000);
			return true;
		}
		else {
			return false;
		}
	}
	
	public HotelAlldetailPage navigatetohotelalldetail() {
		System.out.println("entered into navigate to hotelalldetail");
		page.waitForTimeout(20000);
		page.click(summary);
		String noofhotels = page.locator(summary).first().textContent();
		System.out.println("we found " + noofhotels + "hotels in search");
		if(noofhotels!=null) {
			System.out.println("hotels available");
			//page.click(filter);
			//page.locator(filteroption).first().click();
			
			//new popup opened
			
			Page page1 = page.waitForPopup(() -> {
				
				page.click(fhotel);
			});
			
			page1.waitForTimeout(20000);
//			System.out.println("waiting for noofrooms available");
//			page1.click(noofroom);
//			String availroom = page1.innerText(noofroom);
//			System.out.println("No of rooms available : " + availroom);
//			page1.waitForTimeout(5000);
			return new HotelAlldetailPage(page1) ;
		}
		else {
			return new HotelAlldetailPage(page);
		}
		
	}
}
