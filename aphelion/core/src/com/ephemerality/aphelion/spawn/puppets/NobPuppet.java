package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class NobPuppet extends Puppet {

	private TextureRegion texture;
	
	public NobPuppet(float x, float y, float w, float h, TextureRegion texture) {
		super(x, y, w, h);
		this.texture = texture;
	}
	
	@Override
	public void update() {
		
	}
	
	@Override
	public void render(ScreenManager screen) {
		if(texture != null)
			screen.render(texture, x, y);
	}

	@Override
	public TextureRegion getIcon() {
		return texture;
	}


	
}
