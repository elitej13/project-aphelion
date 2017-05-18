package com.ephemerality.aphelion.ui;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.player.Player;

public class UIManager {
	
	Player player;
	ContextMenu menu;
	Overlay ui;
	boolean isPaused;
	
	public UIManager(Player player) {
		this.player = player;
		ui = new Overlay();
		menu = new ContextMenu(player);
	}
	
	
	public void update() {
		ui.fill = player.stats.getPercentDamaged();
		
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