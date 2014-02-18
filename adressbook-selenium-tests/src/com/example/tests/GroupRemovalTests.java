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
		SortedListOf<GroupData> oldList = app.getGroupHelper().getUIGroups();
	    
	    //actions
		app.getGroupHelper().deleteGroup(indexesList);
	    
	    //save new state
		SortedListOf<GroupData> newList = app.getGroupHelper().getUIGroups();
	    
	    //compare states
		assertThat(newList, equalTo(oldList.without(indexesList)));
	}

}