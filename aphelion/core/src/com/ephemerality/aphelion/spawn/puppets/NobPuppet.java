package com.ephemerality.aphelion.spawn.puppets;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;

public class NobPuppet extends Puppet {

	private static final float maxOffset = 16f;
	private TextureRegion texture;
	private float offset;
	private boolean dir;
	
	public NobPuppet(float x, float y, float w, float h, short ID) {
		super(x, y, w, h);
		this.texture = SpriteSheet.fetchTextureRegionFromEntityID(ID);
	}
	
	@Override
	public void update() {
	}
	@Override
	public void anim(float speed) {
		if(dir) {
			offset += speed;
			if(offset >= maxOffset)
				dir = !dir;
		}else {
			offset -= speed;
			if(offset <= 0)
				dir = !dir;
		}
	}
	@Override
	public void render(ScreenManager screen) {
		if(texture != null)
			screen.render(texture, x, y + offset);
	}

	@Override
	public TextureRegion getIcon() {
		return texture;
	}


	
}
