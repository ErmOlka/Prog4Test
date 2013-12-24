package com.example.fw;

import org.openqa.selenium.By;

import com.example.tests.GroupData;

public class GroupHelper extends HelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}

	public void initNewGroupCreation() {
		click(By.name("new"));
	  }

	public void fillGroupForm(GroupData group) {
		typeText(By.name("group_name"), group.name);
		typeText(By.name("group_header"), group.header);
		typeText(By.name("group_footer"), group.footer);
	  }

	public void submitGroupCreation() {
		click(By.name("submit"));
	  }

	public void returnToGroupsPage() {
		click(By.linkText("group page"));
	  }

	public void deleteGroup(int index) {
		selectGroupByIndex(index);
		click(By.name("delete"));
	}

	public void initGroupModification(int index) {
		selectGroupByIndex(index);
		click(By.name("edit"));
	}

	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + index + "]"));
	}

	public void submitGroupModification() {
		click(By.name("update"));		
	}

}
