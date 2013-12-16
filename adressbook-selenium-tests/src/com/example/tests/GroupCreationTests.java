package com.example.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
  @Test
  public void testNonEmptyGroupCreation() throws Exception {
    openMainPage();
    gotoGroupsPage();
    initNewGroupCreation();
    GroupData group = new GroupData();
    group.groupName = "Group name 1";
    group.groupHeader = "Header 1";
    group.groupFooter = "Footer 1";
	fillGroupForm(group);
    submitGroupCreation();
    returnToGroupsPage();
  }
  
  @Test
  public void testDefoltGroupCreation() throws Exception {
    openMainPage();
    gotoGroupsPage();
    initNewGroupCreation();
    submitGroupCreation();
    returnToGroupsPage();
  }
  
  @Test
  public void testEmptyGroupCreation() throws Exception {
    openMainPage();
    gotoGroupsPage();
    initNewGroupCreation();
    GroupData group = new GroupData();
    group.groupName = "";
    group.groupHeader = "";
    group.groupFooter = "";
	fillGroupForm(group);
    submitGroupCreation();
    returnToGroupsPage();
  }
}
