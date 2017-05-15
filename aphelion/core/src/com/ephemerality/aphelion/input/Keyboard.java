package com.ephemerality.aphelion.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ephemerality.aphelion.util.debug.Debug;

public class Keyboard {
	
	public static boolean[] ispressed = new boolean[255];
	public static boolean[] keyschecked = new boolean[255];
	private static String buffer = "";
	
	public static void update() {
		buildBuffer();
		InputManager.up = Gdx.input.isKeyPressed(Input.Keys.W) | Gdx.input.isKeyPressed(Input.Keys.UP);
		InputManager.down = Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN);
		InputManager.left = Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT);
		InputManager.right = Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT);
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) InputManager.hasInteracted = true;
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE) || Gdx.input.isKeyJustPressed(Input.Keys.TAB)) InputManager.hasPaused = true;	
		if(Gdx.input.isKeyJustPressed(Input.Keys.SLASH)) InputManager.debug = true;
		//Grave (`) is same key as Tilde (~)
		if(Gdx.input.isKeyJustPressed(Input.Keys.GRAVE)) {
			InputManager.console = true;
			Keyboard.buffer = "";
			Debug.text = "";
		}
	}
	
	
	public static void buildBuffer() {
		for(int i = 0; i < 255; i++) {
			if(Gdx.input.isKeyJustPressed(i)) {
				buffer += Input.Keys.toString(i);
			}
		}
	}
	
	
	public static String getBuffer() {
		String temp = Keyboard.buffer;
		Keyboard.buffer = "";
		return temp;
	}
	
	
}
