package com.ephemerality.aphelion.editor.framework.menu;

import com.kotcrab.vis.ui.util.InputValidator;

public class StringValidator implements InputValidator{

	@Override
	public boolean validateInput(String input) {
		if(input.length() > 0 && input.length() < 100) {
			if(input.contains(".") || input.contains(",") || input.contains("/") || input.contains("\\") || input.contains(":")) {
				return false;
			}
			return true;
		}
		return false;
	}

}
