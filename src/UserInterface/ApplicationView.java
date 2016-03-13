package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

public class ApplicationView {
	// ================View Variables needed by Controller=================//
	// Main Display
	private Shell parentShell;
	private Display display;

	// Composites
	private final static StackLayout layout = new StackLayout();
	private static Composite primaryComposite;

	// Menu Bar
	private MenuItem menuFileItemNew;
	private MenuItem menuFileItemOpen;
	private MenuItem menuFileItemSave;
	private MenuItem menuFileItemSaveAs;

	private MenuItem menuHelpItemProgramHelp;
	private MenuItem menuHelpItemGeneralHelp;

	private MenuItem menuModeAdd;
	private MenuItem menuModeAnalysis;

	// FileBrowser
	private FileDialog fileBrowser;

	/**
	 * Open the window.
	 * 
	 * @wbp.parser.entryPoint
	 */
	public void open() {
		display = Display.getDefault();
		ColorPalette.setDisplay(display);
		createMainContents();
		parentShell.open();
		parentShell.layout();
	}

	/**
	 * Create contents of the window.
	 */
	protected void createMainContents() {
		parentShell = new Shell(SWT.CLOSE | SWT.TITLE | SWT.MIN);
		parentShell.setToolTipText("");
		parentShell.setSize(1080, 720);
		parentShell.setText("-Grid-");
		parentShell.setBackground(ColorPalette.CUSTOM_BLACK);
		parentShell.setLayout(null);
		Menu menu = new Menu(parentShell, SWT.BAR);
		parentShell.setMenuBar(menu);

		final Composite parent = new Composite(parentShell, SWT.NONE);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		parent.setLayout(layout);

		// primaryComposite = new PrimaryComposite(parentShell, SWT.NONE);
		Composite[] compositeScreens = { new PrimaryComposite(parentShell, SWT.NONE), new GraphComposite(parentShell, SWT.NONE) };
		
		MenuItem mntmFile = new MenuItem(menu, SWT.CASCADE);
		mntmFile.setText("File");

		Menu menu_file = new Menu(mntmFile);
		mntmFile.setMenu(menu_file);

		menuFileItemNew = new MenuItem(menu_file, SWT.NONE);
		menuFileItemNew.setText("New");

		menuFileItemOpen = new MenuItem(menu_file, SWT.NONE);
		menuFileItemOpen.setText("Open File...");

		MenuItem menuFileLineSpeorator1 = new MenuItem(menu_file, SWT.SEPARATOR);
		menuFileLineSpeorator1.setText("menu.file.separator");

		menuFileItemSave = new MenuItem(menu_file, SWT.NONE);
		menuFileItemSave.setText("Save");

		menuFileItemSaveAs = new MenuItem(menu_file, SWT.NONE);
		menuFileItemSaveAs.setText("Save As...");

		MenuItem mntmMode = new MenuItem(menu, SWT.CASCADE);
		mntmMode.setText("Modes");

		Menu menu_mode = new Menu(mntmMode);
		menu_mode.setLocation(new Point(0, 0));
		mntmMode.setMenu(menu_mode);

		menuModeAdd = new MenuItem(menu_mode, SWT.NONE);
		menuModeAdd.setText("Add Item");
		menuModeAdd.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				primaryComposite = compositeScreens[0];
				SelectComposite.PRIMARY_COMPOSITE.setCurrent();
				System.out.println("In Add Mode");
			}
		});

		menuModeAnalysis = new MenuItem(menu_mode, SWT.NONE);
		menuModeAnalysis.setText("Analyze");
		menuModeAnalysis.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				primaryComposite = compositeScreens[1];
				SelectComposite.PRIMARY_COMPOSITE.setCurrent();
				System.out.println("In Analysis Mode");
			}
		});

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
	public Display getDisplay() {
		return display;
	}

	public Shell getParentShell() {
		return parentShell;
	}

	public MenuItem getMenuFileItemNew() {
		return menuFileItemNew;
	}

	public MenuItem getMenuFileItemOpen() {
		return menuFileItemOpen;
	}

	public MenuItem getMenuFileItemSave() {
		return menuFileItemSave;
	}

	public MenuItem getMenuFileItemSaveAs() {
		return menuFileItemSaveAs;
	}

	public MenuItem getMenuHelpItemProgramHelp() {
		return menuHelpItemProgramHelp;
	}

	public MenuItem getMenuHelpItemGeneralHelp() {
		return menuHelpItemGeneralHelp;
	}

	public enum SelectComposite {
		PRIMARY_COMPOSITE(primaryComposite);

		private Composite c;

		private SelectComposite(Composite composite) {
			c = composite;
		}

		public void setCurrent() {
			layout.topControl = c;
		}
	}

}
