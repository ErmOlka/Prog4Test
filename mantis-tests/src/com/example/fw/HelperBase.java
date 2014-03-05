package com.example.fw;

public class HelperBase {
	
	protected ApplicationManager manager;
	
	public HelperBase(ApplicationManager manager) {
		this.manager = manager;
	}
	
	public void pause(int pause) {
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
