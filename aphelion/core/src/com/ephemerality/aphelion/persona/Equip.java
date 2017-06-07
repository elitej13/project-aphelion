package com.ephemerality.aphelion.persona;

import com.ephemerality.aphelion.spawn.equipment.armor.Armor;
import com.ephemerality.aphelion.spawn.equipment.weapon.Weapon;

public class Equip {
	
	Weapon weapon;
	Armor armor;
	Stats stats;
	
	public Equip(Stats stats) {
		this.stats = stats;
	}
	public Equip(Stats stats, Weapon weapon, Armor armor) {
		this.weapon = weapon;
		this.armor = armor;
	}
	
	public Weapon setWeapon(Weapon weapon) {
		Weapon current = this.weapon;
		this.weapon = weapon;
		return current;
	}
	public boolean isDead() {
		return stats.isDead();
	}
	public float getPercentDamaged() {
		return stats.getPercentDamaged();
	}
	public float getDamage() {
		float damage = stats.getDamage();
		if(weapon != null) damage += weapon.getStrength();
		return damage;
	}
	public String getFormattedDamage() {
		return stats.getFormattedDamage();
	}
	public float modHealth(float amount) {
		return stats.modHealth(amount);
	}
	

}
