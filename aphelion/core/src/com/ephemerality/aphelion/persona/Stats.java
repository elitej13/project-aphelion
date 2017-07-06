package com.ephemerality.aphelion.persona;

import com.ephemerality.aphelion.util.Util;

public class Stats {
	
	float health, health_max;
	int strength, dexterity, intelligence;
	int level;
	
	public Stats() {
		this(5, 5, 5);
	}
	public Stats(int strength, int dexterity, int intelligence) {
		this.strength = strength;
		this.dexterity = dexterity;
		this.intelligence = intelligence;
		health_max = strength * dexterity + intelligence;
		health = health_max;
	}
	
	public float getPercentDamaged() {
		return health / health_max;
	}
	public String getFormattedHealth() {
		return health + "/" + health_max;
	}
	public void levelUp(boolean strength, boolean dexterity, boolean intelligence) {
		if(strength) 
			this.strength++;
		if(dexterity)
			this.dexterity++;
		if(intelligence)
			this.intelligence++;
		level++;
		health_max = this.strength * this.dexterity + this.intelligence;
		health = health_max;
	}
	public float modHealth(float amount) {
		float hp = health;
		health = Util.clamp(health + amount, 0, health_max);
		return hp - health;
	}
	public float getPhysicalDamage() {
		return strength;
	}
	public float getMagicalDamage() {
		return intelligence;
	}
	public float getPhysicalResist() {
		return strength * 0.5f;
	}
	public float getMagicalResist() {
		return intelligence * 0.5f;
	}
	public boolean isDead() {
		if(health <= 0) 
			return true;
		return false;
	}
	public int getLevel() {
		return level;
	}
	
}
