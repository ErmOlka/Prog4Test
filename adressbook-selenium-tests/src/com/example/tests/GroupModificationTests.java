package com.example.tests;

import static com.example.tests.GroupDataGenerator.loadGroupsFromXmlFile;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupModificationTests extends TestBase {
	
	@DataProvider
	public Iterator<Object[]> GroupsFromFile() throws IOException {
		return wrapGroupsForDataProvider(loadGroupsFromXmlFile(new File("groups.xml"))).iterator();
	}
	
	@Test(dataProvider = "GroupsFromFile") 
	public void modifyRandomGroup(GroupData group) {
	    
	    //save old state
		SortedListOf<GroupData> oldList = app.getModel().getGroups();
	    
		Random rnd = new Random();
		
	    //actions
	    if (oldList.isEmpty())
	    	throw new Error("Нет групп для редактирования");
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    app.getGroupHelper().modyfyGroup(index,group);
	    
	    //save new state
	    SortedListOf<GroupData> newList = app.getModel().getGroups();
	    
	    //compare states
		assertThat(newList, equalTo(oldList.without(index).withAdded(group)));
		
		//compare model to implementation
		if (wantToCheck()) {
			if ("yes".equals(app.getProperty("check.db"))) {
				assertThat(app.getModel().getGroups(), equalTo(app.getHibernateHelper().listGroups()));
				System.out.println("check db has been implemented");
			}
			if ("yes".equals(app.getProperty("check.ui"))) {
				assertThat(app.getModel().getGroups(), equalTo(app.getGroupHelper().getUIGroups()));
				System.out.println("check ui has been implemented");
			}
		}
	}

}
