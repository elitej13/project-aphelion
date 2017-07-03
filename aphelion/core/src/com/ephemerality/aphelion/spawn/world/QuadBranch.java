package com.ephemerality.aphelion.spawn.world;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.entities.nob.Item;
import com.ephemerality.aphelion.spawn.entities.nob.Nob;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.spawn.equipment.weapon.Weapon;
import com.ephemerality.aphelion.util.debug.Debug;

public class QuadBranch {

	private Set<Nob> leaves;
	private Set<Mob> bugs;
	private Rectangle bounds;
	private MapManager map;
	
	public QuadBranch(MapManager map, float x0, float y0, float width, float height) {
		this.map = map;
		leaves = new HashSet<>();
		bugs = new HashSet<>();
		bounds = new Rectangle(x0, y0, width, height);
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
	public HashSet<Mob> getNearbyMobs (Mob mob, float range) {
		HashSet<Mob> nearby = new HashSet<>();
		for(Mob m : bugs) {
			if(m.equals(mob))
				continue;
			if(getDistance(mob.body, m.body) <= range)
				nearby.add(m);
		}
		return nearby;
	}
	public double getDistance(Rectangle a, Rectangle b) {
		double xx = a.x - b.x;
		double yy = a.y - b.y;
		xx *= xx;
		yy *= yy;
		return Math.sqrt(xx + yy);
	}
	public Warp checkWarpCollision(Mob mob) {
		return map.getWarp(mob.body);
	}
	public boolean checkAttackCollision(Mob mob) {
		for(Mob m : bugs) {
			if(m.equals(mob))
				continue;
			if(mob.body.overlaps(m.body)) {
				float damage = 0;
				Weapon.ATTACK_TYPE type = m.equip.getAttackType();
				if(type.equals(Weapon.ATTACK_TYPE.MELEE_MIXED) || type.equals(Weapon.ATTACK_TYPE.RANGED_MIXED)) {
					damage += Math.max(mob.equip.getMagicalDamage() - m.equip.getMagicalResistance(), 0);
					damage += Math.max(mob.equip.getPhysicalDamage() - m.equip.getPhysicalResistance(), 0);
				}else if(type.equals(Weapon.ATTACK_TYPE.MELEE_MAGIC) || type.equals(Weapon.ATTACK_TYPE.RANGED_MAGIC)) {
					damage += Math.max(mob.equip.getMagicalDamage() - m.equip.getMagicalResistance(), 0);
				}else if(type.equals(Weapon.ATTACK_TYPE.MELEE_PHYSICAL) || type.equals(Weapon.ATTACK_TYPE.RANGED_PHYSICAL)) {
					damage += Math.max(mob.equip.getPhysicalDamage() - m.equip.getPhysicalResistance(), 0);
				}
				if(m.gracePeriod <= 0) {
					m.equip.modHealth(-damage);
					m.gracePeriod = mob.equip.getStun();
					System.out.println("Stunned for: " + mob.equip.getStun());
					System.out.println("Damage: " + damage);
					System.out.println("New Health: " + m.equip.getFormattedDamage());
					Debug.pushToConsole(mob.getID() + " attacked " + m.getID() + ", doing " + damage + " damage", false);
				}
				return true;
			}
		}			
		return false;
	}
	public boolean checkMoveCollisions(Entity entity, Rectangle body) {
		Entity grabbed = null;
		for(Entity e : bugs) {
			if(e.equals(entity))
				continue;
			if(body.overlaps(e.body)) {
//				Debug.pushToConsole("Collision between " + entity.getID() + ", and " + e.getID(), false);
				//TODO: Test if not having collision boxes for movement works okay.
				return false;
			}
		}
		for(Entity e : leaves) {
			if(e instanceof Item && entity instanceof Player) {
				if(body.overlaps(e.body)) {
					Player player = (Player) entity;
					grabbed = e;
					player.inventory.queue.add(grabbed);
					break;
				}
			}
		}
		if(grabbed != null) {
			leaves.remove(grabbed);
			grabbed.isRemoved = true;
			return false;
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
		screen.renderRectangle(bounds);
	}
}
