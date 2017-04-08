package com.ephemerality.aphelion.editor.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.ephemerality.aphelion.editor.framework.menu.MenuManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.debug.Debug;

public class Editor extends ApplicationAdapter {
	
	public static final String version = "0.01";
	private ScreenManager screen;
	private MenuManager menu;
	private MapManager map;
	
	
	@Override
	public void create () {
		super.create();
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
		map = new MapManager();
		menu = new MenuManager(screen, map);
	}

	public void update() {
		InputManager.update();
		Debug.update();
		menu.update();
	}
	
	@Override
	public void render () {
		update();
		screen.start();
		map.render(screen);
		Debug.render(screen);
		screen.finish();

		menu.render();
	}
	
	@Override
	public void dispose () {
		menu.dispose();
	}
	
	
	
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		menu.resize(width, height);
	}
	
	
}
