package com.ephemerality.aphelion.graphics;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.ephemerality.aphelion.util.Direction;

public class ScreenManager {
	
	HashMap<Vector2, Texture> rectangles;
	public static BitmapFont font;
	public SpriteBatch sb;
	public OrthographicCamera oc;
	public Rectangle bounds;
	Color color;
	int FONT_SIZE = 12;
	boolean batchHasBegun;
	
	public static final int WIDTH = 1000, HEIGHT = 600;
	
	public ScreenManager() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		
		rectangles = new HashMap<>();
		center = new Rectangle(w / 2 - 1, h / 2 - 1, 4f, 4f);
		color = new Color(0, 0, 0, 1f);
		oc = new OrthographicCamera(w, h);
		oc.setToOrtho(false, w, h);
		sb = new SpriteBatch();
		bounds = new Rectangle(0, 0, w, h);
		update();

		//Font Generations
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/inconsolata/Inconsolata-Bold.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		
		parameter.size = FONT_SIZE;
		parameter.color = new Color(0, 0, 0, 1.0f);
		font = generator.generateFont(parameter);
		
		Pixmap map = new Pixmap(1, 1, Format.RGBA8888);
		map.setColor(Color.BLACK);
		pixel = new Texture(map);
	}
	public void update() {
		oc.update();
		sb.setProjectionMatrix(oc.combined);
	}
		
	/**
	 * @param body Body to center screen around
	 */
	public void resize(Rectangle body) {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		float cx = body.x + (body.width / 2f);
		float cy = body.y + (body.height / 2f);
		oc = new OrthographicCamera(w, h);
		oc.setToOrtho(false, w, h);
		oc.position.set(cx, cy, 0);
		bounds.set(cx - (w / 2f), cy - (h / 2f), w, h);
		center.set(w / 2 - 1, h / 2 - 1, 4f, 4f);
		update();
	}
	public void resize() {
		int w = Gdx.graphics.getWidth();
		int h = Gdx.graphics.getHeight();
		float cx = oc.position.x;
		float cy = oc.position.y;
		oc = new OrthographicCamera(w, h);
		oc.setToOrtho(false, w, h);
		oc.position.set(cx, cy, 0);
		bounds.set(cx - (w / 2f), cy - (h / 2f), w, h);
		center.set(w / 2 - 1, h / 2 - 1, 4f, 4f);
		update();
	}
	public void start() {
		Gdx.gl20.glClearColor(color.r, color.b, color.g, color.a);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);		
