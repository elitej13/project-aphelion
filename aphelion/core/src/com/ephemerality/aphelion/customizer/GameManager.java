package com.ephemerality.aphelion.customizer;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.world.MapManager;

public class GameManager {
	
	MapManager map;
	
	public GameManager() {
		map = new MapManager();
	}
	
	public void update() {
		
	}
	
	
	public void render(ScreenManager screen) {
		map.renderBackGround(screen);
	}
	public void resize(int width, int height) {
	}
	
}
