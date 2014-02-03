package com.example.fw;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JDialogOperator;
import org.netbeans.jemmy.operators.JTextFieldOperator;
import org.netbeans.jemmy.operators.JTreeOperator;

public class FolderHelper extends HelpersBase {

	public FolderHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public Folders getFolders() {
		List<String> list = new ArrayList<String>();
		JTreeOperator tree = new JTreeOperator(mainFrame);
		Object[] children = tree.getChildren(tree.getRoot());
		for (Object child : children) {
			list.add("" + child);
		}
		return new Folders(list);
	}

	public String createFolderByMenu(String folderName) {
		manager.getMenuHelper().pushCreateFolder();
		JDialogOperator dialog = new JDialogOperator(mainFrame);
		new JTextFieldOperator(dialog).setText(folderName);
		new JButtonOperator(dialog, "OK").push();
		return waitMessageDialog("Warning", 3000);
	}
	
	public String createFolderByToolBar(String folderName) {
		manager.getToolBarHelper().pushCreateFolder();
		JDialogOperator dialog = new JDialogOperator(mainFrame);
		new JTextFieldOperator(dialog).setText(folderName);
		new JButtonOperator(dialog, "OK").push();
		return waitMessageDialog("Warning", 3000);
	}

	public void deleteFolderByToolBar(int index) {
		new JTreeOperator(mainFrame).selectRow(index);
		manager.getToolBarHelper().pushDeleteElement();
		new JButtonOperator(new JDialogOperator(mainFrame), "Yes").push();
	}
	
	public void deleteFolderByMenu(int index) {
		new JTreeOperator(mainFrame).selectRow(index);
		manager.getMenuHelper().pushDeleteElement();
		new JButtonOperator(new JDialogOperator(mainFrame), "Yes").push();
	}

	public int getTreeSize() {
		JTreeOperator tree = new JTreeOperator(mainFrame);
		return tree.getChildren(tree.getRoot()).length;
	}	
	
	public String randomStringEngAlphaNumeric(final int length) {
		Random rnd = new Random();
		if (rnd.nextInt(10) == 0)
			return "";
		char[] chars = "abcdefghijklmnopqrstuvwxyz QWERTYUIOPASDFGHJKLZXCVBNM 1234567890_ éöóêåíãøùçõúôûâàïğîëäæıÿ÷ñìèòüáş ÉÖÓÊÅÍÃØÙÇÕÚÔÛÂÀÏĞÎËÄÆİß×ÑÌÈÒÜÁŞ 1234567890_ ".toCharArray();
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		return sb.toString();
	}

}
