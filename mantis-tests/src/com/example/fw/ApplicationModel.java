package com.example.fw;

import java.util.List;

import com.example.fw.User;
import com.example.utils.SortedListOf;

public class ApplicationModel {
	
	private SortedListOf<User> users;

	public SortedListOf<User> getUsers() {
		return new SortedListOf<User>(users);
	}
	
	public void setUsers(List<User> users) {
		this.users = new SortedListOf<User>(users);
	}
	
	public ApplicationModel addUser(User user) {
		users.add(user);
		return this;
	}

	public ApplicationModel removeUser(User user) {
		users.remove(user);
		return this;
	}
	
}
