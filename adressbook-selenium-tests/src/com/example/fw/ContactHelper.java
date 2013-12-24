package com.example.fw;

import org.openqa.selenium.By;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initCreationContact() {
		click(By.linkText("add new"));
	  }

	public void fillContactForm(ContactData contact) {
		typeText(By.name("firstname"),contact.firstName);
		typeText(By.name("lastname"),contact.lastName);
		typeText(By.name("address"),contact.address1);
		typeText(By.name("home"),contact.homePhone1);
		typeText(By.name("mobile"),contact.mobilePhone);
		typeText(By.name("work"),contact.workPhone);
		typeText(By.name("email"),contact.email1);
		typeText(By.name("email2"),contact.email2);
	    selectByText(By.name("bday"), contact.birthDay);
	    selectByText(By.name("bmonth"), contact.birthMonth);
	    typeText(By.name("byear"),contact.birthYear);
	    selectByText(By.name("new_group"), contact.contactGroup);
	    typeText(By.name("address2"),contact.address2);
	    typeText(By.name("phone2"),contact.homePhone2);
	  }

	public void submitCreationContact() {
		click(By.name("submit"));
	  }

	public void returnToHomePage() {
		click(By.linkText("home page"));
	  }

}
