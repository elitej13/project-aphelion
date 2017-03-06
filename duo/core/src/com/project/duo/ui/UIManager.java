package com.project.duo.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.duo.spawn.entities.Player;

public class UIManager {
	
	
	private Player player;
	private ContextMenu menu;
	private Overlay ui;
	private boolean isPaused;
	
	public UIManager(Player player) {
		this.player = player;
		ui = new Overlay();
		menu = new ContextMenu();
	}
	
	public void update() {
		ui.update(player);
	}
	
	
	
	public void render(SpriteBatch sb) {
		if(!isPaused) {
			ui.render(sb);			
		}else {
			menu.render(sb);
		}
	}
	
	public void setPause(boolean isPaused) {
		this.isPaused = isPaused;
	}
	
}