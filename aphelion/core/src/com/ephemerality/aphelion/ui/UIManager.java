package com.ephemerality.aphelion.ui;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.player.Player;

public class UIManager {
	
	private ContextMenu menu;
	private Overlay ui;
	private boolean isPaused;
	
	public UIManager(Player player) {
		ui = new Overlay();
		menu = new ContextMenu(player);
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