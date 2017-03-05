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
			
		}else if(dir == Direction.SOUTH) {
			
		}else if(dir == Direction.WEST) {
			
		}else if(dir == Direction.EAST) {
					
		}
	}
	
	
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		
	}
	
}