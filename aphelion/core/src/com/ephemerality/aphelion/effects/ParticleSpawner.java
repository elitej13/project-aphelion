package com.ephemerality.aphelion.effects;

import java.util.ArrayList;
import java.util.List;

import com.ephemerality.aphelion.graphics.ScreenManager;

public class ParticleSpawner {
	float x, y;
	int life, pLife;
	int quantityPerFrame;
	List<Particle> particles;
	List<Particle> removed;
	
	public ParticleSpawner(float x, float y, int spawnerLife, int particleLife, int quantityPerFrame) {
		particles = new ArrayList<>();
		removed = new ArrayList<>();
		this.x = x;
		this.y = y;
		this.life = spawnerLife;
		this.pLife = particleLife;
		this.quantityPerFrame = quantityPerFrame;

		for(int i = 0; i < quantityPerFrame; i++) 
			particles.add(new Particle(x, y, pLife));
	}
	
	public void update() {
		for(int i = 0; i < quantityPerFrame; i++) 
			particles.add(new Particle(x, y, pLife));
//		for(Particle p : particles) {
//			p.update();
//			if(p.life >= p.death)
//				removed.add(p);
//		}
		particles.removeAll(removed);
		removed.clear();
	}
	
	
	public void render(ScreenManager screen) {
		for(Particle p : particles) 
			p.render(screen);
	}
	
}