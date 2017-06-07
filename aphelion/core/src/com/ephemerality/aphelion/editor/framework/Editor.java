package com.ephemerality.aphelion.editor.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.ephemerality.aphelion.editor.Mouse;
import com.ephemerality.aphelion.editor.framework.ui.GUI;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
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
		SpriteSheet.init();
		screen = new ScreenManager();
		map = new MapManager();
		mouse = new Mouse();
		gui = new GUI(screen, map);
	}
<<<<<<< HEAD
	
=======
>>>>>>> ce13df3267208cc5df371e55e50e51a526d0fe30
	public void update() {
		Debug.update();
		InputManager.update();
		mouse.update();
		gui.update();
	}
	
	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0.5f, 0, 0.5f, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		gui.render();
	}
	
	@Override
	public void dispose () {
		gui.dispose();
	}
	
	
	
	int width, height;
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		this.width = width;
		this.height = height;
		gui.resize(width, height);
	}
}