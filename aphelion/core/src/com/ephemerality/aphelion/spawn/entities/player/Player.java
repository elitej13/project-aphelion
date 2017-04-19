package com.ephemerality.aphelion.spawn.entities.player;

import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.puppets.MobPuppet;
import com.ephemerality.aphelion.util.Direction;

public class Player extends Mob {

	ScreenManager screen;
	
	//testing purposes - not permanent
	int speed = 10;
	
	public Player(ScreenManager screen, float x, float y) {
		super(screen, x, y, 128, 64, (short)1);
		this.screen = screen;		
		screen.setPosition(x, y);
	}

	@Override
	public void update() {
		boolean movedLastFrame = moving;
		for(int i = 0; i < speed; i++){	
			moving = updateMove();
		}
		if(moving != movedLastFrame) {
			movingChangedThisFrame = true;
		}else {
			movingChangedThisFrame = false;
		}
		
		updateAnim();
		
	}
	
	public void updateAnim() {
		MobPuppet mp = (MobPuppet) puppet;
		if(movingChangedThisFrame) {
			if(moving) {
				mp.setAnimation("run");				
			}else {
				mp.setAnimation("idle");
			}
		}
		if(moving) {
			if(dir == Direction.EAST && mp.flippedX())
				mp.flipX();
			if(dir == Direction.WEST && !mp.flippedX())
				mp.flipX();
			
		}
		mp.setPosition(body.x + 64, body.y + 64);
		mp.update();
	}
	
	
	
	public boolean updateMove() {
		boolean up = InputManager.up;
		boolean down = InputManager.down;
		boolean left = InputManager.left;
		boolean right = InputManager.right;
		boolean moved = false;
		if(!(up && down)){
			if(up) {
				dir = Direction.NORTH;
				if(move()) {
					screen.translate(dir);					
					moved = true;
				}
			}
			if(down) {
				dir = Direction.SOUTH;
				if(move()) {
					screen.translate(dir);
					moved = true;
				}
			}
		}
		if(!(left && right)){
			if(left) {
				dir = Direction.WEST;
				if(move()) {
					screen.translate(dir);
					moved = true;
				}
			}
			if(right) {
				dir = Direction.EAST;
				if(move()) {
					screen.translate(dir);	
					moved = true;
				}
			}
		}
		return moved;
	}
}