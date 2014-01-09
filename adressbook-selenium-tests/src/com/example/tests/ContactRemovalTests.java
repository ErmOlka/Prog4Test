package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class ContactRemovalTests extends TestBase{
	
	@Test
	public void deleteFirstContact() {
		app.navigateTo().mainPage();
	    
	    //save old state
    	List<ContactData> oldList = app.getContactHelper().getContacts();
    	
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
    	//actions
	    if (oldList.isEmpty() == true) 
	    	System.out.println("modifyRandomContact: Ќет контактов, редактировать нечего");
	    app.getContactHelper().initContactModification(index); 
		app.getContactHelper().deleteContact(); 
		app.getContactHelper().returnToHomePage();
	    
		//save new states
		List<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
		oldList.remove(index);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}
	
}
