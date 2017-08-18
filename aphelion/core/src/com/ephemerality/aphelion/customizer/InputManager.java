package com.ephemerality.aphelion.customizer;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Keys;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class InputManager implements InputProcessor{

	public final static float SCROLL_SPEED = 4f;
	
	GUIManager gui;
	GameManager game;
	ScreenManager screen;
	boolean[] keys = new boolean[256];
	
	public InputManager(ScreenManager screen, GUIManager gui, GameManager game) {
		this.screen = screen;
		this.gui = gui;
		this.game = game;
	}
	
	public void update() {
		if(keys[Keys.UP] || keys[Keys.W]) screen.translate(0, SCROLL_SPEED);
		if(keys[Keys.DOWN] || keys[Keys.S]) screen.translate(0, -SCROLL_SPEED);
		if(keys[Keys.LEFT] || keys[Keys.A]) screen.translate(-SCROLL_SPEED, 0);
		if(keys[Keys.RIGHT] || keys[Keys.D]) screen.translate(SCROLL_SPEED, 0);
	}
	
	@Override
	public boolean keyDown(int keycode) {
		keys[keycode] = true;
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		keys[keycode] = false;
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(!gui.dragged(screenX, screenY)) {
			
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(!gui.mouseMoved(screenX, screenY)) {
			
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if(!gui.scrolled(amount)) {
			screen.zoom(amount);
//			screen.resize();
		}
		return false;
	}

}
