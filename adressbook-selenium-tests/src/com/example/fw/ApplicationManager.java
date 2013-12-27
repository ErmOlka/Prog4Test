package com.example.fw;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class ApplicationManager {
	
	public WebDriver driver;
	public String baseUrl;
	
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private RandomHelper randomHelper;
	private NamesGeneratorHelper namesGeneratorHelper;
	
	//before
	public ApplicationManager() {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}	
	
	//after
	public void stop() {
	    driver.quit();
	}
	
	public NavigationHelper getNavigationHelper() {
		if (navigationHelper == null) {
			navigationHelper = new NavigationHelper(this);
		}
		return navigationHelper;
	}
	
	public GroupHelper getGroupHelper() {
		if (groupHelper == null) {
			groupHelper = new GroupHelper(this);
		}
		return groupHelper;
	}
	
	public ContactHelper getContactHelper() {
		if (contactHelper == null) {
			contactHelper = new ContactHelper(this);
		}
		return contactHelper;
	}
	
	public RandomHelper getRandomHelper() {
		if (randomHelper == null) {
			randomHelper = new RandomHelper(this);
		}
		return randomHelper;
	}
	
	public NamesGeneratorHelper getNamesGeneratorHelper() {
		if (namesGeneratorHelper == null) {
			namesGeneratorHelper = new NamesGeneratorHelper(this);
		}
		return namesGeneratorHelper;
	}
}
