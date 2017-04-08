package com.ephemerality.aphelion.spawn.entities;


import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.puppets.Puppet;

public class Entity implements Comparable<Entity>{
	
	public Rectangle body;
	protected boolean renderable;
	protected Puppet puppet;
	
	public Entity(float x, float y, int w, int h, boolean renderable) {
		this.renderable = renderable;
		body = new Rectangle(x, y, w, h);
	}
	
	public void update() {
	}
	
	
	public void render(ScreenManager screen) {
		if(renderable) 
//			For DEBUGGING purposes, each entity should declare renderable and should be correlated to whether the puppet is null or not
		screen.render(SpriteSheet.rectangle, body);
	}


	@Override
	public int compareTo(Entity e) {
		if(this.body.y > e.body.y) return -1;
		if(this.body.y < e.body.y) return +1;
		return 0;
	}
}