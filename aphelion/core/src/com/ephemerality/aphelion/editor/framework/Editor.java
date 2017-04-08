package com.ephemerality.aphelion.editor.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.ephemerality.aphelion.editor.framework.menu.MenuManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.util.debug.Debug;

public class Editor extends ApplicationAdapter {
	
	public static final String version = "0.01";
	private ScreenManager screen;
	private MenuManager menu;
	
	
	@Override
	public void create () {
		super.create();
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
		menu = new MenuManager();
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
		Debug.render(screen);
		menu.render();
		screen.finish();
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
