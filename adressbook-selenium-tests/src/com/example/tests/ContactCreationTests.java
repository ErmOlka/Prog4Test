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
  public void testEmptyContactCreation() throws Exception {
    openMainPage();
    initCreationContact();
	submitCreationContact();
    returnToHomePage();
  }
}
