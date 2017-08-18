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
import com.ephemerality.aphelion.util.Util;

public class ScreenManager {
	
	HashMap<Vector2, Texture> rectangles;
	final Color DEFAULT_COLOR, TINT_COLOR;
	public static BitmapFont font;
	public SpriteBatch sb;
	public OrthographicCamera oc;
	public Rectangle bounds;
	boolean batchHasBegun;
	int FONT_SIZE = 16;
	Color color;
	
	public static final float MIN_ZOOM = 0.5f;
	public static final float MAX_ZOOM = 4f;
	
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
		
		
		DEFAULT_COLOR = new Color(sb.getColor());
		TINT_COLOR = new Color(0.2f, 0.2f, 0.2f, 1f);
	}
	public void update() {
		oc.update();
		sb.setProjectionMatrix(oc.combined);
	}
		
	/**
	 * @param body Body to center screen around
	 */
	public void resize(Rectangle body) {
		int w = (int) (Gdx.graphics.getWidth() * oc.zoom);
		int h = (int) (Gdx.graphics.getHeight() * oc.zoom);
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
		int w = (int) (Gdx.graphics.getWidth() * oc.zoom);
		int h = (int) (Gdx.graphics.getHeight() * oc.zoom);
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
	public void zoom(int amount) {
		float oldZoom = oc.zoom;
		oc.zoom += amount * 0.1f;
		oc.zoom = (float) (Math.floor(oc.zoom * 10f) / 10f);
		oc.zoom = Util.clamp(oc.zoom, MIN_ZOOM, MAX_ZOOM);
		float dw = (oc.viewportWidth * oc.zoom) - (Gdx.graphics.getWidth() * oldZoom);
		float dh = (oc.viewportHeight * oc.zoom) - (Gdx.graphics.getHeight() * oldZoom);
		bounds.x = bounds.x - (dw / 2f);
		bounds.y = bounds.y - (dh / 2f);
		bounds.width = Gdx.graphics.getWidth() * oc.zoom;
		bounds.height = Gdx.graphics.getHeight() * oc.zoom;
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/inconsolata/Inconsolata-Bold.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = (int) (FONT_SIZE * oc.zoom);
		parameter.color = new Color(0, 0, 0, 1.0f);
		font = generator.generateFont(parameter);
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
		}
	}
	
	
	public void renderRectangle(Rectangle body) {
		renderRectangle(body, Color.PINK, 0, 0);
	}
	public void renderFixedRectangle(Rectangle body, Color col, float x, float y) {
		renderRectangle(body, col, (x * oc.zoom) + bounds.x, (y * oc.zoom) + bounds.y);
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
		font.draw(sb, string, (x * oc.zoom) + bounds.x, (y * oc.zoom) + bounds.y);
	}
	public void renderString(Color col, String string, float x, float y) {
		Color previous = sb.getColor();
		sb.setColor(col.r, col.g, col.b, col.a);
		font.draw(sb, string, x, y);
		sb.setColor(previous.r, previous.g, previous.b, previous.a);
	}
	public void renderFixedString(Color col, String string, float x, float y) {
		renderString(col, string, (x * oc.zoom) + bounds.x, (y * oc.zoom) + bounds.y);
	}
	public void renderFixedString(BitmapFont font, String string, float x, float y) {
		font.draw(sb, string, (x * oc.zoom) + bounds.x, (y * oc.zoom) + bounds.y);
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
	public void render(Texture texture, Rectangle body) {
		sb.draw(texture, body.x, body.y, body.width, body.height);
	}
	public void render(TextureRegion texture, Rectangle body) {
		sb.draw(texture, body.x, body.y, body.width, body.height);
		
	}
	public void renderFixed(Texture texture, Rectangle body) {
		sb.draw(texture, bounds.x + (body.x * oc.zoom), bounds.y + (body.y * oc.zoom), body.width * oc.zoom, body.height * oc.zoom);	
	}
	public void renderFixed(TextureRegion texture, Rectangle body) {
		sb.draw(texture, bounds.x + (body.x * oc.zoom), bounds.y + (body.y * oc.zoom), body.width * oc.zoom, body.height * oc.zoom);
	}
	public void renderFixed(Texture texture, float x, float y, float w, float h) {
		sb.draw(texture, bounds.x + (x * oc.zoom), bounds.y + (y * oc.zoom), w * oc.zoom, h * oc.zoom);
	}
	public void renderFixed(TextureRegion texture, float x, float y, float w, float h) {
		sb.draw(texture, x + bounds.x, bounds.y + y, w, h);
	}
	public void renderFixed(Texture texture, float x, float y, float scale) {
//		TODO: verify this draw call as accurate
		sb.draw(texture, (x * oc.zoom) + bounds.x, (y * oc.zoom) + bounds.y, texture.getWidth() * scale * oc.zoom, texture.getHeight() * scale * oc.zoom);
	}
	public void renderFixed(TextureRegion texture, float x, float y, float scale) {
//		TODO: verify this draw call as accurate
		sb.draw(texture, (x * oc.zoom) + bounds.x, (y * oc.zoom) + bounds.y, texture.getRegionWidth() * scale * oc.zoom, texture.getRegionHeight() * scale * oc.zoom);
	}
	
	
	
	public void tintRenderer() {
		sb.setColor(TINT_COLOR);
	}
	public void untintRenderer() {
		sb.setColor(DEFAULT_COLOR);
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
