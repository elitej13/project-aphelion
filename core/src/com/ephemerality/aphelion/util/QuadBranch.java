package com.project.duo.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.entities.Entity;
import com.project.duo.spawn.entities.mob.Mob;
import com.project.duo.spawn.entities.nob.Nob;
import com.project.duo.spawn.world.MapManager;

public class QuadBranch {

	private List<Nob> leaves;
	private List<Mob> bugs;
	private Rectangle bounds;
	private MapManager map;
	
	public QuadBranch(MapManager map, float x0, float y0, float width, float height) {
		this.map = map;
		leaves = new ArrayList<Nob>();
		bugs = new ArrayList<Mob>();
		bounds = new Rectangle(x0, y0, width, height);
	}
	
	public List<Mob> getOutOfBounds() {
		List<Mob> mobs = new ArrayList<Mob>();
		for(Mob m : bugs) {
			if(!(m.body.overlaps(bounds))) {
				mobs.add(m);
			}
		}
		for(Mob m : mobs) {
			bugs.remove(m);
		}
		return mobs;
	}
	
//	DEBUGGING PURPOSES
	public void printAll() {
		System.out.println(bugs);
	}
	
	public boolean isOnBranch(Entity e) {
		for(Entity entity : leaves) {
			if(e.equals(entity))
				return true;
		}
		return false;
	}
	
	public boolean overlaps(Rectangle body){
		return bounds.overlaps(body);
	}
	
	public boolean checkCollisions(Entity entity, Rectangle body) {
		for(Entity e : leaves) {
			if(e.equals(entity))
				continue;
			if(body.overlaps(e.body))
				return true;
		}
		for(Entity e : bugs) {
			if(e.equals(entity))
				continue;
			if(body.overlaps(e.body))
				return true;
		}
		for(Rectangle r : map.getSurroundingTiles(body.getPosition(new Vector2()))) {
			if(r == null)
				continue;
			if(body.overlaps(r)) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 
	 * @param e : Entity to be added to the branch
	 * @param collidableSet : which collidable is the set to be passed to 0-2
	 */
	public void addMob(Mob m, int collidableSet) {
		bugs.add(m);
		if(collidableSet == 0) {
			m.branch0 = this;
		}else if(collidableSet == 1) {
			m.branch1 = this;
		}else if(collidableSet == 2) {
			m.branch2 = this;
		}else if(collidableSet == 3) {
			m.branch3 = this;
		}
	}
	public void addNob(Nob n) {
		leaves.add(n);
	}
	
}
