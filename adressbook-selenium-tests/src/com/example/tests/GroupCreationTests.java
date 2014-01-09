package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
	
	@Test(dataProvider = "radomValidGroupGenerator") 
	public void testGroupCreationValidData(GroupData group) throws Exception {
		    
	    //save old state
		List<GroupData> oldList = app.getGroupHelper().getGroups();
		
		//actions
		app.getGroupHelper().creationGroup(group);
		
		//save new state
		List<GroupData> newList = app.getGroupHelper().getGroups();
		
		//compare states
	    oldList.add(group);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}
  
}
