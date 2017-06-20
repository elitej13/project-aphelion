package com.ephemerality.aphelion.spawn.equipment.weapon;

import com.ephemerality.aphelion.persona.Equipment;

public class Weapon extends Equipment {

	
	public static enum  ATTACK_TYPE {MELEE_MAGIC, MELEE_PHYSICAL, MELEE_MIXED,
							RANGED_MAGIC, RANGED_PHYSICAL, RANGED_MIXED};
	
	public static final short BASIC_ID = 21001;
	public static final short OP_ID = 21009;
	
	public static final Weapon RANGED_BASIC = new Ranged(5f, 5f, 1f, BASIC_ID);
	public static final Weapon RANGED_OP = new Ranged(100f, 100f, 1f, OP_ID);
	

	public static final Weapon MELEE_BASIC = new Melee(5f, 5f, 1f, BASIC_ID);
	public static final Weapon MELEE_OP = new Melee(100f, 100f, 1f, OP_ID);
	
	
	public float physicalDamage, magicalDamage, stun;
	public ATTACK_TYPE type;
	
	public Weapon(float physicalDamage, float magicalDamage, float stun, boolean melee, short ID) {
		super(ID);
		this.magicalDamage = magicalDamage;
		this.physicalDamage = physicalDamage;
		this.stun = stun;
		if(melee) {
			if(magicalDamage > 0 && physicalDamage > 0) {
				type = ATTACK_TYPE.MELEE_MIXED;
			}else if(physicalDamage > 0) {
				type = ATTACK_TYPE.MELEE_PHYSICAL;
			}else if(magicalDamage > 0) {
				type = ATTACK_TYPE.MELEE_MAGIC;
			}
		}else {
			if(magicalDamage > 0 && physicalDamage > 0) {
				type = ATTACK_TYPE.RANGED_MIXED;
			}else if(physicalDamage > 0) {
				type = ATTACK_TYPE.RANGED_PHYSICAL;
			}else if(magicalDamage > 0) {
				type = ATTACK_TYPE.RANGED_MAGIC;
			}
		}
	}

	public float getStun() {
		return stun;
	}

}
