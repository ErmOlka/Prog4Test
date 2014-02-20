package com.example.tests;


public class Sample {
	
	public static void main(String[] args) {
		String homePhone1 = "";
		String mobilePhone = "";
		String phone = "";
		String workPhone = "789";
		
		System.out.println(homePhone1 != null);
		System.out.println(!homePhone1.equals(""));
		
		if (homePhone1 != null && homePhone1.equals("") == false)
			phone = homePhone1;
		else {
			if (mobilePhone != null && !mobilePhone.equals(""))
				phone = mobilePhone;
			else phone = workPhone;
		}
		System.out.println("phone " + phone);
		
	}
}
