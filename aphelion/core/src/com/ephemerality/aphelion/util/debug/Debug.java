package com.ephemerality.aphelion.util.debug;

import java.util.Date;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ephemerality.aphelion.framework.Master;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.input.Keyboard;
import com.ephemerality.aphelion.util.FileManager;

public class Debug {
	
	public static String divider = "\n" + "=========================================================================================================";
	public static String full_log = "Full Log Report" + divider; 
	public static String text = "";
	public static String log_name;
	public static boolean infoIsActive;
	public static boolean logging;
	
	private static LinkedList<String> history = new LinkedList<String>();
	private static LinkedList<String> log = new LinkedList<String>();
	private static BitmapFont logger, console;
	private static Texture textfield;
	private static Texture cursor;

	private static boolean cursor_active;
	private static boolean consoleIsActive;
	private static int FONT_SIZE = 20;
	private static int cursoroffset;
	private static int historypointer;
	private static float systemTime;
	private static float fontheight;
	private static float fontwidth;
	private static float cursor_anim;
	
	
	public static void init() {
		//Font Generations
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/inconsolata/Inconsolata-Bold.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		parameter.size = FONT_SIZE;
		parameter.color = new Color(0, 1.0f, 0, 1.0f);
		logger = generator.generateFont(parameter);
		
		parameter.color = new Color(0, 0, 0, 1f);
		parameter.mono = true;
		console = generator.generateFont(parameter);
		
		
		//Font Metrics
		GlyphLayout glyphLayout = new GlyphLayout();
		String item = "D";
		
		glyphLayout.setText(logger,item);
		fontheight = glyphLayout.height + 2f;
		fontwidth = glyphLayout.width;
		
		
		//Textures
		Pixmap pix = new Pixmap(1, 1, Pixmap.Format.RGB888);
		
		pix.setColor(0.9f, 0.9f, 0.9f, 1f);
		pix.fillRectangle(0, 0, 1, 1);
		textfield = new Texture(pix);
		
		pix.setColor(0, 0, 0, 1f);
		pix.fillRectangle(0, 0, 1, 1);
		cursor = new Texture(pix);
		
		
		//Cleanup
		generator.dispose();
		pix.dispose();

		systemTime = 0;
		
		Date date = new Date();
		log_name = "log_" + date.toString().replaceAll(" ", "-").replaceAll(":", ".") + ".txt";
	}
	
	public static void update() {
		systemTime += Gdx.graphics.getRawDeltaTime();
		if(consoleIsActive) {
			String cache = Keyboard.getBuffer();			
			cursor_anim += Gdx.graphics.getDeltaTime();
			if(cursor_anim > 0.5f) {
				cursor_anim = 0;
				cursor_active = !cursor_active;
			}
			if(cache.contains("Enter")) {
				history.push(text);
				historypointer = -1;
				if(history.size() > 10) history.removeLast();
				execute(text.split(" "));
			}else if(cache.contains("Delete")) {
				if(cursoroffset > 0) {
					if(text.length() - cursoroffset > 0) {
						text = text.substring(0, text.length() - cursoroffset - 1) + text.substring(text.length() - cursoroffset);						
					}
				}else {
					if(text.length() > 0) {
						text = text.substring(0, text.length() - 1);
					}
				}
			}else if(cache.contains("Forward Delete")) {
				if(cursoroffset > 0)
					text = text.substring(0, text.length() - cursoroffset) + text.substring(text.length() - cursoroffset + 1);
			}else if(cache.contains("L-Shift") || cache.contains("R-Shift")) {
				cache = cache.replaceAll("L-Shift", "");
				cache = cache.replaceAll("R-Shift", "");
				pushCachetoText(cache);
			}else if(cache.contains("Space")) {
				cache = cache.replaceAll("Space", " ");
				cache = cache.toLowerCase();
				pushCachetoText(cache);
			}else if(cache.contains("Up")) {
				if(history.size() > ++historypointer) text = history.get(historypointer);
				else historypointer--;
			}else if(cache.contains("Down")) {
				if(--historypointer > 0) text = history.get(historypointer);
				else historypointer++;
			}else if(cache.contains("Right")) {
				if(cursoroffset > 0 && cursoroffset <= text.length())
					cursoroffset--;
			}else if(cache.contains("Left")) {
				if(cursoroffset >= 0 & cursoroffset < text.length())
					cursoroffset++;
			}else {
				cache = cache.toLowerCase();
				pushCachetoText(cache);
			}
		}
		if(InputManager.checkForDebugKey()) {
			infoIsActive = !infoIsActive;
		}
		if(InputManager.checkForConsoleKey()) {
			consoleIsActive = !consoleIsActive;
			if(consoleIsActive) {
				String[] args = {"pause", "true"};
				Master.pushArgs(args);
			}else {
				String[] args = {"pause", "false"};
				Master.pushArgs(args);
			}
		}
	}
	
	
	public static void render(Batch batch, float xoffset, float yoffset) {
		if(infoIsActive) {
			//Info Panel
			float fps = Gdx.graphics.getFramesPerSecond();
			float x = Gdx.graphics.getWidth() - 250 + xoffset;
			float y = Gdx.graphics.getHeight() - 10 + yoffset;
			int offset = 1;			
			logger.draw(batch, "Debug Info", x , y);
			logger.draw(batch, "----------------------------------", x, y - fontheight * offset++);
			logger.draw(batch, "System Time: " + getFormattedTime(systemTime), x, y - fontheight * offset++);
			logger.draw(batch, "FPS: " + fps, x, y - fontheight * offset++);
			
			//Console Log
			offset = 0;
			x = 10f + xoffset;
			logger.draw(batch, "Log", x, y - fontheight * offset++);
			logger.draw(batch, "----------------------------------", x, y - fontheight * offset++);
			for(int i = 0; i < log.size(); i++) {
				logger.draw(batch,log.get(i), x, y - fontheight * offset++);
			}
			
		}
		if(consoleIsActive) {
			//Console
			float x = 10f + xoffset;
			float y = 2f + yoffset;
			batch.draw(textfield, xoffset, yoffset, Gdx.graphics.getWidth(), 20f);
			console.draw(batch, text, x, y + fontheight);
			if(cursor_active) batch.draw(cursor, x + (fontwidth * (text.length() - cursoroffset)), y, 2f, 16f);
		}else {
			cursoroffset = 0;
		}
	}
	
	public static void pushCachetoText(String cache) {
		cache = cache.replaceAll("Numpad ", "");
		cache = cache.replaceAll("numpad ", "");
		
		if(cursoroffset > 0) {
			if(text.length() > 0)
				text = text.substring(0, text.length() - cursoroffset) + cache + text.substring(text.length() - cursoroffset);
		}else {	
			text += cache;
		}
	}
	
	
	public static void pushToConsole(String message, boolean omitTimeStamp) {
		if(!omitTimeStamp) log(getFormattedTime(systemTime) + " | " + message);
		else log(message);
	}
	public static void log(String message) {
		log.push(message);
		full_log += ("\n" + message);
		if(log.size() > 10)
			log.removeLast();
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
	
	public static void execute(String[] args) {
		text = "";
		Master.pushArgs(args);		
	}
	
	public static void dispose() {
		textfield.dispose();
		cursor.dispose();
		logger.dispose();
	}


	public static void startLogging() {
		logging = true;
		FileManager.writeToFile(log_name, full_log, false, false);
	}
}
