package com.example.fw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.example.utils.SortedListOf;

public class ContactHelper extends HelpersBase {
	
	private String exportPath = manager.getProperty("exportContacts.path");
	public File oldContacts = new File(manager.getProperty("oldContacts"));
	public File newContacts = new File(manager.getProperty("newContacts"));

	public ContactHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}
	
	public void createContact(Contact contact) {
		initContactCreation();
		fillContactForm(contact);
		confirmContactCreation();
	}
	
	public void deleteContact(int index) {
		initContactDeletion(index);
		confirmContactDeletion();
	}
	
//-------------------------------------------------------------------------------------------------------------------------------------------------

	private void initContactCreation() {
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("Add").winWaitAndActivate("Add Contact", "", 5000);
	}
	
	private void fillContactForm(Contact contact) {
		manager.getAutoItHelper()
			.send("TDBEdit12", contact.name)
			.send("TDBEdit11", contact.surname);
	}
	
	private void confirmContactCreation() {
		manager.getAutoItHelper()
			.click("Save")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
	}
	
	public void initContactDeletion(int index) {
		selectContactByIndex(index);
		Contact contact = getSelectedContact();
		selectContactByIndex(index);
		manager.getAutoItHelper()
			.send("{SPACE}")
			.click("Delete");
		System.out.println("Deleted contact: " + contact.name + " " + contact.surname);
	}
	
	public void confirmContactDeletion() {
		manager.getAutoItHelper()
			.winWaitAndActivate("Confirm", "", 5000)
			.click("TButton2");
	}
	
//------------------------------------------------------------------------------------------------------------------------------------------------------

	public int randomContactIndex(File file) throws IOException {
		if (file.exists())
		{
			int contactCount = getContactCount(file);
			int index = new Random().nextInt(contactCount);
			if (index == 0)
				index = 1;
			System.out.println("index = " + index);
			return index;
		}
		else {
			System.out.println("FILE <" + file + "> NOT FOUND IN METHOD RANDOM INDEX");
			return 0;
		}
	}
	
	public void exportContacts(File file, boolean isFirstExport) {
		if (file.exists()) {
			file.delete();
			System.out.println("OLD FILE <" + file + "> HAS BEEN DELETED");
		}
		String fileName = file.toString();
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("Export");
		if (isFirstExport) 
			manager.getAutoItHelper().winWaitAndActivate("Сохранить как", "", 5000);
		if (!isFirstExport)
			manager.getAutoItHelper().winWaitAndActivate("Save CSV File", "", 5000);
		manager.getAutoItHelper()
			.send("ComboBox1", exportPath)
			.send("Edit1", fileName)
			.click("Button2");
		manager.getAutoItHelper()
			.winWaitAndActivate("Information", "", 5000)
			.click("TButton1")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
	}
	
	public int getContactCount(File file) throws IOException {
		List<Contact> contacts = new ArrayList<Contact>();
		if (file.exists()) {
			contacts = loadExportedContactsFromFile(file);
			return contacts.size() - 1;
		}
		else {
			System.out.println("FILE <" + file + "> NOT FOUND IN METHOD GET COUNT");
			return 0;
		}		
	}
	
	public SortedListOf<Contact> loadExportedContactsFromFile(File file) throws IOException {
		if (!file.exists()) {
			System.out.println("FILE FOR LOADING CONTACTS <" + file + "> NOT FOUND");
		}
		SortedListOf<Contact> list = new SortedListOf<Contact>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] part = line.split(",");
			System.out.println("LINE: " + line);
			System.out.println("PART: " + part.toString());
			Contact contact = new Contact()
				.setName(part[0])
				.setSurname(part[1]);
			list.add(contact);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}
	
	private void selectContactByIndex(int index) {
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("TListView1");
		for(int i = 0; i < index; i++) {
			manager.getAutoItHelper().send("{DOWN}");
			System.out.println("send DOWN_" + (i+1));
		}
	}
	
	private Contact getSelectedContact() {
		manager.getAutoItHelper().click("Edit")
			.winWaitAndActivate("Update Contact", "", 5000);
		Contact contact = new Contact()
			.setName(manager.getAutoItHelper().getText("TDBEdit12"))
			.setSurname(manager.getAutoItHelper().getText("TDBEdit11"));
		manager.getAutoItHelper()
			.click("Cancel")
			.winWaitAndActivate("AddressBook Portable", "", 5000);
		return contact;
	}
	
	public Contact getFirstContact() {
		manager.getAutoItHelper()
			.winWaitAndActivate("AddressBook Portable", "", 5000)
			.click("TListView1")
			.send("{DOWN}{SPACE}");
		Contact contact = getSelectedContact();
		return contact;		
	}

}
