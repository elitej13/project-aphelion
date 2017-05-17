package com.ephemerality.aphelion.spawn.entities.nob;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.Entity;
import com.ephemerality.aphelion.spawn.puppets.NobPuppet;

public class Nob extends Entity{

	public Nob(float x, float y, int w, int h, boolean renderable, short ID) {
		super(x, y, w, h, renderable, ID);
		this.puppet = new NobPuppet(x, y, w, h, ID);
	}
	
	@Override
	public void render(ScreenManager screen) {
		super.render(screen);
		if(renderable) {
			if(puppet != null) {
				puppet.render(screen);
			}
		}
	}
}
