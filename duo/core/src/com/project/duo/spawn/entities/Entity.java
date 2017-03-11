package com.project.duo.spawn.entities;

import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.Puppet;
import com.project.duo.util.Direction;

public class Entity {
	
	public List<Entity> collidables0, collidables1, collidables2, collidables3;
	public Rectangle body;
	protected Puppet puppet;
	
	
	public Entity(float x, float y, float w, float h) {
		body = new Rectangle(x, y, w, h);
		puppet = new Puppet();
		
	}
	
	public void update() {
		puppet.update();
	}
	
	public void move(Direction dir) {
		if(dir == Direction.NORTH) {
			body.setPosition(body.x, body.y + 1);
		}else if(dir == Direction.SOUTH) {
			body.setPosition(body.x, body.y - 1);
		}else if(dir == Direction.WEST) {
			body.setPosition(body.x - 1, body.y);
		}else if(dir == Direction.EAST) {
			body.setPosition(body.x + 1, body.y);
		}
	}
	public void clearCollidables() {
		collidables0 = null;
		collidables1 = null;
		collidables2 = null;
		collidables3 = null;
	}
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		puppet.render(sb, c0, body.getPosition(new Vector2()));
	}
	
}