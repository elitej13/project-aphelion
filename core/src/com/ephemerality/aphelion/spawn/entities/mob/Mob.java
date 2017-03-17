package com.project.duo.spawn.entities.mob;

import com.badlogic.gdx.math.Rectangle;
import com.project.duo.spawn.entities.Entity;
import com.project.duo.spawn.puppets.MobPuppet;
import com.project.duo.util.Direction;
import com.project.duo.util.QuadBranch;

public class Mob extends Entity{

	public QuadBranch branch0, branch1, branch2, branch3;
	
	public Mob(float x, float y, int w, int h) {
		super(x, y, w, h, true);
		puppet = new MobPuppet(w, h);
	}
	public void move(Direction dir) {
		Rectangle body = projectMove(dir);
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
			movePosition(dir);
		}
	}
	public void movePosition(Direction dir) {
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
	public Rectangle projectMove(Direction dir) {
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
		return projected;
	}
	
	
	public void clearBranches() {
		branch0 = null;
		branch1 = null;
		branch2 = null;
		branch3 = null;
	}
}
