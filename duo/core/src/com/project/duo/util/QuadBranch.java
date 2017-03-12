package com.project.duo.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.entities.Entity;
import com.project.duo.spawn.world.MapManager;

public class QuadBranch {

	private List<Entity> leaves;
	private Rectangle bounds;
	private MapManager map;
	
	public QuadBranch(MapManager map, float x0, float y0, float width, float height) {
		this.map = map;
		leaves = new ArrayList<Entity>();
		bounds = new Rectangle(x0, y0, width, height);
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
	public void addEntity(Entity e, int collidableSet) {
		leaves.add(e);
		if(collidableSet == 0) {
			e.branch0 = this;
		}else if(collidableSet == 1) {
			e.branch1 = this;
		}else if(collidableSet == 2) {
			e.branch2 = this;
		}else if(collidableSet == 3) {
			e.branch3 = this;
		}
	}
	
}
