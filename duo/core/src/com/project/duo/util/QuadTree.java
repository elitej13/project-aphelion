package com.project.duo.util;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.entities.Entity;
import com.project.duo.spawn.world.MapManager;

public class QuadTree {
	
	private QuadBranch NE, NW, SW, SE;
	private Rectangle N, W, S, E;
	
	public QuadTree(Vector2 mapPixelSize) {
		float width = mapPixelSize.x;
		float height = mapPixelSize.y;
		float bW = width / 2;
		float bH = height / 2;
		int offset = MapManager.tileSize * 2;
		SW = new QuadBranch(0, 0, bW, bH);
		NW = new QuadBranch(0, bH, bW, bH);
		SE = new QuadBranch(bW, 0, bW, bH);
		NE = new QuadBranch(bW, bH, bW, bH);
		W = new Rectangle(0, bH - offset, bW, offset * 2);
		S = new Rectangle(bW - offset, 0, offset * 2, bH);
		N = new Rectangle(bW - offset, bH, offset * 2, bH);
		E = new Rectangle(bW, bH - offset, bW, offset * 2);
	}
	
	public void addEntity(Entity e) {
		boolean north = false, south = false, east = false, west = false;
		e.clearCollidables();
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
