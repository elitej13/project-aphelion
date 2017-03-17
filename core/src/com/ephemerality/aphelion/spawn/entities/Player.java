package com.project.duo.spawn.entities;

import com.project.duo.input.InputManager;
import com.project.duo.spawn.entities.mob.Mob;
import com.project.duo.util.Direction;

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