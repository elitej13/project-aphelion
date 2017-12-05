package com.ephemerality.aphelion.customizer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class InputManager implements InputProcessor{

	public final static float SCROLL_SPEED = 5f;
	public final static float MAX_SCROLL_SPEED = 12f;
	
	GUIManager gui;
	GameManager game;
	ScreenManager screen;
	boolean[] keys = new boolean[256];
	
	public InputManager(ScreenManager screen, GUIManager gui, GameManager game) {
		this.screen = screen;
		this.gui = gui;
		this.game = game;
		com.ephemerality.aphelion.input.InputManager.init();
	}
	
	public void update() {
		com.ephemerality.aphelion.input.InputManager.update();

		if(!Editor.paused) {
			if(keys[Keys.SPACE]) {
				if(keys[Keys.UP] || keys[Keys.W]) screen.translate(0, MAX_SCROLL_SPEED * screen.oc.zoom);
				if(keys[Keys.DOWN] || keys[Keys.S]) screen.translate(0, -MAX_SCROLL_SPEED * screen.oc.zoom);
				if(keys[Keys.LEFT] || keys[Keys.A]) screen.translate(-MAX_SCROLL_SPEED * screen.oc.zoom, 0);
				if(keys[Keys.RIGHT] || keys[Keys.D]) screen.translate(MAX_SCROLL_SPEED * screen.oc.zoom, 0);			
			}else {
				if(keys[Keys.UP] || keys[Keys.W]) screen.translate(0, SCROLL_SPEED * screen.oc.zoom);
				if(keys[Keys.DOWN] || keys[Keys.S]) screen.translate(0, -SCROLL_SPEED * screen.oc.zoom);
				if(keys[Keys.LEFT] || keys[Keys.A]) screen.translate(-SCROLL_SPEED * screen.oc.zoom, 0);
				if(keys[Keys.RIGHT] || keys[Keys.D]) screen.translate(SCROLL_SPEED * screen.oc.zoom, 0);				
			}
		}
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
	
	public void editMap(int screenX, int screenY, int pointer) {
		int x = (int) ((screenX * screen.oc.zoom) + screen.bounds.x);
		int y = (int) (((Gdx.graphics.getHeight() - screenY) * screen.oc.zoom) + screen.bounds.y);
		game.editMap(screen, x, y, GUIManager.active);
		
	}
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(!gui.mouseMoved(screenX, screenY, button == Buttons.LEFT)) {
			editMap(screenX, screenY, pointer);
		}
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if(!gui.mouseMoved(screenX, screenY, button == Buttons.LEFT)) {
			
		}
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		if(!gui.dragged(screenX, screenY)) {
			editMap(screenX, screenY, pointer);
		}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(!gui.mouseMoved(screenX, screenY,  Gdx.input.isButtonPressed(Buttons.LEFT))) {
			
		}
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		if(!gui.scrolled(amount, new Vector2(Gdx.input.getX(), Gdx.input.getY()))) {
			screen.zoom(amount);
//			screen.resize();
		}
		return false;
	}

}