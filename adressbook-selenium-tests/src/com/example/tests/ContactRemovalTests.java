package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	//удаление некоторого рандомного количества рандомных контактов
	@Test(dataProvider = "deletionSomeContacts") 
	public void deleteRandomNumberOfRandomContacts(int index) {
	    
	    //save old state
    	List<ContactData> oldList = app.getContactHelper().getContacts();
	    
    	//actions
	    app.getContactHelper().deleteContact(index);
	    
		//save new states
		List<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
		oldList.remove(index);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}
	
}
