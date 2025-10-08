package com.qa.neworbit.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;

public class HotelAlldetailPage {
	private Page page;
	
	//locators
	
	private String noofroom = "//div[contains(@class, 'information')]//span//span";
	
//	private String policytype = ""; 
	private String policyfilter ="(//input[@type='search'])[3]";
	private String policyfilteroption = "//div[@title='Refundable']//div";
	private String roomname = "//li[@class='ant-list-item'][1]/div/div/div/div[1]/div/div[1]/span[1]";
	private String mealplan = "//li[@class='ant-list-item'][1]/div/div/div/div[1]/div/div[1]/span[2]";
	private String ratestype = "//li[@class='ant-list-item'][1]/div/div/div/div[1]/div/div[2]/span[1]";
	private String roomprice = "//li[@class='ant-list-item'][1]/div/div/div/div[2]/div/div[1]/span[2]";
	private String selectroom = "//li[@class='ant-list-item'][1]/div/div/div/div[2]/div/button";
	private String firstname = "//input[@id='items_0_adults_0_firstname']";
	private String lastname = "//input[@id='items_0_adults_0_lastname']";
	private String panno = "//input[@id='items_0_adults_0_pan']";
	private String validate = "//button[@class='ant-btn css-1odpy5d ant-btn-link ant-btn-color-link ant-btn-variant-link validate-pan-btn display-inline-block grn-common-btn']";
	
	private String remarkcb = "(//input[@class='ant-checkbox-input'])[2]";
	private String cpcb = "(//input[@class='ant-checkbox-input'])[3]";
	private String tcone ="(//input[@class='ant-checkbox-input'])[4]";
	private String tctwo ="(//input[@class='ant-checkbox-input'])[5]";
	private String tcthree ="(//input[@class='ant-checkbox-input'])[6]";
	
	private String agentref = "//input[@id='agentref']";
	private String number = "//input[@id='econtactnumber']";
	
	
	private String submit = "//button[@class='ant-btn css-1odpy5d ant-btn-primary ant-btn-color-primary ant-btn-variant-solid undefined grn-common-btn']";
	private String status = "//div[@class='text-text-secondary']";
	private String bid = "//span[@class='bid msgbid']";
	
	
	
	// constructor
	
	public HotelAlldetailPage(Page page) {
		this.page=page;
	}

	//methods
	
	public String HotelAllDetailTitle() {
		String actualtitle = page.title();
		return actualtitle;
	}
	
	public String  NoOfRoomsCount() {
		//page.waitForTimeout(10000);
		//page.waitForTimeout(20000);
		System.out.println("waiting for noofrooms available");
		page.click(noofroom);
		String availroom = page.innerText(noofroom);
		System.out.println("No of rooms available : " + availroom);
		page.waitForTimeout(5000);
		
		return availroom;
	}
	
	public String roomSelect() {
		
		//page.waitForTimeout(20000);
		//page.click(policyfilter);
		//page.click(policyfilteroption);
		//page.waitForTimeout(20000);
		String room = page.innerText(roomname);
		System.out.println("Selected room name: " + room);
		String meal = page.innerText(mealplan);
		System.out.println("Selected room meal plan: " + meal);
		String roomratetype = page.innerText(ratestype);
		System.out.println("Room rates are :- " + roomratetype);
		String price = page.innerText(roomprice);
		System.out.println("Selected room Price :- " + price);
		
		page.click(selectroom);
		//page.waitForTimeout(20000);
		
		
		return "room selected";
		
	}
	
	public String booking() {
		
		//generating random names
		
		Faker faker = new Faker();

        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);

		
		page.fill(firstname, firstName);
		page.fill(lastname, lastName);
		page.fill(panno, "AAACD3494N");
		page.click(validate);
		page.waitForTimeout(5000);
		
		page.click(remarkcb);
		page.click(cpcb);
		page.click(tcone);
		//page.click(tctwo);
		//page.click(tcthree);
		
		page.fill(agentref, "piyush");
		page.fill(number, "9876543210");
		page.click(submit);
		page.waitForTimeout(20000);
		
		String bookingid = page.innerText(bid);
		System.out.println("booking id:- " + bookingid);
		String bookingstatus = page.innerText(status);
				
		return bookingstatus;
		
	}
		
}
