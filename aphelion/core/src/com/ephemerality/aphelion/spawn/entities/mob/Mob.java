package com.ephemerality.aphelion.spawn.entities.mob;

import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.persona.Equip;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.puppets.MobPuppet;
import com.ephemerality.aphelion.spawn.puppets.Puppet;
import com.ephemerality.aphelion.spawn.world.QuadBranch;
import com.ephemerality.aphelion.spawn.world.Warp;
import com.ephemerality.aphelion.util.Direction;
import com.ephemerality.aphelion.util.debug.Debug;

public class Mob extends Entity{

	public static final short PLAYER = 30000;
	public static final short DUMMY = 30001;
	
	public QuadBranch branch0, branch1, branch2, branch3;
	public Equip equip;
	protected boolean moving, movingChangedThisFrame, attacking, attackStartedThisFrame;
	protected Puppet puppet;
	protected Direction dir;
	
	public Mob(float x, float y, int w, int h, short ID, LoadManager assets, String asset, Equip equip) {
		super(x, y, w, h, true, ID);
		puppet = new MobPuppet(x + 64, y + 64, w, h, assets, asset);
		this.equip = equip;
	}
	public Mob(float x, float y, float offsetX, float offsetY, int collisionW, int collisionH, short ID, LoadManager assets, String asset, Equip equip) {
		super(x, y, offsetX, offsetY, collisionW, collisionH, true, ID);
		puppet = new MobPuppet(x + 64, y + 64, collisionW, collisionH, assets, asset);
		this.equip = equip;
	}
	@Override
	public void update() {
		behavior();
		updateAnim();
		updateDeath();
	}
	public void updateDeath() {
		if(equip.isDead()) {
			isRemoved = true;
		}		
	}
	public void behavior() {
		moving = updateMove();
	}
	public void updateAnim() {
		MobPuppet mp = (MobPuppet) puppet;
		if(attackStartedThisFrame) {
			attackStartedThisFrame = false;
			attacking = true;
			mp.doll.setAnimation("attack");
		}
		if(attacking) {
			if(mp.hasAnimationFinished()) {
				attacking = false;
				if(moving) mp.doll.setAnimation("run");
				else mp.doll.setAnimation("idle");
			}
		}else {
			if(movingChangedThisFrame) {
				if(moving) {
					mp.doll.setAnimation("run");				
				}else {
					mp.doll.setAnimation("idle");
				}
			}
		}
		if(moving) mp.updateAnim(dir);
		mp.updatePosition(body.x + 64 - offsetX, body.y + 64 - offsetX);
		mp.update();
	}
	public boolean updateMove() {
		return false;
	}
	/**
	 * @return True if moving was successful.
	 */
	public boolean move() {
		Rectangle body = projectMove();
		boolean collided = false;
		if(branch0 != null) {
			if(branch0.checkMoveCollisions(this, body)) collided = true;
		}
		if(branch1 != null) {
			if(branch1.checkMoveCollisions(this, body)) collided = true;
		}
		if(branch2 != null) {
			if(branch2.checkMoveCollisions(this, body)) collided = true;
		}
		if(branch3 != null) {
			if(branch3.checkMoveCollisions(this, body)) collided = true;
		}
		if(collided == false) {
			movePosition();
		}
		return !collided;
	}
	public Warp warp() {
		Warp warp = null;
		if(branch0 != null)
			warp = branch0.checkWarpCollision(this);
		if(branch1 != null)
			warp = branch1.checkWarpCollision(this);
		if(branch2 != null)
			warp = branch2.checkWarpCollision(this);
		if(branch3 != null)
			warp = branch3.checkWarpCollision(this);
		return warp;
	}
	public boolean attack() {
		boolean hit = false;
		if(branch0 != null) {
			if(branch0.checkAttackCollision(this)) hit = true;
		}
		if(branch1 != null) {
			if(branch1.checkAttackCollision(this)) hit = true;
		}
		if(branch2 != null) {
			if(branch2.checkAttackCollision(this)) hit = true;
		}
		if(branch3 != null) {
			if(branch3.checkAttackCollision(this)) hit = true;
		}
		return hit;
	}
	public void movePosition() {
		float x = body.x;
		float y = body.y;
		if(dir == Direction.NORTH) {
			body.setPosition(x, y + 1);
		}else if(dir == Direction.SOUTH) {
			body.setPosition(x, y - 1);
		}else if(dir == Direction.WEST) {
			body.setPosition(x - 1, y);
		}else if(dir == Direction.EAST) {
			body.setPosition(x + 1, body.y);
		}else {
			System.out.println("Unhandeled direction");
		}
	}
	public Rectangle projectMove() {
		Rectangle projected = null;
		if(dir == Direction.NORTH) {
			projected = new Rectangle(body.getX(), body.getY() + 1, body.width, body.height);
		}else if(dir == Direction.SOUTH) {
			projected = new Rectangle(body.getX(), body.getY() - 1, body.width, body.height);
		}else if(dir == Direction.WEST) {
			projected = new Rectangle(body.getX() - 1, body.getY(), body.width, body.height);
		}else if(dir == Direction.EAST) {
			projected = new Rectangle(body.getX() + 1, body.getY(), body.width, body.height);
		}else {
			System.out.println("Unhandeled direction");
		}
//		System.out.println(this + body.toString());
		return projected;
	}
	
	
	public void clearBranches() {
		branch0 = null;
		branch1 = null;
		branch2 = null;
		branch3 = null;
	}
	
	
	@Override
	public void render(ScreenManager screen) {
		super.render(screen);
		if(renderable) {
			if(puppet != null) {
				puppet.render(screen);
			}
		}
	}
}
