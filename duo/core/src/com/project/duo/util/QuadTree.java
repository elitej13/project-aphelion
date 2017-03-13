package com.project.duo.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.entities.Entity;
import com.project.duo.spawn.world.MapManager;

public class QuadTree {
	
	private QuadBranch NE, NW, SW, SE;
	private Rectangle N, W, S, E;
	private float timer;
	
	public QuadTree(MapManager map) {
		float width = map.mapPixelSize.x;
		float height = map.mapPixelSize.y;
		float bW = width / 2;
		float bH = height / 2;
		int offset = MapManager.tileSize * 2;
		SW = new QuadBranch(map, 0, 0, bW, bH);
		NW = new QuadBranch(map, 0, bH, bW, bH);
		SE = new QuadBranch(map, bW, 0, bW, bH);
		NE = new QuadBranch(map, bW, bH, bW, bH);
		W = new Rectangle(0, bH - offset, bW, offset * 2);
		S = new Rectangle(bW - offset, 0, offset * 2, bH);
		N = new Rectangle(bW - offset, bH, offset * 2, bH);
		E = new Rectangle(bW, bH - offset, bW, offset * 2);
	}
//	TODO : fine tune this time of the offset
	public void update() {
		if(timer < 1f) {
			timer += Gdx.graphics.getDeltaTime();
		}else {
			timer = 0;
			sortEntities();
			SW.printAll();
			NW.printAll();
			SE.printAll();
			NE.printAll();
		}
	}
	
	private void sortEntities() {
		List<Entity> entities = new ArrayList<Entity>();
		List<Entity> safe = new ArrayList<Entity>();
		entities.addAll(SW.getOutOfBounds());
		entities.addAll(NW.getOutOfBounds());
		entities.addAll(SE.getOutOfBounds());
		entities.addAll(NE.getOutOfBounds());
		for(Entity e : entities) {
			if(SW.isOnBranch(e)) {
				safe.add(e);
			}else if(NW.isOnBranch(e)) {
				safe.add(e);
			}else if(SE.isOnBranch(e)) {
				safe.add(e);
			}else if(NE.isOnBranch(e)) {
				safe.add(e);
			}
		}
		for(Entity e : safe) {
			while(entities.remove(e));
		}
		for(Entity e : entities) {
			addEntity(e);
		}
	}

	public void addEntity(Entity e) {
		boolean north = false, south = false, east = false, west = false;
		e.clearBranches();
		if(N.overlaps(e.body)) {
			NE.addEntity(e, 0);
			NW.addEntity(e, 1);
			north = true;
		}
		if(S.overlaps(e.body)) {
			if(north) {
				SE.addEntity(e, 2);
				SW.addEntity(e, 3);
				return;
			}else {
				SE.addEntity(e, 0);
				SW.addEntity(e, 1);
			}
			south = true;
		}
		if(E.overlaps(e.body)) {
			east = true;
			if(north) {
				SE.addEntity(e, 2);
			}else if(south) {
				NE.addEntity(e, 2);
			}else {
				NE.addEntity(e, 0);
				SE.addEntity(e, 1);
			}
		}
		if(W.overlaps(e.body)) {
			west = true;
			if(north && !east) {
				SW.addEntity(e, 2);
			}else if(south && !east) {
				NW.addEntity(e, 2);
			}else if(north && east) {
				SW.addEntity(e, 3);
			}else if(south && east) {
				NW.addEntity(e, 3);
			}else if(east) {
				NW.addEntity(e, 2);
				SW.addEntity(e, 3);
			}else {
				NW.addEntity(e, 0);
				SW.addEntity(e, 1);
			}
		}
		if(!(north || south || east || west)) {
			if(NE.overlaps(e.body)) {
				NE.addEntity(e, 0);
			}else if(SE.overlaps(e.body)) {
				SE.addEntity(e, 0);
			}else if(SW.overlaps(e.body)) {
				SW.addEntity(e, 0);
			}else if(NE.overlaps(e.body)) {
				NE.addEntity(e, 0);
			}
		}
	}
	
}
