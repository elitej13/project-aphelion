package com.project.duo.spawn;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.project.duo.graphics.Sprite;

public class Puppet {
	
	private TextureRegion hat;
	private TextureRegion head;
	private TextureRegion arms;
	private TextureRegion torso;
	private TextureRegion legs;
	
	public Puppet() {
		hat = Sprite.default_hat_idle;
		head = Sprite.default_head_idle;
		arms = Sprite.default_arms_idle;
		torso = Sprite.default_torso_idle;
		legs = Sprite.default_legs_idle;
	}
	
	public void update() {
		
			
		
	}
	
	
	public void render(SpriteBatch sb, Vector2 offset, Vector2 position) {
		float x = position.x + offset.x;
		float y = position.y + offset.y;
		
		if(hat != null)
			sb.draw(hat, x, y + 32);
		if(head != null)
			sb.draw(head, x, y);
		if(torso != null)
			sb.draw(torso, x, y - 32);
		if(arms != null)
			sb.draw(arms, x, y - 32);
		if(legs != null)
			sb.draw(legs, x, y - 64);
	}



}
