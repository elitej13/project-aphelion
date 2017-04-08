package com.ephemerality.aphelion.spawn.entities.tiles;

import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.world.MapManager;

public class GrassTile extends Tile{

	public GrassTile(float x, float y) {
		super(x, y, MapManager.tileSize, MapManager.tileSize, SpriteSheet.default_grass_0);
	}
	
	
	
}
