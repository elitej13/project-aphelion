package com.ephemerality.aphelion.spawn.entities;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.spawn.entities.mob.Mob;
import com.ephemerality.aphelion.spawn.puppets.Puppet;

public class Entity implements Comparable<Entity>{
	
	public Rectangle body;
	public boolean isRemoved;
	protected boolean renderable;
	protected Puppet puppet;
	protected short ID;
	
	public Entity(float x, float y, int w, int h, boolean renderable, short ID) {
		this.renderable = renderable;
		body = new Rectangle(x, y, w, h);
		this.ID = ID;
	}
	
	public void update() {
	}
	
	private static Color font_color = new Color(1f, 1f, 1f, 1f);
	public void render(ScreenManager screen) {
		if(renderable) {
//			For DEBUGGING purposes, each entity should declare renderable and should be correlated to whether the puppet is null or not
			screen.renderRectangle(body);
			if(this instanceof Mob) {
				Mob m = (Mob) this;
				screen.renderString(font_color, m.getClass().getSimpleName() + ": " + m.stats.getFormattedDamage(), body.x, body.y - 12);				
			}
		}
	}


	@Override
	public int compareTo(Entity e) {
		if(this.body.y > e.body.y) return -1;
		if(this.body.y < e.body.y) return +1;
		return 0;
	}
	
	public short getID() {
		return ID;
	}
	public Short getWrappedID() {
		return new Short(ID);
	}
	public TextureRegion getIcon() {
		return puppet.getIcon();
	}
}