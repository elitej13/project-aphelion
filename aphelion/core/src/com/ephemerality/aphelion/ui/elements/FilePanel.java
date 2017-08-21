package com.ephemerality.aphelion.ui.elements;

import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.customizer.GameManager;
import com.ephemerality.aphelion.spawn.entities.nob.Tile;
import com.ephemerality.aphelion.spawn.world.Level;

public class FilePanel extends EphPanel{

	GameManager game;
	
	public FilePanel(float x, float y, float w, float h, String title, Color color, float rowHeight, GameManager game) {
		super(x, y, w, h, title, color, false, rowHeight);
		this.game = game;
		initChildren(x, y, w, h);
	}

	private void initChildren(float x, float y, float w, float h) {
		Color utilBarDefault = new Color(0.212f, 0.192f, 0.216f, 1f);
		Color utilBarHighlight = new Color(0.114f, 0.102f, 0.118f, 1f);
		float xc = x;
		float yc = y + h - rowHeight - rowHeight;
		int rows = 0;
		
		
		children.put("New", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "New", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				game.map.level = new Level(50, 50, Tile.GRASS_ID);
			}
		});
		children.put("Open", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "Open", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		children.put("Save As", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "Save As", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		children.put("Save", new EphButton(xc, yc - (rowHeight * rows++), w, rowHeight, "Save", utilBarDefault, utilBarHighlight) {
			@Override
			public void behavior() {
				
			}
		});
		totalScroll = 0;
	}
}
