package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.util.debug.Debug;


public class Master extends ApplicationAdapter {
	
	//-1 Exiting, 0 Main menu, 1 Game
	
	static int state = 0;
	ScreenManager screen;
	LoadManager loader;
	GameManager game;
	MenuManager main;	
	
	@Override
	public void create () {
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
		loader = new LoadManager();
		main = new MenuManager();
		game = new GameManager(screen);
	}

	
	public void update() {		
		InputManager.update();
		Debug.update();
		if(Master.state == -1) {
			Gdx.app.exit();
		}else if(Master.state == 0) {
			loader.update();
		}else if(Master.state == 1) {
			main.update();
		}else if(Master.state == 2) {
			game.update();
		}
		screen.update();
	}
	
	public static void setState(int state){
		if(state >= -1 && state <= 1) 
			Master.state = state;
		else 
			System.out.println("Error changing state.");
	}
	
	
	
	
	@Override
	public void resize(int width, int height) {
		screen.resize(width, height);
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void render () {
//	TODO : Have update on a fixed timer		
		update();		
		screen.start();
		if(state == 0) {
			loader.render(screen);
		}else if(state == 1) {
			main.render(screen);
		}else if(state == 2) {
			game.render(screen);
		}
		Debug.render(screen.getSpriteBatch(), screen.getBounds().x, screen.getBounds().y);
		screen.finish();
		
		
	}
	
	@Override
	public void dispose () {
		
//		game.dispose(); //do stuff in place of this: save, close assets, etc..
		loader.dispose();
		main.dispose();
		screen.dispose();
	}
}
