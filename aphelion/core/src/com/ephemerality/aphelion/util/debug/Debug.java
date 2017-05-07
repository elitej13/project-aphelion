package com.ephemerality.aphelion.util.debug;

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
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.input.Keyboard;

public class Debug {
	
	
	private static LinkedList<String> log = new LinkedList<String>();
	private static LinkedList<String> history = new LinkedList<String>();
	private static Texture textfield;
	private static Texture cursor;
	private static BitmapFont logger, console;
	public static String text = "";

	public static boolean active;
	private static int FONT_SIZE = 20;
	private static int cursoroffset;
	private static int historypointer;
	private static float systemTime;
	private static float fontheight;
	private static float fontwidth;
	
	
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
	}
	
	
	public static void update() {
		systemTime += Gdx.graphics.getRawDeltaTime();
		if(!active) {
			
		}else {
			String cache = Keyboard.getBuffer();			
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
			active = !active;
		}
	}
	
	
	public static void render(Batch batch, float xoffset, float yoffset) {
		if(active) {
			float fps = Gdx.graphics.getFramesPerSecond();
			float x = Gdx.graphics.getWidth() - 100 + xoffset;
			float y = Gdx.graphics.getHeight() - 10 + yoffset;
			int offset = 1;
			
			//Info Panel
			logger.draw(batch, "Debug Info", x , y);
			logger.draw(batch, "FPS: " + fps, x, y - fontheight * offset++);
			
			offset = 0;
			x = 10f + xoffset;
			//Console Log
			logger.draw(batch, "Log", x, y - fontheight * offset++);
			logger.draw(batch, "----------------------------------", 10, y - fontheight * offset++);
			for(int i = 0; i < log.size(); i++) {
				logger.draw(batch,log.get(i), x, y - fontheight * offset++);
			}
			
			//Console
			x = 10f + xoffset;
			y = 2f + yoffset;
			batch.draw(textfield, xoffset, yoffset, Gdx.graphics.getWidth(), 20f);
			console.draw(batch, text, x, y + fontheight);
			batch.draw(cursor, x + (fontwidth * (text.length() - cursoroffset)), yoffset, 2f, 16f);
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
		if(!omitTimeStamp) log.push(getFormattedTime(systemTime) + " | " + message);
		else log.push(message);
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
	
	public static boolean execute(String[] args) {
		text = "";
		try {
			if(args[0].equalsIgnoreCase("set")) {
				if(args[1].equalsIgnoreCase("help")) {
					
				}else if(args[1].equalsIgnoreCase("background")) {
					float r = Float.parseFloat(args[2]);
					float g = Float.parseFloat(args[3]);
					float b = Float.parseFloat(args[4]);
					float a = Float.parseFloat(args[5]);
					ScreenManager.color = new Color(r, g, b, a);
					
				}else {
					pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
				}
			}
			
			else if(args[0].equalsIgnoreCase("help")) {
				pushToConsole("For a list of all setable variables try \"set help\"", true);
				pushToConsole("\"set\" followed by background or other such variables.", true);
				pushToConsole("Here are a list of all valid commands", false);
				
			}else {
				pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
			
			}
		}catch(IndexOutOfBoundsException e) {
			pushToConsole(DebugType.Console_Expecting_Args.toString(), false);
		}catch(NumberFormatException e) {
			pushToConsole(DebugType.Console_Expecting_Number.toString(), false);
		}
		
		return true;
	}
	
	public static void dispose() {
		textfield.dispose();
		cursor.dispose();
		logger.dispose();
	}
}
