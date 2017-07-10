package com.ephemerality.aphelion.spawn.entities.player;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.ephemerality.aphelion.framework.GameManager;
import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.input.InputManager;
import com.ephemerality.aphelion.persona.Equip;
import com.ephemerality.aphelion.persona.Stats;
import com.ephemerality.aphelion.persona.inventory.Inventory;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.world.Warp;
import com.ephemerality.aphelion.util.Direction;

public class Player extends Mob {

	public Inventory inventory;
	public ScreenManager screen;
	boolean isWarped;
	
//testing purposes - not permanent
	int speed = 10;
	
	public Player(ScreenManager screen, LoadManager assets, float x, float y) {
		super(x, y, 116, 32, Mob.PLAYER, assets, LoadManager.MONSTER_SCML, new Equip(new Stats()));
		this.screen = screen;		
		screen.setPosition(x + body.width / 2, y + body.height / 2);
		inventory = new Inventory();
	}
	public Player(ScreenManager screen, LoadManager assets, byte[] data) {
		super(ByteBuffer.wrap(data).getFloat(0), ByteBuffer.wrap(data).getFloat(1), 128, 64, Mob.PLAYER, assets, LoadManager.MONSTER_SCML, new Equip(new Stats()));
		this.screen = screen;
		screen.setPosition(ByteBuffer.wrap(data).getFloat(0), ByteBuffer.wrap(data).getFloat(1));
		byte[] inven = Arrays.copyOf(data, data.length - 2 * Float.BYTES);
		inventory = new Inventory(inven);
		screen.setPosition(body.x + body.width / 2, body.y + body.height / 2);
	}
	@Override
	public void update() {
		behavior();
		updateAnim();
		inventory.update();
	}
	@Override
	public void behavior() {
		Warp warp = warp();
		if(warp != null) {
			if(!isWarped) {
				GameManager.requestWarp(warp);
				isWarped = true;
			}
		}else {
			isWarped = false;
		}
		
		
		
		if(!attacking && (Gdx.input.isTouched() || Gdx.input.isKeyJustPressed(Input.Keys.SPACE))) {
			attackStartedThisFrame = true;
		}
		if(attacking) {
			attack();
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
	/**
	 * Inventory
	 * Position
	 * @return
	 */
	public byte[] toByteArray() {
		byte[] inven = inventory.toByteArray();
		byte[] data = ByteBuffer.allocate(inven.length + Float.BYTES * 2).array();
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.putFloat(body.x);
		buffer.putFloat(body.y);
		buffer.put(inven);
		return data;
	}
}