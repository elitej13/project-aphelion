package com.ephemerality.aphelion.editor.framework.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ephemerality.aphelion.graphics.SpriteSheet;
import com.ephemerality.aphelion.spawn.entities.nob.Environment;
import com.ephemerality.aphelion.spawn.entities.nob.Tile;
import com.kotcrab.vis.ui.util.TableUtils;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisTree;
import com.kotcrab.vis.ui.widget.VisWindow;

public class TilePane extends VisWindow{
	
	VisTree visTree;
	
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
		visTree = new VisTree();
		
		Node tiles = new Node(new VisLabel("Tiles"));
		Node entities = new Node(new VisLabel("Entities"));
		Node items = new Node(new VisLabel("Items"));
		
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
		
		Node sand = new Node(new VisLabel("Sand 0"));
		sand.setIcon((new TextureRegionDrawable(SpriteSheet.default_sand_0)));
		sand.setObject(new Short(Tile.SAND_ID));
		
		Node water = new Node(new VisLabel("Water 0"));
		water.setIcon((new TextureRegionDrawable(SpriteSheet.default_water_0)));
		water.setObject(new Short(Tile.WATER_ID));
		
		Node gravel = new Node(new VisLabel("Gravel 0"));
		gravel.setIcon((new TextureRegionDrawable(SpriteSheet.default_gravel_0)));
		gravel.setObject(new Short(Tile.GRAVEL_ID));
		
		tiles.add(grass);
		tiles.add(dirt);
		tiles.add(brick);
		tiles.add(wood);
		tiles.add(sand);
		tiles.add(water);
		tiles.add(gravel);
		
//---------------------------------Environment--------------------------------//
		Node house = new Node(new VisLabel("House"));
		house.setIcon((new TextureRegionDrawable(SpriteSheet.default_house)));
		house.setObject(new Short(Environment.House.ID));
		
		Node fence = new Node(new VisLabel("Fence"));
		fence.setIcon((new TextureRegionDrawable(SpriteSheet.default_fence)));
		fence.setObject(new Short(Environment.Fence.ID));
		
		Node gate = new Node(new VisLabel("Gate"));
		gate.setIcon((new TextureRegionDrawable(SpriteSheet.default_gate)));
		gate.setObject(new Short(Environment.Gate.ID));
		
		Node tree = new Node(new VisLabel("Tree"));
		tree.setIcon((new TextureRegionDrawable(SpriteSheet.default_tree)));
		tree.setObject(new Short(Environment.Tree.ID));
		
		Node bridge = new Node(new VisLabel("Bridge"));
		bridge.setIcon((new TextureRegionDrawable(SpriteSheet.default_bridge)));
		bridge.setObject(new Short(Environment.Bridge.ID));
		
		entities.add(house);
		entities.add(fence);
		entities.add(gate);
		entities.add(tree);
		entities.add(bridge);

		items.add(new Node(new VisLabel("item 3.1")));
		items.add(new Node(new VisLabel("item 3.2")));
		items.add(new Node(new VisLabel("item 3.3")));


		visTree.add(tiles);
		visTree.add(entities);
		visTree.add(items);

		add(visTree).expand().fill();
	}
	
	
	
	/**
	 * Returns the ID of the selected entity
	 * @return short
	 */
	public short getSelected() {
		Node node = visTree.getSelection().getLastSelected();
		if(node == null)
			return Tile.VOID_ID;
		if(node.getObject() instanceof Short) {
			Short ID = (Short) node.getObject();
			return ID.shortValue();
		}
		return Tile.VOID_ID;
	}
	
	
	
	
	
}
