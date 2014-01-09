package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class ContactModificationTests extends TestBase {
	
	@Test(dataProvider = "radomValidContactModificationGenerator")
	public void modifyRandomContact(ContactData contact) {
	    app.getNavigationHelper().openMainPage();
	    
	    //save old state
    	List<ContactData> oldList = app.getContactHelper().getContacts();
    	
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
    	
    	//actions
	    if (oldList.isEmpty() == true) 
	    	System.out.println("modifyRandomContact: Ќет контактов, редактировать нечего");
	    app.getContactHelper().initContactModification(index);
		app.getContactHelper().fillContactForm(contact);
		app.getContactHelper().submitContactModification();
		app.getContactHelper().returnToHomePage();
	    
		//save new states
		List<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
		oldList.remove(index);
	    oldList.add(contact);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}

}
