package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.util.debug.Debug;


public class Master extends ApplicationAdapter {
	
	//-1 Exiting, 0 Main menu, 1 Game
	
	static int state;
	ScreenManager screen;
	GameManager game;
	MenuManager main;	
	
	@Override
	public void create () {
//		super.create();
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
		main = new MenuManager();
		game = new GameManager(screen);
	}

	
	public void update() {		
		InputManager.update();
		Debug.update();
		if(Master.state == -1) {
			Gdx.app.exit();
		}else if(Master.state == 0) {
			main.update();
		}else if(Master.state == 1) {
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
	
	
	
	
//	Start of Experimental	//
	public void resize(int width, int height) {
		super.resize(width, height);
		screen.resize();
	}
//	End of Experimental	//
	
	
	
	@Override
	public void render () {
//	TODO : Have update on a fixed timer		
		update();		
		screen.start();
		if(state == 0) {
			main.render(screen);
		}else if(state == 1) {
			game.render(screen);
		}
		Debug.render(screen.getSpriteBatch());
		screen.finish();
		
		
	}
	
	@Override
	public void dispose () {
		
//		game.dispose(); //do stuff in place of this: save, close assets, etc..
		main.dispose();
		screen.dispose();
	}
}
