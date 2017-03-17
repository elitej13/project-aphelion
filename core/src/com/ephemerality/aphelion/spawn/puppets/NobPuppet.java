package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

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
	public void render(SpriteBatch sb, Vector2 offset, Vector2 position) {
		float x = position.x - offset.x;
		float y = position.y - offset.y;
		
		if(sprite != null)
			sb.draw(sprite, x, y);
	}
	
	
	
	
	
}
