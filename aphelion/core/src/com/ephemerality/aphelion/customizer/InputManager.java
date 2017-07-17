package com.ephemerality.aphelion.customizer;

import com.badlogic.gdx.InputProcessor;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class InputManager implements InputProcessor{

	GUIManager gui;
	GameManager game;
	ScreenManager screen;
	
	public InputManager(ScreenManager screen, GUIManager gui, GameManager game) {
		this.screen = screen;
		this.gui = gui;
		this.game = game;
	}
	
	
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		gui.mouseMoved(screenX, screenY);
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		System.out.println(amount);
		if(!gui.scrolled(amount)) {
			screen.scrolled(amount);
		}
		return false;
	}

}
