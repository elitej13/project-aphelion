package com.ephemerality.aphelion.util;

public class Stats {
	
	float health, health_max;
	int strength, dexterity, intelligence;
	
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
	public void levelUp(boolean strength, boolean dexterity, boolean intelligence) {
		if(strength) 
			this.strength++;
		if(dexterity)
			this.dexterity++;
		if(intelligence)
			this.intelligence++;
		
		health_max = this.strength * this.dexterity + this.intelligence;
		health = health_max;
	}
	public void modHealth(float amount) {
		health = Util.clamp(health + amount, 0, health_max);
		
	}
	public float getDamage() {
		return strength;
	}
	public boolean isDead() {
		if(health <= 0) 
			return true;
		return false;
	}
	
}
