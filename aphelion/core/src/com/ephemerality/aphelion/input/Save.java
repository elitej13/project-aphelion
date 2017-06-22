package com.ephemerality.aphelion.input;

import java.nio.ByteBuffer;
import java.util.Arrays;

import com.ephemerality.aphelion.framework.GameManager;
import com.ephemerality.aphelion.framework.Master;

public class Save {

	public static final String EXTENSION = ".sav";
	public static final int MAX_CHARS_PER_NAME = 32;
	public static final int HEADER_SIZE_IN_BYTES = MAX_CHARS_PER_NAME * Character.BYTES + (7 * Integer.BYTES);
	public static final int FOOTER_SIZE_IN_BYTES = Integer.BYTES;
	
	public int hours, minutes, seconds, level;
	public char[] name;
	
	public byte[] header;
	public byte[] player;
	public byte[] world;
	public byte[] footer;
	
	public Save(String name) {
		char[] chars = name.toCharArray();
		this.name = Arrays.copyOf(chars, MAX_CHARS_PER_NAME);
	}
	public Save(byte[] data) {
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.asCharBuffer().subSequence(0, 1);
		hours = buffer.getInt();
		minutes = buffer.getInt();
		seconds = buffer.getInt();
		level = buffer.getInt();
	}
	public byte[] toByteArray(GameManager game) {
		footer = generateFooter(game);
		world = generateWorldData(game);
		player = generatePlayerData(game);
		header = generateHeader(game);
		byte[] data = ByteBuffer.allocate(header.length + player.length + world.length + footer.length).array();
		ByteBuffer.wrap(data).put(header).put(player).put(world).put(footer);
		return data;
	}
	/**
	 * NAME = 32 chars worth of bytes = 32 * 2 = 64 - 
	 * {@link com.ephemerality.aphelion.input.Save#MAX_CHARS_PER_NAME Max Chars} <br>
	 * LEVEL, HOURS, MINUTES, SECONDS, WorldDataSize, PlayerDataSize = 7 ints worth of bytes = 7 * 4 = 28 - 
	 * {@link com.ephemerality.aphelion.input.Save#HEADER_SIZE_IN_BYTES Header Size}
	 * @param game
	 * @return header data
	 */
	public byte[] generateHeader(GameManager game) {
		byte[] data = ByteBuffer.allocate(HEADER_SIZE_IN_BYTES).array();
		ByteBuffer buffer = ByteBuffer.wrap(data);
		int[] time = Master.getTime(GameManager.playTime);
		hours = time[0];
		minutes = time[1];
		seconds = time[2];
		level = game.ent.player.equip.getLevel();
		buffer.put(new String(name).getBytes());
		buffer.putInt(hours);
		buffer.putInt(minutes);
		buffer.putInt(seconds);
		buffer.putInt(level);
		buffer.putInt(player.length);
		buffer.putInt(world.length);
		return data;
	}
	public byte[] generatePlayerData(GameManager game) {
		byte[] data = ByteBuffer.allocate(Float.BYTES * 2).array();
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.putFloat(game.ent.player.body.x);
		buffer.putFloat(game.ent.player.body.y);
		return data;
	}
	public byte[] generateWorldData(GameManager game) {
		//TODO : fix this reesty shit
		return new byte[2];
	}
	public byte[] generateFooter(GameManager game) {
		int handshake = 666;
		byte[] footer = ByteBuffer.allocate(Integer.BYTES).array();
		ByteBuffer.wrap(footer).putInt(handshake);
		return footer;
	}
	public String getFormattedName() {
		return new String(name).trim() + "-" + hours + "_" + minutes + "_" + seconds;
	}
}
