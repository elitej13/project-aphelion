package com.ephemerality.aphelion.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class InputManager {
	
	public static boolean up, down, left, right;
	private static boolean hasPaused, hasInteracted;
	
	public InputManager() {
	//	In here set up a way to branch based on medium of input.
	
	}
	
	
	//	TODO: Fix the SHIT out of this! Gamepad support, key binding support, etc
	public void update() {
		
		InputManager.up = Gdx.input.isKeyPressed(Input.Keys.W) | Gdx.input.isKeyPressed(Input.Keys.UP);
		InputManager.down = Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN);
		InputManager.left = Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT);
		InputManager.right = Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) hasInteracted = true;
		if(Gdx.input.isKeyJustPressed(Input.Keys.E)) hasPaused = true;
	}
	
	
	
	public static boolean checkForPause() {
		if(InputManager.hasPaused == true) {
			InputManager.hasPaused = false;
			return true;
		}else return false;
	}
	public static boolean checkForInteract() {
		if(InputManager.hasInteracted == true) {
			InputManager.hasInteracted = false;
			return true;
		}else return false;
	}
}
