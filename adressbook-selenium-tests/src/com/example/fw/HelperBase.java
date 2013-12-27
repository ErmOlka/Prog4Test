package com.example.fw;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public abstract class HelperBase {
	
	protected ApplicationManager manager;
	protected WebDriver driver;
	public boolean acceptNextAlert = true;
	public String xpathGroupList = "//input[@name='selected[]']";
	public String xpathContactList = "(//img[@alt='Edit'])";

	public HelperBase(ApplicationManager manager) {
		this.manager = manager;
		this.driver = manager.driver;
	}
	
	protected void click(By locator) {
		driver.findElement(locator).click();
	}

	protected void typeText(By locator, String text) {
		if (text != null) {
			driver.findElement(locator).clear();
		    driver.findElement(locator).sendKeys(text);	
		}
	}
	
	protected void selectByText(By locator, String text) {
		if (text != null) {
			if (text != "RandomGroup" && text != "RandomDay" && text != "RandomMonth") 
				new Select(driver.findElement(locator)).selectByVisibleText(text);
			else if (text == "RandomGroup")
				randomDropDownValue(By.name("new_group"));
			else if (text == "RandomDay")
				randomDropDownValue(By.name("bday"));
			else if (text == "RandomMonth")
				randomDropDownValue(By.name("bmonth"));
		}
	}
	
	public void randomDropDownValue(By locator) {
		new Select(driver.findElement(locator)).selectByIndex(manager.getRandomHelper().randomNumeric(0, dropDownSize(locator)));
	}

	protected int dropDownSize(By locator) {
		return new Select(driver.findElement(locator)).getOptions().size();
	}
	
/*............................................................................................................................*/	
	public boolean isElementPresent(By by) {
		try{
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public String closeAlertAndGetItsText() {
		try{
			Alert alert = driver.switchTo().alert();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alert.getText();
		} finally {
			acceptNextAlert = true;
		}
	}
	
}
