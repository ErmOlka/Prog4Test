package com.example.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
	
	@Test
	public void modifyFirstContact() {
	    app.getNavigationHelper().openMainPage();
	    app.getContactHelper().initContactModification(1);
		ContactData contact = new ContactData();
		contact.firstName = "New First Name";
		contact.lastName = "New Last Name";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();
	}
	
	@Test
	public void modifyRandomContact() {
	    app.getNavigationHelper().openMainPage();
	    app.getContactHelper().initContactModification(app.getRandomHelper().randomIndex(By.xpath(app.getContactHelper().xpathContactList)));
		ContactData contact = new ContactData();
		contact.firstName = "New Random First Name";
		contact.lastName = "New Random Last Name";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();
	}

}
