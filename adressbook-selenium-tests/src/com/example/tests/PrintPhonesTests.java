package com.example.tests;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class PrintPhonesTests extends TestBase{

	@Test
	public void CheckPrintPhones() {
		//save state on home page
		SortedListOf<ContactData> homePageList = app.getContactHelper().getContacts();
	    
	    //actions

		
	    //save state on print page
	    SortedListOf<ContactData> printPageList = app.getContactHelper().getPrintContacts();
	    
	    //compare states
		assertThat(printPageList, equalTo(homePageList));
	}
}
