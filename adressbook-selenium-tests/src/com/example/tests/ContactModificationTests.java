package com.example.tests;

import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> ContactsFromFile() throws IOException {
		return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
	}
	
	@Test(dataProvider = "ContactsFromFile")
	public void modifyRandomContact(ContactData contact) {
	    
	    //save old state
		SortedListOf<ContactData> oldList = app.getContactHelper().getUIContacts(true);
    	
    	Random rnd = new Random();
    	
    	//actions
	    if (oldList.isEmpty()) 
	    	throw new Error("Нет контактов для редактирования");
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    app.getContactHelper().modifyContactByIndex(index,contact);
	    
		//save new states
	    SortedListOf<ContactData> newList = app.getContactHelper().getUIContacts(true);
		
		//compare states
		assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));
	}

}
