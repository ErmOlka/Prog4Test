package com.example.fw;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.utils.SortedListOf;

public class AccountHelper extends HelperWithWebDriverBase{
	
	public AccountHelper(ApplicationManager applicationManager) {
	super(applicationManager);
	}
	
	public void signup(User user) {
		openUrl("/");
		click(By.xpath("//span[1]/a")); //signup user button
		typeText(By.name("username"), user.login);
		typeText(By.name("email"), user.email);
		clickSubmitButton();
		checkError();
		openConfirmationLink(user);
		typeText(By.name("password"), user.password);
		typeText(By.name("password_confirm"), user.password);
		clickSubmitButton();
		manager.getModel().addUser(user);
	}

	public void login(User user) {
		openUrl("/");
		if (loggedUserName() != null) {
			if (!loggedUserName().equals(user.login)) {
				logout();
				loginUser(user);
			}
			else {
				System.out.println("USER <" + user.login + "> ALREADY LOGGED");
			}
		}
		else {
			loginUser(user);
		}
	}

	public String logout() {
		String login = loggedUserName();
		if (login != null) {
			click(By.linkText("Logout"));
			System.out.println("USER <" + login + "> WAS LOGOUT SUCCESSFULLY");
		}
		else System.out.println("NO USER LOGGED NOW");
		return login;
	}

	public String loggedUserName() {
		if (isElementPresent(By.cssSelector("td.login-info-left span")))
			return findElement(By.cssSelector("td.login-info-left span")).getText();
		else return null;
	}
	
	public void initDeletionUser(User user, User admin) {
		login(admin);
		click(By.xpath("//span[1]/a")); //manage users button
		findUserForDelete(user);
		click(By.cssSelector("input[class='button'][type='submit'][value='Delete User']")); //delete user button
	}
	
	public String deletingUserName() {
		String text = findElement(By.xpath("//div[2]")).getText();
		String userName = text.substring(text.indexOf(": ") + 2);
		System.out.println("USER FOR DELETING: " + userName);
		return userName;
	}
	
	public void submitDeletionUser(User user) {
		click(By.cssSelector("input.button"));
		manager.getModel().removeUser(user);
	}

//-------------------------------------------------------------------------------------------------------------------------------------	
	
	public void clickSubmitButton() {
		click(By.cssSelector("input.button"));
	}
	
	private void findUserForDelete(User user) {
		List<WebElement> rows = findElements(By.xpath("//table[3]/tbody/tr"));
		String n = null;
		for (int i = 0; i < (rows.size() - 3); i++) {
			String name = findElement(By.xpath("//table[3]/tbody/tr[" + (i + 3) + "]/td[1]")).getText();
			if (name.equals(user.login)) {
				n = name;
				click(By.xpath("//table[3]/tbody/tr[" + (i + 3) + "]/td[1]/a"));
				break;
			}
		}
		if (n == null)
			throw new Error("THE USER <" + user.login + "> IS NOT DETECTED. DELETION IS IMPOSSIBLE");	
	}
	
	private void loginUser(User user) {
		typeText(By.name("username"), user.login);
		typeText(By.name("password"), user.password);
		clickSubmitButton();	
		System.out.println("USER <" + user.login + "> WAS LOGIN SUCCESSFULLY");
	}
	
	public SortedListOf<User> getUIUsers(User admin) {
		SortedListOf<User> users = new SortedListOf<User>();
		
		login(admin);
		click(By.linkText("Manage"));
		click(By.xpath("//span[1]/a")); //manage users button
		List<WebElement> rows = findElements(By.xpath("//table[3]/tbody/tr"));
		for (int i = 0; i < (rows.size() - 3); i++) {
			User user = new User();
			String login = findElement(By.xpath("//table[3]/tbody/tr[" + (i + 3) + "]/td[1]")).getText();
			String email = findElement(By.xpath("//table[3]/tbody/tr[" + (i + 3) + "]/td[3]")).getText();
			user.setLogin(login).setEmail(email);
			users.add(user);
		}
		return users;
		
	}
	
	public String getConfirmationLink(String text) {
		Pattern regex = Pattern.compile("http\\S*");
		Matcher matcher = regex.matcher(text);
		if (matcher.find()) {
			return matcher.group();
		} else {
			return "";
		}
	}
	
	private void openConfirmationLink(User user) {
		String msg = null;
		for (int i = 0; i < 10; i++) {
		  msg = manager.getMailHelper().getNewMail(user.login, user.password);
		  if (msg != null) {
		    break; // прервать цикл
		  }
		  pause(5000);
		}
		String confirmationLink = getConfirmationLink(msg);
		System.out.println("CONFIRMATION LINK: " + confirmationLink);
		openAbsoluteUrl(confirmationLink);
	}

	private void checkError() {
		WebElement errorMessage = null;
		if (isElementPresent(By.cssSelector("table.width50 tbody tr td p"))) {
			errorMessage = findElement(By.cssSelector("table.width50 tbody tr td p"));
		}
		if (errorMessage != null) {
			throw new RuntimeException(errorMessage.getText());
		}
	}

}
