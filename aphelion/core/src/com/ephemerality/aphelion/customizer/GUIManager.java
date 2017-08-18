package com.ephemerality.aphelion.customizer;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.ui.elements.EphButton;
import com.ephemerality.aphelion.ui.elements.EphElement;
import com.ephemerality.aphelion.ui.elements.EphPanel;
import com.ephemerality.aphelion.ui.elements.TilePanel;

public class GUIManager {
	
	
	HashMap<String, EphElement> elements;
	EphElement beingDragged;
	boolean clicking;
	
	public GUIManager() {
		elements = new HashMap<>();
		setElements(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	public void setElements(int width, int height) {		
		Color utilBarDefault = new Color(0.212f, 0.192f, 0.216f, 1f);
		Color utilBarHighlight = new Color(0.114f, 0.102f, 0.118f, 1f);
		Color windowPaneDefault = new Color(0.3f, 0.28f, 0.3f, 1f);
		
		elements.put("File", new EphButton(0, height - 20f, 75f, 20f, "File", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		elements.put("Edit", new EphButton(75f, height - 20f, 75f, 20f, "Edit", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		elements.put("View", new EphButton(150f, height - 20f, 75f, 20f, "View", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		
		
		elements.put("Selection Pane", new TilePanel(0, 0f, 225f, height - 20f, "Selection Pane", windowPaneDefault, true) {
			@Override
			public void behavior() {
				super.behavior();
			}
		});
	}
	
	public boolean mouseMoved(int screenX, int screenY) {
		boolean activity = false;
		Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
		Iterator<EphElement> iter = elements.values().iterator();
		boolean clicked = Gdx.input.isButtonPressed(Buttons.LEFT);
		if(!clicked) clicking = false;
		while(iter.hasNext()) {
			EphElement el = iter.next();
			if(el instanceof EphButton) {
				if(el.checkActive(mousePos) && clicked && !clicking) {
					el.behavior();
					activity = true;
					clicking = true;
				}				
			}
			else if(el instanceof EphPanel) {
				if(el.checkActive(mousePos) ) {
					EphPanel ep = (EphPanel) el;
					if(ep.movable) {
						ep.behavior();
						if(ep.moving) {
							beingDragged = ep;
						}
					}
				}
			}
		}
		return activity;
	}
	public boolean dragged(int screenX, int screenY) {
		return false;
	}
	public boolean scrolled(int amount) {
		return false;
	}
	public void render(ScreenManager screen) {
		Iterator<EphElement> iter = elements.values().iterator();
		while(iter.hasNext()) {
			iter.next().render(screen);
		}
	}
	public void resize(int width, int height) {
		setElements(width, height);
	}
	
}
