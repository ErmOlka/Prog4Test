package com.example.tests;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.fw.Folders;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class FolderCreationTest extends TestBase {
	
	Random rnd = new Random();
	
	@Test
	public void testFolderCreationByMenu() {
		String folder = app.getFolderHelper().randomStringEngAlphaNumeric(rnd.nextInt(20));
		Folders oldFolders = app.getFolderHelper().getFolders();
		app.getFolderHelper().createFolder(folder);
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));
	}
	
	@Test
	public void testVariousFoldersCreationByMenu() {
		String folder1 = app.getFolderHelper().randomStringEngAlphaNumeric(rnd.nextInt(20));
		String folder2 = app.getFolderHelper().randomStringEngAlphaNumeric(rnd.nextInt(20));
		Folders oldFolders = app.getFolderHelper().getFolders();
		assertThat(app.getFolderHelper().createFolder(folder1), is(nullValue()));
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders.withAdded(folder1)));
		assertThat(app.getFolderHelper().createFolder(folder2), is(nullValue()));
		Folders newFolders2 = app.getFolderHelper().getFolders();
		assertThat(newFolders2, equalTo(newFolders.withAdded(folder2)));
	}
	
	@Test
	public void testFoldersWithSameNameCreationByMenu() {
		String folder = app.getFolderHelper().randomStringEngAlphaNumeric(rnd.nextInt(20));
		Folders oldFolders = app.getFolderHelper().getFolders();
		assertThat(app.getFolderHelper().createFolder(folder), is(nullValue()));
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders.withAdded(folder)));
		assertThat(app.getFolderHelper().createFolder(folder), containsString("Duplicated folder name"));
		Folders newFolders2 = app.getFolderHelper().getFolders();
		assertThat(newFolders2, equalTo(newFolders));
	}

}
