package com.ephemerality.aphelion.editor.framework.ui;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisWindow;

public class TileScreen extends VisWindow{
	
	boolean up, down, left, right;
	Table tiles, envs;
	MapManager map;
	Stack stack;
	
	public TileScreen(MapManager map) {
		super("Viewport");
		this.map = map;
		
		stack = new Stack();
		tiles = new Table();
		envs = new Table();
		
		stack.setFillParent(true);
		
		setTiles();
		setEnvs();
		
		stack.addActor(tiles);
		stack.addActor(envs);
		addActor(stack);
		addActor(tiles);
		
		TableUtils.setSpacingDefaults(this);
		
		columnDefaults(0).left();
		setLayoutEnabled(true);
		setMovable(false);
	
	}
	
	
	
	public void setTiles() {
		tiles.clear();
		tiles.setSize(map.mapPixelSize.x, map.mapPixelSize.y);
		for(int y = 0; y < map.level.HEIGHT; y++) {
			for(int x = 0; x < map.level.WIDTH; x++) {
				System.out.println(map.level.tiles[x + (map.level.HEIGHT - y - 1) * map.level.WIDTH]);
				tiles.add(SpriteSheet.fetchImageFromEntityID(map.level.tiles[x + (map.level.HEIGHT - y - 1) * map.level.WIDTH]));				
			}
			tiles.row();
		}
		tiles.align(Align.bottomLeft);
	}
	public void setEnvs() {
		envs.clear();
		envs.setSize(map.mapPixelSize.x, map.mapPixelSize.y);
		Iterator<Vector2> iter = map.level.env.keySet().iterator();
		while(iter.hasNext()) {
			Vector2 cPos = iter.next();
			Short cID = map.level.env.get(cPos);
			tiles.addActorAt(0, SpriteSheet.fetchImageFromEntityID(cID));
		}
//		envs.addActorAt(SpriteSheet.fet, actor);
	}
	
	public void update() {
		int speed = 5;
		if(up) {
			tiles.moveBy(0, -speed);
		}
		if(down) {
			tiles.moveBy(0, speed);
		}
		if(left) {
			tiles.moveBy(speed, 0);
		}
		if(right) {
			tiles.moveBy(-speed, 0);
		}
		if(map.hasRecentlyReloaded()) {
			setTiles();
		}
	}



	public void editTile(float x, float y, short tileID) {
		x -= tiles.getX();
		y -= tiles.getY();
		
		int xx = (int) (x / MapManager.tileSize);
		int yy = (int) (y / MapManager.tileSize);
		
		
		for(Cell<?> cell : tiles.getCells()) {
			if(xx == cell.getActorX() / MapManager.tileSize && yy == cell.getActorY() / MapManager.tileSize) {
				cell.setActor(SpriteSheet.fetchImageFromEntityID(tileID));
			}
		}
		map.editTile(xx, yy, tileID);
	}
	public void resize(int width, int height) {
		float sw = Gdx.graphics.getWidth();
		float sh = Gdx.graphics.getHeight();
		setSize(sw * 0.88f, sh - 25f);
		setPosition(0, 0);
	}
}
