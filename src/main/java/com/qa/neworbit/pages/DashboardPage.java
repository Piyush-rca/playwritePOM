package com.qa.neworbit.pages;

import com.microsoft.playwright.Page;

public class DashboardPage {
	
	private Page  page;
	
	
	//1. String locators available on dashboard page.
	private String datetoclick;
	private String destination = "(//input[@class='ant-select-selection-search-input'])[1]";
	
	private String destmatch = "//div[@class='ant-select-item ant-select-item-option']";
	private String checkin = "//input[@id='checkin']";
//	private String chkinmonth = "//button[@aria-label='month panel']";
	private String chkinmonth = "//button[@aria-label='Choose a month']";
	
//	private String chkinyear = "//button[@aria-label='year panel']";
	private String chkinyear = "//button[@aria-label='Choose a year']";
	
	//private String checkout = "//input[@id='checkout']";
	private String next = "//button[@aria-label='Next month (PageDown)']";
	//private String noofnight = "//input[@aria-owns='rc_select_1_list']";
	//private String nationality = "//input[@aria-owns='rc_select_2_list']";
	
	private String date;
	private String paxnationality = "(//input[@class='ant-select-selection-search-input'])[3]";
	
	private String searchbutton = "//button[@type='submit']";
	private String summary = "//span[@class='output-text-notes']/span[@class='f-w-700']";
	
	//2. Constructor of DashbaordPage class
	public DashboardPage(Page page) {
		this.page = page;
	}
	
	
	//3. implement page action/methods
	
	public String DashboardTitle() {
		String actualtitle = page.title();
		return actualtitle;
	}
	
	public boolean search(String City, String day, String month, String Year, String Nationality) {
		datetoclick = day;
		date = "//td[@class='ant-picker-cell ant-picker-cell-in-view']/div[text()='" + datetoclick + "']";
		System.out.println("Day want to search :" + datetoclick);
		page.fill(destination, City);
		page.locator(destmatch).first().click();
		page.click(checkin);
		String cMonth = page.textContent(chkinmonth);
		String cYear = page.textContent(chkinyear);
		System.out.println("Current month and year :" + cMonth +" "+ cYear);
		while(!(cMonth.equals(month) && cYear.equals(Year))){
			page.click(next);
			cMonth = page.textContent(chkinmonth);
			cYear = page.textContent(chkinyear);
		}
		System.out.println("Updated Current month and year :" + cMonth +" "+ cYear);
		//System.out.println(date);

		page.click(date);
		page.fill(paxnationality, Nationality);
		//page.getByText("India", new Page.GetByTextOptions().setExact(true)).click();
		page.getByText(Nationality, new Page.GetByTextOptions().setExact(true)).click();

		page.click(searchbutton);
		page.click(summary);
		if(page.isVisible(summary)) {
			System.out.println("search is created");
			String hotelcount = page.locator(summary).first().innerText();
			System.out.println("hotels found : " + hotelcount);
			return true;
		}
		else {
			System.out.println("search is not created");
			return false;
			}

	}
	
	public SearchResultPage navigatetosearchresult(String City, String day, String month, String Year, String Nationality) {
		
		System.out.println("on dashboard page");
		datetoclick = day;
		date = "//td[@class='ant-picker-cell ant-picker-cell-in-view']/div[text()='" + datetoclick + "']";
		System.out.println("Day want to search :" + datetoclick);
		//String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		//System.out.println("wait started at: " + timestamp);
		//page.waitForTimeout(20000);
		//String timestampnew = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		//System.out.println("Test started at: " + timestampnew);
		page.fill(destination, City);
		page.locator(destmatch).first().click();
		//page.getByText("Dubai United Arab Emirates", new Page.GetByTextOptions().setExact(true)).click();
		page.click(checkin);
		String cMonth = page.textContent(chkinmonth);
		String cYear = page.textContent(chkinyear);
		System.out.println("Current month and year :" + cMonth +" "+ cYear);
		while(!(cMonth.equals(month) && cYear.equals(Year))){
			page.click(next);
			cMonth = page.textContent(chkinmonth);
			cYear = page.textContent(chkinyear);
		}
		System.out.println("Updated Current month and year :" + cMonth +" "+ cYear);
		System.out.println(date);
		page.click(date);
//		page.fill(paxnationality, Nationality);
		//page.getByText("India", new Page.GetByTextOptions().setExact(true)).click();
		
//		page.getByText(Nationality, new Page.GetByTextOptions().setExact(true)).click();

		page.click(searchbutton);		
		System.out.println("Search button clicked");
		return new SearchResultPage(page);
	}
	
}