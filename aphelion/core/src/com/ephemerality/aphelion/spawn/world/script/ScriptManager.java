package com.ephemerality.aphelion.spawn.world.script;

import com.ephemerality.aphelion.framework.GameManager;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class ScriptManager {
	
	GameManager game;
	AchievementManager chieves;
	public ScriptManager(GameManager game) {
		this.game = game;
		chieves = new AchievementManager(game);
	}
	
	
	
	public void update() {
		chieves.update();
	}
	
	
	public void render(ScreenManager screen) {
		chieves.render(screen);
	}
	
}
