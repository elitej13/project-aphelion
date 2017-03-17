package com.ephemerality.aphelion.spawn.entities;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.spawn.puppets.Puppet;

public class Entity implements Comparable<Entity>{
	
	public Rectangle body;
	protected Puppet puppet;
	private boolean renderable;
	
	public Entity(float x, float y, int w, int h, boolean renderable) {
		body = new Rectangle(x, y, w, h);
		this.renderable = renderable;
		
	}
	
	public void update() {
		puppet.update();
	}
	
	
	public void render(SpriteBatch sb, Vector2 c0, Vector2 c1) {
		if(renderable) 
//			For DEBUGGING purposes, each entity should declare renderable and should be correlated to whether the puppter is null or not
			if(puppet != null)
				puppet.render(sb, c0, body.getPosition(new Vector2()));
	}


	@Override
	public int compareTo(Entity e) {
		if(this.body.y > e.body.y) return -1;
		if(this.body.y < e.body.y) return +1;
		return 0;
	}
}