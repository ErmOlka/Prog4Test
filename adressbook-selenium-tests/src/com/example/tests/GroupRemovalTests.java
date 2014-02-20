package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupRemovalTests extends TestBase {
	
	//удаление некоторого рандомного количества рандомных групп
	@Test(dataProvider = "deletionSomeGroups") 
	public void deleteRandomNumberOfRandomGroups(SortedListOf<Integer> indexesList) {
	    
	    //save old state
		SortedListOf<GroupData> oldList = app.getModel().getGroups();
	    
	    //actions
		app.getGroupHelper().deleteGroup(indexesList);
	    
	    //save new state
		SortedListOf<GroupData> newList = app.getModel().getGroups();
	    
	    //compare states
		assertThat(newList, equalTo(oldList.without(indexesList)));
		
		//compare model to implementation
		if (wantToCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) {
				assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().listGroups()));
				System.out.println("check db has been implemented");
			}
			if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getUIGroups()));
				System.out.println("check ui has been implemented");
			}
		}
	}

}