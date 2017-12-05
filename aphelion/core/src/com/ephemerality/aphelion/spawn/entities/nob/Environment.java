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
	
	House	(new Rectangle(0, 0, 192, 192), 	192, 	192, 	(short) -20001),
	Fence	(new Rectangle(0, 0, 64, 16), 		64, 	64, 	(short) -20002),
	Gate	(new Rectangle(0, 0, 64, 16), 		64, 	64,		(short) -20003),
	Bridge	(new Rectangle(0, 0, 64, 64),		64, 	192,   	(short)  20004),
	Tree	(new Rectangle(48, 0, 32, 32), 		128, 	128,	(short) -20005);
	
	public final Rectangle BODY;
	public final short ID;
	public final int WIDTH, HEIGHT;
	
	private Environment(Rectangle body, int w, int h, short id) {
		this.BODY = body;
		this.ID = id;
		this.WIDTH = w;
		this.HEIGHT = h;
	}
	public static EnvNob instantiateEnvNob(float x, float y, short ID) {
		for(Environment env : Environment.values()) {
			if(ID == env.ID) {
				return new EnvNob(env.BODY, x, y, env.WIDTH, env.HEIGHT, ID);
			}
		}
		return null;
	}
}