package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class MenuManager {

	private Stage stage;
	
	public void update() {
		stage = new Stage();
	}
	
	
	public void render(ScreenManager screen) {
		
	}
	
	public void dispose() {
		stage.dispose();
	}
	
}
