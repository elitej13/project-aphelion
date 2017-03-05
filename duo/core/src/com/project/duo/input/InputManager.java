package com.project.duo.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputManager {
	
	public static boolean up, down, left, right;
	
	public InputManager() {
	//In here set up a way to branch based on medium of input.
	
	
	}
	
	
//	TODO: Fix the SHIT out of this! Gamepad support, key binding support, etc
	public void update() {
		up = Gdx.input.isKeyPressed(Input.Keys.W) | Gdx.input.isKeyPressed(Input.Keys.UP);
		down = Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN);
		left = Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT);
		right = Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT);
	}
	
}
