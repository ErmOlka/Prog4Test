package com.example.tests;

import static org.testng.Assert.assertEquals;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
	
	@Test(dataProvider = "radomValidGroupGenerator")
	public void modifyRandomGroup(GroupData group) {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    
	    //save old state
	    List<GroupData> oldList = app.getGroupHelper().getGroups();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    //actions
	    if (app.driver.findElements(By.xpath(app.getGroupHelper().xpathGroupList)).isEmpty() == true)
	    	System.out.println("modifyRandomGroup: Групп нет, редактировать нечего");
	    
	    app.getGroupHelper().initGroupModification(index);
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupsPage();
	    
	    //save new states
	    List<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
	    oldList.remove(index);
	    oldList.add(group);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}
	
	/*
	* Оставила себе для сравнения
	@Test
	public void modifyMyRandomGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    
	    //save old state
	    List<GroupData> oldList = app.getGroupHelper().getGroups();
	    
	    //actions
		if (app.driver.findElements(By.xpath(app.getGroupHelper().xpathGroupList)).isEmpty() == true) 
	    	System.out.println("modifyMyRandomGroup: Групп нет, редактировать нечего");
		
		int randomIndex = app.getRandomHelper().randomIndex(By.xpath(app.getGroupHelper().xpathGroupList));
		app.getGroupHelper().initGroupModification(randomIndex);
	    GroupData group = new GroupData();
		group.name = "new random group name";
		app.getGroupHelper().fillGroupForm(group);
		app.getGroupHelper().submitGroupModification();
		app.getGroupHelper().returnToGroupsPage();
	    
	    //save new states
	    List<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
	    oldList.remove(randomIndex);
	    oldList.add(group);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}
	*/
	
}
