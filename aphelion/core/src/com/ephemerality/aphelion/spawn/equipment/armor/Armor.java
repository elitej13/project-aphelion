package com.ephemerality.aphelion.spawn.equipment.armor;

import com.ephemerality.aphelion.persona.Equipment;

public class Armor extends Equipment{
		
	public static final short BASIC_ID = 21001;
	public static final short OP_ID = 21009;
	
	public static final Armor BASIC = new Armor(5f, 5f, BASIC_ID);
	public static final Armor OP = new Armor(100f, 100f, OP_ID);
	
	
	
	
	public float physicalResist, magicalResist;
	
	public Armor(float physicalResist, float magicalResist, short ID) {
		super(ID);
		this.physicalResist = physicalResist;
		this.magicalResist = magicalResist;
	}
	
}
