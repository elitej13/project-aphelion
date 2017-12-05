package com.ephemerality.aphelion.customizer;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.util.FileManager;
import com.ephemerality.aphelion.util.debug.Debug;
import com.ephemerality.aphelion.util.debug.DebugType;

public class Editor extends ApplicationAdapter {
	

	public static final String version = "Pre-alpha || Version 0.0.23";
	public static float SYSTEM_TIME;
	public static boolean paused;
	
	ScreenManager screen;
	GameManager game;
	GUIManager gui;
	InputManager input;
	
	//-----------------------Strictly for debug
		private static String[] args;
		private static boolean requested;
		private Thread.UncaughtExceptionHandler _unCaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread thread, Throwable ex) {
				ex.printStackTrace();
				Debug.full_log += ("\n" + DebugType.Uncaught_Exception.toString());
				Debug.full_log += (Debug.divider + "\n" + ex.getMessage() + Debug.divider);
				FileManager.writeToFile(Debug.log_name, Debug.full_log, false, false);
			}
		};
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
		/**
		 * 
		 * @param timeInSeconds
		 * @return int[] where index 0 is hours, 1 is minutes, 2 is seconds, and 3 is milliseconds
		 */
		public static int[] getTime(float timeInSeconds) {
			int[] time = new int[4];
			time[0] = (int) ((timeInSeconds / 60f) / 60f);
			time[1] = (int) ((timeInSeconds / 60f) - (time[0] * 60f));
			time[2] = (int) (timeInSeconds - time[1] * 60f);
			time[3] = (int) ((timeInSeconds - (int)(timeInSeconds)) * 100);
			return time;
		}
		//Debugger methods
		public void debugUpdate() {
			Debug.update();
			if(requested) {
				execute();
			}
		}
		public boolean execute() {
			requested = false;
			try {
				if(args[0].equalsIgnoreCase("log")) {
					if(args[1].equalsIgnoreCase("dump")) {
						Debug.startLogging();
						Debug.pushToConsole(DebugType.Console_Logging.toString(), false);
					}else {
						Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
					}
				}
				
				else if(args[0].equalsIgnoreCase("save")) {
					if(game.saveMap(args[1])) {
						Debug.pushToConsole(DebugType.Map_save_Successful.toString(), false);
					}else {
						Debug.pushToConsole(DebugType.Map_save_Unsuccessful.toString(), false);
					}
				}
				
				
				else if(args[0].equalsIgnoreCase("load")) {
					if(game.loadMap(args[1])) {
						Debug.pushToConsole(DebugType.Map_load_Successful.toString(), false);
					}else {
						Debug.pushToConsole(DebugType.Map_load_Unsuccessful.toString(), false);
					}
				}
				
				else if(args[0].equalsIgnoreCase("pause")) {
					if(args[1].equalsIgnoreCase("true")) {
						paused = true;
					}else if(args[1].equalsIgnoreCase("false")) {
						paused = false;
					}else {
						Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
					}
				}
				
				else if(args[0].equalsIgnoreCase("help")) {
					Debug.pushToConsole("For a list of all set-able variables try \"set help\"", true);
					Debug.pushToConsole("\"set\" followed by background or other such variables.", true);
					Debug.pushToConsole("Here is a list of all valid commands", false);
					
				}else {
					Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
				
				}
			}catch(IndexOutOfBoundsException e) {
				Debug.pushToConsole(DebugType.Console_Expecting_Args.toString(), false);
				e.printStackTrace();
			}catch(NumberFormatException e) {
				Debug.pushToConsole(DebugType.Console_Expecting_Number.toString(), false);
			}
			
			return true;
		}
		public static void pushArgs(String[] args) {
			requested = true;
			Editor.args = args;
		}
		//End of Debugger methods
	//-------------------------End of debug
	
	
	@Override
	public void create() {
		super.create();
		SpriteSheet.init();
		Debug.init();
		
		screen = new ScreenManager();
		game = new GameManager();
		gui = new GUIManager(screen, game);
		input = new InputManager(screen, gui, game);
		
		Gdx.input.setInputProcessor(input);
	}

	public void update() {
		debugUpdate();
		input.update();
		game.update();
		screen.update();
	}
	@Override
	public void render () {
		update();
		screen.start();
		game.render(screen);
		gui.render(screen);

		screen.renderFixedString(Color.GOLD, version, Gdx.graphics.getWidth() - 250, 15);
		Debug.render(screen.sb, screen.bounds.x, screen.bounds.y);
//		screen.renderFixed(SpriteSheet.default_house, -100, -100)
		screen.finish(); 
		
	}
	
	@Override
	public void dispose () {
	}
	
	
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		screen.resize();
		gui.resize(width, height);
	}
}
