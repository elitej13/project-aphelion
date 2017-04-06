package com.ephemerality.aphelion.input;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.controllers.Controller;
import com.badlogic.gdx.controllers.ControllerListener;
import com.badlogic.gdx.controllers.ControllerManager;
import com.badlogic.gdx.controllers.Controllers;
import com.badlogic.gdx.controllers.PovDirection;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Timer;

public class Gamepad implements ControllerListener{
	
	private Controller cont;
	public boolean isActive;

	public boolean up, down, left, right;
	public boolean hasPaused, hasInteracted, goingBack, isSelected;
	
//	private String TAG = DebugType.Controller.toString();
	
	public Gamepad() {
		Controllers.addListener(this);
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                reloadControllers();
            }
        }, 5f, 0.5f);
    }
	
	public void update() {
		System.out.println(true);
	}
	
	
	
	@Override
	public boolean buttonDown(Controller controller, int buttonCode) {
		checkForActiveControllerChange(controller);
//		Gdx.app.log(TAG, "Button Down: " + buttonCode);
		return false;
	}
	@Override
	public boolean buttonUp(Controller controller, int buttonCode) {
		checkForActiveControllerChange(controller);
//		Gdx.app.log(TAG, "Button Up: " + buttonCode);
		return false;
	}
	@Override
	public boolean axisMoved(Controller controller, int axisCode, float value) {
//		Gdx.app.log(TAG, "Axis Moved: " + axisCode + ", Value: " + value);
		return false;
	}
	@Override
	public boolean povMoved(Controller controller, int povCode, PovDirection value) {
		checkForActiveControllerChange(controller);
//		Gdx.app.log(TAG, "POV Moved: " + povCode + ", Value: " + value);
		return false;
	}
	@Override
	public boolean xSliderMoved(Controller controller, int sliderCode, boolean value) {
		checkForActiveControllerChange(controller);
//		Gdx.app.log(TAG, "Axis Moved: " + sliderCode + ", Value: " + value);
		return false;
	}
	@Override
	public boolean ySliderMoved(Controller controller, int sliderCode, boolean value) {		
		checkForActiveControllerChange(controller);
//		Gdx.app.log(TAG, "Axis Moved: " + sliderCode + ", Value: " + value);
		return false;
	}
	@Override
	public boolean accelerometerMoved(Controller controller, int accelerometerCode, Vector3 value) {	
		checkForActiveControllerChange(controller);
//		Gdx.app.log(TAG, "Axis Moved: " + accelerometerCode + ", Vector Value: " + value);
		return false;
	}
	
	/**
	 * Courtesy of LibGDX GitHub user Cevantime
	 * https://github.com/libgdx/libgdx/issues/2182
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Array<Controller> reloadControllers(){
		String controllersClassName = "com.badlogic.gdx.controllers.Controllers";
		try {
			//very hacky and dirty method to update controllers
			Class controllersClass = Class.forName(controllersClassName);
			Field managersField = controllersClass.getDeclaredField("managers");
			managersField.setAccessible(true);
			ObjectMap<Application, ControllerManager> managers = (ObjectMap<Application, ControllerManager>) managersField.get(null);
			managersField.setAccessible(false);
			String desktopControllerManagerClassName = "com.badlogic.gdx.controllers.desktop.DesktopControllerManager";
			managers.put(Gdx.app, (ControllerManager) Class.forName(desktopControllerManagerClassName).newInstance());
			Field runnablesField = Class.forName("com.badlogic.gdx.backends.lwjgl.LwjglApplication").getDeclaredField("runnables");
			runnablesField.setAccessible(true);
			Array<Runnable> runnables = (Array<Runnable>) runnablesField.get(Gdx.app);
			runnablesField.setAccessible(false);
			runnables.removeIndex(runnables.size-1);
		} catch (ClassNotFoundException ex) {
			Logger.getLogger(Gamepad.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NoSuchFieldException ex) {
			Logger.getLogger(Gamepad.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SecurityException ex) {
			Logger.getLogger(Gamepad.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalArgumentException ex) {
			Logger.getLogger(Gamepad.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			Logger.getLogger(Gamepad.class.getName()).log(Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			Logger.getLogger(Gamepad.class.getName()).log(Level.SEVERE, null, ex);
		}
		if(isActive) checkActiveIsConnected();
		return Controllers.getControllers();
	}
	public void checkActiveIsConnected() {
		boolean activeIsConnected = false;
		for(Controller controller : Controllers.getControllers()) {
			if(controller.equals(cont)) {
				activeIsConnected = true;
			}
		}
		if(!activeIsConnected) {
			isActive = false;
			//Notify User
		}
	}
	
	public void checkForActiveControllerChange(Controller controller) {
		if(controller != cont) {
			isActive = true;
			cont = controller;
		}
		//Notify user
	}
	
	@Override
	public void connected(Controller controller) {
	}
	@Override
	public void disconnected(Controller controller) {
	}
}

