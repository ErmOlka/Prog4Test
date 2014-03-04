package com.example.tests;

import org.testng.annotations.Test;

public class test extends TestBase{

	@Test
	public void createMailUser() {
		if (!app.getJamesHelper().doesUserExist("manualTestUser"))
			app.getJamesHelper().createUser("manualTestUser", "123456");
	}
	
}
