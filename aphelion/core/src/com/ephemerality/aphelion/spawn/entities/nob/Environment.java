package com.ephemerality.aphelion.spawn.entities.nob;

import com.badlogic.gdx.math.Rectangle;

//public class Environment extends Nob{
//
//	public static final short HOUSE		= -20001;
//	public static final short FENCE		= -20002;
//	public static final short GATE		= -20003;
//	public static final short BRIDGE	= -20004;
//	public static final short TREE		= -20005;
//	
//	
//	
//	public Environment(float x, float y, int w, int h, short ID) {
//		super(x, y, w, h, true, ID);
//	}
//}
public enum Environment {
	
	House	(new Rectangle(0, 0, 192, 192), 	(short) -20001),
	Fence	(new Rectangle(0, 0, 64, 16), 		(short) -20002),
	Gate	(new Rectangle(0, 0, 64, 16), 		(short) -20003),
	Bridge	(null, 								(short)  20004),
	Tree	(new Rectangle(48, 0, 72, 64), 		(short) -20005);
	
	public final Rectangle body;
	public final short ID;
	
	private Environment(Rectangle body, short ID) {
		this.body = body;
		this.ID = ID;
	}
	
}