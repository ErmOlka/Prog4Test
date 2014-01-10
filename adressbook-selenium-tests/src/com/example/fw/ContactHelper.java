package com.example.fw;

import org.openqa.selenium.By;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends HelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private SortedListOf<ContactData> cachedContacts;
	
	public SortedListOf<ContactData> getContacts() {
		if (cachedContacts == null)
			rebuildCache();
		return cachedContacts;
	}
		
	private void rebuildCache() {
		cachedContacts = new SortedListOf<ContactData>();
		
		manager.navigateTo().mainPage();
		int tableRowsCount = driver.findElements(By.xpath("//tr[@name='entry']")).size();
		for (int i = 0; i < tableRowsCount; i++) {
			ContactData contact = new ContactData();
			contact.lastName = driver.findElement(By.xpath("//tr[" + (i + 2) + "]/td[2]")).getText();
			contact.firstName = driver.findElement(By.xpath("//tr[" + (i + 2) + "]/td[3]")).getText();
			cachedContacts.add(contact);
		}
	}
	
	public ContactHelper creationContact(ContactData contact, boolean formType) {
		manager.navigateTo().mainPage();
	    initCreationContact();
		fillContactForm(contact,CREATION);
		submitCreationContact();
	    returnToHomePage();
	    rebuildCache();
	    return this;
	}
	
	public ContactHelper modifyContact(int index, ContactData contact, boolean formType) {
		manager.navigateTo().mainPage();
	    initContactModification(index);
		fillContactForm(contact,MODIFICATION);
		submitContactModification();
		returnToHomePage();
		rebuildCache();
		return this;
	}
	
	public ContactHelper deleteContact(int index) {
		manager.navigateTo().mainPage();
		initContactModification(index); 
		submitContactDeletion(); 
		returnToHomePage();
		rebuildCache();
		return this;
	}
	
//-----------------------------------------------------------------------------------------------------------------------------------

	public ContactHelper initCreationContact() {
		click(By.linkText("add new"));
		return this;
	}

	public ContactHelper fillContactForm(ContactData contact, boolean formType) {
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
	    else if (! driver.findElements(By.name("new_group")).isEmpty()) //с данным условием происходит задержка теста модификации контакта после заполнения года рождения, т.е. в том месте, где было бы поле группы
	    	throw new Error("На форме редактирования присутствует выбор группы");//если условие else закомментировать, то задержки не происходит.
	    typeText(By.name("address2"),contact.address2);
	    typeText(By.name("phone2"),contact.homePhone2);
	    return this;
	}

	public ContactHelper submitCreationContact() {
		click(By.name("submit"));
		cachedContacts = null;
		return this;
	}
	
	public ContactHelper initContactModification(int index) {
		click(By.xpath("(//img[@alt='Edit'])[" + (index + 1) + "]"));
		return this;
	}

	public ContactHelper submitContactModification() {
		click(By.xpath("//input[@type='submit'][@value='Update']"));
		cachedContacts = null;
		return this;
	}
	
	public ContactHelper submitContactDeletion() {
		click(By.xpath("//input[@type='submit'][@value='Delete']"));
		cachedContacts = null;
		return this;
	}

	public ContactHelper returnToHomePage() {
		click(By.linkText("home page"));
		return this;
	}
	
}
