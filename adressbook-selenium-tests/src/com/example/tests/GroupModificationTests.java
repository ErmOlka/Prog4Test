package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupModificationTests extends TestBase {
	
	@Test(dataProvider = "radomValidGroupGenerator")
	public void modifyRandomGroup(GroupData group) {
	    
	    //save old state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();
	    
		Random rnd = new Random();
		
	    //actions
	    if (app.driver.findElements(By.xpath("//input[@name='selected[]']")).isEmpty() == true)
	    	throw new Error("modifyRandomGroup: Групп нет, редактировать нечего");
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    app.getGroupHelper().modyfyGroup(index,group);
	    
	    //save new state
	    SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();
	    
	    //compare states
		assertThat(newList, equalTo(oldList.without(index).withAdded(group)));
	}

}
