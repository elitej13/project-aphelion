package com.ephemerality.aphelion.ui.elements;

import java.util.HashMap;
import java.util.Iterator;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.badlogic.gdx.graphics.Texture;

public class EphPanel extends EphElement{

	
	HashMap<String, EphElement> children;
	public boolean movable, moving;
	float vTextInset, hTextInset;
	Texture image;

	
	
	public EphPanel(float x, float y, float w, float h, String title, Color color, boolean movable) {
		super(x, y, w, h, title);
		Pixmap pix = new Pixmap(1, 1, Format.RGBA8888);
		pix.setColor(color);
		pix.fillRectangle(0, 0, 1, 1);
		image = new Texture(pix);
		
		children = new HashMap<>();
		
		this.movable = movable;
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
	@Override
	public void render(ScreenManager screen) {
		screen.renderFixed(image, body);
		screen.renderFixedString(title, body.x + hTextInset, body.y + body.height - vTextInset);
	}
}