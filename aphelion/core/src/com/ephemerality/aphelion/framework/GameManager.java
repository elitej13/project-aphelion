package com.ephemerality.aphelion.framework;

import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.entities.EntityManager;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.ui.UIManager;

public class GameManager {
	
	private MapManager map;
	private EntityManager ent;
	private UIManager ui;
	public boolean isPaused;
	
	public GameManager(ScreenManager screen) {
		map = new MapManager();
		ent = new EntityManager(screen, map);
		ui = new UIManager();
	}
	public void init(LoadManager assets) {
		ent.init(assets);
	}
	
	public void update() {
		if(!isPaused) {
			ent.update();
		}
		if(InputManager.checkForPause()) {
			isPaused = !isPaused;
			ui.setPause(isPaused);
		}
		ui.update();			
	}
	
	public void setLevel(String path, boolean absolutepath) {
		map.load(path, absolutepath);
	}
	/**
	 * @param c0 Bottom left corner of view port
	 * @param c1 Top right corner of view port
	 */
	public void render(ScreenManager screen) {
		map.render(screen);
		ent.render(screen);
		ui.render(screen);
	}
	
	
	public void dispose() {
		
	}
	
}
