package com.ephemerality.aphelion.ui.elements;

import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.graphics.SpriteSheet;

public class TilePanel extends EphPanel{

	public TilePanel(float x, float y, float w, float h, String title, Color color, boolean movable) {
		super(x, y, w, h, title, color, movable, 68f);
		initChildren(x, y, w, h);
	}

	private void initChildren(float x, float y, float w, float h) {
		Color utilBarDefault = new Color(0.212f, 0.192f, 0.216f, 1f);
		Color utilBarHighlight = new Color(0.114f, 0.102f, 0.118f, 1f);
		Color windowPaneDefault = new Color(0.3f, 0.28f, 0.3f, 1f);
		float xc = x + 8f;
		float yc = y + h - 64f;
		int rows = 0;
		
		
		//Tiles
		children.put("tiles", new EphButton(xc, yc - (rowHeight * rows++), 136f, 32f, "Tiles", utilBarDefault, utilBarHighlight));
		children.put("default_grass_0", new EphButton(xc, yc - (rowHeight * rows), 64f, 64f, "Grass 0", SpriteSheet.default_grass_0));
		children.put("default_dirt_0", new EphButton(xc + 72f, yc - (rowHeight * rows++), 64f, 64f, "Dirt 0", SpriteSheet.default_dirt_0));
		children.put("default_brick_0", new EphButton(xc, yc - (rowHeight * rows), 64f, 64f, "Brick 0", SpriteSheet.default_brick_0));
		children.put("default_wood_0", new EphButton(xc + 72f, yc - (rowHeight * rows++), 64f, 64f, "Wood 0", SpriteSheet.default_wood_0));
		children.put("default_sand_0", new EphButton(xc, yc - (rowHeight * rows), 64f, 64f, "Sand 0", SpriteSheet.default_sand_0));
		children.put("default_water_0", new EphButton(xc + 72f, yc - (rowHeight * rows++), 64f, 64f, "Water 0", SpriteSheet.default_water_0));
		children.put("default_gravel_0", new EphButton(xc, yc - (rowHeight * rows), 64f, 64f, "Gravel 0", SpriteSheet.default_gravel_0));
		rows++;
		
		//Envs
		children.put("envs", new EphButton(xc, yc - (rowHeight * rows++), 136f, 32f, "Envs", utilBarDefault, utilBarHighlight));
		children.put("default_house", new EphButton(xc, yc - (rowHeight * rows), 64f, 64f, "House", SpriteSheet.default_house));
		children.put("default_fence", new EphButton(xc + 72f, yc - (rowHeight * rows++), 64f, 64f, "Fence", SpriteSheet.default_fence));
		children.put("default_gate", new EphButton(xc, yc - (rowHeight * rows), 64f, 64f, "Gate", SpriteSheet.default_gate));
		children.put("default_tree", new EphButton(xc + 72f, yc - (rowHeight * rows++), 64f, 64f, "Tree", SpriteSheet.default_tree));
		children.put("default_bridge", new EphButton(xc, yc - (rowHeight * rows), 64f, 64f, "Bridge", SpriteSheet.default_bridge));
		
		
		totalScroll = rows * rowHeight;
		totalScroll -= (Math.floor((h - y - 64f) / rowHeight) * rowHeight);
	}
	
}

