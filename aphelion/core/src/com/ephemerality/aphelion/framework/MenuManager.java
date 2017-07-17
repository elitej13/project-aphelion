package com.ephemerality.aphelion.framework;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.util.State;
import com.ephemerality.aphelion.util.debug.Debug;

public class MenuManager {

	private BitmapFont one, two, three, four, five;
	private Color regular, hover;
	private float topinset, leftinset;
	private int selection = -1;
	private int FONT_SIZE = 60;
	
	//0 for main, 1 for start game, 2 for load game, 3 for options
	private menu_states state = menu_states.main;
	private enum menu_states {main, start, load, options};
	private int max_selection;
	
	public MenuManager() {
		regular = new Color(0.9f, 0.9f, 0.8f, 1.0f);
		hover = new Color(0.5f, 0.5f, 0.4f, 1.0f);
		
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/captain-falcon_mecha/Mecha_Condensed_Bold.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = FONT_SIZE;
		parameter.color = regular;
		one = generator.generateFont(parameter);
		two = generator.generateFont(parameter);
		three = generator.generateFont(parameter);
		four = generator.generateFont(parameter);
		five = generator.generateFont(parameter);
		generator.dispose();

		one.setColor(regular);
		two.setColor(regular);
		three.setColor(regular);
		four.setColor(regular);
		five.setColor(regular);
		
		topinset = (Gdx.graphics.getHeight() * 0.8f);
		leftinset = (Gdx.graphics.getWidth() * 0.45f);
		
		max_selection = 2;
	}
	
	
	public void update() {
		boolean up = Gdx.input.isKeyJustPressed(Input.Keys.UP) | Gdx.input.isKeyJustPressed(Input.Keys.W) | Gdx.input.isKeyJustPressed(Input.Keys.DPAD_UP);
		boolean down = Gdx.input.isKeyJustPressed(Input.Keys.DOWN) | Gdx.input.isKeyJustPressed(Input.Keys.S) | Gdx.input.isKeyJustPressed(Input.Keys.DPAD_DOWN);
		boolean enter = Gdx.input.isKeyJustPressed(Input.Keys.ENTER);
		if(!(up && down)) {
			if(down && selection < max_selection) {
				selection++;
				setFontColors();
			}else if(up && selection > 0) {
				selection--;
				setFontColors();
			}			
		}
		if(enter && !Debug.infoIsActive) {
			if(state == menu_states.main) {
				if(selection == 0) {
					//Start
					setState(menu_states.start);
				}else if(selection == 1) {
					//Options
					setState(menu_states.options);
				}else if(selection == 2) {
					//Quit
					Master.setState(State.EXITING);
				}				
			}
			else if(state == menu_states.start) {	
				if(selection == 0) {
					//New
					startGame();
				}else if(selection == 1) {
					//Load
					setState(menu_states.load);
				}else if(selection == 2) {
					//Back
					setState(menu_states.main);
				}				
			}
			else if(state == menu_states.load) {	
				if(selection == 0) {
					startGame();
				}else if(selection == 1) {
					startGame();
				}else if(selection == 2) {
					startGame();
				}				
			}
			else if(state == menu_states.options) {	
				if(selection == 0) {
					//Controls
				}else if(selection == 1) {
					//Graphics
				}else if(selection == 2) {
					//Sound
				}else if(selection == 3) {
					//Back
					setState(menu_states.main);
				}							
			}
		}
	}
	public void startGame() {
		Master.setState(State.GAME);
		state = menu_states.main;
		selection = -1;
	}
	public void setState(menu_states state) {
		if(state == menu_states.main || state == menu_states.start) max_selection = 2;
		else if(state == menu_states.options || state == menu_states.load) max_selection = 3;
		this.state = state;
		selection = -1;
		setFontColors();		
	}
	
	public void setFontColors() {
		one.setColor(regular);
		two.setColor(regular);
		three.setColor(regular);
		four.setColor(regular);
		five.setColor(regular);
		
		if(selection == 0) one.setColor(hover);
		else if(selection == 1) two.setColor(hover);
		else if(selection == 2) three.setColor(hover);
		else if(selection == 3) four.setColor(hover);
		else if(selection == 4) five.setColor(hover);
		
	}
	
	public void render(ScreenManager screen, LoadManager assets) {
//		screen.render(assets.getTexture(assets.MENU_FRAME), 100, 100, 50f, 50f);
		
		//Main
		if(state == menu_states.main) {
			screen.renderFixedString(one, "Play", leftinset, topinset);
			screen.renderFixedString(two, "Options", leftinset, topinset - 100f);
			screen.renderFixedString(three, "Quit", leftinset, topinset - 200f);		
		}
		//Start
		else if(state == menu_states.start) {
			screen.renderFixedString(one, "New Game", leftinset, topinset);
			screen.renderFixedString(two, "Load Game", leftinset, topinset - 100f);
			screen.renderFixedString(three, "Back", leftinset, topinset - 200f);	
		}
		//Load
		else if(state == menu_states.load) {	
			screen.renderFixedString(one, "One", leftinset, topinset);
			screen.renderFixedString(two, "Two", leftinset, topinset - 100f);
			screen.renderFixedString(three, "Three", leftinset, topinset - 200f);
			screen.renderFixedString(four, "Back", leftinset, topinset - 300f);
		}
		//Options
		else if(state == menu_states.options) {	
			screen.renderFixedString(one, "Controls", leftinset, topinset);
			screen.renderFixedString(two, "Graphics", leftinset, topinset - 100f);
			screen.renderFixedString(three, "Sound", leftinset, topinset - 200f);
			screen.renderFixedString(four, "Back", leftinset, topinset - 300f);	
		}
	}
	
	public void dispose() {
		one.dispose();
		two.dispose();
		three.dispose();
		four.dispose();
	}
	
}
