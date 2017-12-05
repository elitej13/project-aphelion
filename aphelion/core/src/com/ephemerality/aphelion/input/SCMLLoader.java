package com.ephemerality.aphelion.input;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetLoaderParameters;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.brashmonkey.spriter.SCMLReader;
import com.brashmonkey.spriter.LibGdx.LibGdxLoader;
import com.ephemerality.aphelion.spawn.puppets.Doll;

/** {@link AssetLoader} for {@link Doll} instances. The Doll is loaded asynchronously.
 * @author elitej13 */
public class SCMLLoader extends AsynchronousAssetLoader<DollInfo, SCMLLoader.DollParameter> {
	public SCMLLoader(FileHandleResolver resolver) {
		super(resolver);
	}
		
	DollInfo info;
	
	
	@Override
	public void loadAsync(AssetManager manager, String fileName, FileHandle file, DollParameter parameter) {
		info = new DollInfo();
		info.data = new SCMLReader(file.read()).getData();
		info.loader = new LibGdxLoader(info.data);
		info.batch = parameter.batch;
	}

	@Override
	public DollInfo loadSync(AssetManager manager, String fileName, FileHandle file, DollParameter parameter) {
		DollInfo info = this.info;
		info.loader.load(file.file());
		info.renderer = new ShapeRenderer();
		this.info = null;
		return info;
	}
	

	@Override
	@SuppressWarnings("rawtypes")
	public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, DollParameter parameter) {
		return null;
	}

	public static class DollParameter extends AssetLoaderParameters<DollInfo> {
		public SpriteBatch batch;
	}
}


