package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.graphics.ScreenManager;

public abstract class Puppet {
	
	protected float x, y, w, h;
	
	public Puppet(float x, float y, float w, float h) {
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
	}
	public abstract void update() ;
	public abstract void render(ScreenManager screen) ;
	public abstract TextureRegion getIcon() ;



}
