package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

import static com.example.fw.ContactHelper.MODIFICATION;

public class ContactModificationTests extends TestBase {
	
	@Test(dataProvider = "radomValidContactGenerator")
	public void modifyRandomContact(ContactData contact) {
	    
	    //save old state
    	List<ContactData> oldList = app.getContactHelper().getContacts();
    	
    	Random rnd = new Random();
    	
    	//actions
	    if (oldList.isEmpty() == true) 
	    	throw new Error("modifyRandomContact: Ќет контактов, редактировать нечего");
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    app.getContactHelper().modifyContact(index,contact,MODIFICATION);
	    
		//save new states
		List<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
		oldList.remove(index);
	    oldList.add(contact);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}

}
