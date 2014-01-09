package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void mainPage() {
		if (! onMainPage())
			driver.get(manager.baseUrl + "/addressbookv4.1.4/");
	  }
	
	public void groupsPage() {
		if (! onGroupsPage())
			click(By.linkText("groups"));
	  }

	private boolean onMainPage() {
		if (driver.findElement(By.xpath("//input[@value='Send e-Mail']")).isDisplayed())
			return true;
		else
			return false;
	}

	private boolean onGroupsPage() {
		if (driver.getCurrentUrl().contains("/group.php")
				&& driver.findElements(By.name("new")).size() > 0)
			return true;
		else
			return false;
	}

}
