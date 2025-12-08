package com.qa.neworbit.pages;

import com.github.javafaker.Faker;
import com.microsoft.playwright.Page;

public class HotelAlldetailPage {
	private Page page;
	
	//locators
	
	private String noofroom = "//div[contains(@class, 'information')]//span//span";
	
//	private String policytype = ""; 
	private String policyfilter ="//span[@title='Refund Policy : All']";
	private String policyfilteroption = "//div[@title='Refundable']//div";
	
	private String recordstatus = "//div[@class='f-w-700 f-20 text-grey m-y-4']";
	
	private String roomname = "//li[@class='ant-list-item'][1]/div/div/div/div[1]/div/div[1]/span[1]";
	private String mealplan = "//li[@class='ant-list-item'][1]/div/div/div/div[1]/div/div[1]/span[2]";
	private String ratestype = "//li[@class='ant-list-item'][1]/div/div/div/div[1]/div/div[2]/span[1]";
	private String roomprice = "//li[@class='ant-list-item'][1]/div/div/div/div[2]/div/div[1]/span[2]";
	private String selectroom = "//li[@class='ant-list-item'][1]/div/div/div/div[2]/div/button";
	private String firstname = "//input[@id='items_0_adults_0_firstname']";
	private String lastname = "//input[@id='items_0_adults_0_lastname']";
	private String panno = "//input[@id='items_0_adults_0_pan']";
	private String validate = "//button[contains(@class,'validate-pan-btn')]";
	
	private String remarkcb = "(//input[@class='ant-checkbox-input'])[2]";
	private String cpcb = "(//input[@class='ant-checkbox-input'])[3]";
	private String tcone ="(//input[@class='ant-checkbox-input'])[4]";
	private String tctwo ="(//input[@class='ant-checkbox-input'])[5]";
	private String tcthree ="(//input[@class='ant-checkbox-input'])[6]";
	
	private String agentref = "//input[@id='agentref']";
	private String number = "//input[@id='econtactnumber']";
	
	
	private String submit = "//span[text()=('Submit For Confirmation')]";
	private String status = "//div[@class='text-text-secondary']";
	private String bid = "//span[@class='cursor-pointer bid msgbid']";
	
	
	
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
	
	public void closeTab() {
        page.close();
    }
	
	public String roomSelect() {
		
		//page.waitForTimeout(20000);
		page.click(policyfilter);
		System.out.println("policy filter clicked");
		page.click(policyfilteroption);
		System.out.println("refundable cliked");
		page.waitForTimeout(20000);
		
		/// logic to validate options available or not
		if (page.isVisible(recordstatus)){
		System.out.println("entered into the if condition");
		String avastatus = page.innerText(recordstatus);
		System.out.println("records are available " + avastatus);
		page.close();
		page.waitForTimeout(10000);
		System.out.println("tab is closed");
		}
		
		
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
        page.waitForTimeout(10000);
        
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
		System.out.println("Submit button clicked");
		page.waitForTimeout(20000);
		
		String bookingid = page.innerText(bid);
		System.out.println("booking id:- " + bookingid);
		String bookingstatus = page.innerText(status);
				
		return bookingstatus;
		
	}
		
}
