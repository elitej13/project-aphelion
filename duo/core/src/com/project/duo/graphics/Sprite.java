package com.project.duo.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite {

	public static Texture tilesheet = new Texture(Gdx.files.internal("textures/tilesheet.png"));
	public static Texture entitysheet = new Texture(Gdx.files.internal("textures/entitysheet.png"));
	
	public static TextureRegion default_head_idle = new TextureRegion(Sprite.entitysheet, 	0, 0, 	32, 32);
	public static TextureRegion default_torso_idle = new TextureRegion(Sprite.entitysheet, 	0, 32, 	32, 32);
	public static TextureRegion default_arms_idle = new TextureRegion(Sprite.entitysheet, 	0, 64, 	32, 32);
	public static TextureRegion default_legs_idle = new TextureRegion(Sprite.entitysheet, 	0, 96, 	32, 32);
	public static TextureRegion default_hat_idle = new TextureRegion(Sprite.entitysheet, 	0, 128, 32, 32);
	
	
	public static TextureRegion default_grass_0 = new TextureRegion(Sprite.tilesheet, 	0, 0, 64, 64);
	public static TextureRegion default_dirt_0 = new TextureRegion(Sprite.tilesheet, 	0, 64, 64, 64);
	public static TextureRegion default_brick_0 = new TextureRegion(Sprite.tilesheet, 	0, 128, 64, 64);
	public static TextureRegion default_wood_0 = new TextureRegion(Sprite.tilesheet, 	0, 192, 64, 64);
	

	
}
