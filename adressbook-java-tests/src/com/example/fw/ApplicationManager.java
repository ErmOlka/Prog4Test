package com.example.fw;

import java.util.Properties;

public class ApplicationManager {
	
	private Properties properties;
	
	//before
	public ApplicationManager(Properties properties) {
	    this.properties = properties;
	}	
	
	//after
	public void stop() {
	}
	
}
