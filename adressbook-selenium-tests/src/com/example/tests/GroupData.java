package com.example.tests;

public class GroupData extends TestBase {
	public String groupName;
	public String groupHeader;
	public String groupFooter;

	public GroupData() {
	}
	public GroupData(String groupName, String groupHeader, String groupFooter) {
		this.groupName = groupName;
		this.groupHeader = groupHeader;
		this.groupFooter = groupFooter;
		
	}
}