package com.example.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	@Test
	public void deleteFirstContact() {
		app.getNavigationHelper().openMainPage();
		if (app.driver.findElements(By.xpath(app.getContactHelper().xpathContactList)).isEmpty() == false) {
			app.getContactHelper().initContactModification(1); 
			app.getContactHelper().deleteContact(); 
			app.getContactHelper().returnToHomePage();
		}
		else System.out.println("deleteFirstContact: Нет контактов, удалять нечего");
	}
	
	@Test
	public void deleteRandomContact() {
		app.getNavigationHelper().openMainPage();
		if (app.driver.findElements(By.xpath(app.getContactHelper().xpathContactList)).isEmpty() == false) {
			app.getContactHelper().initContactModification(app.getRandomHelper().randomIndex(By.xpath(app.getContactHelper().xpathContactList)));
			app.getContactHelper().deleteContact(); 
			app.getContactHelper().returnToHomePage();
		}
		else System.out.println("deleteRandomContact: Нет контактов, удалять нечего");
	}

}
