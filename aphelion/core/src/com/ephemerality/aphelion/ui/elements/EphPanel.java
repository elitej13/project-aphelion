package com.ephemerality.aphelion.ui.elements;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.util.Util;
import com.badlogic.gdx.graphics.Texture;

public class EphPanel extends EphElement{

	
	public HashMap<String, EphElement> children;
	public boolean movable, moving;
	float vTextInset, hTextInset;
	float scrollOffset;
	float totalScroll;
	public float rowHeight;
	Texture image;
	
	
	public EphPanel(float x, float y, float w, float h, String title, Color color, boolean movable, float rowHeight) {
		super(x, y, w, h, title);
		Pixmap pix = new Pixmap(1, 1, Format.RGBA8888);
		pix.setColor(color);
		pix.fillRectangle(0, 0, 1, 1);
		image = new Texture(pix);
		
		children = new HashMap<>();
		
		this.movable = movable;
		this.rowHeight = rowHeight;
		vTextInset = 10f;
		hTextInset = 5f;
	}
	
	/**
	 * Be sure to call this behavior ex: super.behavior();
	 */
	@Override
	public void behavior() {
		Iterator<EphElement> iter = children.values().iterator();
		while(iter.hasNext()) {
			iter.next().behavior();
		}
	}
	/**
	 * 
	 * @param amount Positive to scroll up, negative to scroll down.
	 */
	public void scroll(float amount) {
		if(scrollOffset + amount >= 0 && scrollOffset + amount <= totalScroll) {
			Iterator<EphElement> iter = children.values().iterator();
			while(iter.hasNext()) {
				iter.next().body.y += amount;
			}
		}
		scrollOffset = Util.clamp(scrollOffset + amount, 0, totalScroll);
	}
	@Override
	public void render(ScreenManager screen) {
		screen.renderFixed(image, body);
		screen.renderFixedString(title, body.x + hTextInset, body.y + body.height - vTextInset);
		
		Iterator<EphElement> iter = children.values().iterator();
		while(iter.hasNext()) {
			EphElement el = iter.next();
			if(el.body.y < body.y + body.height && el.body.y + el.body.height > body.y) {
				el.render(screen);
			}
		}
	}
}