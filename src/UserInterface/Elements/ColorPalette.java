package UserInterface.Elements;

import java.lang.reflect.Field;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

/**
 * Class to hold Colors for the program. Do not instantiate, only reference.
 * 
 * @author riley_000
 */
public class ColorPalette {

	private static Display display;
	// Colors
	public final static Color CUSTOM_BLACK 	= new Color(display, 0x2C, 0x3E, 0x50);
	public final static Color CUSTOM_WHITE 	= new Color(display, 0xFA, 0xFA, 0xFF);
	public final static Color CUSTOM_PURPLE = new Color(display, 0x9B, 0x59, 0xB6);
	public final static Color CUSTOM_GREEN 	= new Color(display, 0x2E, 0xCC, 0x71);
	public final static Color CUSTOM_BLUE 	= new Color(display, 0x34, 0x98, 0xDB);
	public final static Color CUSTOM_ORANGE = new Color(display, 0xE6, 0x7E, 0x22);
	public final static Color CUSTOM_YELLOW = new Color(display, 0xF1, 0xC4, 0x0F);
	public final static Color CUSTOM_RED 	= new Color(display, 0xE7, 0x4C, 0x3C);
	
	private static Color[] palette = { CUSTOM_BLACK, CUSTOM_BLUE, CUSTOM_WHITE, CUSTOM_GREEN, CUSTOM_ORANGE,
			CUSTOM_PURPLE, CUSTOM_RED, CUSTOM_YELLOW };
	
	public static Color[] plotPalette = { CUSTOM_BLACK, CUSTOM_BLUE, CUSTOM_GREEN, CUSTOM_ORANGE,
			CUSTOM_PURPLE, CUSTOM_RED, CUSTOM_YELLOW };

	/**
	 * Set the display for which the color elements are held.
	 * @param d Top level display of program. MUST be called prior to use of colors.
	 */
	public static void setDisplay(Display d) {
		display = d;
	}

	/**
	 * To be called when program is closed. Free memory, preventing leaks.
	 */
	public static void disposeColors() {
		for (Color c : palette)
			c.dispose();
	}
}
