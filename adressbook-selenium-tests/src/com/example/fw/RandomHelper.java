package com.example.fw;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class RandomHelper extends HelperBase {

	public RandomHelper(ApplicationManager manager) {
		super(manager);
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

	public String randomValue(String randomValue) {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return "";
		if (rnd.nextInt(10) == 0)
			return null;
		else 
			return randomValue;
	}
	
	public String randomBirthDay() {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return null;
		int maxIndex = new Select(driver.findElement(By.xpath("//select[@name='bday']"))).getOptions().size();    
		int index = rnd.nextInt(maxIndex-1);
		String randomBirthDay = driver.findElement(By.xpath("//select[@name='bday']/option[" + (index + 1) + "]")).getText();
		return randomBirthDay;
	}
	
	public String randomBirthMonth() {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return null;
		int index = rnd.nextInt(new Select(driver.findElement(By.xpath("//select[@name='bmonth']"))).getOptions().size()-1);
		String randomBirthDay = driver.findElement(By.xpath("//select[2]/option[" + (index + 1) + "]")).getText();
		return randomBirthDay;
	}
	
	public String randomContactGroup() {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return null;
		int index = rnd.nextInt(new Select(driver.findElement(By.xpath("//select[@name='new_group']"))).getOptions().size()-1);
		String randomBirthDay = driver.findElement(By.xpath("//select[1]/option[" + (index + 1) + "]")).getText();
		return randomBirthDay;
	}

}
