package com.ephemerality.aphelion.spawn.entities.nob;

import com.badlogic.gdx.math.Rectangle;

public class EnvNob extends Nob{
	
	public EnvNob(Rectangle body, float x, float y, int w, int h, short ID) {
		super(body.x + x, body.y + y, (int)body.width, (int)body.height, true, ID);
		puppet.x = x;
		puppet.y = y;
	}
	
	
	
}
