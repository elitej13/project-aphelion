package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.util.FileManager;
import com.ephemerality.aphelion.util.debug.Debug;
import com.ephemerality.aphelion.util.debug.DebugType;


public class Master extends ApplicationAdapter {
	
	public static final String version = "Pre-Alpha || Version 0.0.9";
	public static float SYSTEM_TIME;
	//-1 Exiting, 0 Main menu, 1 Game
	private static int state = 0;
	ScreenManager screen;
	LoadManager loader;
	GameManager game;
	MenuManager main;	

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
//-------------------------End of debug
	
	@Override
	public void create () {
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
		loader = new LoadManager(screen);
		main = new MenuManager();
		
		Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler);
	}
	
	
	public void update() {	
		InputManager.update();
		
		debugUpdate();
		SYSTEM_TIME += Gdx.graphics.getRawDeltaTime();
		if(state == -1) {
			Gdx.app.exit();
		}else if(state == 0) {
			loader.update();
			if(state != 0) {
				game = new GameManager(screen, loader, "Josh");
			}
		}else if(state == 1) {
			main.update();
		}else if(state == 2) {
//			System.out.println("Update Start Time: " + System.currentTimeMillis());
			game.update();
//			System.out.println("Update Stop Time: " + System.currentTimeMillis());
		}
		screen.update();
//		screen.rotate(0f, 1f, 0f, 1f);
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
			if(args[0].equalsIgnoreCase("set")) {
				if(args[1].equalsIgnoreCase("help")) {
					Debug.pushToConsole("set level string (level name store in maps folder)", true);
					Debug.pushToConsole("set level int int (width height)", true);
					Debug.pushToConsole("set background float float float", true);
					Debug.pushToConsole("Current set commands:", false);
				}else if(args[1].equalsIgnoreCase("background")) {
					float r = Float.parseFloat(args[2]);
					float g = Float.parseFloat(args[3]);
					float b = Float.parseFloat(args[4]);
					float a = Float.parseFloat(args[5]);
					screen.setColor(new Color(r, g, b, a));
					Debug.pushToConsole("Setting background to " + r + " " + g + " "+ b + " " + " " + a, false);
					
				}else if(args[1].equalsIgnoreCase("level")) {
					if(args.length > 3) {
						int w = Integer.parseInt(args[2]);
						int h = Integer.parseInt(args[3]);
						game.resizeLevel(w, h);
					}else {
						String path = "maps/" + args[2] + ".bin";
						game.loadLevel(args[2], path, false);
						Debug.pushToConsole("Setting level to " + path, false);						
					}
				}else {
					Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
				}
			}
			
			else if(args[0].equalsIgnoreCase("log")) {
				if(args[1].equalsIgnoreCase("dump")) {
					Debug.startLogging();
					Debug.pushToConsole(DebugType.Console_Logging.toString(), false);
				}else {
					Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
				}
			}
			
			else if(args[0].equalsIgnoreCase("save")) {
				if(game.save()) {
					Debug.pushToConsole(DebugType.Game_save_Successful.toString(), false);
				}else {
					Debug.pushToConsole(DebugType.Game_save_Unsuccessful.toString(), false);
				}
			}
			
			else if(args[0].equalsIgnoreCase("pause")) {
				if(args[1].equalsIgnoreCase("true")) {
					game.isPaused = true;
				}else if(args[1].equalsIgnoreCase("false")) {
					game.isPaused = false;
				}else {
					Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
				}
			}
			
			else if(args[0].equalsIgnoreCase("help")) {
				Debug.pushToConsole("For a list of all set-able variables try \"set help\"", true);
				Debug.pushToConsole("\"set\" followed by background or other such variables.", true);
				Debug.pushToConsole("Here are a list of all valid commands", false);
				
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
		Master.args = args;
	}
	//End of Debugger methods
	
	public static void setState(int state){
		if(state >= -1 && state <= 2) 
			Master.state = state;
		else 
			System.out.println("Error changing state.");
		
		if(state == 0) {
			String[] args = {"set", "background", "0f", "0f", "0f", "1f"};
			pushArgs(args);
		}else if(state == 1) {
			String[] args = {"set", "background", "0.6f", "0.6f", "0.6f", "1f"};
			pushArgs(args);
		}else if(state == 2) {
			String[] args = {"set", "background", "0f", "0f", "0f", "1f"};
			pushArgs(args);
		}
	}
	
	
	
	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
		screen.resize(width, height);
	}
	
	@Override
	public void resume() {
		
	}
	
	@Override
	public void render () {
//	TODO : Have update on a fixed timer		
		update();
		screen.start();
		if(state == 0) {
			loader.render(screen);
		}else if(state == 1) {
			main.render(screen, loader);
		}else if(state == 2) {
//			System.out.println("Render Start Time: " + System.currentTimeMillis());
			game.render(screen);
//			System.out.println("Render Stop Time: " + System.currentTimeMillis());
		}
		Debug.render(screen.getSpriteBatch(), screen.getBounds().x, screen.getBounds().y);
		screen.renderFixedString(Color.GOLD, version, Gdx.graphics.getWidth() - 175, 15);
		screen.finish();
	}
	
	@Override
	public void dispose () {
		if(game != null) game.dispose(); //do stuff in place of this: save, close assets, etc..
		loader.dispose();
		main.dispose();
		screen.dispose();
		if(Debug.logging) {
			FileManager.writeToFile(Debug.log_name, Debug.full_log, false, false);
		}
		Debug.dispose();
	}
}
