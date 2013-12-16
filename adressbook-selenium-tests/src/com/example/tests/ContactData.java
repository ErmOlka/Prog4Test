package com.example.tests;

public class ContactData extends TestBase {
	public String firstName;
	public String lastName;
	public String address1;
	public String homePhone1;
	public String mobilePhone;
	public String workPhone;
	public String email1;
	public String email2;
	public String bDay;
	public String bMonth;
	public String bYear;
	public String address2;
	public String homePhone2;
	public String contactGroup;

	public ContactData() {
	}
	public ContactData(String firstName, String lastName, String adress1,
			String homePhone1, String mobilePhone, String workPhone,
			String email1, String email2, String bDay, String bMonth,
			String bYear, String adress2, String homePhone2, String contactGroup) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = adress1;
		this.homePhone1 = homePhone1;
		this.mobilePhone = mobilePhone;
		this.workPhone = workPhone;
		this.email1 = email1;
		this.email2 = email2;
		this.bDay = bDay;
		this.bMonth = bMonth;
		this.bYear = bYear;
		this.address2 = adress2;
		this.homePhone2 = homePhone2;
		this.contactGroup = contactGroup;
	}
	public ContactData(String firstName, String lastName, String adress1,
			String homePhone1, String mobilePhone, String workPhone,
			String email1, String email2, String bDay, String bMonth,
			String bYear, String adress2, String homePhone2) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address1 = adress1;
		this.homePhone1 = homePhone1;
		this.mobilePhone = mobilePhone;
		this.workPhone = workPhone;
		this.email1 = email1;
		this.email2 = email2;
		this.bDay = bDay;
		this.bMonth = bMonth;
		this.bYear = bYear;
		this.address2 = adress2;
		this.homePhone2 = homePhone2;
	}
}