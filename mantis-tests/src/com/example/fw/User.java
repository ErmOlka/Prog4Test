package com.example.fw;

public class User implements Comparable<User>  {

	public int id;
	public String login;
	public String password;
	public String email;
	
	public User() {
	}
	
	@Override
	public String toString() {
		return "User [login=" + login + ", password=" + password + ", email=" + email + "]";
	}

	@Override
	public int hashCode() {
		//final int prime = 31;
		int result = 1;
		/*
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		*/
		return result;
	}
	
	@Override
	public int compareTo(User other) {
		if (this.login == null)
			this.login = "";
		if (other.login == null)
			other.login = "";
		return this.login.toLowerCase().compareTo(other.login.toLowerCase());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

	public User setId(int id) {
		this.id = id;
		return this;
	}
	
	public User setLogin(String login) {
		this.login = login;
		return this;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	public User setEmail(String email) {
		this.email = email;
		return this;
	}

	public int getId() {
		return id;
	}
	
	public String getLogin() {
		return login;
	}

	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

}
