package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class ContactRemovalTests extends TestBase{
	
	@Test(dataProvider = "deletionSomeContact") //�������� ������ �������� � ������� � ����� ���������� � ���
	public void deleteRandomContact(ContactData contact) {
		//save old state
		SortedListOf<ContactData> oldList = app.getContactHelper().getUIContacts(true);
		
    	//actions
	    app.getContactHelper().deleteContact(contact);
	    
		//save new states
	    SortedListOf<ContactData> newList = app.getContactHelper().getUIContacts(true);
	    
		//compare states
	    assertThat(newList, equalTo(oldList.without(contact)));
	}
	
	@Test(dataProvider = "deletionSomeContacts") //�������� ���������� ���������, �� ��� ������ � ����� ���������� � ���
	public void deleteRandomContacts(int index) {
		//save old state
		SortedListOf<ContactData> oldList = app.getContactHelper().getUIContacts(true);
		
    	//actions
	    app.getContactHelper().deleteContactByIndex(index);
	    
		//save new states
	    SortedListOf<ContactData> newList = app.getContactHelper().getUIContacts(true);
	    
		//compare states
	    assertThat(newList, equalTo(oldList.without(index)));
	}
	
}
