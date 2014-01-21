package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;
import com.example.utils.SortedListOf;

import static com.example.tests.GroupDataGenerator.generateRandomGroups;

public class TestBase {
	
	public ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		app = new ApplicationManager();
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }
	
	//провайдер для создания, модификации групп
	@DataProvider
	public Iterator<Object[]> radomValidGroupGenerator() {
		return wrapGroupsForDataProvider(generateRandomGroups(5)).iterator();
	}

	public static List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (GroupData group : groups) {
			list.add(new Object[]{group});
		}
		return list;
	}

	//провайдер для удаления групп
	@DataProvider
	public Iterator<Object[]> deletionSomeGroups() {
		List<Object[]> list = new ArrayList<Object[]>();
		SortedListOf<Integer> indexesList = app.getRandomHelper().randomIndexesList();
		list.add(new Object[] {indexesList});
		return list.iterator();
	}
	
	//провайдер для создания, модификации контактов
	@DataProvider
	public Iterator<Object[]> radomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		boolean gender = true;
		for (int i = 0; i < 3; i++) {
			int rand = rnd.nextInt(10);
			if (rand < 5)
				gender = true; //male
			if (rand >= 5) 
				gender = false; //female
			String birthDay = app.getRandomHelper().randomValue("RandomDay");
			if (birthDay == null || birthDay == "")
				birthDay = "-";
			String birthMonth = app.getRandomHelper().randomValue("RandomMonth");
			if (birthMonth == null || birthMonth == "")
				birthMonth = "-";
			String contactGroup = app.getRandomHelper().randomValue("RandomGroup");
			if (contactGroup == null || contactGroup == "")
				contactGroup = "[none]";
			
			String homePhone1 = app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7));
			String mobilePhone = app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7));
			String workPhone = app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7));
			String email1 = app.getRandomHelper().randomValue(app.getRandomHelper().randomEMail(9,5,3));
			String email2 = app.getRandomHelper().randomValue(app.getRandomHelper().randomEMail(7,4,2));

			String phone = null;
			if (homePhone1 == null || homePhone1.equals("")) {
				if (mobilePhone != null && ! mobilePhone.equals(""))
					phone = mobilePhone;
				else if (workPhone != null && ! workPhone.equals(""))
					phone = workPhone;
				else phone = "";
			}
			else phone = homePhone1;
						
			String email = null;
			if (email1 == null || email1.equals("")) {
				if (email2 != null && ! email2.equals(""))
					email = email2;
				else email = "";
			}
			else email = email1;
			
			ContactData contact = new ContactData()
				.withFirstName(app.getRandomHelper().randomValue(app.getRandomHelper().randomFirstName(gender)))
				.withLastName(app.getRandomHelper().randomValue(app.getRandomHelper().randomLastName(gender)))
				.withAddress1(app.getRandomHelper().randomValue("Адрес 1: " + app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(30))))
				.withHomePhone1(homePhone1)
				.withMobilePhone(mobilePhone)
				.withWorkPhone(workPhone)
				.withEmail1(email1)
				.withEmail2(email2)
				.withBirthDay(birthDay)
				.withBirthMonth(birthMonth)
				.withBirthYear(app.getRandomHelper().randomValue("19" + app.getRandomHelper().randomStringNumeric(2)))
				.withContactGroup(contactGroup)
				.withAddress2(app.getRandomHelper().randomValue("Адрес 2: " + app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(100))))
				.withHomePhone2(homePhone1)
				.withPhone(phone)
				.withEmail(email);
			list.add(new Object[] {contact});
		}
		return list.iterator();
	}
	
	//провайдер для удаления контакта с выводом в отчет информации о нем
	@DataProvider
	public Iterator<Object[]> deletionSomeContact() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		int maxCount = app.getContactHelper().getContacts(true).size();
		if (! app.getContactHelper().isElementPresent(By.xpath("(//img[@alt='Edit'])")))
			throw new Error("Нет контактов");
		int index = rnd.nextInt(maxCount);
		ContactData contact = app.getContactHelper().getContacts(true).get(index);
		list.add(new Object[] {contact});
		return list.iterator();
	}
	
	//провайдер для удаления некоторого количества контактов, но без вывода в отет информации о них
	@DataProvider
	public Iterator<Object[]> deletionSomeContacts() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		int maxCount = app.getContactHelper().getContacts(true).size();
	    if (maxCount == 0) 
	    	throw new Error("Нет контактов для удаления"); 
	    int countForDelete = rnd.nextInt(maxCount);//если надо удалить конкретное количество контактов, то вместо rnd надо подставить нужное число
	    if (countForDelete == 0)
	    	countForDelete = 1;
		for (int i = 0; i < countForDelete; i++) {
			maxCount = maxCount - 1;
			if (maxCount == 0)
				throw new Error("Нет контактов для удаления");
			int index = rnd.nextInt(maxCount);
			list.add(new Object[] {index});
		}
		return list.iterator();
	}

}
