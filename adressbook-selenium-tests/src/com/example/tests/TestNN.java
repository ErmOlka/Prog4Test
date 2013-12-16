package com.example.tests;

import org.testng.annotations.Test;

public class TestNN extends TestBase {
	
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
	fillContactForm(contact);
	submitCreationContact();
    returnToHomePage();
  }
}