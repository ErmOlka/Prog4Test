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
	    
	    //save old state
	    List<GroupData> oldList = app.getGroupHelper().getGroups();
	    
	    Random rnd = new Random();
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    //actions
	    if (app.driver.findElements(By.xpath("//input[@name='selected[]']")).isEmpty() == true)
	    	System.out.println("modifyRandomGroup: Групп нет, редактировать нечего");
	    
	    app.getGroupHelper().modyfyGroup(index,group);
	    
	    //save new state
	    List<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
	    oldList.remove(index);
	    oldList.add(group);
	    Collections.sort(oldList);
	    assertEquals(newList, oldList);
	}

}
