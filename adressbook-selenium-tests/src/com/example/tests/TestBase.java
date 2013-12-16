package com.example.tests;

import static org.junit.Assert.fail;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {

	private static WebDriver driver;
	private static String baseUrl;
	private static StringBuffer verificationErrors = new StringBuffer();
	private static int countGroups;

	@BeforeTest
	public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "http://localhost/";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	protected void returnToGroupsPage() {
		driver.findElement(By.linkText("group page")).click();
	  }

	protected void submitGroupCreation() {
		driver.findElement(By.name("submit")).click();
	  }

	protected void fillGroupForm(GroupData group) {
		driver.findElement(By.name("group_name")).clear();
	    driver.findElement(By.name("group_name")).sendKeys(group.groupName);
	    driver.findElement(By.name("group_header")).clear();
	    driver.findElement(By.name("group_header")).sendKeys(group.groupHeader);
	    driver.findElement(By.name("group_footer")).clear();
	    driver.findElement(By.name("group_footer")).sendKeys(group.groupFooter);
	  }

	protected void initNewGroupCreation() {
		driver.findElement(By.name("new")).click();
	  }

	protected void gotoGroupsPage() {
		driver.findElement(By.linkText("groups")).click();
	  }

	protected void openMainPage() {
		driver.get(baseUrl + "/addressbookv4.1.4/");
	  }

	protected void returnToHomePage() {
		driver.findElement(By.linkText("home page")).click();
	  }

	protected void submitCreationContact() {
		driver.findElement(By.name("submit")).click();
	  }

	protected void fillContactForm(ContactData contact) {
		driver.findElement(By.name("firstname")).clear();
	    driver.findElement(By.name("firstname")).sendKeys(contact.firstName);
	    driver.findElement(By.name("lastname")).clear();
	    driver.findElement(By.name("lastname")).sendKeys(contact.lastName);
	    driver.findElement(By.name("address")).clear();
	    driver.findElement(By.name("address")).sendKeys(contact.address1);
	    driver.findElement(By.name("home")).clear();
	    driver.findElement(By.name("home")).sendKeys(contact.homePhone1);
	    driver.findElement(By.name("mobile")).clear();
	    driver.findElement(By.name("mobile")).sendKeys(contact.mobilePhone);
	    driver.findElement(By.name("work")).clear();
	    driver.findElement(By.name("work")).sendKeys(contact.workPhone);
	    driver.findElement(By.name("email")).clear();
	    driver.findElement(By.name("email")).sendKeys(contact.email1);
	    driver.findElement(By.name("email2")).clear();
	    driver.findElement(By.name("email2")).sendKeys(contact.email2);
	    new Select(driver.findElement(By.name("bday"))).selectByVisibleText(contact.bDay);
	    new Select(driver.findElement(By.name("bmonth"))).selectByVisibleText(contact.bMonth);
	    driver.findElement(By.name("byear")).clear();
	    driver.findElement(By.name("byear")).sendKeys(contact.bYear);
	    countGroups = new Select(driver.findElement(By.name("new_group"))).getOptions().size();
	    int randomIndex = randomNumeric(0, countGroups);
	    new Select(driver.findElement(By.name("new_group"))).selectByIndex(randomIndex);
	    //new Select(driver.findElement(By.name("new_group"))).selectByVisibleText(contact.contactGroup); сделать так чтобы вызывался либо рандом либо конкретное значение
	    driver.findElement(By.name("address2")).clear();
	    driver.findElement(By.name("address2")).sendKeys(contact.address2);
	    driver.findElement(By.name("phone2")).clear();
	    driver.findElement(By.name("phone2")).sendKeys(contact.homePhone2);
	  }

	protected void initCreationContact() {
		driver.findElement(By.linkText("add new")).click();
	  }

	protected String randomStringLatAlphaNumeric(final int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM 1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	protected String randomStringNumeric(final int length) {
		char[] chars = "1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 20; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	//рандомное число от min включительно до max
	protected int randomNumeric(int min,int max) {
		Random rnd = new Random(System.currentTimeMillis());
		int randomNumber = min + rnd.nextInt(max - min + 1);
		return randomNumber;
	}		
}
