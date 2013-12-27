package com.example.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupRemovalTests extends TestBase {
	
	@Test
	public void deleteFirstGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    if (app.driver.findElements(By.xpath(app.getGroupHelper().xpathGroupList)).isEmpty() == false) {
			app.getGroupHelper().deleteGroup(1); 
			app.getGroupHelper().returnToGroupsPage();
	    }
		else System.out.println("deleteFirstGroup: Групп нет, удалять нечего");
	}
	
	@Test
	public void deleteRandomGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    if (app.driver.findElements(By.xpath(app.getGroupHelper().xpathGroupList)).isEmpty() == false) {
			app.getGroupHelper().deleteGroup(app.getRandomHelper().randomIndex(By.xpath(app.getGroupHelper().xpathGroupList)));
			app.getGroupHelper().returnToGroupsPage();
	    }
		else System.out.println("deleteRandomGroup: Групп нет, удалять нечего");
	}

}