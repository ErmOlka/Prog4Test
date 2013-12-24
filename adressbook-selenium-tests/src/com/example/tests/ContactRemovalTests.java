package com.example.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
    
	@Test
	public void deleteFirstContact() {
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().initContactModification(1); 
		app.getContactHelper().deleteContact(); 
		app.getContactHelper().returnToHomePage();
	}
	
	@Test
	public void deleteRandomContact() {
		app.getNavigationHelper().openMainPage();
		app.getContactHelper().initContactModification(app.getRandomHelper().randomIndex(By.xpath(app.getContactHelper().xpathContactList)));
		app.getContactHelper().deleteContact(); 
		app.getContactHelper().returnToHomePage();
	}

}
