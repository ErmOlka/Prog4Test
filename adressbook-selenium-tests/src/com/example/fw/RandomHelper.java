package com.example.fw;

import java.util.Random;

import org.openqa.selenium.By;

public class RandomHelper extends HelperBase {

	public RandomHelper(ApplicationManager manager) {
		super(manager);
	}

	public int randomNumeric(int min, int max) {
		Random rnd = new Random(System.currentTimeMillis());
		int randomNumber = min + rnd.nextInt(max - min);
		return randomNumber;
	}
	
	public String randomStringNumeric(final int length) {
		char[] chars = "1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public String randomStringEngAlphaNumeric(final int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyz QWERTYUIOPASDFGHJKLZXCVBNM 1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public String randomStringRusAlphaNumeric(final int length) {
		char[] chars = "�������������������������������� �������������������������������� 1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public String randomStringLatAlphaNumericWithoutSpace(final int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM1234567890".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public String randomEMail(int lengthName, int lengthDomen, int lengthZone) {
		String randomEmail = randomStringLatAlphaNumericWithoutSpace(lengthName) 
				+ "@" + randomStringLatAlphaNumericWithoutSpace(lengthDomen) 
				+ "." + randomStringLatAlphaNumericWithoutSpace(lengthZone);
		return randomEmail;
	}
	
	public String randomPhoneNumber(int lengthCountryCode, int lengthCityCode, int lengthNumber) {
		String randomPhoneNumber = randomStringNumeric(lengthCountryCode)
				+ " (" + randomStringNumeric(lengthCityCode) + ") "
				+ randomStringNumeric(lengthNumber);
		return randomPhoneNumber;
	}

	public int randomIndex(By locator) {
		int maxIndex = driver.findElements(locator).size();
		int index = randomNumeric(0, maxIndex);
		return index;
	}
	
	public String randomFirstName(String gender) {
		String randFirstName = null;
		String[] firstNames = null;
		if (gender == "M") 
			firstNames = maleFirstNames();
		else if (gender == "F") 
			firstNames = femaleFirstNames();
		else firstNames = uniFirstNames();
		Random random = new Random();
		for (int i = 0; i < firstNames.length; i++) {
			randFirstName = firstNames[random.nextInt(firstNames.length)];
		}
		return randFirstName;
	}

	public String randomLastName(String gender) {
		String randLastName = null;
		String[] lastNames = null;
		if (gender == "M") 
			lastNames = maleLastNames();
		else if (gender == "F") 
			lastNames = femaleLastNames();
		else lastNames = uniLastNames();
		Random random = new Random();
		for (int i = 0; i < lastNames.length; i++) {
			randLastName = lastNames[random.nextInt(lastNames.length)];
		}
		return randLastName;
	}
	
	public String[] maleFirstNames() {
		String[] maleFirstNames = null;
		maleFirstNames = new String[] {
				"�������",
				"������",
				"����",
				"����",
				"����",
		};
		return maleFirstNames;
	}

	public String[] femaleFirstNames() {
		String[] femaleFirstNames = null;
		femaleFirstNames = new String[] {
				"����",
				"���������",
				"������",
				"�����",
				"���������",
		};
		return femaleFirstNames;
	}
	
	public String[] uniFirstNames() {
		String[] uniFirstNames = null;
		uniFirstNames = new String[] {
				"������� ����",
				"������ ���������",
				"���� ������",
				"���� �����",
				"���� ���������",
		};
		return uniFirstNames;
	}

	public String[] maleLastNames() {
		String[] maleLastNames = null;
		maleLastNames = new String[] {
				"�������",
				"�������",
				"������",
				"�������",
				"�������",
		};
		return maleLastNames;
	}

	public String[] femaleLastNames() {
		String[] femaleLastNames = null;
		femaleLastNames = new String[] {
				"��������",
				"��������",
				"�������",
				"��������",
				"��������",
		};
		return femaleLastNames;
	}
	
	public String[] uniLastNames() {
		String[] uniSurnames = null;
		uniSurnames = new String[] {
				"���������",
				"��������������������������",
				"���������",
				"������",
				"������",
		};
		return uniSurnames;
	}
	
}
