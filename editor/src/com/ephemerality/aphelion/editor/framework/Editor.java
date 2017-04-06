package com.ephemerality.aphelion.editor.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.util.debug.Debug;

public class Editor extends ApplicationAdapter {
	
	
	private ScreenManager screen;
	
	
	
	@Override
	public void create () {
		super.create();
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
	}

	
	public void update() {
		InputManager.update();
		Debug.update();
	}
	
	
	@Override
	public void render () {
		update();
		

		screen.start();
		Debug.render(screen);
		//Render Loop
		
		
		screen.finish();
	}
	
	@Override
	public void dispose () {
	}
}
