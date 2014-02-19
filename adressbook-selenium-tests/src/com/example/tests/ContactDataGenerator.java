package com.example.tests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;

public class ContactDataGenerator {

	private static boolean male = true;
	private static boolean female = false;

	public static void main(String[] args) throws IOException {
		if(args.length < 3) {
			System.out.println("Please specify parameters: <amount of test data>, <file>, <format>");
			return;
		}
		int amount = Integer.parseInt(args[0]);
		File file = new File(args[1]);
		String format = args[2];
		
		if (file.exists()) {
			file.delete();
			System.out.println("File <" + file + "> has been deleted");
		}
		
		List<ContactData> contacts = generateRandomContacts(amount);
		if("csv".equals(format)) 
			saveContactsToCsvFile(contacts,file);
		else if("xml".equals(format)) 
			saveContactsToXmlFile(contacts,file);
		else {
			System.out.println("Unknown format " + format);
			return;
		}
	}

	private static void saveContactsToXmlFile(List<ContactData> contacts, File file) throws IOException {
		XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		String xml = xstream.toXML(contacts);
		Writer writer = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
		writer.write(xml);
		writer.close();		
	}
	
    public static List<ContactData> loadContactsFromXmlFile(File file) {
    	XStream xstream = new XStream();
		xstream.alias("contact", ContactData.class);
		return (List<ContactData>) xstream.fromXML(file);
	}

	private static void saveContactsToCsvFile(List<ContactData> contacts, File file) throws IOException {
		FileWriter writer = new FileWriter(file);
		for (ContactData contact : contacts) {
			writer.write(
					contact.getFirstName() 		+ "," + 
					contact.getLastName() 		+ "," + 
					contact.getAddress1() 		+ "," + 
					contact.getHomePhone1() 	+ "," + 
					contact.getMobilePhone() 	+ "," + 
					contact.getWorkPhone() 		+ "," + 
					contact.getEmail1() 		+ "," + 
					contact.getEmail2() 		+ "," + 
					contact.getBirthDay() 		+ "," + 
					contact.getBirthMonth() 	+ "," + 
					contact.getBirthYear() 		+ "," + 
					contact.getContactGroup() 	+ "," + 
					contact.getAddress2() 		+ "," + 
					contact.getHomePhone2()		+ ",!" + "\n"
				);
		}
		writer.close();
	}
	
