package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

import com.example.tests.ContactData;

public class ContactHelper extends HelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initCreationContact() {
		click(By.linkText("add new"));
	  }

	public void fillContactForm(ContactData contact, boolean formType) {
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
	    if (formType == CREATION)
	    	selectByText(By.name("new_group"), contact.contactGroup);
	    else if (driver.findElements(By.name("new_group")).size() != 0)
	    	throw new Error("На форме редактирования присутствует выбор группы");
	    typeText(By.name("address2"),contact.address2);
	    typeText(By.name("phone2"),contact.homePhone2);
	  }

	public void submitCreationContact() {
		click(By.name("submit"));
	  }

	public void returnToHomePage() {
		click(By.linkText("home page"));
	  }

	public void deleteContact() {
		click(By.xpath("//input[@type='submit'][@value='Delete']"));
	}

	public void initContactModification(int index) {
		click(By.xpath("(//img[@alt='Edit'])[" + (index + 1) + "]"));
	}

	public void submitContactModification() {
		click(By.xpath("//input[@type='submit'][@value='Update']"));
		
	}

	public List<ContactData> getContacts() {
		List<ContactData> contacts = new ArrayList<ContactData>();
		int tableRowsCount = driver.findElements(By.xpath("//tr[@name='entry']")).size();
		for (int i = 0; i < tableRowsCount; i++) {
			ContactData contact = new ContactData();
			contact.lastName = driver.findElement(By.xpath("//tr[" + (i + 2) + "]/td[2]")).getText();
			contacts.add(contact);
		}
		return contacts;

	}
	
}
