package com.qa.neworbit.pages;

import com.microsoft.playwright.Page;

public class SearchResultPage {
	private Page page;

	//1. locators
	
	private String summary = "//span[@class='output-text-notes']/span[@class='f-w-700']";
	
	//private String filter ="//span[@class='ant-select-selection-item']//span[@class='f-w-600']";
	
	//private String filteroption = "//div[@class='rc-virtual-list-holder-inner']//div[@class='ant-select-item-option-content']//span[@class='f-w-600']";
	
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
		System.out.println("we found " + noofhotels + "hotels in search, now arranging low to high");
		if(noofhotels!=null) {
			System.out.println("hotels available");
			System.out.println("commented");
//			page.click(filter);
//			System.out.println("Filter clicked");
//			page.locator(filteroption).first().click();
//			System.out.println("filter option clicked");
			String hname = page.locator(hotelname).first().textContent();
			System.out.println("Selected hotel name : " + hname);
			String hprice = page.locator(initprice).first().textContent();
			System.out.println("Initial price : " + hprice );
			String hcurrency = page.locator(currency).first().textContent();
			System.out.println("Selected hotel price : " + hcurrency + " " + hprice);
			//page.click(showrates);
			
			//new popup opened
			
			Page page1 = page.waitForPopup(() -> {
				
				page.click(showrates);
			});
			page1.waitForTimeout(20000);
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
		
		//page.click(summary);
		String noofhotels = page.locator(summary).first().textContent();
		System.out.println("we found " + noofhotels + "hotels in search, now arranging low to high");
		if(noofhotels!=null) {
			System.out.println("hotels available");
			//page.click(filter);
			//page.locator(filteroption).first().click();
			String hname = page.locator(hotelname).first().textContent();
			System.out.println("Selected hotel name : " + hname);
			String hprice = page.locator(initprice).first().textContent();
			System.out.println("Initial price : " + hprice );
			String hcurrency = page.locator(currency).first().textContent();
			System.out.println("Selected hotel price : " + hcurrency + " " + hprice);
			
			//new popup opened
			
			Page page1 = page.waitForPopup(() -> {
				
				page.click(showrates);
			});
			
			page1.click(noofroom);
			String availroom = page1.innerText(noofroom);
			System.out.println("No of rooms available : " + availroom);
			page1.waitForTimeout(2000);
			
			return new HotelAlldetailPage(page1) ;
		}
		else {
			return new HotelAlldetailPage(page);
		}
		
	}
}