	public static List<ContactData> loadContactsFromCsvFile(File file) throws IOException {
		List<ContactData> list = new ArrayList<ContactData>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		while(line != null) {
			String[] part = line.split(",");
			ContactData contact = new ContactData()
						.withFirstName(part[0]) 		
						.withLastName(part[1]) 		
						.withAddress1(part[2]) 		
						.withHomePhone1(part[3]) 	
						.withMobilePhone(part[4]) 	
						.withWorkPhone(part[5]) 		
						.withEmail1(part[6]) 		
						.withEmail2(part[7]) 		
						.withBirthDay(part[8]) 		
						.withBirthMonth(part[9]) 	
						.withBirthYear(part[10]) 		
						.withContactGroup(part[11]) 	
						.withAddress2(part[12]) 		
						.withHomePhone2(part[13]);
			list.add(contact);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}

	public static List<ContactData> generateRandomContacts(int amount) throws IOException {
		List<ContactData> list = new ArrayList<ContactData>();
		Random rnd = new Random();
		for (int i = 0; i < amount; i++) {
			boolean gender = genderSelect();
			String birthDay = "" + rnd.nextInt(32);
			if (birthDay == "0")
				birthDay = "-";
			String birthMonth = randomMonth();
			String homePhone1 = randomValue(randomPhoneNumber(1,3,7));
			String mobilePhone = randomValue(randomPhoneNumber(1,3,7));
			String workPhone = randomValue(randomPhoneNumber(1,3,7));
			String email1 = randomValue(randomEMail(9,5,3));
			String email2 = randomValue(randomEMail(7,4,2));
			String phone = phoneSelect(homePhone1, mobilePhone, workPhone);
			String email = emailSelect(email1, email2);
			ContactData contact = new ContactData()
				.withFirstName(randomValue(randomFirstName(gender)))
				.withLastName(randomValue(randomLastName(gender)))
				.withAddress1(randomValue("Àäðåñ 1: " + randomStringAlphaNumeric(rnd.nextInt(30))))
				.withHomePhone1(homePhone1)
				.withMobilePhone(mobilePhone)
				.withWorkPhone(workPhone)
				.withEmail1(email1)
				.withEmail2(email2)
				.withBirthDay(birthDay)
				.withBirthMonth(birthMonth)
				.withBirthYear(randomValue("19" + randomStringNumeric(2)))
				.withContactGroup("[none]")
				.withAddress2(randomValue("Àäðåñ 2: " + randomStringAlphaNumeric(rnd.nextInt(100))))
				.withHomePhone2(randomValue(randomPhoneNumber(1,3,7)))
				.withPhone(phone)
				.withEmail(email);
			list.add(contact);
		}
		return list;
	}

	private static String randomMonth() {
		String month = null;
		int monthCode = new Random().nextInt(13);
		if (monthCode == 0)
			month = "-";
		if (monthCode == 1)
			month = "January";
		if (monthCode == 2)
			month = "February";
		if (monthCode == 3)
			month = "March";
		if (monthCode == 4)
			month = "April";
		if (monthCode == 5)
			month = "May";
		if (monthCode == 6)
			month = "June";
		if (monthCode == 7)
			month = "July";
		if (monthCode == 8)
			month = "August";
		if (monthCode == 9)
			month = "September";
		if (monthCode == 10)
			month = "October";
		if (monthCode == 11)
			month = "November";
		if (monthCode == 12)
			month = "December";
		return month;
	}

	private static boolean genderSelect() {
		Random rnd = new Random();
		boolean gender = true;
		if (rnd.nextInt(10) < 5)
			gender = male;
		if (rnd.nextInt(10) >= 5) 
			gender = female;
		return gender;
	}

	private static String emailSelect(String email1, String email2) {
		String email = null;
		if (email1 == null || email1.equals("")) {
			if (email2 != null && ! email2.equals(""))
				email = email2;
			else email = "";
		}
		else email = email1;
		return email;
	}

	private static String phoneSelect(String homePhone1, String mobilePhone, String workPhone) {
		String phone = null;
		if (homePhone1 == null || homePhone1.equals("")) {
			if (mobilePhone != null && ! mobilePhone.equals(""))
				phone = mobilePhone;
			else if (workPhone != null && ! workPhone.equals(""))
				phone = workPhone;
			else phone = "";
		}
		else phone = homePhone1;
		return phone;
	}
	
	private static String randomValue(String randomValue) {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return "";
		if (rnd.nextInt(10) == 1)
			return null;
		else 
			return randomValue;
	}
	
	private static String randomEMail(int lengthName, int lengthDomen, int lengthZone) {
		String randomEmail = randomStringLatAlphaNumericWithoutSpace(lengthName) 
				+ "@" + randomStringLatAlphaNumericWithoutSpace(lengthDomen) 
				+ "." + randomStringLatAlphaNumericWithoutSpace(lengthZone);
		return randomEmail;
	}
	
	private static String randomPhoneNumber(int lengthCountryCode, int lengthCityCode, int lengthNumber) {
		String randomPhoneNumber = randomStringNumeric(lengthCountryCode)
				+ " (" + randomStringNumeric(lengthCityCode) + ") "
				+ randomStringNumeric(lengthNumber);
		return randomPhoneNumber;
	}
	
	private static String randomStringLatAlphaNumericWithoutSpace(final int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM1234567890._".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	private static String randomStringNumeric(final int length) {
		char[] chars = "1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	private static String randomStringAlphaNumeric(final int length) {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return "";
		else if (rnd.nextInt(10) == 0)
			return null;
		char[] chars = "abcdefghijklmnopqrstuvwxyz QWERTYUIOPASDFGHJKLZXCVBNM 1234567890 ._éöóêåíãøùçõúôûâàïðîëäæýÿ÷ñìèòüáþ ÉÖÓÊÅÍÃØÙÇÕÚÔÛÂÀÏÐÎËÄÆÝß×ÑÌÈÒÜÁÞ 1234567890 ._".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	private static List<String> concatStringLists(List<String> list1, List<String> list2) {
		if (list1 == null)
			return list2;
		if (list2 == null)
			return list1;
		List<String> resultList = new ArrayList<String>(list1);
		resultList.addAll(list2);
		return resultList;
	}
	
	private static String randomFirstName(boolean gender) throws IOException {
		String randFirstName = null;
		List<String> firstNames = null;
		if (gender == male)
			firstNames = readNamesFromFile(new File("maleFirstNames.txt"));
		if (gender == female)
			firstNames = readNamesFromFile(new File("femaleFirstNames.txt"));
		if (randFirstName == null) {
			Random random = new Random();
			for (int i = 0; i < firstNames.size(); i++) {
				randFirstName = firstNames.get(random.nextInt(firstNames.size()));
			}
		}
		return randFirstName;
	}

	private static String randomLastName(boolean gender) throws IOException {
		String randLastName = null;
		List<String> lastNames = null;
		if (gender == male)
			lastNames = concatStringLists(readNamesFromFile(new File("maleLastNames.txt")),readNamesFromFile(new File("uniLastNames.txt")));
		if (gender == female)
			lastNames = concatStringLists(readNamesFromFile(new File("femaleLastNames.txt")),readNamesFromFile(new File("uniLastNames.txt")));
		if (randLastName == null) {
			Random random = new Random();
			for (int i = 0; i < lastNames.size(); i++) {
				randLastName = lastNames.get(random.nextInt(lastNames.size()));
			}
		}
		return randLastName;
	}
	
	private static List<String> readNamesFromFile(File file) throws IOException {
		List<String> list = new ArrayList<String>();
		BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
		String line = bufferedReader.readLine();
		while(line != null) {
			list.add(line);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
		return list;
	}
}
