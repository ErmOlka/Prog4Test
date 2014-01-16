package com.example.fw;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.example.tests.GroupData;
import com.example.utils.SortedListOf;

public class GroupHelper extends HelperBase {

	public GroupHelper(ApplicationManager manager) {
		super(manager);
	}
	
	private SortedListOf<GroupData> cachedGroups;
	
	public SortedListOf<GroupData> getGroups() {
		if (cachedGroups == null)
			rebuildCache();
		return cachedGroups;
	}

	private void rebuildCache() {
		cachedGroups = new SortedListOf<GroupData>();
		
		manager.navigateTo().groupsPage();
		List<WebElement> checkboxes = driver.findElements(By.name("selected[]"));
		for (WebElement checkbox : checkboxes) {
			String title = checkbox.getAttribute("title");
			String name = title.substring("Select (".length(), title.length() - ")".length());
			cachedGroups.add(new GroupData().withName(name));
		}
	}

	public GroupHelper creationGroup(GroupData group) {
		manager.navigateTo().groupsPage();
		initNewGroupCreation();
		fillGroupForm(group);
		submitGroupCreation();
		returnToGroupsPage();
		rebuildCache();
		return this;
	}
	
	public GroupHelper modyfyGroup(int index, GroupData group) {
		manager.navigateTo().groupsPage();
    	initGroupModification(index);
    	fillGroupForm(group);
    	submitGroupModification();
    	returnToGroupsPage();	
    	rebuildCache();
    	return this;
	}
	
	public GroupHelper deleteGroup(SortedListOf<Integer> indexesList) throws InterruptedException {
		manager.navigateTo().groupsPage();
		selectGroupsByIndexes(indexesList);
		submitGroupDeletion();
		returnToGroupsPage();
		rebuildCache();
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
		cachedGroups = null;
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
	
	private void selectGroupsByIndexes(SortedListOf<Integer> indexesList) throws InterruptedException {
		for (int i = 0; i < indexesList.size(); i++) {
			int index = indexesList.get(i);
			if (! manager.driver.findElement(By.xpath("//input[@name='selected[]'][" + (index+1) + "]")).isSelected()){
				click(By.xpath("//input[@name='selected[]'][" + (index+1) + "]"));
				Thread.sleep(3000);
			}
		}
	}
	
	private void selectGroupByIndex(int index) {
		click(By.xpath("//input[@name='selected[]'][" + (index+1) + "]"));
	}
	
	public GroupHelper submitGroupModification() {
		click(By.name("update"));
		cachedGroups = null;
		return this;
	}
	
	public void submitGroupDeletion() {
		click(By.name("delete"));
		cachedGroups = null;
	}

}
