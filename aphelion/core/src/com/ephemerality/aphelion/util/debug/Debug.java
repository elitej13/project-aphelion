package com.ephemerality.aphelion.util.debug;

import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ephemerality.aphelion.input.InputManager;

public class Debug {
	
	private static boolean active;
	
	private static BitmapFont font;
	private static int FONT_SIZE = 36;
	private static LinkedList<String> console = new LinkedList<String>();
	private static float systemTime;
	
	public static void init() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/style-7_thin-pixel-7/thin_pixel-7.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = Debug.FONT_SIZE;
		parameter.color = Color.GREEN;
		font = generator.generateFont(parameter);
		generator.dispose();
		systemTime = 0f;
	}
	
	
	public static void update() {
		systemTime += Gdx.graphics.getRawDeltaTime();
		if(!Debug.active) {
			
		}else {
			
		}
		if(InputManager.checkForDebugKey()) {
			Debug.active = !Debug.active;
		}
	}
	
	
	public static void render(Batch batch, float xoffset, float yoffset) {
		if(Debug.active) {

			GlyphLayout glyphLayout = new GlyphLayout();
			String item = "Debug Info";
			glyphLayout.setText(font,item);
			float w = glyphLayout.height;
			int fontHeight = (int) (w + 2);
			int offset = 1;
			float fps = Gdx.graphics.getFramesPerSecond();
			float x = Gdx.graphics.getWidth() - 100 + xoffset;
			float y = Gdx.graphics.getHeight() - 10 + yoffset;
			
			
			Debug.font.draw(batch, "Debug Info", x , y);
			Debug.font.draw(batch, "FPS: " + fps, x, y - fontHeight * offset++);
			
			offset = 0;
			
			Debug.font.draw(batch, "Console", 10, y - fontHeight * offset++);
			Debug.font.draw(batch, "----------------------------------", 10, y - fontHeight * offset++);
			for(int i = 0; i < console.size(); i++) {
				Debug.font.draw(batch,console.get(i), 10, y - fontHeight * offset++);
			}
		}
	}
	
	public static void pushToConsole(String message) {
		console.push(Debug.getFormattedTime(systemTime) + " | " + message);
		if(console.size() > 10)
			console.removeLast();
	}
	
	public static String getFormattedTime(float timeInSeconds) {
		int mins = (int) (timeInSeconds / 60f);
		int secs = (int) (timeInSeconds - mins * 60f);
		int mSecs = (int) ((timeInSeconds - (int)(timeInSeconds)) * 100);
		String minutes = "00";
		if(mins > 9) minutes = "" + mins;
		else minutes = "0" + mins;
		String seconds = "00";
		if(secs > 9) seconds = "" + secs;
		else seconds = "0" + secs;
		String mSeconds = "00";
		if(mSecs > 9) mSeconds = "" + mSecs;
		else mSeconds = "0" + mSecs;
		
		return minutes + ":" + seconds + ":" + mSeconds;
	}
	
}
