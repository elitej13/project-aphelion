package com.project.duo.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.project.duo.spawn.Puppet;
import com.project.duo.util.Direction;
public class Runtime extends ApplicationAdapter {
	
	//-1 Exiting, 0 Main menu, 1 Game
	private static int state;

	private Game game;
	private MainMenu main;
	private SpriteBatch sb;
	
	
	@Override
	public void create () {
		game = new Game();
		main = new MainMenu(game);
		sb = new SpriteBatch();
	}

	
	public void update() {
		if(state == -1) {
			Gdx.app.exit();
		}else if(state == 0) {
			main.update();
		}else if(state == 1) {
			game.update();
		}
	}
	public static void setState(int state){
		if(state >= -1 && state <= 1) 
			Runtime.state = state;
		else 
			System.out.println("Error changing state.");
	}
	
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 0, 1); 
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
		game.dispose();
		main.dispose();
		sb.dispose();
	}
}
