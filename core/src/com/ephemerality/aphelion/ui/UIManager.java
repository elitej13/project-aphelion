package com.ephemerality.aphelion.ui;

import com.ephemerality.aphelion.graphics.ScreenManager;

public class UIManager {
	
	private ContextMenu menu;
	private Overlay ui;
	private boolean isPaused;
	
	public UIManager() {
		ui = new Overlay();
		menu = new ContextMenu();
	}
	
	
	public void update() {
		
	}
	
	public void render(ScreenManager screen) {
		if(!isPaused) {
			ui.render(screen);
		}else {
			menu.render(screen);
		}
	}
	
	public void setPause(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
}