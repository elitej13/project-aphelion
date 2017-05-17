package com.ephemerality.aphelion.editor.framework.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow;

public class TileScreen extends VisWindow{
	
	boolean up, down, left, right;
	MapManager map;
	Table table;
	
	public TileScreen(MapManager map) {
		super("Viewport");
		this.map = map;
		table = new Table();
		setTiles();
		addActor(table);
		TableUtils.setSpacingDefaults(this);
		
		columnDefaults(0).left();
		setLayoutEnabled(true);
		setSize(800, 800);
		setPosition(0, 0);
	
	}
	
	
	
	public void setTiles() {

		table.clear();
		table.setSize(map.mapPixelSize.x, map.mapPixelSize.y);
		for(int y = 0; y < map.level.HEIGHT; y++) {
			for(int x = 0; x < map.level.WIDTH; x++) {
				table.add(SpriteSheet.fetchImageFromEntityID(map.level.tiles[x + (map.level.WIDTH - y - 1) * map.level.WIDTH]));				
			}
			table.row();
		}
		table.align(Align.bottomLeft);
	}
	
	public void update() {
		int speed = 5;
		if(up) {
			table.moveBy(0, speed);
		}
		if(down) {
			table.moveBy(0, -speed);
		}
		if(left) {
			table.moveBy(-speed, 0);
		}
		if(right) {
			table.moveBy(speed, 0);
		}
		if(map.hasRecentlyReloaded()) {
			setTiles();
		}
	}



	public void editTile(float x, float y, short tileID) {
		x -= table.getX();
		y -= table.getY();
		
		int xx = (int) (x / MapManager.tileSize);
		int yy = (int) (y / MapManager.tileSize);
		
		
		for(Cell<?> cell : table.getCells()) {
			if(xx == cell.getActorX() / MapManager.tileSize && yy == cell.getActorY() / MapManager.tileSize) {
				cell.setActor(SpriteSheet.fetchImageFromEntityID(tileID));
			}
		}
		map.editTile(xx, yy, tileID);
	}

}
