package com.example.tests;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Random;
import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class GroupModificationTests extends TestBase {
	
	@Test(dataProvider = "radomValidGroupGenerator")
	public void modifyRandomGroup(GroupData group) {
	    
	    //save old state
		SortedListOf<GroupData> oldList = app.getGroupHelper().getUIGroups();
	    
		Random rnd = new Random();
		
	    //actions
	    if (oldList.isEmpty())
	    	throw new Error("Нет групп для редактирования");
	    int index = rnd.nextInt(oldList.size()-1);
	    
	    app.getGroupHelper().modyfyGroup(index,group);
	    
	    //save new state
	    SortedListOf<GroupData> newList = app.getGroupHelper().getUIGroups();
	    
	    //compare states
		assertThat(newList, equalTo(oldList.without(index).withAdded(group)));
	}

}
