package com.ephemerality.aphelion.util.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;

public class Debug {
	
	private static boolean active;
	
	private static BitmapFont CAPTAIN_FALCON_MECHA;
	private static int FONT_SIZE = 16;
	
	
	public static void init() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/captain-falcon_mecha/Mecha.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = Debug.FONT_SIZE;
		parameter.color = Color.GREEN;
		CAPTAIN_FALCON_MECHA = generator.generateFont(parameter);
		generator.dispose();
		
	}
	
	
	public static void update() {
		if(!Debug.active) {
			
		}else {
			
		}
		if(InputManager.checkForDebugKey()) {
			Debug.active = !Debug.active;
		}
	}
	
	
	public static void render(ScreenManager screen) {
		if(Debug.active) {
			int fontsize = Debug.FONT_SIZE;
			int offset = 1;
			float fps = Gdx.graphics.getFramesPerSecond();
			float x = Gdx.graphics.getWidth() - 70;
			float y = Gdx.graphics.getHeight() - 10;
			
//			Debug.CAPTAIN_FALCON_MECHA.draw(screen.getSpriteBatch(), "Debugging", x , y);
			Debug.CAPTAIN_FALCON_MECHA.draw(screen.getSpriteBatch(), "FPS: " + fps, x, y - fontsize * offset++);
//			Debug.CAPTAIN_FALCON_MECHA.draw(screen.getSpriteBatch(), "FPS: ", x, y - fontsize * offset++);
		}
	}
	
}
