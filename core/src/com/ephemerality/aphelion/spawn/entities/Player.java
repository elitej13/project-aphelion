package com.ephemerality.aphelion.spawn.entities;

import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.util.Direction;

public class Player extends Mob {

	
	//testing purposes - not permanent
	int speed = 6;
	double health, maxHealth;
	
	
	public Player(float x, float y) {
		super(x, y, 32, 32);
		health = 600;
		maxHealth = 600;
	}

	public void update() {
		for(int i = 0; i < speed; i++){
			updateMove();
		}
//		if(health > 0)health--;
	}
	
	
	
	public void updateMove() {
		boolean up = InputManager.up;
		boolean down = InputManager.down;
		boolean left = InputManager.left;
		boolean right = InputManager.right;
		if(!(up && down)){
			if(up) {
				move(Direction.NORTH);
			}
			if(down) {
				move(Direction.SOUTH);
			}
		}
		if(!(left && right)){
			if(left) {
				move(Direction.WEST);
			}
			if(right) {
				move(Direction.EAST);
			}
		}
	}
	
	public double getHealthProportion() {
		return health / maxHealth;
	}
}