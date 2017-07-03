package com.ephemerality.aphelion.spawn.entities.nob;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.nob.Nob;

public class Item extends Nob {
	
	public static final short CHEST = 20000;
	public static final short SWORD = 20001;
	
	
	public Item(float x, float y, int w, int h, short ID) {
		super(x, y, w, h, true, ID);
	}
	
	
	@Override
	public void update() {
		puppet.anim(0.2f);
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
