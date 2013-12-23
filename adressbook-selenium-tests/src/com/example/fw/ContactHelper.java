package com.example.fw;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {

	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initCreationContact() {
		click(By.linkText("add new"));
	  }

	public void fillContactForm(ContactData contact) {
		type(By.name("firstname"),contact.firstName);
		type(By.name("lastname"),contact.lastName);
		type(By.name("address"),contact.address1);
		type(By.name("home"),contact.homePhone1);
		type(By.name("mobile"),contact.mobilePhone);
		type(By.name("work"),contact.workPhone);
		type(By.name("email"),contact.email1);
		type(By.name("email2"),contact.email2);
	    selectByText(By.name("bday"), contact.birthDay);
	    selectByText(By.name("bmonth"), contact.birthMonth);
	    type(By.name("byear"),contact.birthYear);
	    manager.countGroups = new Select(driver.findElement(By.name("new_group"))).getOptions().size();
	    int randomIndex = manager.randomNumeric(0, manager.countGroups);
	    new Select(driver.findElement(By.name("new_group"))).selectByIndex(randomIndex);
	    //selectByText(By.name("new_group"), contact.contactGroup); //сделать так чтобы вызывался либо рандом либо конкретное значение
	    type(By.name("address2"),contact.address2);
	    type(By.name("phone2"),contact.homePhone2);
	  }

	public void submitCreationContact() {
		click(By.name("submit"));
	  }

	public void returnToHomePage() {
		click(By.linkText("home page"));
	  }

}
