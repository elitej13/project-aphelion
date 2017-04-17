package com.ephemerality.aphelion.spawn.entities.mob;

import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.puppets.MobPuppet;
import com.ephemerality.aphelion.spawn.puppets.Puppet;
import com.ephemerality.aphelion.util.Direction;
import com.ephemerality.aphelion.util.QuadBranch;

public class Mob extends Entity{

	public QuadBranch branch0, branch1, branch2, branch3;
	protected boolean moving, movingChangedThisFrame;
	protected Puppet puppet;
	protected Direction dir;
	
	public Mob(ScreenManager screen, float x, float y, int w, int h, short ID) {
		super(x, y, w, h, true, ID);
		puppet = new MobPuppet(screen, w, h);
		puppet.setPosition(x, y);
	}
	
	@Override
	public void update() {
		puppet.update();
	}
	
	/**
	 * @return True if moving was successful.
	 */
	public boolean move() {
		Rectangle body = projectMove();
		boolean collided = false;
		if(branch0 != null) {
			collided = branch0.checkCollisions(this, body);
		}
		if(branch1 != null && collided == false) {
			collided = branch0.checkCollisions(this, body);
		}
		if(branch2 != null && collided == false) {
			collided = branch0.checkCollisions(this, body);
		}
		if(branch3 != null && collided == false) {
			collided = branch0.checkCollisions(this, body);
		}
		if(collided == false) {
			movePosition();
		}
		return !collided;
	}
	public void movePosition() {
		if(dir == Direction.NORTH) {
			body.setPosition(body.x, body.y + 1);
		}else if(dir == Direction.SOUTH) {
			body.setPosition(body.x, body.y - 1);
		}else if(dir == Direction.WEST) {
			body.setPosition(body.x - 1, body.y);
		}else if(dir == Direction.EAST){
			body.setPosition(body.x + 1, body.y);
		}else {
			System.out.println("Unhandeled direction, assuming EAST");
			body.setPosition(body.x + 1, body.y);
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
			System.out.println("Unhandeled direction, assuming EAST");
			projected = new Rectangle(body.getX() + 1, body.getY(), body.width, body.height);
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
		if(renderable) {
			if(puppet != null) {
				puppet.render(screen);
			}
		}
	}
}
