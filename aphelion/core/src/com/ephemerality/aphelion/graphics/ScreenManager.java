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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.ephemerality.aphelion.util.Direction;

public class ScreenManager {
	
	SpriteBatch sb;
	ShapeRenderer sr;
	OrthographicCamera oc;
	Rectangle bounds;
	Color color;
	BitmapFont font;
	int FONT_SIZE = 12;
	
	
	public ScreenManager() {
		sb = new SpriteBatch();
		sr = new ShapeRenderer();
		oc = new OrthographicCamera();
		bounds = new Rectangle();
		resize();
		color = new Color(0f, 0f, 0f, 1f);
		
		
		rectangles = new HashMap<>();
		center = new Rectangle(Gdx.graphics.getWidth() / 2 - 1, Gdx.graphics.getHeight() / 2 - 1, 4f, 4f);

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
	
	public void resize() {
		oc.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		oc.viewportHeight = Gdx.graphics.getWidth();
		oc.viewportWidth = Gdx.graphics.getHeight();
		bounds.setWidth(Gdx.graphics.getWidth());
		bounds.setHeight(Gdx.graphics.getHeight());
		update();
	}
	public void resize(int width, int height) {
		oc.viewportHeight = height;
		oc.viewportWidth = width;
		oc.setToOrtho(false, width, height);
		bounds.setWidth(width);
		bounds.setHeight(height);
		update();
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
	public void start() {
		Gdx.gl20.glClearColor(color.r, color.b, color.g, color.a);
		Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		Gdx.gl20.glEnable(GL20.GL_BLEND);
		sb.enableBlending();
		sb.begin();
	}
	//Debug
	static Rectangle center;
	public void finish() {
		renderRectangle(center, Color.RED, bounds.x, bounds.y);
		Rectangle scissors = new Rectangle();
		ScissorStack.calculateScissors(oc, sb.getTransformMatrix(), bounds, scissors);
		ScissorStack.pushScissors(scissors);
		sb.end();
	}
	
	public void update() {
		oc.update();
		sr.setProjectionMatrix(oc.combined);
		sb.setProjectionMatrix(oc.combined);
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
	
	
	HashMap<Vector2, Texture> rectangles;
	public void renderRectangle(Rectangle body) {
		renderRectangle(body, Color.PINK, 0, 0);
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
	public void renderString(Color col, String string, float x, float y) {
		font.setColor(col);
		font.draw(sb, string, x, y);
	}
	public void renderFixedString(Color col, String string, float x, float y) {
		font.setColor(col);
		font.draw(sb, string, x + bounds.x, y + bounds.y);
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
		sr.dispose();
	}
	public Rectangle getBounds() {
		return bounds;
	}
	public SpriteBatch getSpriteBatch() {
		return sb;
	}
	
	public ShapeRenderer getShapeRenderer() {
		return sr;
	}
	
	public OrthographicCamera getOrthographicCamera() {
		return oc;
	}
}
