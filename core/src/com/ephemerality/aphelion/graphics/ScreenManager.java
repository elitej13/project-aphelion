package com.ephemerality.aphelion.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.ephemerality.aphelion.util.Direction;

public class ScreenManager {
	
	SpriteBatch sb;
	ShapeRenderer sr;
	OrthographicCamera oc;
	
	
	public ScreenManager() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		oc = new OrthographicCamera();
//		resize();
	}
	
	public void resize() {
		oc.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		oc.update();
		sr.setProjectionMatrix(oc.combined);
		sb.setProjectionMatrix(oc.combined);
	}
	public void setPosition(float x, float y) {
		oc.position.set(x, y, 0f);
		oc.update();
		sr.setProjectionMatrix(oc.combined);
		sb.setProjectionMatrix(oc.combined);
	}
	
	public void update() {
		oc.update();
		sr.setProjectionMatrix(oc.combined);
		sb.setProjectionMatrix(oc.combined);
	}
	public void translate(float x, float y) {
		oc.translate(x, y);
	}
	public void translate(Direction dir) {
		if(dir == Direction.NORTH) {
			oc.translate(0, 1);
		}else if(dir == Direction.SOUTH) {
			oc.translate(0, -1);
		}else if(dir == Direction.WEST) {
			oc.translate(-1, 0);
		}else if(dir == Direction.EAST){
			oc.translate(1, 0);		
		}else {
			System.out.println("Unhandeled direction, assuming EAST");
			oc.translate(0, 0);
		}
	}
	public void dispose() {
		sb.dispose();
		sr.dispose();
	}
	
	public SpriteBatch getSpriteBatch() {
		return sb;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return sr;
	}
	
	public OrthographicCamera getOrthographicCamera() {
		return oc;
	}
	
}
