package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static com.example.tests.ContactDataGenerator.loadContactsFromFile;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.fw.Contact;
import com.example.utils.SortedListOf;

public class TestContactCreation extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> ContactsFromFile() throws IOException {
		return wrapContactsForDataProvider(loadContactsFromFile(new File("contacts.csv"))).iterator();
	}

	@Test(dataProvider = "ContactsFromFile")
	public void shouldCreateContactWithValidData(Contact contact) throws IOException {
		File oldContacts = app.getContactHelper().oldContacts;
		File newContacts = app.getContactHelper().newContacts;
		
		//old states
		app.getContactHelper().exportContacts(oldContacts,true);
		SortedListOf<Contact> oldList = app.getContactHelper().loadExportedContactsFromFile(oldContacts);
		
		//actions
		app.getContactHelper().createContact(contact);
		
		//new states
		app.getContactHelper().exportContacts(newContacts,false);
		SortedListOf<Contact> newList = app.getContactHelper().loadExportedContactsFromFile(newContacts);
		
		//compare
		assertThat(newList, equalTo(oldList.withAdded(contact)));
	}

}
