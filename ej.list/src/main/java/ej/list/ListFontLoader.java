/*
 * Java
 *
 * Copyright 2015 IS2T. All rights reserved.
 * IS2T PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package ej.list;

import ej.microui.display.Font;
import ej.style.font.FontProfile;
import ej.style.font.FontProfile.FontSize;
import ej.style.font.loader.FontLoader;

public class ListFontLoader implements FontLoader {

	@Override
	public Font getFont(FontProfile fontProfile) {
		FontSize size = fontProfile.getSize();
		switch (size) {
		case SMALL:
			// return Font.getFont(Font.LATIN, 20, Font.STYLE_PLAIN);
			return Font.getDefaultFont();

		case MEDIUM:
		default:
			return Font.getFont(Font.LATIN, 20, Font.STYLE_PLAIN);
		}
	}
}