//		Gdx.gl20.glEnable(GL20.GL_BLEND);
//		sb.enableBlending();
		sb.begin();
		batchHasBegun = true;
	}
	//Debug
	static Rectangle center;
	public void finish() {
		renderRectangle(center, Color.RED, bounds.x, bounds.y);
		sb.end();
		batchHasBegun = false;
	}

	public void setPosition(float x, float y) {
		oc.position.set(x, y, 0f);
		float xb = (float) (x - Math.floor(bounds.width / 2));
		float yb =  (float) (y - Math.floor(bounds.height / 2));
		bounds.setPosition(xb, yb);
		update();
	}
	public void rotate(float w, float x, float y, float z) {
		Quaternion quat = new Quaternion();
		quat.w = w;
		quat.x = x;
		quat.y = y;
		quat.z = z;
		oc.rotate(quat);
	}
	public void scrolled(int amount) {
		oc.zoom += amount * 0.1f;
	}
	public void translate(float x, float y) {
		oc.translate(x, y);
		bounds.setPosition(bounds.x + x, bounds.y + y);
		
	}
	public void translate(Direction dir) {
		if(dir == Direction.NORTH) {
			translate(0, 1);
		}else if(dir == Direction.SOUTH) {
			translate(0, -1);
		}else if(dir == Direction.WEST) {
			translate(-1, 0);
		}else if(dir == Direction.EAST){
			translate(1, 0);		
		}else {
			System.out.println("Unhandeled direction");
		}
	}
	
	
	public void renderRectangle(Rectangle body) {
		renderRectangle(body, Color.PINK, 0, 0);
	}
	public void renderFixedRectangle(Rectangle body, Color col, float x, float y) {
		renderRectangle(body, col, x + bounds.x, bounds.y);
	}
	public void renderRectangle(Rectangle body, Color color) {
		renderRectangle(body, color, 0, 0);
	}
	public void renderRectangle(Rectangle body, Color color, float x, float y) {
		Vector2 vector = body.getSize(new Vector2());
		Texture texture = rectangles.get(vector);
		if(texture == null) {
			Pixmap pix = new Pixmap((int) body.width, (int) body.height, Pixmap.Format.RGBA8888);
			pix.setColor(color);
			pix.drawRectangle(0, 0, (int) body.width, (int) body.height);
			texture = new Texture(pix);
			pix.dispose();			
			rectangles.put(vector, texture);
		}
		sb.draw(texture, body.x + x, body.y + y);
	}
	public void renderFixedString(String string, float x, float y) {
		font.draw(sb, string, x + bounds.x, y + bounds.y);
	}
	public void renderString(Color col, String string, float x, float y) {
		Color previous = sb.getColor();
		sb.setColor(col.r, col.g, col.b, col.a);
		font.draw(sb, string, x, y);
		sb.setColor(previous.r, previous.g, previous.b, previous.a);
	}
	public void renderFixedString(Color col, String string, float x, float y) {
		renderString(col, string, x + bounds.x, y + bounds.y);
	}
	public void renderFixedString(BitmapFont font, String string, float x, float y) {
		font.draw(sb, string, x + bounds.x, y + bounds.y);
	}
	public void render(Texture texture, Color color, float x, float y) {
		sb.setColor(color);
		sb.draw(texture, x, y);
		sb.setColor(Color.WHITE);
	}
	public void render(Texture texture, float x, float y) {
		sb.draw(texture, x, y);
	}
	public void render(TextureRegion texture, float x, float y) {
		sb.draw(texture, x, y);
	}
	public void renderFixed(TextureRegion texture, float x, float y) {
		sb.draw(texture, x + bounds.x, y + bounds.y);
	}
	public void render(Texture texture, float x, float y, float w, float h) {
		sb.draw(texture, x, y, w, h);
	}
	public void renderFixed(TextureRegion texture, float x, float y, float w, float h) {
		sb.draw(texture, x + bounds.x, bounds.y + y, w, h);
	}
	public void render(Texture texture, Rectangle body) {
		sb.draw(texture, body.x, body.y, body.width, body.height);
	}
	public void render(TextureRegion texture, Rectangle body) {
		sb.draw(texture, body.x, body.y, body.width, body.height);
	}
	public void renderFixed(Texture texture, Rectangle body) {
		sb.draw(texture, bounds.x + body.x, bounds.y + body.y, body.width, body.height);
	}
	public void renderFixed(TextureRegion texture, Rectangle body) {
		sb.draw(texture, bounds.x + body.x, bounds.y + body.y, body.width, body.height);
	}
	public void renderFixed(Texture texture, float x, float y, float w, float h) {
		sb.draw(texture, x + bounds.x, y + bounds.y, w, h);
	}
	public void renderFixed(Texture texture, float x, float y, float scale) {
//		TODO: verify this draw call as accurate
		sb.draw(texture, x + bounds.x, bounds.y + y, texture.getWidth() * scale, texture.getHeight() * scale);
	}
	public void renderFixed(TextureRegion texture, float x, float y, float scale) {
//		TODO: verify this draw call as accurate
		sb.draw(texture, x, y, texture.getRegionWidth() * scale, texture.getRegionHeight() * scale);
	}
	Texture pixel;
	public void renderPixel(float x, float y) {
		sb.draw(pixel, x, y);
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public void dispose() {
		sb.dispose();
//		sr.dispose();
	}
}
