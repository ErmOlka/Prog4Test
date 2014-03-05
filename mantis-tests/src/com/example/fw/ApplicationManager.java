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
	private ApplicationModel model;
	
	private Properties properties;
	private HibernateHelper hibernateHelper;
	private AccountHelper accountHelper;
	private MailHelper mailHelper;
	private JamesHelper jamesHelper;
	
	//before
	public ApplicationManager(Properties properties) {
	    this.properties = properties;
	    model = new ApplicationModel();
	    model.setUsers(getHibernateHelper().listUsers());
	}	
	
	//after
	public void stop() {
	    driver.quit();
	}
	
	public ApplicationModel getModel() {
		return model;
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

	public AccountHelper getAccountHelper() {
		if (accountHelper == null) {
			accountHelper = new AccountHelper(this);
		}
		return accountHelper;		
	}

	public MailHelper getMailHelper() {
		if (mailHelper == null) {
			mailHelper = new MailHelper(this);
		}
		return mailHelper;		
	}

	public JamesHelper getJamesHelper() {
		if (jamesHelper == null) {
			jamesHelper = new JamesHelper(this);
		}
		return jamesHelper;	
	}

}
