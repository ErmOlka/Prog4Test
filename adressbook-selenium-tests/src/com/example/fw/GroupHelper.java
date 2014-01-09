package com.example.fw;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;

public class GroupHelper extends HelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public GroupHelper creationGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initNewGroupCreation();
		fillGroupForm(group);
		submitGroupCreation();
		returnToGroupsPage();
		return this;
	}
	
	public GroupHelper modyfyGroup(int index, GroupData group) {
		manager.navigateTo().groupsPage();
    	initGroupModification(index);
    	fillGroupForm(group);
    	submitGroupModification();
    	returnToGroupsPage();	
    	return this;
	}
	
	public GroupHelper deleteGroup(int index) {
		manager.navigateTo().groupsPage();
		selectGroupByIndex(index);
		submitGroupDeletion();
		returnToGroupsPage();
		return this;
	}

	public List<GroupData> getGroups() {
		List<GroupData> groups = new ArrayList<GroupData>();
		
		manager.navigateTo().groupsPage();
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			String name = title.substring("Select (".length(), title.length() - ")".length());
			groups.add(new GroupData().withName(name));
		}
		return groups;
	}
	
// ------------------------------------------------------------------------------------------------------------------------------------------------- //	
	
	public GroupHelper initNewGroupCreation() {
		click(By.name("new"));
		return this;
	  }

	public GroupHelper fillGroupForm(GroupData group) {
		typeText(By.name("group_name"), group.getName());
		typeText(By.name("group_header"), group.getHeader());
		typeText(By.name("group_footer"), group.getFooter());
		return this;
	  }

	public GroupHelper submitGroupCreation() {
		click(By.name("submit"));
		return this;
	  }

	public GroupHelper returnToGroupsPage() {
		click(By.linkText("group page"));
		return this;
	  }

	public GroupHelper initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
		return this;
	}
	
	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + (index+1) + "]"));
	}
	
	public GroupHelper submitGroupModification() {
		click(By.name("update"));		
		return this;
	}
	
	public void submitGroupDeletion() {
		click(By.name("delete"));
	}

}
