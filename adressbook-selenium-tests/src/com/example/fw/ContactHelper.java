package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.ContactData;
import com.example.utils.SortedListOf;

public class ContactHelper extends HelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private SortedListOf<ContactData> cachedContacts;
	
	public SortedListOf<ContactData> getContacts(boolean withEmail) {
		if (cachedContacts == null)
			rebuildCache(withEmail);
		return cachedContacts;
	}
	
	private void rebuildCache(boolean withEmail) {
		cachedContacts = new SortedListOf<ContactData>();
		
		manager.navigateTo().mainPage();
		if (isElementPresent(By.xpath("//tr[@name='entry']"))) {
			List<WebElement> rows = driver.findElements(By.xpath("//tr[@name='entry']"));
			for (int i = 0; i < rows.size(); i++) {
				WebElement row = rows.get(i);
				String firstName = row.findElement(By.xpath("./td[3]")).getText();
				String lastName = row.findElement(By.xpath("./td[2]")).getText();
				String phone = row.findElement(By.xpath("./td[5]")).getText();
				if (withEmail) {
					String email = row.findElement(By.xpath("./td[4]")).getText();
					cachedContacts.add(new ContactData()
											.withId(i + 1)
											.withFirstName(firstName)
											.withLastName(lastName)
											.withPhone(phone)
											.withEmail(email)
									);
				}
				else 
					cachedContacts.add(new ContactData()
											.withId(i + 1)
											.withFirstName(firstName)
											.withLastName(lastName)
											.withPhone(phone)
											.withEmail("")
									);
			}
		}
	}
	
	/*
	private void rebuildCache(boolean withEmail) {
		cachedContacts = new SortedListOf<ContactData>();
		
		manager.navigateTo().mainPage();
		if (isElementPresent(By.xpath("//tr[@name='entry']"))) {
			int tableRowsCount = driver.findElements(By.xpath("//tr[@name='entry']")).size();
			for (int i = 0; i < tableRowsCount; i++) {
				WebElement row = driver.findElement(By.xpath("//tr[" + (i + 2) + "]"));
				String firstName = row.findElement(By.xpath("./td[3]")).getText();
				String lastName = row.findElement(By.xpath("./td[2]")).getText();
				String phone = row.findElement(By.xpath("./td[5]")).getText();
				if (withEmail) {
					String email = row.findElement(By.xpath("./td[4]")).getText();
					cachedContacts.add(new ContactData()
											.withFirstName(firstName)
											.withLastName(lastName)
											.withPhone(phone)
											.withEmail(email)
									);
				}
				else 
					cachedContacts.add(new ContactData()
											.withFirstName(firstName)
											.withLastName(lastName)
											.withPhone(phone)
											.withEmail("")
									);
			}
		}
	}
	*/
	
	public SortedListOf<ContactData> getPrintContacts() {
		manager.navigateTo().printPhonesPage();
		SortedListOf<ContactData> contacts = new SortedListOf<ContactData>();
		int tableRowsCount = driver.findElements(By.xpath("//tr")).size();
		stop:
		for (int i = 0; i < tableRowsCount; i++) {
			int tableColumnsCount = driver.findElements(By.xpath("//tr[" + (i + 1) + "]/td")).size();
			for (int j = 0; j < tableColumnsCount; j++) {
				String firstName = null;
				String lastName = null;
				String phone = null;
				
				String contactInfo = driver.findElement(By.xpath("//tr[" + (i + 1) + "]/td[" + (j + 1) + "]")).getText();
				if (contactInfo.equals(".")) //контакты в таблице закончились
					break stop;
				if (contactInfo.indexOf(":") == 0) { //если нет ни имени, ни фамилии
					firstName = "";
					lastName = "";
				}
				else {
					firstName = contactInfo.substring(0,contactInfo.indexOf(":"));
					if (firstName.length() == firstName.indexOf(" ") + 1) { //если есть только имя без фамилии
						firstName = firstName.trim();
						lastName = "";
					}
					else if (! firstName.contains(" ")) { //если есть только фамилия без имени
						lastName = firstName;
						firstName = "";
					}
					else {//есть и имя, и фамилия
						firstName = contactInfo.substring(0,contactInfo.indexOf(" "));
						lastName = contactInfo.substring(contactInfo.indexOf(" ") + 1,contactInfo.indexOf(":"));
					}
				}
				
				if (contactInfo.contains("H: ")) { //если есть домашний телефон
					if (contactInfo.contains("M: ")) //если есть мобильный
						phone = contactInfo.substring(contactInfo.indexOf("H: ") + 3, contactInfo.indexOf("M: ")).replace("\n", "");
					else if (contactInfo.contains("W: ")) //нет мобильного, но есть рабочий
						phone = contactInfo.substring(contactInfo.indexOf("H: ") + 3, contactInfo.indexOf("W: ")).replace("\n", "");
					else if (contactInfo.contains("Birthday: ")) //нет мобильного и рабочего, но есть дата рождения
						phone = contactInfo.substring(contactInfo.indexOf("H: ") + 3, contactInfo.indexOf("Birthday: ")).replace("\n", "");
					else if (contactInfo.contains("P: ")) //нет мобильного, рабочего и даты рождения, но есть второй домашний
						phone = contactInfo.substring(contactInfo.indexOf("H: ") + 3, contactInfo.indexOf("P: ")).replace("\n", "");
					else phone = contactInfo.substring(contactInfo.indexOf("H: ") + 3); //нет никаких данных после домашнего телефона
				}
				
				else if (contactInfo.contains("M: ")) { //если нет домашнего, но есть мобильный телефон
					if (contactInfo.contains("W: ")) //есть рабочий телефон
						phone = contactInfo.substring(contactInfo.indexOf("M: ") + 3, contactInfo.indexOf("W: ")).replace("\n", "");
					else if (contactInfo.contains("Birthday: ")) //нет рабочего, но есть дата рождения
						phone = contactInfo.substring(contactInfo.indexOf("M: ") + 3, contactInfo.indexOf("Birthday: ")).replace("\n", "");
					else if (contactInfo.contains("P: ")) //нет рабочего и даты рождения, но есть второй домашний
						phone = contactInfo.substring(contactInfo.indexOf("M: ") + 3, contactInfo.indexOf("P: ")).replace("\n", "");
					else phone = contactInfo.substring(contactInfo.indexOf("M: ") + 3); //нет никаких данных после домашнего телефона
				}
				
				else if (contactInfo.contains("W: ")) { //если нет домашнего и мобильного, но есть рабочий телефон
					if (contactInfo.contains("Birthday: ")) //есть дата рождения
						phone = contactInfo.substring(contactInfo.indexOf("W: ") + 3, contactInfo.indexOf("Birthday: ")).replace("\n", "");
					else if (contactInfo.contains("P: ")) //нет даты рождения, но есть второй домашний
						phone = contactInfo.substring(contactInfo.indexOf("W: ") + 3, contactInfo.indexOf("P: ")).replace("\n", "");
					else phone = contactInfo.substring(contactInfo.indexOf("W: ") + 3); //нет никаких данных после домашнего телефона
				}
			
				else phone = "";
				
				contacts.add(new ContactData()
									.withFirstName(firstName)
									.withLastName(lastName)
									.withPhone(phone)
									.withEmail("")
							);
			}
		}
		return contacts;
	}
	
	public ContactHelper creationContact(ContactData contact) {
		manager.navigateTo().mainPage();
	    initCreationContact();
		fillContactForm(contact,CREATION);
		submitCreationContact();
	    returnToHomePage();
	    rebuildCache(true);
	    return this;
	}
	

	public ContactHelper modifyContactByIndex(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		initContactModificationByIndex(index);
		fillContactForm(contact,MODIFICATION);
		submitContactModification();
		returnToHomePage();
		rebuildCache(true);
		return this;
	}
	
	
	public ContactHelper deleteContact(ContactData contact) {
		manager.navigateTo().mainPage();
		initContactModification(contact); 
		submitContactDeletion(); 
		returnToHomePage();
		rebuildCache(true);
		return this;
	}
	
	public ContactHelper deleteContactByIndex(int index) {
		manager.navigateTo().mainPage();
		initContactModificationByIndex(index); 
		submitContactDeletion(); 
		returnToHomePage();
		rebuildCache(true);
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
	    else if (isElementPresent(By.name("new_group")))
	    	throw new Error("На форме редактирования присутствует выбор группы");
	    typeText(By.name("address2"),contact.getAddress2());
	    typeText(By.name("phone2"),contact.getHomePhone2());
	    return this;
	}

	public ContactHelper submitCreationContact() {
		click(By.name("submit"));
		cachedContacts = null;
		return this;
	}
	
	public ContactHelper initContactModification(ContactData contact) {
		click(By.xpath("(//img[@alt='Edit'])[" + (contact.getId()) + "]"));
		return this;
	}
	
	public ContactHelper initContactModificationByIndex(int index) {
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
