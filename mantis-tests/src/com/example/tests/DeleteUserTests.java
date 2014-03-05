package com.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.example.fw.User;
import com.example.utils.SortedListOf;

public class DeleteUserTests extends TestBase {
	
	public User user = new User()
		.setLogin("testuser1")
		.setPassword("123456")
		.setEmail("testuser1@localhost.localdomain");
	
	@BeforeClass
	public void initAccHelper() {
		accHelper = app.getAccountHelper();
	}
	
	@Test
	public void deletionUser() {
		//old list of users
		SortedListOf<User> oldList	= app.getModel().getUsers();
		
		//deleting user
		accHelper.initDeletionUser(user, admin);
		assertThat(accHelper.deletingUserName(), equalTo(user.login));
		accHelper.submitDeletionUser(user);
		
		
		//new list of users
		SortedListOf<User> newList	= app.getModel().getUsers();
		
		//assert
		assertThat(newList, equalTo(oldList.without(user)));
		
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

}
