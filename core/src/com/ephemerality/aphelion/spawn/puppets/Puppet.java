package com.project.duo.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class Puppet {
	
	
	
	
	public Puppet(int width, int height) {}
	
	public abstract void update() ;
	
	
	public abstract void render(SpriteBatch sb, Vector2 offset, Vector2 position) ;



}
