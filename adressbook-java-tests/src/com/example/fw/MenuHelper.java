package com.example.fw;

import org.netbeans.jemmy.operators.JMenuBarOperator;

public class MenuHelper extends HelpersBase {
	
	public MenuHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void pushCreateFolder() {
		new JMenuBarOperator(mainFrame).pushMenuNoBlock("File|New folder...");
	}

}
