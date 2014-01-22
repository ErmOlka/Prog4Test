package com.example.tests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import org.openqa.selenium.By;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import com.example.fw.ApplicationManager;
import com.example.utils.SortedListOf;

import static com.example.tests.GroupDataGenerator.generateRandomGroups;
import static com.example.tests.ContactDataGenerator.generateRandomContacts;

public class TestBase {
	
	public ApplicationManager app;

	@BeforeTest
	public void setUp() throws Exception {
		Properties properties = new Properties();
		properties.load(new FileReader(new File("application.properties")));
		app = new ApplicationManager(properties);
	  }
	
	@AfterTest
	public void tearDown() throws Exception {
		app.stop();
	  }
	
	//��������� ��� ��������, ����������� �����
	@DataProvider
	public Iterator<Object[]> radomValidGroupGenerator() {
		return wrapGroupsForDataProvider(generateRandomGroups(5)).iterator();
	}

	//��������� ��� �������� �����
	@DataProvider
	public Iterator<Object[]> deletionSomeGroups() {
		List<Object[]> list = new ArrayList<Object[]>();
		SortedListOf<Integer> indexesList = app.getRandomHelper().randomIndexesList();
		list.add(new Object[] {indexesList});
		return list.iterator();
	}
	
	//��������� ��� ��������, ����������� ���������
	@DataProvider
	public Iterator<Object[]> radomValidContactGenerator() throws IOException {
		return wrapContactsForDataProvider(generateRandomContacts(5)).iterator();
	}
		
	//��������� ��� �������� �������� � ������� � ����� ���������� � ���
	@DataProvider
	public Iterator<Object[]> deletionSomeContact() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		int maxCount = app.getContactHelper().getContacts(true).size();
		if (! app.getContactHelper().isElementPresent(By.xpath("(//img[@alt='Edit'])")))
			throw new Error("��� ���������");
		int index = rnd.nextInt(maxCount);
		ContactData contact = app.getContactHelper().getContacts(true).get(index);
		list.add(new Object[] {contact});
		return list.iterator();
	}
	
	//��������� ��� �������� ���������� ���������� ���������, �� ��� ������ � ����� ���������� � ���
	@DataProvider
	public Iterator<Object[]> deletionSomeContacts() {
		List<Object[]> list = new ArrayList<Object[]>();
		Random rnd = new Random();
		int maxCount = app.getContactHelper().getContacts(true).size();
	    if (maxCount == 0) 
	    	throw new Error("��� ��������� ��� ��������"); 
	    int countForDelete = rnd.nextInt(maxCount);//���� ���� ������� ���������� ���������� ���������, �� ������ rnd ���� ���������� ������ �����
	    if (countForDelete == 0)
	    	countForDelete = 1;
		for (int i = 0; i < countForDelete; i++) {
			maxCount = maxCount - 1;
			if (maxCount == 0)
				break;
			int index = rnd.nextInt(maxCount);
			list.add(new Object[] {index});
		}
		return list.iterator();
	}
	
	public static List<Object[]> wrapContactsForDataProvider(List<ContactData> contacts) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (ContactData contact : contacts) {
			list.add(new Object[]{contact});
		}
		return list;
	}
	
	public static List<Object[]> wrapGroupsForDataProvider(List<GroupData> groups) {
		List<Object[]> list = new ArrayList<Object[]>();
		for (GroupData group : groups) {
			list.add(new Object[]{group});
		}
		return list;
	}

}
