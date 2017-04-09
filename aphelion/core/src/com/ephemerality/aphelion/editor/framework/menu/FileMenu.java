package com.ephemerality.aphelion.editor.framework.menu;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.ephemerality.aphelion.spawn.world.MapManager;
import com.kotcrab.vis.ui.util.dialog.Dialogs;
import com.kotcrab.vis.ui.util.dialog.Dialogs.OptionDialogType;
import com.kotcrab.vis.ui.util.dialog.InputDialogAdapter;
import com.kotcrab.vis.ui.util.dialog.OptionDialogAdapter;
import com.kotcrab.vis.ui.widget.Menu;
import com.kotcrab.vis.ui.widget.MenuItem;
import com.kotcrab.vis.ui.widget.file.FileChooser;
import com.kotcrab.vis.ui.widget.file.FileChooser.Mode;
import com.kotcrab.vis.ui.widget.file.FileChooser.SelectionMode;
import com.kotcrab.vis.ui.widget.file.FileChooserAdapter;
import com.kotcrab.vis.ui.widget.file.FileTypeFilter;

public class FileMenu extends ChangeListener {
	
	MapManager map;
	Menu menu;
	MenuItem newFile, saveAs, save, load, exit;
	FileChooser saver, loader;
	String currentFileName;
	
	public FileMenu(MapManager map) {
		this.map = map;
		menu = new Menu("File");
		newFile = new MenuItem("New", this).setShortcut("f1");
		saveAs = new MenuItem("Save As", this).setShortcut("ctrl + shift + s");
		save = new MenuItem("Save", this).setShortcut("ctrl + s");
		load = new MenuItem("Load", this).setShortcut("f2");
		exit = new MenuItem("Exit", this).setShortcut("alt + f4");
		
		menu.addItem(newFile);
		menu.addItem(saveAs);
		menu.addItem(save);
		menu.addItem(load);
		menu.addItem(exit);
		
		FileChooser.setSaveLastDirectory(true);
		FileChooser.setDefaultPrefsName("com.ephemerality.aphelion.filechooser");
		
		FileTypeFilter typeFilter = new FileTypeFilter(false);
		typeFilter.addRule("Binary files (*.bin)", "bin");
		saver = new FileChooser(Mode.SAVE);
		saver.setFileTypeFilter(typeFilter);
		saver.setMultiSelectionEnabled(false);
		saver.setSelectionMode(SelectionMode.FILES);
		saver.setListener(new FileChooserAdapter() {
			@Override
			public void selected (Array<FileHandle> files) {
				currentFileName = files.get(0).file().getAbsolutePath();
				map.save(currentFileName);
			}
		});
		
		loader = new FileChooser(Mode.OPEN);
		loader.setFileTypeFilter(typeFilter);
		loader.setMultiSelectionEnabled(false);
		loader.setSelectionMode(SelectionMode.FILES);
		loader.setListener(new FileChooserAdapter() {
			@Override
			public void selected (Array<FileHandle> files) {
				currentFileName = files.get(0).file().getAbsolutePath();
				map.load(currentFileName);
			}
		});
		
//		
	}


	@Override
	public void changed(ChangeEvent event, Actor actor) {
//		System.out.println("Cancelled: " + event.isCancelled());
//		System.out.println("Capture: " + event.isCapture());
//		System.out.println("Handled: " + event.isHandled());
//		System.out.println("Stopped: " + event.isStopped());
		if(actor.equals(newFile)) {
			newFileHandler(actor);
		}
		if(actor.equals(saveAs)) {
			actor.getStage().addActor(saver.fadeIn(0f));
		}
		if(actor.equals(save)) {
			System.out.println(currentFileName);
			if(currentFileName == null || !currentFileName.contains("/"))
				actor.getStage().addActor(saver.fadeIn(0f));
			else {
				map.save(currentFileName);
			}
		}
		if(actor.equals(load)) {
			actor.getStage().addActor(loader.fadeIn(0f));
		}
		if(actor.equals(exit)) {
			
		}
	}
	
	
	
	public void newFileHandler(Actor actor) {
		Dialogs.showInputDialog(actor.getStage(), "New File Name", "", new StringValidator(), new InputDialogAdapter() {
			@Override
			public void finished (String input) {
				if(currentFileName != null) {
					Dialogs.showOptionDialog(actor.getStage(), "Save?", "Would you like to save the current level?", OptionDialogType.YES_NO_CANCEL, new OptionDialogAdapter() {
						@Override
						public void yes() {
							map.save(currentFileName);
							currentFileName = input;
							map.createNewLevel(12, 12);
						}
						@Override
						public void no() {
							currentFileName = input;
							map.createNewLevel(12, 12);
						}
					});
					
				}else {
					currentFileName = input;
					map.createNewLevel(12, 12);
					
				}
			}
		});
	}
	
}
