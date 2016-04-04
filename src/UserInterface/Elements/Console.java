package UserInterface.Elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
 * Dynamic Console Composite
 */
public class Console extends ScrolledComposite {
	// Tables
	private Table consoleMessageTable;

	/**
	 * Create3 a console Composite
	 * @param arg0 #ScrolledComposite
	 * @param arg1 #ScrolledComposite
	 * @param bg Consoles background color
	 * @param fg Consoles foreground color
	 */
	public Console(Composite arg0, int arg1, Color bg, Color fg) {
		super(arg0, arg1);
		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
		consoleMessageTable = new Table(this, SWT.FULL_SELECTION);
		setMinSize(consoleMessageTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		consoleMessageTable.setBackground(bg);
		consoleMessageTable.setForeground(fg);
		// Opening message
		TableItem openingMessage = new TableItem(consoleMessageTable, SWT.NONE);
		openingMessage.setText(new String[] { "Hello, and Welcome to Grid." });
		setContent(consoleMessageTable);
	}

	/**
	 * Add a new message to the UI console
	 * 
	 * @param message,
	 *            is a string to be appended to the console
	 */
	public void addToConsole(String message, boolean isError) {
		TableItem t = new TableItem(consoleMessageTable, SWT.NONE);
		t.setForeground((isError) ? ColorPalette.CUSTOM_RED : ColorPalette.CUSTOM_BLACK);
		t.setText(new String[] { message });
		layout();
		//Auto Scroll the Table
		consoleMessageTable.setTopIndex(consoleMessageTable.getItemCount() - 1);
		this.getVerticalBar().setIncrement(-4);
	}

	/**
	 * Clear the on screen UI console
	 */
	public void clearConsole() {
		// consoleMessageTabel.clearAll();
		consoleMessageTable.removeAll();
	}
}
