package com.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.fw.Folders;

public class FolderDeletionTests extends TestBase {
	@Test
	public void testFolderDeletionByToolBar() {
		Folders oldFolders = app.getFolderHelper().getFolders();
		int folderCount = app.getFolderHelper().getTreeSize();
		if (folderCount == 0) 
			throw new Error("No Folder for deletion");
		Random rnd = new Random();
		int index = rnd.nextInt(folderCount);
		app.getFolderHelper().deleteFolderByToolBar(index);
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders.without(index)));
	}
	
	@Test
	public void testFolderDeletionByMenu() {
		Folders oldFolders = app.getFolderHelper().getFolders();
		int folderCount = app.getFolderHelper().getTreeSize();
		if (folderCount == 0) 
			throw new Error("No Folder for deletion");
		Random rnd = new Random();
		int index = rnd.nextInt(folderCount);
		app.getFolderHelper().deleteFolderByMenu(index);
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders.without(index)));
	}

}
