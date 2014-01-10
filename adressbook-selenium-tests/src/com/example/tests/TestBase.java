package com.example.tests;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.example.fw.ApplicationManager;

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
	
	//провайдер дл€ данных группы (создание, модификаци€)
	@DataProvider
	public Iterator<Object[]> radomValidGroupGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		for (int i = 0; i < 3; i++) {
			GroupData group = new GroupData()
				.withName(app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(20)))
				.withHeader(app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(30)))
				.withFooter(app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(40)));
			list.add(new Object[] {group});
		}
		return list.iterator();
	}
	
	//провайдер дл€ удалени€ групп
	@DataProvider
	public Iterator<Object[]> deletionSomeGroups() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		int maxCount = app.getGroupHelper().getGroups().size();
	    if (maxCount == 0) 
	    	throw new Error("deleteRandomNumberOfRandomGroups: Ќет групп, удал€ть нечего"); //почему-то очень долго обрабатываетс€
		int countForDelete = rnd.nextInt(maxCount); //если надо удалить конкретное количество групп, вместо rnd надо подставить число
		for (int i = 0; i < countForDelete; i++) {
			int index = 0;
			index = rnd.nextInt(countForDelete);
			list.add(new Object[] {index});
		}
		return list.iterator();
	}
	
	//провайдер дл€ данных контакта (создание, модификаци€)
	@DataProvider
	public Iterator<Object[]> radomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		String gender = "";
		for (int i = 0; i < 3; i++) {
			int rand = rnd.nextInt(7);
			if (rand == 0)
				gender = "U";
			if (rand > 0 && rand <= 3)
				gender = "M";
			if (rand > 3) 
				gender = "F";
			ContactData contact = new ContactData();
			contact.firstName = app.getRandomHelper().randomValue(app.getRandomHelper().randomFirstName(gender));
			contact.lastName = app.getRandomHelper().randomValue(app.getRandomHelper().randomLastName(gender));
			contact.address1 = app.getRandomHelper().randomValue("јдрес 1: " + app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(30)));
			contact.homePhone1 = app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7));
			contact.mobilePhone = app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7));
			contact.workPhone = app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7));
			contact.email1 = app.getRandomHelper().randomValue(app.getRandomHelper().randomEMail(9,5,3));
			contact.email2 = app.getRandomHelper().randomValue(app.getRandomHelper().randomEMail(7,4,2));
			contact.birthDay = app.getRandomHelper().randomValue("RandomDay");
			if (contact.birthDay == null || contact.birthDay == "")
				contact.birthDay = "-";
			contact.birthMonth = app.getRandomHelper().randomValue("RandomMonth");
			if (contact.birthMonth == null || contact.birthMonth == "")
				contact.birthMonth = "-";
			contact.birthYear = app.getRandomHelper().randomValue("19" + app.getRandomHelper().randomStringNumeric(2));
			contact.contactGroup = app.getRandomHelper().randomValue("RandomGroup");
			if (contact.contactGroup == null || contact.contactGroup == "")
				contact.contactGroup = "[none]";
			contact.address2 = app.getRandomHelper().randomValue("јдрес 2: " + app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(100)));
			contact.homePhone2 = app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7));
			list.add(new Object[] {contact});
		}
		return list.iterator();
	}
	
	//провайдер дл€ удалени€ контактов
	@DataProvider
	public Iterator<Object[]> deletionSomeContacts() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		int maxCount = app.getContactHelper().getContacts().size();
	    if (maxCount == 0) 
	    	throw new Error("deleteRandomNumberOfRandomContacts: Ќет контактов, удал€ть нечего"); //почему-то очень долго обрабатываетс€
		int countForDelete = rnd.nextInt(maxCount); //если надо удалить конкретное количество контактов, вместо rnd надо подставить число
		for (int i = 0; i < countForDelete; i++) {
			int index = 0;
			index = rnd.nextInt(countForDelete);
			list.add(new Object[] {index});
		}
		return list.iterator();
	}

}
