package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class ContactRemovalTests extends TestBase{
	
	@Test(dataProvider = "deletionSomeContacts") //удаление нескольких контактов, но без вывода в отчет информации о них
	public void deleteRandomContacts(int index) {
		//save old state
		SortedListOf<ContactData> oldList = app.getModel().getContacts();
		
    	//actions
	    app.getContactHelper().deleteContactByIndex(index);
	    
		//save new states
	    SortedListOf<ContactData> newList = app.getModel().getContacts();
	    
		//compare states
	    assertThat(newList, equalTo(oldList.without(index)));
	    
		//compare model to implementation
    	if (wantToCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) {
				assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().listContacts()));
				System.out.println("check db has been implemented");
			}
			if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getUIContacts(true)));
				System.out.println("check ui has been implemented");
			}
		}
	}
	
/*
	@Test(dataProvider = "deletionSomeContact") //удаление одного контакта с выводом в отчет информации о нем
	public void deleteRandomContact(ContactData contact) {
		//save old state
		SortedListOf<ContactData> oldList = app.getModel().getContacts();
		
    	//actions
	    app.getContactHelper().deleteContact(contact);
	    
		//save new states
	    SortedListOf<ContactData> newList = app.getModel().getContacts();
	    
		//compare states
	    assertThat(newList, equalTo(oldList.without(contact)));
	    
		//compare model to implementation
    	if (wantToCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) {
				assertThat(app.getModel().getContacts(), equalTo(app.getHibernateHelper().listContacts()));
				System.out.println("check db has been implemented");
			}
			if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getContacts(), equalTo(app.getContactHelper().getUIContacts(true)));
				System.out.println("check ui has been implemented");
			}
		}
	}
	*/
	
}
