package com.ephemerality.aphelion.editor.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.ephemerality.aphelion.editor.Mouse;
import com.ephemerality.aphelion.editor.framework.ui.GUI;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.debug.Debug;

public class Editor extends ApplicationAdapter {
	
	public static final String version = "0.0.5";
	private ScreenManager screen;
	private GUI gui;
	private MapManager map;
	private Mouse mouse;
	
	@Override
	public void create () {
		super.create();
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
		map = new MapManager();
		gui = new GUI(screen, map);
		mouse = new Mouse();
	}

	public void update() {
		InputManager.update();
		Debug.update();
		mouse.update();
	}
	
	@Override
	public void render () {
		update();
		screen.start();
		map.render(screen);
		Debug.render(screen);
		screen.finish();
		gui.render();
	}
	
	@Override
	public void dispose () {
		gui.dispose();
	}
	
	
	
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		gui.resize(width, height);
	}
	
	
}
