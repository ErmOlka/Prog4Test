package com.example.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

public class GroupModificationTests extends TestBase {
	
	@Test
	public void modifyFirstGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    if (app.driver.findElements(By.xpath(app.getGroupHelper().xpathGroupList)).isEmpty() == false) {
			app.getGroupHelper().initGroupModification(1);
			GroupData group = new GroupData();
			group.name = "new name";
			app.getGroupHelper().fillGroupForm(group);
			app.getGroupHelper().submitGroupModification();
			app.getGroupHelper().returnToGroupsPage();
	    }
		else System.out.println("modifyFirstGroup: Групп нет, редактировать нечего");
	}
	
	@Test
	public void modifyRandomGroup() {
	    app.getNavigationHelper().openMainPage();
	    app.getNavigationHelper().gotoGroupsPage();
	    if (app.driver.findElements(By.xpath(app.getGroupHelper().xpathGroupList)).isEmpty() == false) {
			app.getGroupHelper().initGroupModification(app.getRandomHelper().randomIndex(By.xpath(app.getGroupHelper().xpathGroupList)));
			GroupData group = new GroupData();
			group.name = "new random group name";
			app.getGroupHelper().fillGroupForm(group);
			app.getGroupHelper().submitGroupModification();
			app.getGroupHelper().returnToGroupsPage();
	    }
		else System.out.println("modifyRandomGroup: Групп нет, редактировать нечего");
	}
	
}
