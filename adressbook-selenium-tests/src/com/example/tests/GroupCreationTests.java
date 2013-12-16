package com.example.tests;

import org.testng.annotations.Test;

public class GroupCreationTests extends TestBase {
	
  @Test
  public void testNonEmptyGroupCreation() throws Exception {
    openMainPage();
    gotoGroupsPage();
    initNewGroupCreation();
    GroupData group = new GroupData();
    group.groupName = randomStringLatAlphaNumeric(10);
    group.groupHeader = randomStringLatAlphaNumeric(10);
    group.groupFooter = randomStringLatAlphaNumeric(10);
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
