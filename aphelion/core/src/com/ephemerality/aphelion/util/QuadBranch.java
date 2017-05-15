package com.ephemerality.aphelion.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.entities.nob.Nob;
import com.ephemerality.aphelion.spawn.entities.nob.items.Item;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.spawn.world.MapManager;

public class QuadBranch {

	public Set<Entity> removed;
	public boolean dirty;

	private Set<Nob> leaves;
	private Set<Mob> bugs;
	private Rectangle bounds;
	private MapManager map;
	
	public QuadBranch(MapManager map, float x0, float y0, float width, float height) {
		this.map = map;
		leaves = new HashSet<>();
		bugs = new HashSet<>();
		bounds = new Rectangle(x0, y0, width, height);
		removed = new HashSet<>();
		}
	
	public List<Mob> getOutOfBounds() {
		List<Mob> mobs = new ArrayList<>();
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
		Entity grabbed = null;
		for(Entity e : leaves) {
			if(e instanceof Item && entity instanceof Player) {
				if(body.overlaps(e.body)) {
					grabbed = e;
					Player player = (Player) entity;
					player.inventory.queue.add(grabbed);
					break;
				}
			}
		}
		if(grabbed != null) {
			leaves.remove(grabbed);
			if(removed.isEmpty()) dirty = true;
			removed.add(grabbed);
			return false;
		}
		for(Entity e : bugs) {
			if(e.equals(entity))
				continue;
			if(body.overlaps(e.body))
				return true;
		}
		Vector2 position = body.getPosition(new Vector2());
		for(int y = (int) body.height; y > 0; y -= MapManager.tileSize) {
			position.x = body.x;
			for(int x = (int) body.width; x > 0; x -= MapManager.tileSize) {
				for(Rectangle r : map.getSurroundingTiles(position)) {
					if(r == null)
						continue;
					if(body.overlaps(r)) {
						return true;
					}
				}
				position.add(MapManager.tileSize, 0);
			}
			position.add(0, MapManager.tileSize);
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
	
	
	public Set<Mob> getAndRemoveAllBugs() {
		Set<Mob> mobs = new HashSet<>();
		mobs.addAll(bugs);
		bugs.removeAll(bugs);
		return mobs;
	}
	public void removeMob(Mob m) {
		bugs.remove(m);
	}
	public void addNob(Nob n) {
		leaves.add(n);
	}

	public void render(ScreenManager screen) {
		screen.render(SpriteSheet.rectangle, bounds);
	}
}
