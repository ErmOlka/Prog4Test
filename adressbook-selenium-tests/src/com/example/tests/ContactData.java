package com.example.tests;

public class ContactData implements Comparable<ContactData> {
	private int id;
	private String firstName;
	private String lastName;
	private String address1;
	private String homePhone1;
	private String mobilePhone;
	private String workPhone;
	private String email1;
	private String email2;
	private String birthDay;
	private String birthMonth;
	private String birthYear;
	private String contactGroup;
	private String address2;
	private String homePhone2;
	private String phone;
	private String email;
	

	public ContactData() {
	}

	@Override
	public String toString() {
		return "ContactData [firstName=" + firstName + ", lastName=" + lastName + ", phone=" + phone + ", email=" + email + "]";
	}

	@Override
	public int compareTo(ContactData other) {
		int result = 0;
		
		if (this.firstName == null)
			this.firstName = "";
		if (this.lastName == null)
			this.lastName = "";
		if (this.email == null)
			this.email = "";
		if (this.phone == null)
			this.phone = "";
		
		if (other.firstName == null)
			other.firstName = "";
		if (other.lastName == null)
			other.lastName = "";
		if (other.email == null)
			other.email = "";
		if (other.phone == null)
			other.phone = "";

		int resultLastName = this.lastName.toLowerCase().compareTo(other.lastName.toLowerCase());
		if (resultLastName != 0)
			return resultLastName;
		int resultFirstName = this.firstName.toLowerCase().compareTo(other.firstName.toLowerCase());
		if (resultFirstName != 0)
			return resultFirstName;
		int resultEmail = this.email.toLowerCase().compareTo(other.email.toLowerCase());
		if (resultEmail != 0)
			return resultEmail;
		int resultPhone = this.phone.toLowerCase().compareTo(other.phone.toLowerCase());
		if (resultPhone != 0)
			return resultPhone;
		return result;
	}
	
	@Override
	public int hashCode() {
		//final int prime = 31;
		int result = 1;
		/*result = prime * result + ((email1 == null) ? 0 : email1.hashCode());
		result = prime * result
				+ ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result
				+ ((homePhone1 == null) ? 0 : homePhone1.hashCode());
		result = prime * result
				+ ((lastName == null) ? 0 : lastName.hashCode());
				*/
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
		if (this.firstName == null)
			this.firstName = "";
		if (this.lastName == null)
			this.lastName = "";
		if (this.email == null)
			this.email = "";
		if (this.phone == null)
			this.phone = "";
		
		if (other.firstName == null)
			other.firstName = "";
		if (other.lastName == null)
			other.lastName = "";
		if (other.email == null)
			other.email = "";
		if (other.phone == null)
			other.phone = "";
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.replaceAll(" +", " ").trim().equals(other.email.replaceAll(" +", " ").trim()))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.replaceAll(" +", " ").trim().equals(other.firstName.replaceAll(" +", " ").trim()))
			return false;
		if (phone == null) {
			if (other.phone != null)
				return false;
		} else if (!phone.replaceAll(" ", "").equals(other.phone.replaceAll(" ", "")))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.replaceAll(" +", " ").trim().equals(other.lastName.replaceAll(" +", " ").trim()))
			return false;
		return true;
	}

	//----------------------------getters---------------------------
	public int getId() {
		return id;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public String getHomePhone1() {
		return homePhone1;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public String getEmail1() {
		return email1;
	}

	public String getEmail2() {
		return email2;
	}

	public String getBirthDay() {
		return birthDay;
	}

	public String getBirthMonth() {
		return birthMonth;
	}

	public String getBirthYear() {
		return birthYear;
	}

	public String getContactGroup() {
		return contactGroup;
	}

	public String getAddress2() {
		return address2;
	}

	public String getHomePhone2() {
		return homePhone2;
	}
	
	public String getphone() {
		return phone;
	}
	
	public String getEmail() {
		return email;
	}

	//----------------------------setters---------------------------
	public ContactData withId(int id) {
		this.id = id;
		return this;
	}
	
	public ContactData withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	
	public ContactData withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public ContactData withAddress1(String address1) {
		this.address1 = address1;
		return this;
	}

	public ContactData withHomePhone1(String homePhone1) {
		this.homePhone1 = homePhone1;
		return this;
	}

	public ContactData withMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
		return this;
	}

	public ContactData withWorkPhone(String workPhone) {
		this.workPhone = workPhone;
		return this;
	}

	public ContactData withEmail1(String email1) {
		this.email1 = email1;
		return this;
	}
	
	public ContactData withEmail2(String email2) {
		this.email2 = email2;
		return this;
	}

	public ContactData withBirthDay(String birthDay) {
		this.birthDay = birthDay;
		return this;
	}
	
	public ContactData withBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
		return this;
	}
	
	public ContactData withBirthYear(String birthYear) {
		this.birthYear = birthYear;
		return this;
	}

	public ContactData withContactGroup(String contactGroup) {
		this.contactGroup = contactGroup;
		return this;
	}
	
	public ContactData withAddress2(String address2) {
		this.address2 = address2;
		return this;
	}

	public ContactData withHomePhone2(String homePhone2) {
		this.homePhone2 = homePhone2;
		return this;
	}
	
	public ContactData withPhone(String phone) {
		this.phone = phone;
		return this;
	}

	public ContactData withEmail(String email) {
		this.email = email;
		return this;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public void setHomePhone1(String homePhone1) {
		this.homePhone1 = homePhone1;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public void setEmail1(String email1) {
		this.email1 = email1;
	}

	public void setEmail2(String email2) {
		this.email2 = email2;
	}

	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}

	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}

	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}

	public void setContactGroup(String contactGroup) {
		this.contactGroup = contactGroup;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public void setHomePhone2(String homePhone2) {
		this.homePhone2 = homePhone2;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}