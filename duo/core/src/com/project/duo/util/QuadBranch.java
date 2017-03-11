package com.project.duo.util;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Rectangle;
import com.project.duo.spawn.entities.Entity;

public class QuadBranch {

	private List<Entity> leaves;
	private Rectangle bounds;
	
	public QuadBranch(float x0, float y0, float width, float height) {
		leaves = new ArrayList<Entity>();
		bounds = new Rectangle(x0, y0, width, height);
	}
	
	public boolean overlaps(Rectangle body){
		return bounds.overlaps(body);
	}
	
	public boolean checkCollisions(Entity entity) {
		for(Entity e : leaves) {
			if(entity.body.overlaps(e.body))
				return true;
		}
		return false;
	}
	/**
	 * 
	 * @param e : Entity to be added to the branch
	 * @param collidableSet : which colidable is the set to be passed to 0-2
	 */
	public void addEntity(Entity e, int collidableSet) {
		leaves.add(e);
		if(collidableSet == 0) {
			e.collidables0 = leaves;
		}else if(collidableSet == 1) {
			e.collidables1 = leaves;
		}else if(collidableSet == 2) {
			e.collidables2 = leaves;
		}else if(collidableSet == 3) {
			e.collidables3 = leaves;
		}
	}
	
}
