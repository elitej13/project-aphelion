package com.ephemerality.aphelion.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.util.Direction;

public class ScreenManager {
	
	SpriteBatch sb;
	ShapeRenderer sr;
	OrthographicCamera oc;
	Rectangle bounds;
	
	
	public ScreenManager() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		oc = new OrthographicCamera();
		bounds = new Rectangle();
		resize();
	}
	
	public void resize() {
		oc.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		bounds.setWidth(Gdx.graphics.getWidth());
		bounds.setHeight(Gdx.graphics.getHeight());
		update();
	}
	public void setPosition(float x, float y) {
		oc.position.set(x, y, 0f);
		float xb = (float) (x - Math.floor(bounds.width / 2));
		float yb =  (float) (y - Math.floor(bounds.height / 2));
		bounds.setPosition(xb, yb);
		update();
	}
	
	public void update() {
		oc.update();
		sr.setProjectionMatrix(oc.combined);
		sb.setProjectionMatrix(oc.combined);
	}
	public void translate(float x, float y) {
		oc.translate(x, y);
		bounds.setPosition(bounds.x + x, bounds.y + y);
		
	}
	public void translate(Direction dir) {
		if(dir == Direction.NORTH) {
			translate(0, 1);
		}else if(dir == Direction.SOUTH) {
			translate(0, -1);
		}else if(dir == Direction.WEST) {
			translate(-1, 0);
		}else if(dir == Direction.EAST){
			translate(1, 0);		
		}else {
			System.out.println("Unhandeled direction, assuming EAST");
			translate(0, 0);
		}
	}
	public void dispose() {
		sb.dispose();
		sr.dispose();
	}
	public Rectangle getBounds() {
		return bounds;
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
