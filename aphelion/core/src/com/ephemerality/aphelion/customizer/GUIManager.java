package com.ephemerality.aphelion.customizer;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.ui.elements.Button;
import com.ephemerality.aphelion.ui.elements.Element;

public class GUIManager {
	
	
	HashMap<String, Element> elements;
	boolean clicking;
	
	public GUIManager() {
		elements = new HashMap<>();
		setElements(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	public void setElements(int width, int height) {
		Color utilBarDefault = new Color(0.212f, 0.192f, 0.216f, 1f);
		Color utilBarHighlight = new Color(0.114f, 0.102f, 0.118f, 1f);
		
		elements.put("File", new Button(0, height - 20f, 75f, 20f, "File", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		elements.put("Edit", new Button(75f, height - 20f, 75f, 20f, "Edit", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		elements.put("View", new Button(150f, height - 20f, 75f, 20f, "View", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
	}
	
	public void mouseMoved(int screenX, int screenY) {		
		Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
		Iterator<Element> iter = elements.values().iterator();
		boolean clicked = Gdx.input.isButtonPressed(Buttons.LEFT);
		if(!clicked) clicking = false;
		while(iter.hasNext()) {
			Element el = iter.next();
			if(el.checkActive(mousePos) && clicked && !clicking) {
				el.behavior();
				clicking = true;
			}
		}
	}
	public boolean scrolled(int amount) {
		return false;
	}
	public void render(ScreenManager screen) {
		Iterator<Element> iter = elements.values().iterator();
		while(iter.hasNext()) {
			iter.next().render(screen);
		}
	}
	public void resize(int width, int height) {
		setElements(width, height);
	}
	
}
