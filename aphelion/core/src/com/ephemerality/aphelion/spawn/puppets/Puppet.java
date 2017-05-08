package com.ephemerality.aphelion.spawn.puppets;

import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;

public abstract class Puppet {
	
	public Puppet(float width, float height) {}
	
	public abstract void init(LoadManager assets);
	public abstract void update() ;
	public abstract void setPosition(float x, float y);
	
	public abstract void render(ScreenManager screen) ;



}
