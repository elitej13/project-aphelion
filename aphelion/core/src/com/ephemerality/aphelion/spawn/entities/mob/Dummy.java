package com.ephemerality.aphelion.spawn.entities.mob;

import java.util.HashSet;
import java.util.Iterator;

import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.spawn.entities.player.Player;
import com.ephemerality.aphelion.util.Stats;

public class Dummy extends Mob{

	public Dummy(float x, float y, int w, int h, short ID, LoadManager assets, Stats stats) {
		super(x, y, w, h, ID, assets, stats);
		nearby = new HashSet<>();
	}
	
	HashSet<Mob> nearby;
	
	@Override
	public void behavior() {
		if(branch0 != null) 
			nearby.addAll(branch0.getNearbyMobs(this, 10f));
		if(branch1 != null) 
			nearby.addAll(branch1.getNearbyMobs(this, 10f));
		if(branch2 != null) 
			nearby.addAll(branch2.getNearbyMobs(this, 10f));
		if(branch3 != null) 
			nearby.addAll(branch3.getNearbyMobs(this, 10f));
		
		Iterator<Mob> iter = nearby.iterator();
		Mob target = null;
		while(iter.hasNext()) {		
			Mob m = iter.next();
			if(m instanceof Player) {
				target = m;
				break;
			}
		}
		if(target != null) {
			
		}
	}
	
}
