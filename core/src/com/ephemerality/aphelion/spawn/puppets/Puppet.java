package com.ephemerality.aphelion.spawn.puppets;

import com.ephemerality.aphelion.graphics.ScreenManager;

public abstract class Puppet {
	
	public Puppet(int width, int height) {}
	
	public abstract void update() ;
	public abstract void setPosition(float x, float y);
	
	public abstract void render(ScreenManager screen) ;



}
