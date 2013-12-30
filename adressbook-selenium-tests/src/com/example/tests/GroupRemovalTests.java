package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase {
	
	@Test
	public void deleteRandomGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    
	    //save old state
	    List<GroupData> oldList = app.getGroupHelper().getGroups();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    //actions
	    if (oldList.isEmpty() == true) 
	    	System.out.println("deleteFirstGroup: Групп нет, удалять нечего");
	    
		app.getGroupHelper().deleteGroup(index); 
		app.getGroupHelper().returnToGroupsPage();
	    
	    //save new states
	    List<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
	    oldList.remove(index);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}
	
	/*
	* Оставила себе для сравнения
	@Test
	public void deleteGroupWithMyRandomIndex() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    
	    //save old state
	    List<GroupData> oldList = app.getGroupHelper().getGroups();
	    
	    //actions
	    if (oldList.isEmpty() == true)
	    	System.out.println("deleteRandomGroup: Групп нет, удалять нечего");
	    
	    int randomIndex = app.getRandomHelper().randomIndex(By.xpath(app.getGroupHelper().xpathGroupList));
	    app.getGroupHelper().deleteGroup(randomIndex);
		app.getGroupHelper().returnToGroupsPage();
	    
	    //save new states
	    List<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
	    oldList.remove(randomIndex);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}
	*/

}