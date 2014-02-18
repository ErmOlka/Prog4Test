package com.example.fw;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class GroupHelper extends WebDriverHelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private String xpathGroups = "//input[@name='selected[]']";
	
	public SortedListOf<GroupData> getUIGroups() {
		SortedListOf<GroupData> groups = new SortedListOf<GroupData>();
		
		manager.navigateTo().groupsPage();
		if (isElementPresent(By.name("selected[]"))) {
			List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
			for (WebElement checkbox : checkboxes) {
				String title = checkbox.getAttribute("title");
				String name = title.substring("Select (".length(), title.length() - ")".length());
				groups.add(new GroupData().withName(name));
			}
		}
		return groups;
	}

	public GroupHelper creationGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initNewGroupCreation();
		fillGroupForm(group);
		submitGroupCreation();
		returnToGroupsPage();
		//update model
    	manager.getModel().addGroup(group);
		return this;
	}
	
	public GroupHelper modyfyGroup(int index, GroupData group) {
		manager.navigateTo().groupsPage();
    	initGroupModification(index);
    	fillGroupForm(group);
    	submitGroupModification();
    	returnToGroupsPage();	
    	//update model
    	manager.getModel().removeGroup(index).addGroup(group);
    	return this;
	}
	
public GroupHelper deleteGroup(SortedListOf<Integer> indexesList) {
		manager.navigateTo().groupsPage();
		selectGroupsByIndexes(indexesList);
		submitGroupDeletion();
		returnToGroupsPage();
		//update model
    	manager.getModel().removeGroups(indexesList);
		return this;
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
	
	private void selectGroupsByIndexes(SortedListOf<Integer> indexesList) {
		for (int i = 0; i < indexesList.size(); i++) {
			int index = indexesList.get(i);
			if (! manager.driver.findElement(By.xpath(xpathGroups + "[" + (index+1) + "]")).isSelected()){
				click(By.xpath(xpathGroups + "[" + (index+1) + "]"));
			}
		}
	}

	private void selectGroupByIndex(int index) {
		click(By.xpath(xpathGroups + "[" + (index+1) + "]"));
	}
	
	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		return this;
	}
	
	public void submitGroupDeletion() {
		click(By.name("delete"));
	}
	
	public SortedListOf<Integer> randomIndexesList() {
		Random rnd = new Random();
		int maxCount = manager.getModel().getGroups().size();
		SortedListOf<Integer> indexesList = new SortedListOf<Integer>();
	    if (maxCount == 0) 
	    	throw new Error("Нет групп для удаления"); 
		int countForDelete = rnd.nextInt(maxCount); 
		if (countForDelete == 0)
	    	countForDelete = 1;
		for (int i = 0; i < countForDelete; i++) {
			int index = rnd.nextInt(maxCount);
			indexesList.add(index);
		}
		//удаляем повторы
		for (int i = 0; i < indexesList.size() - 1; i++) {
			for (int j = i + 1; j < indexesList.size();) {
				if (indexesList.get(i) == indexesList.get(j)) 
					indexesList.remove(i);
				else j++;
			}
		}
		return indexesList;
	}

}
