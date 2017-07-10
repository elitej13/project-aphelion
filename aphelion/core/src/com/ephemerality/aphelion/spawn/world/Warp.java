package com.ephemerality.aphelion.spawn.world;

import java.nio.ByteBuffer;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.util.FileManager;

public class Warp {
	
	public static final int BYTES = 36;
	
	public String level;
	public Rectangle local;
	public Vector2 dest;
	
	public Warp(String level, Rectangle local, Vector2 dest) {
		this.level = level;
		this.local = local;
		this.dest = dest;
	}
	public Warp(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		byte[] level = new byte[Level.STRING_SIZE * Character.BYTES];
		byte[] local = new byte[FileManager.RECTANGLE_BYTES];
		byte[] dest = new byte[FileManager.VECTOR2_BYTES];
		buffer.put(level);
		buffer.put(local);
		buffer.put(dest);
		this.level = new String(level);
		this.local = FileManager.rectangleFromByteArray(local);
		this.dest = FileManager.vector2FromByteArray(dest);
	}
	public byte[] tobytes() {
		return ByteBuffer.allocateDirect(BYTES).put(level.getBytes()).put(FileManager.toByteArray(local)).put(FileManager.toByteArray(dest)).array();
	}
	
	
	
	
//	String levelA, levelB;
//	public boolean inLevel;
//	public Rectangle rectA, rectB;
//	static enum DESTINATION {A, B};
//	DESTINATION dest;
//	
//	public Warp(String level, Rectangle rectA, Rectangle rectB) {
//		inLevel = true;
//		this.levelA = level;
//		this.levelB = level;
//		this.rectA = rectA;
//		this.rectB = rectB;
//	}
//	
//	public Warp(String levelA, String levelB, Rectangle rectA, Rectangle rectB) {
//		inLevel = false;
//		this.levelA = levelA;
//		this.levelB = levelB;
//		this.rectA = rectA;
//		this.rectB = rectB;
//	}
//	public boolean checkActivated(Rectangle rect, String currentLevel) {
//		if(levelA == currentLevel){
//			if(rectA.overlaps(rect)){
//				dest = DESTINATION.B;
//				return true;			
//			}
//		}
//		if(levelB == currentLevel) {
//			if(rectB.overlaps(rect)) {
//				dest = DESTINATION.A;
//				return true;	
//			}			
//		}
//		return false;
//	}
//	public String getDestination() {
//		if(dest == DESTINATION.A) {
//			return levelA;
//		}
//		if(dest == DESTINATION.B) {
//			return levelB;
//		}
//		return null;
//	}
//	public void positionBody(Rectangle rect) {
//		Rectangle position = null;
//		if(dest == DESTINATION.A) position = rectA;
//		if(dest == DESTINATION.B) position = rectB;
//		rect.setX(position.x + ((position.width - rect.width) / 2f));
//		rect.setY(position.y + ((position.height - rect.height) / 2f));
//	}
//	public void render(ScreenManager screen, String level) {
//		if(level.equals(levelA)) {
//			screen.renderRectangle(rectA, Color.BLUE);
//			screen.renderString(Color.BLUE, levelA, rectA.x, rectA.y - 5f);
//		}
//		if(level.equals(levelB)) {
//			screen.renderRectangle(rectB, Color.BLUE);
//			screen.renderString(Color.BLUE, levelB, rectB.x, rectB.y - 5f);
//		}
//		
//	}
}
