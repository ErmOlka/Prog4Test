package com.example.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
	
  @Test
  public void testRandomGroupCreation() throws Exception {
    openMainPage();
    gotoGroupsPage();
    initNewGroupCreation();
    GroupData group = new GroupData();
    group.name = randomStringLatAlphaNumeric(20);
    group.header = randomStringLatAlphaNumeric(10);
    group.footer = randomStringLatAlphaNumeric(50);
	fillGroupForm(group);
    submitGroupCreation();
    returnToGroupsPage();
  }
  
  @Test
  public void testDefaultGroupCreation() throws Exception {
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
    group.name = "";
    group.header = "";
    group.footer = "";
	fillGroupForm(group);
    submitGroupCreation();
    returnToGroupsPage();
  }
}
