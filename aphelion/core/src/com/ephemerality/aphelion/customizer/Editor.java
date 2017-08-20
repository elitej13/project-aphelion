package com.ephemerality.aphelion.customizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ApplicationAdapter;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.util.debug.Debug;

public class Editor extends ApplicationAdapter {
	

	public static final String version = "0.0.22";
	ScreenManager screen;
	InputManager input;
	GameManager game;
	GUIManager gui;
	
	
	@Override
	public void create() {
		super.create();
		SpriteSheet.init();
		
		screen = new ScreenManager();
		game = new GameManager();
		gui = new GUIManager();
		input = new InputManager(screen, gui, game);
		
		Gdx.input.setInputProcessor(input);
	}
	
	public void update() {
		Debug.update();
		input.update();
		game.update();
		screen.update();
	}
	@Override
	public void render () {
		update();
		screen.start();
		game.render(screen);
		gui.render(screen);
		
//		screen.renderFixed(SpriteSheet.default_house, -100, -100)
		screen.finish(); 
		
	}
	
	@Override
	public void dispose () {
	}
	
	
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		screen.resize();
		gui.resize(width, height);
	}
}
