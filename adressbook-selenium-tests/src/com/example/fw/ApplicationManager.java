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
	private Properties properties;
	private HibernateHelper hibernateHelper;
	
	private ApplicationModel model;
	
	//before
	public ApplicationManager(Properties properties) {
	    this.properties = properties;
	    model = new ApplicationModel();
	    model.setGroups(getHibernateHelper().listGroups());
	    model.setContacts(getHibernateHelper().listContacts());
	}	
	
	//after
	public void stop() {
	    driver.quit();
	}
	
	public ApplicationModel getModel() {
		return model;
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
	
	public HibernateHelper getHibernateHelper() {
		if (hibernateHelper == null) {
			hibernateHelper = new HibernateHelper(this);
		}
		return hibernateHelper;		
	}

	public WebDriver getDriver() {
		if (driver == null) {
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
		return driver;
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
