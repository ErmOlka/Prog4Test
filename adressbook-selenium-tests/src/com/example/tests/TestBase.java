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
	
	@DataProvider
	public Iterator<Object[]> radomValidGroupGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		for (int i = 0; i < 3; i++) {
			GroupData group = new GroupData();
			group.name = generateRandomString(20);
			group.header = generateRandomString(30);
			group.footer = generateRandomString(40);
			list.add(new Object[] {group});
		}
		return list.iterator();
	}
	
	public String generateRandomString(int lenght) {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return "";
		else if (rnd.nextInt(10) == 0)
			return null;
		else return app.getRandomHelper().randomStringEngAlphaNumeric(rnd.nextInt(lenght));
	}
	
	@DataProvider
	public Iterator<Object[]> radomValidContactCreationGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		String gender = "";
		for (int i = 0; i < 5; i++) {
			int rand = rnd.nextInt(7);
			if (rand == 0)
				gender = "U";
			if (rand > 0 && rand <= 3)
				gender = "M";
			if (rand > 3) 
				gender = "F";
			ContactData contact = new ContactData();
			contact.firstName = app.getRandomHelper().randomFirstName(gender);
			contact.lastName = app.getRandomHelper().randomLastName(gender);
			contact.address1 = "Адрес 1: " + generateRandomString(30);
			contact.homePhone1 = app.getRandomHelper().randomPhoneNumber(1,3,7);
			contact.mobilePhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
			contact.workPhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
			contact.email1 = app.getRandomHelper().randomEMail(9,5,3);
			contact.email2 = app.getRandomHelper().randomEMail(7,4,2);
			contact.birthDay = "RandomDay";
			contact.birthMonth = "RandomMonth";
			contact.birthYear = "19" + app.getRandomHelper().randomStringNumeric(2);
			//if (app.driver.findElements(By.name("new_group")).isEmpty() == false)
			contact.contactGroup = "RandomGroup";
			contact.address2 = "Адрес 2: " + generateRandomString(100);
			contact.homePhone2 = app.getRandomHelper().randomPhoneNumber(1,3,7);
			list.add(new Object[] {contact});
		}
		return list.iterator();
	}
	
	@DataProvider
	public Iterator<Object[]> radomValidContactModificationGenerator() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		String gender = "";
		for (int i = 0; i < 5; i++) {
			int rand = rnd.nextInt(7);
			if (rand == 0)
				gender = "U";
			if (rand > 0 && rand <= 3)
				gender = "M";
			if (rand > 3) 
				gender = "F";
			ContactData contact = new ContactData();
			contact.firstName = app.getRandomHelper().randomFirstName(gender);
			contact.lastName = app.getRandomHelper().randomLastName(gender);
			contact.address1 = "Адрес 1: " + generateRandomString(30);
			contact.homePhone1 = app.getRandomHelper().randomPhoneNumber(1,3,7);
			contact.mobilePhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
			contact.workPhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
			contact.email1 = app.getRandomHelper().randomEMail(9,5,3);
			contact.email2 = app.getRandomHelper().randomEMail(7,4,2);
			contact.birthDay = "RandomDay";
			contact.birthMonth = "RandomMonth";
			contact.birthYear = "19" + app.getRandomHelper().randomStringNumeric(2);
			//if (app.driver.findElements(By.name("new_group")).isEmpty() == false)
			//contact.contactGroup = "RandomGroup";
			contact.address2 = "Адрес 2: " + generateRandomString(100);
			contact.homePhone2 = app.getRandomHelper().randomPhoneNumber(1,3,7);
			list.add(new Object[] {contact});
		}
		return list.iterator();
	}

}
