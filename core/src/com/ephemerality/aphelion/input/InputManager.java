package com.ephemerality.aphelion.input;

public class InputManager {
	
	public static boolean up, down, left, right;
	public static boolean hasPaused, hasInteracted, goingBack, isSelected, debug;
//	private static Gamepad pad;
//	private static ApplicationType Type;
	
	public static void init() {
//		InputManager.Type = Gdx.app.getType();
//	    if(InputManager.Type == Application.ApplicationType.Desktop){
//	    	pad = new Gamepad();
//	    }
	}
	
	
	//	TODO: Fix the SHIT out of this! Gamepad support, key binding support, etc
	public static void update() {
//		if(InputManager.Type == Application.ApplicationType.Desktop) {
//			if(pad.isActive) {
//				pad.update();
//			}else {
				Keyboard.update();
//			}			
//		}
		//TODO: Handle more types
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
	public static boolean checkForDebugKey() {
		if(InputManager.debug == true) {
			InputManager.debug = false;
			return true;
		}else return false;
	}
//	public static Notification checkForMenuUpdate() {
//		if(Gdx.input.isKeyPressed(Input.Keys.W) | Gdx.input.isKeyPressed(Input.Keys.UP))
//			return Notification.UP;
//		if(Gdx.input.isKeyPressed(Input.Keys.S) | Gdx.input.isKeyPressed(Input.Keys.DOWN))
//			return Notification.DOWN;
//		if(Gdx.input.isKeyPressed(Input.Keys.A) | Gdx.input.isKeyPressed(Input.Keys.LEFT))
//			return Notification.LEFT;
//		if(Gdx.input.isKeyPressed(Input.Keys.D) | Gdx.input.isKeyPressed(Input.Keys.RIGHT))
//			return Notification.RIGHT;
//		return null;
//	}
}
