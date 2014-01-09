package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactCreationTests extends TestBase {
	
    @Test(dataProvider = "radomValidContactCreationGenerator")
    public void testContactCreationValidData(ContactData contact) throws Exception {
    	app.getNavigationHelper().openMainPage();
	    
	    //save old state
    	List<ContactData> oldList = app.getContactHelper().getContacts();
    	
    	//actions
	    app.getContactHelper().initCreationContact();
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitCreationContact();
	    app.getContactHelper().returnToHomePage();
	    
		//save new states
		List<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
	    oldList.add(contact);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
  }
 
}