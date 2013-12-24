package com.example.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase {
	
	@Test
	public void deleteFirstGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
		app.getGroupHelper().deleteGroup(1); 
		app.getGroupHelper().returnToGroupsPage();
	}
	
	@Test
	public void deleteRandomGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
		app.getGroupHelper().deleteGroup(app.getRandomHelper().randomIndex(By.xpath(app.getGroupHelper().xpathGroupList)));
		app.getGroupHelper().returnToGroupsPage();
	}

}