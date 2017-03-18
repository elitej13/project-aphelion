package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.graphics.ScreenManager;

public class NobPuppet extends Puppet {

	private TextureRegion sprite;
	
	public NobPuppet(int width, int height, TextureRegion sprite) {
		super(width, height);
		this.sprite = sprite;
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render(ScreenManager screen) {
		float x = 0;
		float y = 0;
		if(sprite != null)
			screen.getSpriteBatch().draw(sprite, x, y);
	}

	@Override
	public void setPosition(float x, float y) {
		
	}
	
	
	
	
	
}
