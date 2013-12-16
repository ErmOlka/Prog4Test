package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
	
  @Test
  public void testRandomContactCreation() throws Exception {
    openMainPage();
    initCreationContact();
    ContactData contact = new ContactData();
	contact.firstName = randomStringLatAlphaNumeric(5);
	contact.lastName = randomStringLatAlphaNumeric(7);
	contact.address1 = randomStringLatAlphaNumeric(20);
	contact.homePhone1 = randomStringNumeric(7);
	contact.mobilePhone = randomStringNumeric(7);
	contact.workPhone = randomStringNumeric(7);
	contact.email1 = "qwerty@qwe.rty";
	contact.email2 = "asdfg@as.dfg";
	contact.bDay = "9";
	contact.bMonth = "September";
	contact.bYear = "1978";
	contact.address2 = randomStringLatAlphaNumeric(10);
	contact.homePhone2 = randomStringNumeric(7);
	fillContactForm(contact);
	submitCreationContact();
    returnToHomePage();
  }
  
  @Test
  public void testDefoltContactCreation() throws Exception {
    openMainPage();
    initCreationContact();
	submitCreationContact();
    returnToHomePage();
  }

  @Test
  public void testNoneGroupContactCreation() throws Exception {
	  openMainPage();
	  initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "FirstName";
		contact.lastName = "LastName";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.bDay = "-";
		contact.bMonth = "-";
		contact.bYear = "";
		contact.address2 = "";
		contact.homePhone2 = "";
		contact.contactGroup = "[none]";
		fillContactForm(contact);
		submitCreationContact();
	  returnToHomePage();
	}
  
  @Test
  public void testEmptyContactCreation() throws Exception {
	  openMainPage();
	  initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "";
		contact.lastName = "";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.bDay = "-";
		contact.bMonth = "-";
		contact.bYear = "";
		contact.address2 = "";
		contact.homePhone2 = "";
		contact.contactGroup = "[none]";
		fillContactForm(contact);
		submitCreationContact();
	  returnToHomePage();
	}
  
  @Test
  public void testBdayContactCreation() throws Exception {
	  openMainPage();
	  initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "BDay";
		contact.lastName = "WithoutBMonth";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.bDay = "5";
		contact.bMonth = "-";
		contact.bYear = "";
		contact.address2 = "";
		contact.homePhone2 = "";
		contact.contactGroup = "[none]";
		fillContactForm(contact);
		submitCreationContact();
	  returnToHomePage();
	}
  
  @Test
  public void testBMonthContactCreation() throws Exception {
	  openMainPage();
	  initCreationContact();
	  ContactData contact = new ContactData();
		contact.firstName = "BMonth";
		contact.lastName = "WithoutBDay";
		contact.address1 = "";
		contact.homePhone1 = "";
		contact.mobilePhone = "";
		contact.workPhone = "";
		contact.email1 = "";
		contact.email2 = "";
		contact.bDay = "-";
		contact.bMonth = "May";
		contact.bYear = "";
		contact.address2 = "";
		contact.homePhone2 = "";
		contact.contactGroup = "[none]";
		fillContactForm(contact);
		submitCreationContact();
	  returnToHomePage();
	}
}