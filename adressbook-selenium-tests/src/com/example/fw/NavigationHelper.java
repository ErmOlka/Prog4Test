package com.example.fw;

import org.openqa.selenium.By;

public class NavigationHelper extends HelperBase{

	public NavigationHelper(ApplicationManager manager) {
		super(manager);
	}

	public void mainPage() {
		if (! onMainPage()) {
			if (mainMenu())
				click(By.linkText("home"));
			else driver.get(manager.baseUrl + "/addressbookv4.1.4/");
		}
	  }
	
	public void groupsPage() {
		if (! onGroupsPage()) {
			if (mainMenu())
				click(By.linkText("groups"));
			else driver.get(manager.baseUrl + "/addressbookv4.1.4/group.php");
		}
	  }
	
	public void printPhonesPage() {
		if (! onPrintPhonesPage()) {
			if (mainMenu())
				click(By.linkText("print phones"));
			else driver.get(manager.baseUrl + "/addressbookv4.1.4/view.php?all&print&phones");
		}
	}

	private boolean mainMenu() {
		if (driver.findElements(By.id("nav")).size() > 0)
			return true;
		else
			return false;
	}
	
	private boolean onMainPage() {
		return driver.findElements(By.id("maintable")).size() > 0;
	}

	private boolean onGroupsPage() {
		if (driver.getCurrentUrl().contains("/group.php")
				&& driver.findElements(By.name("new")).size() > 0)
			return true;
		else
			return false;
	}
	
	private boolean onPrintPhonesPage() {
		if (driver.getCurrentUrl().contains("/view.php?all&print&phones")
				&& driver.findElements(By.id("view")).size() > 0)
			return true;
		else
			return false;
	}

}
