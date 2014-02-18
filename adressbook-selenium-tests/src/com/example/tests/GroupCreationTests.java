package com.example.tests;

import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupCreationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> GroupsFromFile() throws IOException {
		return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
	}
	
	@Test(dataProvider = "GroupsFromFile") 
	public void testGroupCreationValidData(GroupData group) throws Exception {
		    
	    //save old state
		SortedListOf<GroupData> oldList	= app.getModel().getGroups();
		System.out.println("getGroups (old)" + app.getModel().getGroups());
		System.out.println("oldList before create: <" + oldList + ">");
		
		//actions
		app.getGroupHelper().creationGroup(group);
		
		//save new state
		SortedListOf<GroupData> newList = app.getModel().getGroups();
		
		//compare states
		System.out.println("group: <" + group + ">");
		System.out.println("oldList after create: <" + oldList + ">");
		System.out.println("oldList.withAdded " + oldList.withAdded(group));
		System.out.println("newList: <" + newList + ">");
		assertThat(newList, equalTo(oldList.withAdded(group)));
		
		//compare model to implementation
		if (wantToCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) 
				assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().listGroups()));
			if ("yes".equals(app.getProperty("check.ui")))
				assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getUIGroups()));
		}
	}
	
	/*
	@Test(dataProvider = "GroupsFromFile") 
	public void testGroupCreationValidData(GroupData group) throws Exception {
		    
	    //save old state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();
		
		//actions
		app.getGroupHelper().creationGroup(group);
		
		//save new state
		SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();
		
		//compare states
		assertThat(newList, equalTo(oldList.withAdded(group)));
	}
	*/
	
}
