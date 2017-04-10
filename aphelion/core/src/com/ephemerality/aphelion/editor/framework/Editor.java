package com.ephemerality.aphelion.editor.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.ephemerality.aphelion.editor.Mouse;
import com.ephemerality.aphelion.editor.framework.ui.GUI;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.ephemerality.aphelion.util.debug.Debug;

public class Editor extends ApplicationAdapter {
	
	public static final String version = "0.0.6";
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
		gui.update();
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		gui.render();
		update();
		screen.start();
		Debug.render(screen);
		map.render(screen);
		screen.finish();
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
