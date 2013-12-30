package com.example.tests;

public class ContactData implements Comparable<ContactData> {
	public String firstName;
	public String lastName;
	public String address1;
	public String homePhone1;
	public String mobilePhone;
	public String workPhone;
	public String email1;
	public String email2;
	public String birthDay;
	public String birthMonth;
	public String birthYear;
	public String contactGroup;
	public String address2;
	public String homePhone2;

	public ContactData() {
	}

	@Override
	public String toString() {
		return "ContactData [firstName=" + firstName + "]";
	}

	@Override
	public int hashCode() {
		//final int prime = 31;
		int result = 1;
		//result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContactData other = (ContactData) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		return true;
	}

	@Override
	public int compareTo(ContactData other) {
		return this.firstName.toLowerCase().compareTo(other.firstName.toLowerCase());
	}
	

	
	
}