package com.ephemerality.aphelion.spawn.entities.mob;

import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.puppets.MobPuppet;
import com.ephemerality.aphelion.spawn.puppets.Puppet;
import com.ephemerality.aphelion.util.Direction;
import com.ephemerality.aphelion.util.QuadBranch;

public class Mob extends Entity{

	public QuadBranch branch0, branch1, branch2, branch3;
	protected boolean moving, movingChangedThisFrame, attacking, attackStartedThisFrame;
	protected Puppet puppet;
	protected Direction dir;
	
	public Mob(float x, float y, int w, int h, short ID, LoadManager assets) {
		super(x, y, w, h, true, ID);
		puppet = new MobPuppet(body.x + 64, body.y + 64, body.width, body.height, assets);
	}
	@Override
	public void update() {
		behavior();
		updateAnim();
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
		mp.updatePosition(body.x + 64, body.y + 64);
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
			if(branch0.checkCollisions(this, body)) collided = true;
		}
		if(branch1 != null) {
			if(branch1.checkCollisions(this, body)) collided = true;
		}
		if(branch2 != null) {
			if(branch2.checkCollisions(this, body)) collided = true;
		}
		if(branch3 != null) {
			if(branch3.checkCollisions(this, body)) collided = true;
		}
		if(collided == false) {
			movePosition();
		}
		return !collided;
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
