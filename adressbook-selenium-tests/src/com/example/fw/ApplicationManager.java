package com.example.fw;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class ApplicationManager {
	
	public WebDriver driver;
	public String baseUrl;
	
	private NavigationHelper navigationHelper;
	private GroupHelper groupHelper;
	private ContactHelper contactHelper;
	private RandomHelper randomHelper;
	private NamesGeneratorHelper namesGeneratorHelper;
	private Properties properties;
	
	//before
	public ApplicationManager(Properties properties) {
	    this.properties = properties;
	    String browser = properties.getProperty("browser");
	    if("firefox".equals(browser))
	    	driver = new FirefoxDriver();
	    else if ("ie".equals(browser))
	    	driver = new InternetExplorerDriver();
	    else if ("chrome".equals(browser))
	    	driver = new ChromeDriver();
	    else throw new Error("Unsupported browser: " + browser);
	    baseUrl = properties.getProperty("baseUrl");
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	    driver.get(baseUrl);
	}	
	
	//after
	public void stop() {
	    driver.quit();
	}
	
	public NavigationHelper navigateTo() {
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
