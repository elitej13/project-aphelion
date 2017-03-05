package com.project.duo.spawn.entities;

import com.project.duo.input.InputManager;
import com.project.duo.spawn.Puppet;

public class Player extends Entity {

	
	//testing purposes - not permanent
	int speed = 5;
	public Player(float x, float y) {
		super(x, y);
		puppet = new Puppet();
	}

	public void update(InputManager input) {
		for(int i = 0; i < speed; i++){
			updateMove(input);
		}
	}
	
	
	
	public void updateMove(InputManager input) {
//		if(!(input.goingUp() && input.goingDown())){
//			if(input.goingUp()) {
//				move(Direction.NORTH);
//			}
//			if(input.goingDown()) {
//				move(Direction.SOUTH);
//			}
//		}
//		if(!(input.goingLeft() && input.goingRight())){
//			if(input.goingLeft()) {
//				move(Direction.WEST);
//			}
//			if(input.goingRight()) {
//				move(Direction.EAST);
//			}
//		}
	}
}