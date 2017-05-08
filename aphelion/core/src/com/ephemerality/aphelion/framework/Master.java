package com.ephemerality.aphelion.framework;

import java.lang.Thread.UncaughtExceptionHandler;

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
	
	//-1 Exiting, 0 Main menu, 1 Game
	private static int state = 0;
	private boolean hasInitialized;
	
	ScreenManager screen;
	LoadManager loader;
	GameManager game;
	MenuManager main;	

//	Strictly for debug
	private static String[] args;
	private static boolean requested;
	private UncaughtExceptionHandler defaultUEH;
	private Thread.UncaughtExceptionHandler _unCaughtExceptionHandler = new Thread.UncaughtExceptionHandler() {
		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			ex.printStackTrace();
			Debug.full_log += ("\n" + DebugType.Uncaught_Exception.toString());
			Debug.full_log += (Debug.divider + "\n" + ex.getMessage() + Debug.divider);
			FileManager.writeToFile(Debug.log_name, Debug.full_log, false, false);
		}
	};
//	End of debug
	
	@Override
	public void create () {
		InputManager.init();
		Debug.init();
		screen = new ScreenManager();
		loader = new LoadManager(screen);
		main = new MenuManager();
		game = new GameManager(screen);
		
		
		defaultUEH = Thread.getDefaultUncaughtExceptionHandler();
		Thread.setDefaultUncaughtExceptionHandler(_unCaughtExceptionHandler);
		
	}

	
	public void update() {		
		InputManager.update();
		debugUpdate();
		if(state == -1) {
			Gdx.app.exit();
		}else if(state == 0) {
			loader.update();
		}else if(state == 1) {
			main.update();
		}else if(state == 2) {
			if(!hasInitialized) game.init(loader);
			game.update();
		}
		screen.update();
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
					Debug.pushToConsole("set background float float float", true);
					Debug.pushToConsole("Current set commands:", false);
				}else if(args[1].equalsIgnoreCase("background")) {
					float r = Float.parseFloat(args[2]);
					float g = Float.parseFloat(args[3]);
					float b = Float.parseFloat(args[4]);
					float a = Float.parseFloat(args[5]);
					screen.setColor(new Color(r, g, b, a));
					Debug.pushToConsole("Setting bakcground to " + r + " " + g + " "+ b + " " + " " + a, false);
					
				}else if(args[1].equalsIgnoreCase("level")) {
					String path = "maps/" + args[2] + ".bin";
					game.setLevel(path, false);
					Debug.pushToConsole("Setting level to " + path, false);
				}else {
					Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
				}
			}
			
			else if(args[0].equalsIgnoreCase("log")) {
				if(args[1].equalsIgnoreCase("dump")) {
					Debug.startLogging();
				}else {
					Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
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
				Debug.pushToConsole("For a list of all setable variables try \"set help\"", true);
				Debug.pushToConsole("\"set\" followed by background or other such variables.", true);
				Debug.pushToConsole("Here are a list of all valid commands", false);
				
			}else {
				Debug.pushToConsole(DebugType.Console_Unrecognized_Command.toString(), false);
			
			}
		}catch(IndexOutOfBoundsException e) {
			Debug.pushToConsole(DebugType.Console_Expecting_Args.toString(), false);
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
			game.render(screen);
		}
		Debug.render(screen.getSpriteBatch(), screen.getBounds().x, screen.getBounds().y);
		screen.finish();
	}
	
	@Override
	public void dispose () {
		game.dispose(); //do stuff in place of this: save, close assets, etc..
		loader.dispose();
		main.dispose();
		screen.dispose();
		if(Debug.logging) {
			FileManager.writeToFile(Debug.log_name, Debug.full_log, false, false);
		}
		Debug.dispose();
	}
}
