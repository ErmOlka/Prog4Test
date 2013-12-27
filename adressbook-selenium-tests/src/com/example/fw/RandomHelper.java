package com.example.fw;

import java.util.Random;

import org.openqa.selenium.By;

public class RandomHelper extends HelperBase {

	public RandomHelper(ApplicationManager manager) {
		super(manager);
	}

	public int randomNumeric(int min, int max) {
		int randomNumber;
		if (min == max) {
			randomNumber = 1;
			return randomNumber;
		}
		Random rnd = new Random(System.currentTimeMillis());
		randomNumber = min + rnd.nextInt(max - min);
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
		char[] chars = "abcdefghijklmnopqrstuvwxyz QWERTYUIOPASDFGHJKLZXCVBNM 1234567890 ._".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public String randomStringRusAlphaNumeric(final int length) {
		char[] chars = "éöóêåíãøùçõúôûâàïðîëäæýÿ÷ñìèòüáþ ÉÖÓÊÅÍÃØÙÇÕÚÔÛÂÀÏÐÎËÄÆÝß×ÑÌÈÒÜÁÞ 1234567890 ._".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}
	
	public String randomStringLatAlphaNumericWithoutSpace(final int length) {
		char[] chars = "abcdefghijklmnopqrstuvwxyzQWERTYUIOPASDFGHJKLZXCVBNM1234567890._".toCharArray();
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
		int index = randomNumeric(1, maxIndex);
		return index;
	}
	
	private String[] concatArray(String[] firstArray, String[] secondArray) {
		if (firstArray == null)
			return secondArray;
		if (secondArray == null)
			return firstArray;
		String[] resultArray = new String[firstArray.length + secondArray.length];
		System.arraycopy(firstArray, 0, resultArray, 0, firstArray.length);
		System.arraycopy(secondArray, 0, resultArray, firstArray.length, secondArray.length);
		return resultArray;
	}
	
	public String randomFirstName(String gender) {
		String randFirstName = null;
		String[] firstNames = null;
		if (gender == "M") 
			firstNames = manager.getNamesGeneratorHelper().maleFirstNames();
		else if (gender == "F") 
			firstNames = manager.getNamesGeneratorHelper().femaleFirstNames();
		else randFirstName = "Óêàæèòå êîððåêòíûé ïîë - M èëè F";
		if (randFirstName == null) {
			Random random = new Random();
			for (int i = 0; i < firstNames.length; i++) {
				randFirstName = firstNames[random.nextInt(firstNames.length)];
			}
		}
		return randFirstName;
	}

	public String randomLastName(String gender) {
		String randLastName = null;
		String[] lastNames = null;
		if (gender == "M") 
			lastNames = concatArray(manager.getNamesGeneratorHelper().maleLastNames(),manager.getNamesGeneratorHelper().uniLastNames());
		else if (gender == "F") 
			lastNames = concatArray(manager.getNamesGeneratorHelper().femaleLastNames(),manager.getNamesGeneratorHelper().uniLastNames());
		else lastNames = manager.getNamesGeneratorHelper().uniLastNames();
		Random random = new Random();
		for (int i = 0; i < lastNames.length; i++) {
			randLastName = lastNames[random.nextInt(lastNames.length)];
		}
		return randLastName;
	}
	
}
