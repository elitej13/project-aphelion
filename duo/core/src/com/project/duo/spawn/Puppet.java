package com.project.duo.spawn;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.project.duo.framework.Game;
import com.project.duo.graphics.Sprite;
import com.project.duo.util.Direction;

public class Puppet {
	
	TextureRegion hat;
	TextureRegion head;
	TextureRegion arms;
	TextureRegion torso;
	TextureRegion legs;
	Vector3 pos;
	
	public Puppet() {
		pos = new Vector3(128, 128, 0);
		hat = Sprite.default_hat_idle;
		head = Sprite.default_head_idle;
		arms = Sprite.default_arms_idle;
		torso = Sprite.default_torso_idle;
		legs = Sprite.default_legs_idle;
	}
	
	
	public void move(Direction dir) {
		if(dir == Direction.NORTH) pos.add(Game.NORTH);
		else if(dir == Direction.EAST) pos.add(Game.EAST);
		else if(dir == Direction.SOUTH) pos.add(Game.SOUTH);
		else if(dir == Direction.WEST) pos.add(Game.WEST);
	}
	
	public void render(SpriteBatch sb) {
		float x = pos.x;
		float y = pos.y;
		float z = pos.z;
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
