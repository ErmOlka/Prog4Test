package com.example.tests;

import static org.testng.Assert.*;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.*;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.fw.User;

public class SignupUserTests extends TestBase{
	
	public User user = new User()
		.setLogin("testuser1")
		.setPassword("123456")
		.setEmail("testuser1@localhost.localdomain");
		
	@BeforeClass
	public void createMailUser() {
		james = app.getJamesHelper();
		accHelper = app.getAccountHelper();
		if (!james.doesUserExist(user.login))
			james.createUser(user.login, user.password);
	}
	
	@AfterClass
	public void deleteMailUser() {
		if (james.doesUserExist(user.login))
			james.deleteUser(user.login);
	}

	
	@Test
	public void newUserShouldSignup() {
		accHelper.signup(user);
		accHelper.login(user);
		assertThat(accHelper.loggedUserName(), equalTo(user.login));
		
		//compare model to implementation
    	if ("yes".equals(app.getProperty("check.db"))) {
			assertThat(app.getModel().getUsers(), equalTo(app.getHibernateHelper().listUsers()));
			System.out.println("check db has been implemented");
			}
		if ("yes".equals(app.getProperty("check.ui"))) {
			assertThat(app.getModel().getUsers(), equalTo(app.getAccountHelper().getUIUsers(admin)));
			System.out.println("check ui has been implemented");
		}
	}
	
	@Test
	public void userShouldNotSignup() {
		try {
			accHelper.logout();
			accHelper.signup(user);
		} catch (Exception e) {
			assertThat(e.getMessage(), containsString("That username is already being used"));
			return;
		}
		fail("Exception expected");
	}
	
	
	

}
