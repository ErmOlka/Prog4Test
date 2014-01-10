package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class ContactCreationTests extends TestBase {
	
    @Test(dataProvider = "radomValidContactGenerator")
    public void testContactCreationValidData(ContactData contact) throws Exception {
	    
	    //save old state
    	SortedListOf<ContactData> oldList = app.getContactHelper().getContacts();
    	
    	//actions
    	app.getContactHelper().creationContact(contact);
	    
		//save new states
    	SortedListOf<ContactData> newList = app.getContactHelper().getContacts();
		
		//compare states
    	assertThat(newList, equalTo(oldList.withAdded(contact)));
    }
 
}