package com.project.duo.spawn.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.project.duo.spawn.Puppet;
import com.project.duo.util.Direction;

public class Entity {
	
	public Vector2 position;
	protected Puppet puppet;
	
	public Entity(float x, float y) {
		position = new Vector2(x,y);
	}
	
	public void update() {
		puppet.update();
	}
	
	public void move(Direction dir) {
		if(dir == Direction.NORTH) {
			position.add(0, 1);
		}else if(dir == Direction.SOUTH) {
			position.add(0, -1);
		}else if(dir == Direction.WEST) {
			position.add(-1, 0);
		}else if(dir == Direction.EAST) {
			position.add(1, 0);
		}
	}
	
	
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		puppet.render(sb, c0, position);
	}
	
}