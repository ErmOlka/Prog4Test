package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;
import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase {
	
	@Test(dataProvider = "radomValidContactGenerator")
	public void modifyRandomContact(ContactData contact) {
	    
	    //save old state
		SortedListOf<ContactData> oldList = app.getContactHelper().getContacts(true);
    	
    	Random rnd = new Random();
    	
    	//actions
	    if (oldList.isEmpty()) 
	    	throw new Error("Нет контактов для редактирования");
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    app.getContactHelper().modifyContact(index,contact);
	    
		//save new states
	    SortedListOf<ContactData> newList = app.getContactHelper().getContacts(true);
		
		//compare states
		assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));
	}

}
