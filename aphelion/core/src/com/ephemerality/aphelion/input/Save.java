package com.ephemerality.aphelion.input;

import java.nio.ByteBuffer;

import com.ephemerality.aphelion.framework.GameManager;

public class Save {

	public static final String EXTENSION = ".sav";
	public String name;
	public int hours, minutes, seconds;
	public int level;
	
	
	public Save(String name) {
		this.name = name;
	}
	public Save(String name, int hours, int minutes, int seconds, int level) {
		this.name = name;
		this.hours = hours;
		this.minutes = minutes;
		this.seconds = seconds;
	}
	public Save(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		int nameSize = buffer.get();
		byte[] nameBytes = new byte[nameSize * 2];
		buffer.get(nameBytes);
		name = new String(nameBytes);
		hours = buffer.getInt();
		minutes = buffer.getInt();
		seconds = buffer.getInt();
		level = buffer.getInt();
	}
	
	
	public byte[] toByteArray(GameManager game) {
		byte[] header = getHeader(game);
		byte[] player = getPlayerData(game);
		byte[] world = getWorldData(game);
		byte[] footer = getFooter(game);
		byte[] data = ByteBuffer.allocate(header.length + player.length + world.length + footer.length).array();
		ByteBuffer.wrap(data).put(header).put(player).put(world).put(footer);
		return data;
	}
	
	public byte[] getHeader(GameManager game) {
		int nameSize = name.length();
		byte[] header = ByteBuffer.allocate((nameSize * 2) + 1 + 4).array();
		ByteBuffer.wrap(header).put(name.getBytes()).putInt(hours).putInt(minutes).putInt(seconds).putInt(level);
		return header;
	}
	public byte[] getPlayerData(GameManager game) {
		byte[] data = ByteBuffer.allocate(Integer.BYTES * 2).array();
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.putFloat(game.ent.player.body.x);
		buffer.putFloat(game.ent.player.body.y);
		
		return null;
	}
	public byte[] getWorldData(GameManager game) {
		return null;
	}
	public byte[] getFooter(GameManager game) {
		int handshake = 666;
		byte[] footer = ByteBuffer.allocate(Integer.BYTES).array();
		ByteBuffer.wrap(footer).putInt(handshake);
		return footer;
	}
	
}
