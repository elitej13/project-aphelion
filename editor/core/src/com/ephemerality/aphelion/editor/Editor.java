package com.ephemerality.aphelion.editor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;

public class Editor extends ApplicationAdapter {
	
	
	private ScreenManager screen;
	
	
	
	@Override
	public void create () {
		super.create();
		InputManager.update();
		screen = new ScreenManager();
	}

	
	public void update() {
		InputManager.update();
		
	}
	
	
	@Override
	public void render () {
		update();
		
		Gdx.gl.glClearColor(0, 0, 0, 1); 
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		screen.getSpriteBatch().enableBlending();
		

		screen.getSpriteBatch().begin();
		
		
		screen.getSpriteBatch().end();
	}
	
	@Override
	public void dispose () {
	}
}
