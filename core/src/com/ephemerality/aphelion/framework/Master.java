package com.project.duo.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.duo.input.InputManager;
public class Master extends ApplicationAdapter {
	
	//-1 Exiting, 0 Main menu, 1 Game
	private static int state;

	private GameManager game;
	private MenuManager main;
	private InputManager input;
	private SpriteBatch sb;
	
	@Override
	public void create () {
		input = new InputManager();
		game = new GameManager();
		main = new MenuManager();
		sb = new SpriteBatch();
	}

	
	public void update() {
//		FPS counter
//		System.out.println(Gdx.graphics.getFramesPerSecond());
		
		
		input.update();
		if(Master.state == -1) {
			Gdx.app.exit();
		}else if(Master.state == 0) {
			main.update();
		}else if(Master.state == 1) {
			game.update();
		}
	}
	public static void setState(int state){
		if(state >= -1 && state <= 1) 
			Master.state = state;
		else 
			System.out.println("Error changing state.");
	}
	
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		sb.enableBlending();
		
		sb.begin();
		if(state == 0) {
			main.render(sb);
		}else if(state == 1) {
			game.render(sb);
		}
		sb.end();
		
		update();
		
	}
	
	@Override
	public void dispose () {
		
//		game.dispose(); //do stuff in place of thiss
		main.dispose();
		sb.dispose();
	}
}
