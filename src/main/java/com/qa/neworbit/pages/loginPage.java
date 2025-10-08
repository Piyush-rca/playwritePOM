package com.qa.neworbit.pages;

import java.io.IOException;
import java.util.Properties;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class loginPage {

	Page page;
	Properties prop;

	//1 String locators- OR

	//String login = "(//button[@type='button'])[1]";
	String email = "//input[@id='login_form_email']";
	String passwrd = "//input[@id='login_form_password']";
	String login = "//button[span[text()='Login']]";
			
//	String email = "//input[@name='uidField']";
//	String passwrd = "//input[@id='ak-stage-password-input']";;
//	String login = "//button[@type='submit']";
	String search = "//button[@type='submit']";

	
	//2. constructor of login class
	public loginPage(Page page){
		this.page=page;
	}

	//3. page action/methods:
	public String getloginPageTitle() {
		return page.title();
	}

	public boolean dologin(String UserName, String Password) throws IOException, MailosaurException, InterruptedException{
		System.out.println("Login Credential: " + UserName + " : " + Password);
		//page.click(login);
		page.fill(email, UserName);
		page.fill(passwrd, Password);
		page.click(login);
		
		System.out.println("step1");
		//OTP functionality automate
		
		    String apiKey = "OieNDJHh3ZYkXYldhJ2OBSXf6Id2MHXo";
		    String serverId = "7orzazff";
		    String serverDomain = "7orzazff.mailosaur.net";

		    System.out.println("step2");
		    
		    MailosaurClient mailosaur = new MailosaurClient(apiKey);
		    
		    Message message = null;
		    int retries = 12; // 12 * 5 seconds = 60 seconds max wait

		    for (int i = 0; i <= retries; i++) {
		        try {
		            MessageSearchParams params = new MessageSearchParams()
		                    .withServer(serverId)
		                    .withReceivedAfter(System.currentTimeMillis()); 
		                    // Only mails from last 5 minutes

		            SearchCriteria criteria = new SearchCriteria()
		                    .withSentTo("anything@" + serverDomain);

		            message = mailosaur.messages().get(params, criteria);

		            if (message != null) {
		                System.out.println("Email found at attempt " + (i+1));
		                break;
		            }
		        } catch (Exception e) {
		            System.out.println("No email yet, retrying... attempt " + (i+1));
		        }

		        // Wait 5 seconds before next retry
		        Thread.sleep(5000);
		    }

		    if (message == null) {
		        throw new RuntimeException("OTP email not received within timeout.");
		    }

		    System.out.println("step4");
		    
		    
		    Document doc = Jsoup.parse(message.html().body());
		    
		    String otp = doc.select("td:contains(OTP:) + td span").text().trim();
		    System.out.println("Extracted OTP: " + otp);
		    
		    Locator otpInputs = page.locator("input[aria-label^='OTP Input']");
		    //String otp = "123456"; // extracted OTP

		    // Convert OTP string into an array of characters
		    String[] otpDigits = otp.split("");
		    
		    for (int i = 0; i < otpDigits.length; i++) {
		        otpInputs.nth(i).fill(String.valueOf(otpDigits[i]));
		    }
		    
		//////////////////////////////////////////
		    
		page.click(search);
		if (page.isVisible(search)) {
			System.out.println("You are logged in successfully....");
			return true;
		}
		else {
			System.out.println("Please provide a correct credentials");
			return false;
		}
	}
	
	public DashboardPage navigatetodashboard(String UserName, String Password) throws InterruptedException{
			System.out.println("Login Credential: " + UserName + " : " + Password);
			//page.click(login);
			page.fill(email, UserName);
			//page.click(passwrd);
			page.fill(passwrd, Password);
			page.click(login);
			page.waitForTimeout(30000);
			
//			System.out.println("step1");
//			//OTP functionality automate
//			
//			    String apiKey = "OieNDJHh3ZYkXYldhJ2OBSXf6Id2MHXo";
//			    String serverId = "7orzazff";
//			    String serverDomain = "7orzazff.mailosaur.net";
//
//			    System.out.println("step2");
//			    
//			    MailosaurClient mailosaur = new MailosaurClient(apiKey);
//			    
//			    Message message = null;
//			    int retries = 12; // 12 * 5 seconds = 60 seconds max wait
//			    
//			    System.out.println("Otp initiated time : " + System.currentTimeMillis());
//
//			    for (int i = 0; i <= retries; i++) {
//			        try {
//			            MessageSearchParams params = new MessageSearchParams()
//			                    .withServer(serverId)
//			                    .withReceivedAfter(System.currentTimeMillis()); 
//			                    // Only mails from last 5 minutes
//			            
//
//			            SearchCriteria criteria = new SearchCriteria()
//			                    .withSentTo("anything@" + serverDomain);
//
//			            message = mailosaur.messages().get(params, criteria);
//
//			            if (message != null) {
//			                System.out.println("Email found at attempt " + (i+1));
//			                break;
//			            }
//			        } catch (Exception e) {
//			            System.out.println("No email yet, retrying... attempt " + (i+1));
//			        }
//
//			        // Wait 5 seconds before next retry
//			        Thread.sleep(5000);
//			    }
//
//			    if (message == null) {
//			        throw new RuntimeException("OTP email not received within timeout.");
//			    }
//
//			    System.out.println("step4");
//			    
//			    
//			    Document doc = Jsoup.parse(message.html().body());
//			    
//			    String otp = doc.select("td:contains(OTP:) + td span").text().trim();
//			    System.out.println("Extracted OTP: " + otp);
//			    
//			    Locator otpInputs = page.locator("input[aria-label^='OTP Input']");
//			    //String otp = "123456"; // extracted OTP
//
//			    // Convert OTP string into an array of characters
//			    String[] otpDigits = otp.split("");
//			    
//			    for (int i = 0; i < otpDigits.length; i++) {
//			        otpInputs.nth(i).fill(String.valueOf(otpDigits[i]));
//			    }
						
			return new DashboardPage(page); 
	}
}
