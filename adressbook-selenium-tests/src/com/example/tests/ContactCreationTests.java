package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
	
  @Test
  public void testRandomMaleContactCreation() throws Exception {
    app.getNavigationHelper().openMainPage();
    app.getContactHelper().initCreationContact();
    ContactData contact = new ContactData();
	contact.firstName = app.getRandomHelper().randomFirstName("M");
	contact.lastName = app.getRandomHelper().randomLastName("M");
	contact.address1 = "Адрес 1: " + app.getRandomHelper().randomStringEngAlphaNumeric(30);
	contact.homePhone1 = app.getRandomHelper().randomPhoneNumber(1,3,7);
	contact.mobilePhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
	contact.workPhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
	contact.email1 = app.getRandomHelper().randomEMail(9,5,3);
	contact.email2 = app.getRandomHelper().randomEMail(7,4,2);
	contact.birthDay = "RandomDay";
	contact.birthMonth = "RandomMonth";
	contact.birthYear = "19" + app.getRandomHelper().randomStringNumeric(2);
	contact.contactGroup = "RandomGroup";
	contact.address2 = "Адрес 2: " + app.getRandomHelper().randomStringEngAlphaNumeric(100);
	contact.homePhone2 = app.getRandomHelper().randomPhoneNumber(1,3,7);
	app.getContactHelper().fillContactForm(contact);
	app.getContactHelper().submitCreationContact();
    app.getContactHelper().returnToHomePage();
  }
  
  @Test
  public void testRandomFemaleContactCreation() throws Exception {
    app.getNavigationHelper().openMainPage();
    app.getContactHelper().initCreationContact();
    ContactData contact = new ContactData();
	contact.firstName = app.getRandomHelper().randomFirstName("F");
	contact.lastName = app.getRandomHelper().randomLastName("F");
	contact.address1 = "Адрес 1: " + app.getRandomHelper().randomStringRusAlphaNumeric(50);
	contact.homePhone1 = app.getRandomHelper().randomPhoneNumber(1,3,7);
	contact.mobilePhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
	contact.workPhone = app.getRandomHelper().randomPhoneNumber(1,3,7);
	contact.email1 = app.getRandomHelper().randomEMail(9,5,3);
	contact.email2 = app.getRandomHelper().randomEMail(7,4,2);
	contact.birthDay = "RandomDay";
	contact.birthMonth = "RandomMonth";
	contact.birthYear = "19" + app.getRandomHelper().randomStringNumeric(2);
	contact.contactGroup = "RandomGroup";
	contact.address2 = "Адрес 2: " + app.getRandomHelper().randomStringRusAlphaNumeric(70);
	contact.homePhone2 = app.getRandomHelper().randomPhoneNumber(1,3,7);
	app.getContactHelper().fillContactForm(contact);
	app.getContactHelper().submitCreationContact();
    app.getContactHelper().returnToHomePage();
  }
  
  @Test
  public void testDefaultContactCreation() throws Exception {
    app.getNavigationHelper().openMainPage();
    app.getContactHelper().initCreationContact();
	app.getContactHelper().submitCreationContact();
    app.getContactHelper().returnToHomePage();
  }

  @Test
  public void testEmptyContactCreation() throws Exception {
	  app.getNavigationHelper().openMainPage();
	  app.getContactHelper().initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "";
		contact.lastName = "";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.birthDay = "-";
		contact.birthMonth = "-";
		contact.birthYear = "";
		contact.contactGroup = "[none]";
		contact.address2 = "";
		contact.homePhone2 = "";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitCreationContact();
	  app.getContactHelper().returnToHomePage();
	}

}