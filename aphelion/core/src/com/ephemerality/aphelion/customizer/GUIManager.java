package com.ephemerality.aphelion.customizer;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.ui.elements.EditPanel;
import com.ephemerality.aphelion.ui.elements.EphButton;
import com.ephemerality.aphelion.ui.elements.EphElement;
import com.ephemerality.aphelion.ui.elements.EphPanel;
import com.ephemerality.aphelion.ui.elements.FilePanel;
import com.ephemerality.aphelion.ui.elements.InfoPanel;
import com.ephemerality.aphelion.ui.elements.TilePanel;
import com.ephemerality.aphelion.ui.elements.ViewPanel;

public class GUIManager {
	
	
	HashMap<String, EphElement> elements, disabled;
	public static short active;
	EphElement beingDragged;
	boolean clicking;
	ScreenManager screen;
	GameManager game;
	
	public GUIManager(ScreenManager screen, GameManager game) {
		this.game = game;
		this.screen = screen;
		elements = new HashMap<>();
		disabled = new HashMap<>();
		setElements(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	public void setElements(int width, int height) {		
		Color utilBarDefault = new Color(0.212f, 0.192f, 0.216f, 1f);
		Color utilBarHighlight = new Color(0.114f, 0.102f, 0.118f, 1f);
		Color windowPaneDefault = new Color(0.3f, 0.28f, 0.3f, 1f);
		
		elements.put("File", new EphButton(0, height - 20f, 75f, 20f, "File", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				if (elements.get("File Panel") != null) {
					disabled.put("File Panel", elements.get("File Panel"));
					elements.remove("File Panel");
				}else {
					elements.put("File Panel", disabled.get("File Panel"));
					disabled.remove("File Panel");

					if (elements.get("Edit Panel") != null) {
						disabled.put("Edit Panel", elements.get("Edit Panel"));
						elements.remove("Edit Panel");
					}
					if (elements.get("View Panel") != null) {
						disabled.put("View Panel", elements.get("View Panel"));
						elements.remove("View Panel");
					}
				}
			}
		});
		elements.put("Edit", new EphButton(75f, height - 20f, 75f, 20f, "Edit", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				if (elements.get("Edit Panel") != null) {
					disabled.put("Edit Panel", elements.get("Edit Panel"));
					elements.remove("Edit Panel");
				}else {
					if (elements.get("File Panel") != null) {
						disabled.put("File Panel", elements.get("File Panel"));
						elements.remove("File Panel");
					}
					
					elements.put("Edit Panel", disabled.get("Edit Panel"));
					disabled.remove("Edit Panel");
					
					if (elements.get("View Panel") != null) {
						disabled.put("View Panel", elements.get("View Panel"));
						elements.remove("View Panel");
					}
				}
			}
		});
		elements.put("View", new EphButton(150f, height - 20f, 75f, 20f, "View", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				if (elements.get("View Panel") != null) {
					disabled.put("View Panel", elements.get("View Panel"));
					elements.remove("View Panel");
				}else {
					if (elements.get("File Panel") != null) {
						disabled.put("File Panel", elements.get("File Panel"));
						elements.remove("File Panel");
					}
					
					if (elements.get("Edit Panel") != null) {
						disabled.put("Edit Panel", elements.get("Edit Panel"));
						elements.remove("Edit Panel");
					}
					
					elements.put("View Panel", disabled.get("View Panel"));
					disabled.remove("View Panel");
				}
			}
		});
		elements.put("Buffer Bar", new EphPanel(225f, height - 20f, width - 225f, 20f, "", utilBarDefault, false, 20f));
		elements.put("Info Panel", new InfoPanel(0f, height - 92f, width - 152f, 72f, "Info Panel", windowPaneDefault, 24f));
		
		disabled.put("File Panel", new FilePanel(0f, height - 145f, 150f, 125f, "File Panel", utilBarDefault, 24f, game));
		disabled.put("Edit Panel", new EditPanel(75f, height - 145f, 150f, 125f, "Edit Panel", utilBarDefault, 24f));
		disabled.put("View Panel", new ViewPanel(150f, height - 145f, 150f, 125f, "View Panel", utilBarDefault, 24f));
		
		elements.put("Selection Pane", new TilePanel(width - 152f, 0f, 152f, height - 20f, "Selection Pane", windowPaneDefault, true));
	}
	
	public synchronized boolean mouseMoved(int screenX, int screenY, boolean clicked) {
		boolean activity = false;
		Vector2 mousePos = new Vector2(screenX, Gdx.graphics.getHeight() - screenY);
		Iterator<EphElement> iter = elements.values().iterator();
		if(!clicked) clicking = false;
		while(iter.hasNext()) {
			EphElement el = iter.next();
			if(el instanceof EphButton) {
				if(el.checkActive(mousePos) && clicked && !clicking) {
					el.behavior();
					activity = true;
					clicking = true;
					return activity;
				}				
			}
			else if(el instanceof EphPanel) {
				if(el.checkActive(mousePos)) {
					EphPanel ep = (EphPanel) el;
					Iterator<EphElement> it = ep.children.values().iterator();
					while(it.hasNext()) {
						EphElement ee = it.next();
						if(ee.checkActive(mousePos) && clicked && !clicking) {
							ee.behavior();
							activity = true;
							clicking = true;
						}
					}
					ep.behavior();
//					if(ep.movable) {
//						if(ep.moving) {
//							beingDragged = ep;
//						}
//					}
				}
			}
		}
		return activity;
	}
	public boolean dragged(int screenX, int screenY) {
		return false;
	}
	
	public boolean scrolled(int amount, Vector2 vect) {
		Iterator<EphElement> iter = elements.values().iterator();
		while(iter.hasNext()) {
			EphElement el = iter.next();
			if(el instanceof EphPanel) {
				EphPanel ep = (EphPanel) el;
				if(ep.checkActive(vect)) {
					if(amount > 0) ep.scroll(ep.rowHeight);
					else ep.scroll(-ep.rowHeight);
					return true;
				}				
			}
		}
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
