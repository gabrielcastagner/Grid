package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class PrimaryComposite extends Composite {

	private Button buttonAdd;
	private Button buttonRemove;
	private Button buttonAnalyze;
	private Console consoleScrolledComposite;
	private Console dataDisplay;
	private TableList inputData;

	private final String[] powerOption = { "Solar", "Wind" };
	private Combo comboPowerOptions;
	private Combo[] comboBoxes;

	private Composite currentSubComposite = new Composite(this, SWT.None);
	private Composite[] subComposites = { new SolarSubComposite(currentSubComposite, SWT.None),
			new WindSubComposite(currentSubComposite, SWT.None) };

	final StackLayout layout = new StackLayout();

	public PrimaryComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		setElementsToComposite();
		setBackground(ColorPalette.CUSTOM_BLACK);
		setForeground(ColorPalette.CUSTOM_BLUE);
		setLayout(null);
		setBounds(0, 0, 1080, 720);
	}

	private void setElementsToComposite() {
		// Console For interacting with user.
		consoleScrolledComposite = new Console(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL,
				ColorPalette.CUSTOM_WHITE, ColorPalette.CUSTOM_BLACK);
		consoleScrolledComposite.setBounds(25, 474, 1024, 150);

		// Temporary Console For Displaying Data
		dataDisplay = new Console(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		dataDisplay.setBounds(560, 50, 480, 350);
		dataDisplay.clearConsole();
		dataDisplay.addToConsole("Temporary Data Display Console");

		inputData = new TableList(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		inputData.setBounds(25, 50, 480, 200);

		currentSubComposite.setLayout(layout);
		currentSubComposite.setBackground(ColorPalette.CUSTOM_BLACK);
		currentSubComposite.setForeground(ColorPalette.CUSTOM_BLUE);
		currentSubComposite.setBounds(25, 325, 300, 150);

		Label lblPType = new Label(this, SWT.NONE);
		lblPType.setSize(163, 33);
		lblPType.setLocation(25, 275);
		lblPType.setText("Power Type:");
		lblPType.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblPType.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPType.setBackground(ColorPalette.CUSTOM_BLACK);
		lblPType.setForeground(ColorPalette.CUSTOM_BLUE);

		buttonAdd = new Button(this, SWT.NONE);
		buttonAdd.setBounds(340, 325, 166, 25);
		buttonAdd.setText("Add Power Source");
		buttonAdd.addListener(SWT.Selection, event -> {
			consoleScrolledComposite
					.addToConsole(powerOption[comboPowerOptions.getSelectionIndex()] + " Power Source has been added");
		});

		buttonRemove = new Button(this, SWT.NONE);
		buttonRemove.setBounds(340, 365, 166, 25);
		buttonRemove.setText("Remove Power Source");
		buttonRemove.addListener(SWT.Selection, event -> {
			consoleScrolledComposite.addToConsole("Power Source has been Removed");
		});

		buttonAnalyze = new Button(this, SWT.NONE);
		buttonAnalyze.setBounds(340, 405, 166, 25);
		buttonAnalyze.setText("Analyze");
		buttonAnalyze.addListener(SWT.Selection, event -> {
			dataDisplay.addToConsole("Data Being Analyzed...");
		});

		comboPowerOptions = new Combo(this, SWT.NONE);
		comboPowerOptions.setItems(powerOption);
		comboPowerOptions.setBounds(189, 275, 94, 30);
		comboPowerOptions.select(0);
		setSubComposit();
		comboPowerOptions.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				setSubComposit();
			}
		});

		comboBoxes = new Combo[] { comboPowerOptions };
	}

	// =====================Behavioral Code====================== //
	public void refreshView() {
		for (Combo c : comboBoxes)
			c.select(0);
		consoleScrolledComposite.clearConsole();
	}

	public void setSubComposit() {
		layout.topControl = subComposites[comboPowerOptions.getSelectionIndex()];
		currentSubComposite.layout();
	}

}
