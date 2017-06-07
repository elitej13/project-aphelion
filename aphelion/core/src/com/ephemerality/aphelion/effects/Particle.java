package com.ephemerality.aphelion.effects;

import java.util.Random;

import com.badlogic.gdx.graphics.Color;
import com.ephemerality.aphelion.graphics.ScreenManager;
import com.ephemerality.aphelion.graphics.SpriteSheet;

public class Particle {
	
	static final Random rand = new Random();
	static double speed = 4; //Lower is faster
	
	Color color;
	double xa, ya, za;
	double xx, yy, zz;
	int life, death, phase, buffer, sway;
	float x, y;
	
	public Particle(float x, float y, int life) {
		this.xx = x;
		this.yy = y;
		int s = rand.nextInt(4);
		if(s == 0) color = Color.BLACK;
		else if(s == 1) color = Color.PURPLE;
		else if(s == 2) color = Color.BLUE;
		else color = Color.CYAN;
		this.death = life + rand.nextInt(30) - 20;
		this.buffer = rand.nextInt(32);
		this.phase = rand.nextInt(16);
		this.xa = rand.nextGaussian();
		this.ya = rand.nextGaussian();	
		this.zz = 3.0;
	}
	
	public void update() {
		if(phase == 16) {
			int s = rand.nextInt(4);
			if(s == 0) color = Color.BLACK;
			else if(s == 1) color = Color.PURPLE;
			else if(s == 2) color = Color.BLUE;
			else color = Color.CYAN;
			phase = 0;
		}else phase++;
		if(life < death) {
			za -= .5;
			if(zz < 0) {
				za *= -1.05;
			}
			xx += (xa * speed);
			yy += (ya * speed);
			zz += za;
			life++;
			
			if(life == death) {
				za = 0;
				phase = 0;
				sway = 1;
				xa = Math.abs((xa));
				ya = Math.abs((ya));
			}
		}
//			else {
//				Vector2i goal = master.getPlayer().getPosition();
//				int xg = goal.getX() * Settings.getTileSize();
//				int yg = goal.getY() * Settings.getTileSize();
//				if(p.phase == 10) {
//					p.sway *= -1;
//					p.phase = 0;
//				}else p.phase++;
//
//				if(p.xx >= xg + 32 + p.buffer) {
//					p.xx -= ((p.xa / speed) + p.za);
//				}else if(p.xx < xg + 32 - p.buffer) {
//					p.xx += ((p.xa / speed) + p.za);
////				}else {
//				}
//				p.xx += (p.xa + p.za) * p.sway;
//				if(p.yy >= yg + 32 + p.buffer) {
//					p.yy -= ((p.ya / speed) + p.za);
//				}else if(p.yy < yg + 32 - p.buffer) {
//					p.yy += ((p.ya / speed) + p.za);
////				}else {
//				}
//				p.yy += (p.ya + p.za) * p.sway;
//				
//				p.za += 0.03;
//				
//				if(p.xx > xg + 8 && p.xx <= xg + 56){
//					if(p.yy > yg + 8 && p.yy <= yg + 56) {
//						particles.remove(i);
//					}
//				}
//			}
	}
	public void render(ScreenManager screen) {
		x = (float) xx;
		y = (float) (yy - zz); 
		screen.render(SpriteSheet.pixel, color, x, y);
	}
	
}
