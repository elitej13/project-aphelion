package com.ephemerality.aphelion.spawn.world;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.entities.nob.Nob;

public class QuadTree {
	
	private QuadBranch NE, NW, SW, SE;
	private Rectangle N, W, S, E;
//	private float timer;
	
	public QuadTree(MapManager map) {
		float width = map.mapPixelSize.x;
		float height = map.mapPixelSize.y;
		float bW = width / 2;
		float bH = height / 2;
		int offset = MapManager.tileSize * 4;
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
//		if(timer < 0.5f) {
//			timer += Gdx.graphics.getDeltaTime();
//		}else {
//			timer = 0;
		sortEntities();
	}
	
	private void sortEntities() {
		Set<Mob> mobs = new HashSet<>();
		mobs.addAll(SW.getAndRemoveAllBugs());
		mobs.addAll(NW.getAndRemoveAllBugs());
		mobs.addAll(SE.getAndRemoveAllBugs());
		mobs.addAll(NE.getAndRemoveAllBugs());
		
		Iterator<Mob> iter = mobs.iterator();
		
		while(iter.hasNext()) {
			Mob m = iter.next();
			addMob(m);
		}		
	}

	public void addEntity(Entity e) {
		if(e instanceof Mob) {
			Mob m = (Mob) e;
			addMob(m);
			return;
		}else if(e instanceof Nob) {
			Nob n = (Nob) e;
			addNob(n);
			return;
		}
		System.out.println("Failed to add entity into QUADTREE.");
	}
	
	public void addMob(Mob m) {
		boolean north = false, south = false, east = false, west = false;
		m.clearBranches();
		if(N.overlaps(m.body)) {
			NE.addMob(m, 0);
			NW.addMob(m, 1);
			north = true;
		}
		if(S.overlaps(m.body)) {
			if(north) {
				SE.addMob(m, 2);
				SW.addMob(m, 3);
				return;
			}else {
				SE.addMob(m, 0);
				SW.addMob(m, 1);
			}
			south = true;
		}
		if(E.overlaps(m.body)) {
			east = true;
			if(north) {
				SE.addMob(m, 2);
			}else if(south) {
				NE.addMob(m, 2);
			}else {
				NE.addMob(m, 0);
				SE.addMob(m, 1);
			}
		}
		if(W.overlaps(m.body)) {
			west = true;
			if(north && !east) {
				SW.addMob(m, 2);
			}else if(south && !east) {
				NW.addMob(m, 2);
			}else if(north && east) {
				SW.addMob(m, 3);
			}else if(south && east) {
				NW.addMob(m, 3);
			}else if(east) {
				NW.addMob(m, 2);
				SW.addMob(m, 3);
			}else {
				NW.addMob(m, 0);
				SW.addMob(m, 1);
			}
		}
		if(!(north || south || east || west)) {
			if(NE.overlaps(m.body)) {
				NE.addMob(m, 0);
			}else if(SE.overlaps(m.body)) {
				SE.addMob(m, 0);
			}else if(SW.overlaps(m.body)) {
				SW.addMob(m, 0);
			}else if(NW.overlaps(m.body)) {
				NW.addMob(m, 0);
			}
		}
	}
	public void addNob(Nob n) {
		if(NE.overlaps(n.body)) {
			NE.addNob(n);
		}else if(SE.overlaps(n.body)) {
			SE.addNob(n);
		}else if(SW.overlaps(n.body)) {
			SW.addNob(n);
		}else if(NE.overlaps(n.body)) {
			NE.addNob(n);
		}
	}
	public void render(ScreenManager screen) {
		screen.renderRectangle(N);
		screen.renderRectangle(S);
		screen.renderRectangle(E);
		screen.renderRectangle(W);
		NE.render(screen);
		NW.render(screen);
		SE.render(screen);
		SW.render(screen);
	}
	
}
