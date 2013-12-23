package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
	
  @Test
  public void testRandomContactCreation() throws Exception {
    app.getNavigationHelper().openMainPage();
    app.getContactHelper().initCreationContact();
    ContactData contact = new ContactData();
	contact.firstName = app.randomStringLatAlphaNumeric(5);
	contact.lastName = app.randomStringLatAlphaNumeric(7);
	contact.address1 = app.randomStringLatAlphaNumeric(20);
	contact.homePhone1 = app.randomStringNumeric(7);
	contact.mobilePhone = app.randomStringNumeric(7);
	contact.workPhone = app.randomStringNumeric(7);
	contact.email1 = "qwerty@qwe.rty";
	contact.email2 = "asdfg@as.dfg";
	contact.birthDay = "9";
	contact.birthMonth = "September";
	contact.birthYear = "1978";
	contact.address2 = app.randomStringLatAlphaNumeric(10);
	contact.homePhone2 = app.randomStringNumeric(7);
	app.getContactHelper().fillContactForm(contact);
	app.getContactHelper().submitCreationContact();
    app.getContactHelper().returnToHomePage();
  }
  
  @Test
  public void testDefoltContactCreation() throws Exception {
    app.getNavigationHelper().openMainPage();
    app.getContactHelper().initCreationContact();
	app.getContactHelper().submitCreationContact();
    app.getContactHelper().returnToHomePage();
  }

  @Test
  public void testNoneGroupContactCreation() throws Exception {
	  app.getNavigationHelper().openMainPage();
	  app.getContactHelper().initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "FirstName";
		contact.lastName = "LastName";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.birthDay = "-";
		contact.birthMonth = "-";
		contact.birthYear = "";
		contact.address2 = "";
		contact.homePhone2 = "";
		//contact.contactGroup = "[none]";
		app.getContactHelper().fillContactForm(contact);
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
		contact.address2 = "";
		contact.homePhone2 = "";
		//contact.contactGroup = "[none]";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitCreationContact();
	  app.getContactHelper().returnToHomePage();
	}
  
  @Test
  public void testBdayContactCreation() throws Exception {
	  app.getNavigationHelper().openMainPage();
	  app.getContactHelper().initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "BDay";
		contact.lastName = "WithoutBMonth";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.birthDay = "5";
		contact.birthMonth = "-";
		contact.birthYear = "";
		contact.address2 = "";
		contact.homePhone2 = "";
		//contact.contactGroup = "[none]";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitCreationContact();
	  app.getContactHelper().returnToHomePage();
	}
  
  @Test
  public void testBMonthContactCreation() throws Exception {
	  app.getNavigationHelper().openMainPage();
	  app.getContactHelper().initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "BMonth";
		contact.lastName = "WithoutBDay";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.birthDay = "-";
		contact.birthMonth = "May";
		contact.birthYear = "";
		contact.address2 = "";
		contact.homePhone2 = "";
		//contact.contactGroup = "[none]";
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitCreationContact();
	  app.getContactHelper().returnToHomePage();
	}
}