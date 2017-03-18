package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;


public class Master extends ApplicationAdapter {
	
	//-1 Exiting, 0 Main menu, 1 Game
	
	static int state;
	ScreenManager screen;
	GameManager game;
	MenuManager main;
	InputManager input;	
	FPSLogger fpslog;
	
	
	
	@Override
	public void create () {
		super.create();
		
		screen = new ScreenManager();
		input = new InputManager();
		main = new MenuManager();
		game = new GameManager(screen);
		fpslog = new FPSLogger();
	}

	
	public void update() {		
		input.update();
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
		
		
		Gdx.gl.glClearColor(0, 0, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screen.getSpriteBatch().enableBlending();

		fpslog.log();
		screen.getSpriteBatch().begin();
		if(state == 0) {
			main.render(screen.getSpriteBatch());
		}else if(state == 1) {
			game.render(screen);
		}
		
		screen.getSpriteBatch().end();
		
		
	}
	
	@Override
	public void dispose () {
		
//		game.dispose(); //do stuff in place of this: save, close assets, etc..
		main.dispose();
		screen.dispose();
	}
}
