package Controller;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import UserInterface.ApplicationView;

public class Controller {

	private ApplicationView view;
	private Shell parentShell;
	private Display display;

	public Controller(ApplicationView v) {
		view = v;
		view.open();
		// initViewEventHandeling();

		try {
			display = view.getDisplay();
			parentShell = view.getParentShell();
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//TODO Console stuff here
		System.out.println("PRINT TO CONSOLE \"Gathering Assets and Loading the Program...\"");
		Parser.DataSetParser.parseWindDataSet();
		System.out.println("PRINT TO CONSOLE \"Program Loaded.\"");
		
	}

	/**
	 * Main Program run loop. Program closes when the display is disposed
	 */
	public void Run() {
		while (!parentShell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		// Free Memory
		display.dispose();
	}
	
}
