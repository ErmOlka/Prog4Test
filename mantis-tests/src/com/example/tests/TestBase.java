package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import com.example.fw.AccountHelper;
import com.example.fw.ApplicationManager;
import com.example.fw.JamesHelper;
import com.example.fw.User;


public class TestBase {
	
	public ApplicationManager app;
	
	protected JamesHelper james;
	protected AccountHelper accHelper;
	
	public User admin = new User()
		.setLogin("administrator")
		.setPassword("qwerty");

	@BeforeSuite
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile", "application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
	  }
	
	@AfterSuite
	public void tearDown() throws Exception {
		app.stop();
	  }

	
}
