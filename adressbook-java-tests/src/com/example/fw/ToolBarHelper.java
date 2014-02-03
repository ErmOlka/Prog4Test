package com.example.fw;

import org.netbeans.jemmy.operators.JButtonOperator;
import org.netbeans.jemmy.operators.JMenuItemOperator;

public class ToolBarHelper extends HelpersBase {

	public ToolBarHelper(ApplicationManager applicationManager) {
		super(applicationManager);
	}

	public void pushDeleteElement() {
		new JButtonOperator(mainFrame, 2).push();
	}

	public void pushCreateFolder() {
		new JButtonOperator(mainFrame, 0).push();
		new JMenuItemOperator(mainFrame, "New folder...").push();
	}

}
