package com.ephemerality.aphelion.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Keyboard {
	
	
	public static void update() {
		InputManager.up = Gdx.input.isKeyPressed(Input.Keys.W) | Gdx.input.isKeyPressed(Input.Keys.UP);
		InputManager.down = Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN);
		InputManager.left = Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT);
		InputManager.right = Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) InputManager.hasInteracted = true;
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) InputManager.hasPaused = true;
	}
	
	
	
	
}
