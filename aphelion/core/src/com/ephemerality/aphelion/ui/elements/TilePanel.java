package com.ephemerality.aphelion.ui.elements;

import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.graphics.SpriteSheet;

public class TilePanel extends EphPanel{

	public TilePanel(float x, float y, float w, float h, String title, Color color, boolean movable) {
		super(x, y, w, h, title, color, movable);
		initChildren();
	}

	private void initChildren() {
		children.put("Wood", new EphButton(0, 0, SpriteSheet.default_wood_0.getRegionWidth(), SpriteSheet.default_wood_0.getRegionHeight(), "Wood", SpriteSheet.default_wood_0));
	}
	
}
