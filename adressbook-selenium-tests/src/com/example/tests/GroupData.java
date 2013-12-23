package com.example.tests;

public class GroupData extends TestBase {
	public String name;
	public String header;
	public String footer;

	public GroupData() {
	}

	public GroupData(String groupName, String groupHeader, String groupFooter) {
		this.name = groupName;
		this.header = groupHeader;
		this.footer = groupFooter;
		
	}

}