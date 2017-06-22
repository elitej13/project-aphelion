package com.ephemerality.aphelion.persona;

import com.ephemerality.aphelion.spawn.equipment.armor.Armor;
import com.ephemerality.aphelion.spawn.equipment.weapon.Weapon;

public class Equip {
	
	Weapon weapon;
	Armor armor;
	Stats stats;
	
	public Equip(Stats stats) {
		this.stats = stats;
		this.weapon = Weapon.MELEE_BASIC;
		this.armor = Armor.BASIC;
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
	public Armor setArmor(Armor armor) {
		Armor current = this.armor;
		this.armor = armor;
		return current;
	}
	public Weapon.ATTACK_TYPE getAttackType() {
		return weapon.type;
	}
	public float getPercentDamaged() {
		return stats.getPercentDamaged();
	}
	public float getPhysicalDamage() {
		float damage = stats.getPhysicalDamage();
		if(weapon != null) damage += weapon.physicalDamage;
		return damage;
	}
	public float getMagicalDamage() {
		float damage = stats.getMagicalDamage();
		if(weapon != null) damage += weapon.magicalDamage;
		return damage;
	}
	public float getPhysicalResistance() {
		float resist = stats.getPhysicalResist();
		if(armor != null) resist += armor.physicalResist;
		return resist;
	}
	public float getMagicalResistance() {
		float resist = stats.getPhysicalResist();
		if(armor != null) resist += armor.magicalResist;
		return resist;
	}
	public String getFormattedDamage() {
		return stats.getFormattedDamage();
	}
	public float modHealth(float amount) {
		return stats.modHealth(amount);
	}
	public boolean isDead() {
		return stats.isDead();
	}
	public float getStun() {
		return weapon.getStun();
	}
	public int getLevel() {
		return stats.getLevel();
	}
	
}
