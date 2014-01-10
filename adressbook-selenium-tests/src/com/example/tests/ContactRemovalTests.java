package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class ContactRemovalTests extends TestBase{
	
	//удаление некоторого рандомного количества рандомных контактов
	@Test(dataProvider = "deletionSomeContacts") 
	public void deleteRandomNumberOfRandomContacts(int index) {
	    
	    //save old state
		SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();
	    
    	//actions
	    app.getContactHelper().deleteContact(index);
	    
		//save new states
	    SortedListOf<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
	    assertThat(newList, equalTo(oldList.without(index)));
	}
	
}
