package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.example.fw.ApplicationManager;


public class TestBase {
	
	public ApplicationManager app;
	private int checkFrequancy;
	private int checkCounter;

	@BeforeTest
	public void setUp() throws Exception {
		String configFile = System.getProperty("configFile", "application.properties");
		Properties properties = new Properties();
		properties.load(new FileReader(new File(configFile)));
		app = new ApplicationManager(properties);
		checkCounter = 0;
		checkFrequancy = Integer.parseInt(properties.getProperty("check.frequancy", "0"));
	  }
	
	protected boolean wantToCheck() {
		checkCounter++;
		if (checkCounter > checkFrequancy) {
			checkCounter = 0;
			return true;
		}
		else
			return false;
	}
	
	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }

	
}
