package com.ephemerality.aphelion.spawn.entities.player;

import com.badlogic.gdx.Gdx;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.entities.player.inventory.Inventory;
import com.ephemerality.aphelion.util.Direction;

public class Player extends Mob {

	ScreenManager screen;
	public Inventory inventory;
	
//testing purposes - not permanent
	int speed = 10;
	
	public Player(ScreenManager screen, LoadManager assets, float x, float y) {
		super(x, y, 128, 64, (short)1, assets);
		this.screen = screen;		
		screen.setPosition(x, y);
		inventory = new Inventory();
	}
	@Override
	public void update() {
		behavior();
		updateAnim();
		inventory.update();
	}
	@Override
	public void behavior() {
		if(!attacking && Gdx.input.isTouched()) {
			attackStartedThisFrame = true;
		}
		
		boolean movedLastFrame = moving;
		for(int i = 0; i < speed; i++){	
			moving = updateMove();
		}
		if(moving != movedLastFrame) {
			movingChangedThisFrame = true;
		}else {
			movingChangedThisFrame = false;
		}
	}
	@Override
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