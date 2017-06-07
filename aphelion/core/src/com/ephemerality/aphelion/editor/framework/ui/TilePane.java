package com.ephemerality.aphelion.editor.framework.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.entities.tiles.Tile;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTree;
import com.kotcrab.vis.ui.widget.VisWindow;

public class TilePane extends VisWindow{
	
	VisTree tree;
	
	public TilePane () {
		super("Tile Selection");

		TableUtils.setSpacingDefaults(this);
		columnDefaults(0).left();
		addTiles();

		
		int w = 150;
		int h = Gdx.graphics.getHeight() - 25;
		int x = Gdx.graphics.getWidth() - w;
		int y = Gdx.graphics.getHeight() - h - 25;
		setSize(w, h);
		setPosition(x, y);
	}

	private void addTiles () {
		tree = new VisTree();
		
		Node tiles = new Node(new VisLabel("Tiles"));
		Node items = new Node(new VisLabel("Items"));
		Node entities = new Node(new VisLabel("Entities"));
		
		Node grass = new Node(new VisLabel("Grass 0"));
		grass.setIcon((new TextureRegionDrawable(SpriteSheet.default_grass_0)));
		grass.setObject(new Short(Tile.GRASS_ID));
		Node dirt = new Node(new VisLabel("Dirt 0"));
		dirt.setIcon((new TextureRegionDrawable(SpriteSheet.default_dirt_0)));
		dirt.setObject(new Short(Tile.DIRT_ID));
		Node brick = new Node(new VisLabel("Brick 0"));
		brick.setIcon((new TextureRegionDrawable(SpriteSheet.default_brick_0)));
		brick.setObject(new Short(Tile.BRICK_ID));
		Node wood = new Node(new VisLabel("Wood 0"));
		wood.setIcon((new TextureRegionDrawable(SpriteSheet.default_wood_0)));
		wood.setObject(new Short(Tile.WOOD_ID));
		
		tiles.add(grass);
		tiles.add(dirt);
		tiles.add(brick);
		tiles.add(wood);

		entities.add(new Node(new VisLabel("item 2.1")));
		entities.add(new Node(new VisLabel("item 2.2")));
		entities.add(new Node(new VisLabel("item 2.3")));

		items.add(new Node(new VisLabel("item 3.1")));
		items.add(new Node(new VisLabel("item 3.2")));
		items.add(new Node(new VisLabel("item 3.3")));

		tiles.setExpanded(true);

		tree.add(tiles);
		tree.add(entities);
		tree.add(items);

		add(tree).expand().fill();
	}
	
	
	
	/**
	 * Returns the ID of the selected entity
	 * @return short
	 */
	public short getSelected() {
		Node node = tree.getSelection().getLastSelected();
		if(node == null)
			return Tile.VOID_ID;
		if(node.getObject() instanceof Short) {
			Short ID = (Short) node.getObject();
			return ID.shortValue();
		}
		return Tile.VOID_ID;
	}
	
	
	
	
	
}
