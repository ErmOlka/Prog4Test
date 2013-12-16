package com.example.tests;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {

  @Test
  public void testNonEmptyContactCreation() throws Exception {
    openMainPage();
    initCreationContact();
    ContactData contact = new ContactData();
	contact.firstName = "FirstName 1";
	contact.lastName = "LastName 1";
	contact.address1 = "Adress 1\nPodAdress 1";
	contact.homePhone1 = "1234567";
	contact.mobilePhone = "7654321";
	contact.workPhone = "0987654";
	contact.email1 = "qwerty@qwe.rty";
	contact.email2 = "asdfg@as.dfg";
	contact.bDay = "9";
	contact.bMonth = "September";
	contact.bYear = "1978";
	contact.address2 = "Adress 2\nPodAdress 2";
	contact.homePhone2 = "3456789";
	contact.contactGroup = "Group name 1";
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