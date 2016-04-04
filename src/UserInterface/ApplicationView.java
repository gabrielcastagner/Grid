package UserInterface;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import Constants.FilePaths;
import UserInterface.Elements.ColorPalette;
import UserInterface.Elements.Table.SolarTableComposite;

/**
 * View instance for program
 * Top level UI
 */
public class ApplicationView {
	// ================View Variables needed by Controller=================//
	// Main Display
	private Shell parentShell;
	private Display display;
	private int shellWidth, shellHeight;
	private Image backgroundMountains;

	// Composites
	private final static StackLayout layout = new StackLayout();
	private static PrimaryComposite primaryComposite;

	// Menu Bar
	private MenuItem menuHelpItemProgramHelp;
	private MenuItem menuHelpItemGeneralHelp;

	// FileBrowser
	private FileDialog fileBrowser;

	/**
	 * Open the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		
		InputStream iconInputStream = SolarTableComposite.class.getResourceAsStream(FilePaths.GRID_ICON_PATH);


		
		display = Display.getDefault();
		ColorPalette.setDisplay(display);
		createMainContents();
		parentShell.open();
		parentShell.layout();
		
		Image gridIcon = new Image(parentShell.getDisplay(), iconInputStream);
		
		parentShell.setImage(gridIcon);
		
	}

	/**
	 * Create contents of the window.
	 */
	protected void createMainContents() {
		parentShell = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN | SWT.MAX);
		parentShell.setToolTipText("");
		//parentShell.setSize(1080, 720);
		
		InputStream backgroundInputSteam = SolarTableComposite.class.getResourceAsStream(FilePaths.BACKGROUND_PATH);
		
		Image bgIcon = new Image(getDisplay(), backgroundInputSteam);
		ImageData bgIconData = bgIcon.getImageData();
		bgIconData = bgIconData.scaledTo(this.getDisplay().getBounds().width, this.getDisplay().getBounds().height);
		
		backgroundMountains = new Image(display, bgIconData);
		
		shellWidth = display.getClientArea().width;
		shellHeight = display.getClientArea().height;
		parentShell.setLocation(0,0);
		parentShell.setSize(shellWidth, shellHeight);
		parentShell.setMaximized(true);
		parentShell.setText("-Grid-");
		parentShell.setLayout(null);
		Menu menu = new Menu(parentShell, SWT.BAR);
		parentShell.setMenuBar(menu);

		final Composite parent = new Composite(parentShell, SWT.NONE);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parent.setLayout(layout);
		parent.setBackgroundImage(backgroundMountains);

		primaryComposite = new PrimaryComposite(parentShell, SWT.NONE);
		primaryComposite.setBackgroundImage(backgroundMountains);
		primaryComposite.setBackgroundMode(SWT.INHERIT_FORCE);

		MenuItem mntmHelp = new MenuItem(menu, SWT.CASCADE);
		mntmHelp.setText("Help");

		Menu menu_help = new Menu(mntmHelp);
		menu_help.setLocation(new Point(0, 0));
		mntmHelp.setMenu(menu_help);

		menuHelpItemProgramHelp = new MenuItem(menu_help, SWT.NONE);
		menuHelpItemProgramHelp.setText("Program Help");

		menuHelpItemGeneralHelp = new MenuItem(menu_help, SWT.NONE);
		menuHelpItemGeneralHelp.setText("General Help");

	}

	// ======================File Browser======================== //
	/**
	 * Opens a file browser window to open a gear train
	 * 
	 * @return String of path for file chosen
	 */
	public String openFileBrowserForOpen() {
		fileBrowser = new FileDialog(parentShell, SWT.OPEN);
		fileBrowser.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
		fileBrowser.setFilterExtensions(new String[] { "*.xml", "*.*" }); // Windows

		fileBrowser.setFilterPath("c:\\"); // Windows path
		return fileBrowser.open();
	}

	/**
	 * Opens file bowser window for saving a gear train
	 * 
	 * @return String of path for file save name and location chosen
	 */
	public String openFileBrowserForSave() {
		fileBrowser = new FileDialog(parentShell, SWT.SAVE);
		fileBrowser.setFilterNames(new String[] { "XML Files", "All Files (*.*)" });
		fileBrowser.setFilterExtensions(new String[] { "*.xml", "*.*" }); // Windows
		fileBrowser.setFilterPath("c:\\"); // Windows path
		return fileBrowser.open();
	}

	// ========================GETTERS===========================//

	// =====Getters for objects required outside the scope of this class===== //
	/**
	 * @return the Display window
	 */
	public Display getDisplay() {
		return display;
	}

	/**
	 * @return the views parent shell
	 */
	public Shell getParentShell() {
		return parentShell;
	}

	/**
	 * @return Program help option in the menu bar under help
	 */
	public MenuItem getMenuHelpItemProgramHelp() {
		return menuHelpItemProgramHelp;
	}

	/**
	 * @return General help option in the menu bar under help
	 */
	public MenuItem getMenuHelpItemGeneralHelp() {
		return menuHelpItemGeneralHelp;
	}

	/**
	 * @return Primary composite for parent shell
	 */
	public PrimaryComposite getPrimaryComposite(){
		return primaryComposite;
	}
	
	/**
	 * @return Modular composite selector
	 */
	public enum SelectComposite {
		PRIMARY_COMPOSITE(primaryComposite);

		private Composite c;

		/**
		 * Composite instance to select
		 * @param composite instance of Composite
		 */
		private SelectComposite(Composite composite) {
			c = composite;
		}

		/**
		 * Select top level composite
		 */
		public void setCurrent() {
			layout.topControl = c;
		}
	}

}
