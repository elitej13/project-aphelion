package com.ephemerality.aphelion.spawn.entities.mob;

import java.util.HashSet;
import java.util.Iterator;

import com.ephemerality.aphelion.graphics.LoadManager;
import com.ephemerality.aphelion.persona.Equip;
import com.ephemerality.aphelion.spawn.entities.player.Player;

public class Dummy extends Mob{

	public Dummy(float x, float y, LoadManager assets, Equip equip) {
		super(x, y, 128, 64, Mob.DUMMY, assets, LoadManager.MONSTER_SCML, equip);
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
