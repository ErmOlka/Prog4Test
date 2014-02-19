package com.example.tests;

import static com.example.tests.ContactDataGenerator.loadContactsFromXmlFile;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactCreationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> ContactsFromFile() throws IOException {
		return wrapContactsForDataProvider(loadContactsFromXmlFile(new File("contacts.xml"))).iterator();
	}

	@Test(dataProvider = "ContactsFromFile")
    public void testContactCreationValidData(ContactData contact) throws Exception {
	    
	    //save old state
		System.out.println("contact" + contact);
    	SortedListOf<ContactData> oldList = app.getModel().getContacts();
    	System.out.println("oldList" + oldList);
    	
    	//actions
    	app.getContactHelper().creationContact(contact);
		
		//save new states
    	SortedListOf<ContactData> newList = app.getModel().getContacts();
    	System.out.println("newList" + newList);
		
		//compare states
    	assertThat(newList, equalTo(oldList.withAdded(contact)));
    	
		//compare model to implementation
    	
		if (wantToCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) {
				SortedListOf<ContactData> modelContacts = app.getModel().getContacts();
				SortedListOf<ContactData> dbContacts = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());
				System.out.println("modelContacts" + modelContacts);
				System.out.println("dbContacts" + dbContacts);
				assertThat(modelContacts, equalTo(dbContacts));
				System.out.println("check db has been implemented");
			}
			if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getUIContacts(true)));
				System.out.println("check ui has been implemented");
			}
		}
    }
	
	/*
	@Test(dataProvider = "ContactsFromFile")
    public void testContactCreationValidData(ContactData contact) throws Exception {
	    
	    //save old state
    	SortedListOf<ContactData> oldList = app.getContactHelper().getContacts(true);
    	
    	//actions
    	app.getContactHelper().creationContact(contact);
		
		//save new states
    	SortedListOf<ContactData> newList = app.getContactHelper().getContacts(true);
		
		//compare states
    	assertThat(newList, equalTo(oldList.withAdded(contact)));
    }
    */
 
}