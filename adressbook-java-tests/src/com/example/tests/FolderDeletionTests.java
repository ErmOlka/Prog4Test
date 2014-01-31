package com.example.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.Random;

import org.testng.annotations.Test;

import com.example.fw.Folders;

public class FolderDeletionTests extends TestBase {
	@Test
	public void testFolderCreationByMenu() {
		Folders oldFolders = app.getFolderHelper().getFolders();
		Random rnd = new Random();
		int index = rnd.nextInt(app.getFolderHelper().getTreeSize());
		app.getFolderHelper().deleteFolder(index);
		Folders newFolders = app.getFolderHelper().getFolders();
		assertThat(newFolders, equalTo(oldFolders.without(index)));
	}

}
