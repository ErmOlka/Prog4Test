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
	
	//провайдер дл€ создани€, модификации групп
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
	    	throw new Error("Ќет групп дл€ удалени€"); //почему-то очень долго обрабатываетс€
		int countForDelete = rnd.nextInt(maxCount); //если надо удалить конкретное количество групп, то вместо rnd надо подставить нужное число
		for (int i = 0; i < countForDelete; i++) {
			int index = 0;
			index = rnd.nextInt(countForDelete);
			list.add(new Object[] {index});
		}
		return list.iterator();
	}
	
	//провайдер дл€ создани€, модификации контактов
	@DataProvider
	public Iterator<Object[]> radomValidContactGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		String gender = "";
		for (int i = 0; i < 3; i++) {
			int rand = rnd.nextInt(11);
			if (rand == 0)
				gender = "U";
			if (rand > 0 && rand <= 5)
				gender = "M";
			if (rand > 5) 
				gender = "F";
			String birthDay = app.getRandomHelper().randomValue("RandomDay");
			if (birthDay == null || birthDay == "")
				birthDay = "-";
			String birthMonth = app.getRandomHelper().randomValue("RandomMonth");
			if (birthMonth == null || birthMonth == "")
				birthMonth = "-";
			String contactGroup = app.getRandomHelper().randomValue("RandomGroup");
			if (contactGroup == null || contactGroup == "")
				contactGroup = "[none]";
			ContactData contact = new ContactData()
				.withFirstName(app.getRandomHelper().randomValue(app.getRandomHelper().randomFirstName(gender)))
				.withLastName(app.getRandomHelper().randomValue(app.getRandomHelper().randomLastName(gender)))
				.withAddress1(app.getRandomHelper().randomValue("јдрес 1: " + app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(30))))
				.withHomePhone1(app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7)))
				.withMobilePhone(app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7)))
				.withWorkPhone(app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7)))
				.withEmail1(app.getRandomHelper().randomValue(app.getRandomHelper().randomEMail(9,5,3)))
				.withEmail2(app.getRandomHelper().randomValue(app.getRandomHelper().randomEMail(7,4,2)))
				.withBirthDay(birthDay)
				.withBirthMonth(birthMonth)
				.withBirthYear(app.getRandomHelper().randomValue("19" + app.getRandomHelper().randomStringNumeric(2)))
				.withContactGroup(contactGroup)
				.withAddress2(app.getRandomHelper().randomValue("јдрес 2: " + app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(100))))
				.withHomePhone2(app.getRandomHelper().randomValue(app.getRandomHelper().randomPhoneNumber(1,3,7)));
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
	    	throw new Error("Ќет контактов дл€ удалени€"); //почему-то очень долго обрабатываетс€
		int countForDelete = rnd.nextInt(maxCount); //если надо удалить конкретное количество контактов, то вместо rnd надо подставить нужное число
		for (int i = 0; i < countForDelete; i++) {
			int index = 0;
			index = rnd.nextInt(countForDelete);
			list.add(new Object[] {index});
		}
		return list.iterator();
	}

}
