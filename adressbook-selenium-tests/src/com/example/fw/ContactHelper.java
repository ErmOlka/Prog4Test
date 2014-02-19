package com.example.fw;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.example.tests.ContactData;
import com.example.utils.ListOf;
import com.example.utils.SortedListOf;

public class ContactHelper extends WebDriverHelperBase {
	
	public static boolean CREATION = true;
	public static boolean MODIFICATION = false;
	
	public ContactHelper(ApplicationManager manager) {
		super(manager);
	}
	
	public SortedListOf<ContactData> getUIContacts(boolean withEmail) {
		SortedListOf<ContactData> сontacts = new SortedListOf<ContactData>();
		
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
					сontacts.add(new ContactData()
											.withId(i + 1)
											.withFirstName(firstName)
											.withLastName(lastName)
											.withPhone(phone)
											.withEmail(email)
									);
				}
				else 
					сontacts.add(new ContactData()
											.withId(i + 1)
											.withFirstName(firstName)
											.withLastName(lastName)
											.withPhone(phone)
											.withEmail("")
									);
			}
		}
		return сontacts;
	}
	
	public SortedListOf<ContactData> getPrintContacts() {
		manager.navigateTo().printPhonesPage();
		SortedListOf<ContactData> contacts = new SortedListOf<ContactData>();
		if (! isElementPresent(By.xpath("//tr")))
			return contacts;
		int tableRowsCount = driver.findElements(By.xpath("//tr")).size();
		ListOf<String> cellsText = getCellsText(tableRowsCount);
		for(String cellText : cellsText) {
			String firstLastName = findFirstLastName(cellText);
			String firstName = firstLastName.substring(0, firstLastName.indexOf("~"));
			String lastName = firstLastName.substring(firstLastName.indexOf("~") + 1);
			String phone = findPhone(cellText);
			contacts.add(new ContactData()
								.withFirstName(firstName)
								.withLastName(lastName)
								.withPhone(phone)
								.withEmail("")
						);
		}
		return contacts;
	}
	
	public ContactHelper creationContact(ContactData contact) {
		manager.navigateTo().mainPage();
	    initCreationContact();
		fillContactForm(contact,CREATION);
		submitCreationContact();
	    returnToHomePage();
		//update model
    	manager.getModel().addContact(contact);
	    return this;
	}
	
	public ContactHelper modifyContactByIndex(int index, ContactData contact) {
		manager.navigateTo().mainPage();
		initContactModificationByIndex(index);
		fillContactForm(contact,MODIFICATION);
		submitContactModification();
		returnToHomePage();
		//update model
    	manager.getModel().removeContact(index).addContact(contact);
		return this;
	}	
	
	public ContactHelper deleteContact(ContactData contact) {
		manager.navigateTo().mainPage();
		initContactModification(contact); 
		submitContactDeletion(); 
		returnToHomePage();
		//update model
    	manager.getModel().removeContact(contact);
		return this;
	}
	
	public ContactHelper deleteContactByIndex(int index) {
		manager.navigateTo().mainPage();
		initContactModificationByIndex(index); 
		submitContactDeletion(); 
		returnToHomePage();
		//update model
    	manager.getModel().removeContact(index);
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
	    if (formType == CREATION) {
	    	randomDropDownValue(By.name("new_group"));
	    }
	    else if (isElementPresent(By.name("new_group")))
	    	throw new Error("На форме редактирования присутствует выбор группы");
	    typeText(By.name("address2"),contact.getAddress2());
	    typeText(By.name("phone2"),contact.getHomePhone2());
	    return this;
	}

	public ContactHelper submitCreationContact() {
		click(By.name("submit"));
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
		return this;
	}
	
	public ContactHelper submitContactDeletion() {
		click(By.xpath("//input[@type='submit'][@value='Delete']"));
		return this;
	}

	public ContactHelper returnToHomePage() {
		click(By.linkText("home page"));
		return this;
	}
	
	private ListOf<String> getCellsText(int tableRowsCount) {
		ListOf<String> cellsText = new ListOf<String>();
		stop:
		for (int i = 0; i < tableRowsCount; i++) {
			int tableColumnsCount = driver.findElements(By.xpath("//tr[" + (i + 1) + "]/td")).size();
			for (int j = 0; j < tableColumnsCount; j++) {
				String contactInfo = driver.findElement(By.xpath("//tr[" + (i + 1) + "]/td[" + (j + 1) + "]")).getText();
				if (contactInfo.equals(".")) //контакты в таблице закончились
					break stop;
				cellsText.add(contactInfo);
			}
		}
		return cellsText;
	}
	
	private String findFirstLastName(String cellText) {
		String firstName = null;
		String lastName = null;
		
		if (cellText.indexOf(":") == 0) { //если нет ни имени, ни фамилии
			firstName = "";
			lastName = "";
		}
		else {
			firstName = cellText.substring(0,cellText.indexOf(":"));
			if (firstName.length() == firstName.indexOf(" ") + 1) { //если есть только имя без фамилии
				firstName = firstName.trim();
				lastName = "";
			}
			else if (! firstName.contains(" ")) { //если есть только фамилия без имени
				lastName = firstName;
				firstName = "";
			}
			else {//есть и имя, и фамилия
				firstName = cellText.substring(0,cellText.indexOf(" "));
				lastName = cellText.substring(cellText.indexOf(" ") + 1,cellText.indexOf(":"));
			}
		}
		
		return firstName + "~" + lastName;
	}
	
	private String findPhone(String cellText) {
		String phone = null;
		if (cellText.contains("H: ")) { //если есть домашний телефон
			if (cellText.contains("M: ")) //если есть мобильный
				phone = phone(cellText, "H: ", "M: ", "\n", "");
			else if (cellText.contains("W: ")) //нет мобильного, но есть рабочий
				phone = phone(cellText, "H: ", "W: ", "\n", "");
			else if (cellText.contains("Birthday: ")) //нет мобильного и рабочего, но есть дата рождения
				phone = phone(cellText, "H: ", "Birthday: ", "\n", "");
			else if (cellText.contains("P: ")) //нет мобильного, рабочего и даты рождения, но есть второй домашний
				phone = phone(cellText, "H: ", "P: ", "\n", "");
			else phone = cellText.substring(cellText.indexOf("H: ") + 3); //нет никаких данных после домашнего телефона
		}
		
		else if (cellText.contains("M: ")) { //если нет домашнего, но есть мобильный телефон
			if (cellText.contains("W: ")) //есть рабочий телефон
				phone = phone(cellText, "M: ", "W: ", "\n", "");
			else if (cellText.contains("Birthday: ")) //нет рабочего, но есть дата рождения
				phone = phone(cellText, "M: ", "Birthday: ", "\n", "");
			else if (cellText.contains("P: ")) //нет рабочего и даты рождения, но есть второй домашний
				phone = phone(cellText, "M: ", "P: ", "\n", "");
			else phone = cellText.substring(cellText.indexOf("M: ") + 3); //нет никаких данных после домашнего телефона
		}
		
		else if (cellText.contains("W: ")) { //если нет домашнего и мобильного, но есть рабочий телефон
			if (cellText.contains("Birthday: ")) //есть дата рождения
				phone = phone(cellText, "W: ", "Birthday: ", "\n", "");
			else if (cellText.contains("P: ")) //нет даты рождения, но есть второй домашний
				phone = phone(cellText, "W: ", "P: ", "\n", "");
			else phone = cellText.substring(cellText.indexOf("W: ") + 3); //нет никаких данных после домашнего телефона
		}
	
		else phone = "";
		
		return phone;
	}

	private String phone(String cellText, String textLable1, String textLable2, String textLable3, String textLable4) {
		return cellText.substring(cellText.indexOf(textLable1) + 3, cellText.indexOf(textLable2)).replace(textLable3, textLable4);
	}
	
}
