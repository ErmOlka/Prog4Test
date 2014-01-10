package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

import static com.example.fw.ContactHelper.CREATION;

public class ContactCreationTests extends TestBase {
	
    @Test(dataProvider = "radomValidContactGenerator")
    public void testContactCreationValidData(ContactData contact) throws Exception {
	    
	    //save old state
    	List<ContactData> oldList = app.getContactHelper().getContacts();
    	
    	//actions
    	app.getContactHelper().creationContact(contact,CREATION);
	    
		//save new states
		List<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
	    oldList.add(contact);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
  }
 
}