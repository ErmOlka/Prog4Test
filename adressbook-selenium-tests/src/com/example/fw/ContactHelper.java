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
	
	public SortedListOf<ContactData> getPrintContacts() {
		SortedListOf<ContactData> contacts = new SortedListOf<ContactData>();
		//перебрать таблицу на странице print phones
		return null;
	}
		
	private void rebuildCache() {
		cachedContacts = new SortedListOf<ContactData>();
		
		manager.navigateTo().mainPage();
		int tableRowsCount = driver.findElements(By.xpath("//tr[@name='entry']")).size();
		for (int i = 0; i < tableRowsCount; i++) {
			String firstName = driver.findElement(By.xpath("//tr[" + (i + 2) + "]/td[3]")).getText();
			String lastName = driver.findElement(By.xpath("//tr[" + (i + 2) + "]/td[2]")).getText();
			String email = driver.findElement(By.xpath("//tr[" + (i + 2) + "]/td[4]")).getText();
			String homePhone = driver.findElement(By.xpath("//tr[" + (i + 2) + "]/td[5]")).getText();
			cachedContacts.add(new ContactData()
									.withFirstName(firstName)
									.withLastName(lastName)
									.withEmail1(email)
									.withHomePhone1(homePhone)
							);
		}
	}
	
	public ContactHelper creationContact(ContactData contact) {
		manager.navigateTo().mainPage();
	    initCreationContact();
		fillContactForm(contact,CREATION);
		submitCreationContact();
	    returnToHomePage();
	    rebuildCache();
	    return this;
	}
	
	public ContactHelper modifyContact(int index, ContactData contact) {
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
		typeText(By.name("firstname"),contact.getFirstName());
		typeText(By.name("lastname"),contact.getLastName());
		typeText(By.name("address"),contact.getAddress1());
		typeText(By.name("home"),contact.getHomePhone1());
		typeText(By.name("mobile"),contact.getMobilePhone());
		typeText(By.name("work"),contact.getWorkPhone());
		typeText(By.name("email"),contact.getEmail1());
		typeText(By.name("email2"),contact.getEmail2());
		selectByText(By.name("bday"), contact.getBirthDay());
	    selectByText(By.name("bmonth"), contact.getBirthMonth());
	    typeText(By.name("byear"),contact.getBirthYear());
	    if (formType == CREATION)
	    	selectByText(By.name("new_group"), contact.getContactGroup());
	    else if (! driver.findElements(By.name("new_group")).isEmpty()) //с данным условием происходит задержка теста модификации контакта после заполнени€ года рождени€, т.е. в том месте, где было бы поле группы
	    	throw new Error("Ќа форме редактировани€ присутствует выбор группы");//если условие else закомментировать, то задержки не происходит.
	    typeText(By.name("address2"),contact.getAddress2());
	    typeText(By.name("phone2"),contact.getHomePhone2());
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
