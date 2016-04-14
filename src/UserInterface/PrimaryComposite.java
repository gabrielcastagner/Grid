package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.wb.swt.SWTResourceManager;
import org.swtchart.Chart;

import UserInterface.Elements.ColorPalette;
import UserInterface.Elements.Console;
import UserInterface.Elements.SolarSubComposite;
import UserInterface.Elements.WindSubComposite;
import UserInterface.Elements.Graph.DataGraph;
import UserInterface.Elements.Table.OutputTableComposite;
import UserInterface.Elements.Table.SolarTableComposite;
import UserInterface.Elements.Table.WindTableComposite;

public class PrimaryComposite extends Composite {

	// Things used for relative screen sizing.
	private int displayWidth, displayHeight;
	private final int edgePaddingWidth;
	private final int edgePaddingHeight;
	private final int compBuffer = 20;

	private Button buttonAdd;
	private Button buttonAnalyze;
	private Console consoleScrolledComposite;
	private Chart dataDisplay;

	private SolarTableComposite inputData;
	private WindTableComposite windInputData;
	private OutputTableComposite outputData;

	// Tab Folders for holding the tabs
	private TabFolder tabTables;
	private TabFolder tabOutputs;

	private final String[] powerOption = { "Solar", "Wind" };
	private Combo comboPowerOptions;
	private Combo[] comboBoxes;

	private Composite currentSubComposite = new Composite(this, SWT.None);
	private Composite[] subComposites;

	final private StackLayout layout = new StackLayout();

	/**
	 * Primary Composite Constructor for the gui.
	 * 
	 * @param arg0
	 *            parent Composite
	 * @param arg1
	 *            style.
	 */
	public PrimaryComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		// setBackground(ColorPalette.CUSTOM_BLACK);
		// setForeground(ColorPalette.CUSTOM_BLUE);
		setBackground(null);
		setLayout(null);
		displayHeight = arg0.getBounds().height;
		displayWidth = arg0.getBounds().width;
		edgePaddingWidth = displayWidth / 50;
		edgePaddingHeight = displayHeight / 20;

		setBounds(0, 0, displayWidth, displayHeight);
		setElementsToComposite();
	}

	/**
	 * Adds the elements to this component of the view.
	 */
	private void setElementsToComposite() {
		// Console For interacting with user.
		consoleScrolledComposite = new Console(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL,
				ColorPalette.CUSTOM_WHITE, ColorPalette.CUSTOM_BLACK);
		consoleScrolledComposite.setBounds(edgePaddingWidth, (int) (displayHeight * 0.775),
				displayWidth - 2 * edgePaddingWidth, 150);

		tabOutputs = new TabFolder(this, SWT.NONE);
		tabOutputs.setBounds((int) (displayWidth * 0.5), edgePaddingHeight, displayWidth / 2 - edgePaddingWidth,
				(int) (displayHeight * 0.65));

		// Temporary Console For Displaying Data
		dataDisplay = DataGraph.createChart(tabOutputs, ColorPalette.CUSTOM_WHITE);
		dataDisplay.setBackground(ColorPalette.CUSTOM_WHITE);
		dataDisplay.setBounds(tabOutputs.getBounds());

		outputData = new OutputTableComposite(tabOutputs, SWT.BORDER | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		outputData.setBounds(tabOutputs.getBounds());

		TabItem tableOutTab = new TabItem(tabOutputs, SWT.NONE);
		tableOutTab.setText("Output Table");
		tableOutTab.setControl(outputData);

		TabItem graphicalTab = new TabItem(tabOutputs, SWT.NONE);
		graphicalTab.setText("Graph");
		graphicalTab.setControl(dataDisplay);

		tabTables = new TabFolder(this, SWT.NONE);
		tabTables.setBounds(edgePaddingWidth, edgePaddingHeight, displayWidth / 2 - 2 * edgePaddingWidth,
				(int) (displayHeight * 0.44));

		inputData = new SolarTableComposite(tabTables, SWT.BORDER | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		inputData.setBounds(tabTables.getBounds());

		windInputData = new WindTableComposite(tabTables, SWT.BORDER | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		windInputData.setBounds(tabTables.getBounds());

		TabItem solarInputTab = new TabItem(tabTables, SWT.NONE);
		solarInputTab.setText("Solar");
		solarInputTab.setControl(inputData);

		TabItem windInputTab = new TabItem(tabTables, SWT.NONE);
		windInputTab.setText("Wind");
		windInputTab.setControl(windInputData);

		currentSubComposite.setLayout(layout);
		currentSubComposite.setBounds(edgePaddingWidth, displayHeight / 2 + edgePaddingHeight + compBuffer,
				2 * displayWidth / 7, displayHeight / 5);
		setUpSubComps();

		Label lblPType = new Label(this, SWT.NONE);
		lblPType.setSize(163, 33);
		lblPType.setLocation(edgePaddingWidth, (int) (0.53 * displayHeight));
		lblPType.setText("Power Type:");
		lblPType.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblPType.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPType.setForeground(ColorPalette.CUSTOM_BLACK);

		buttonAdd = new Button(this, SWT.NONE);
		buttonAdd.setBounds(displayWidth / 4 + 8 * compBuffer, displayHeight / 2 + edgePaddingWidth + 10,
				displayWidth / 10, displayHeight / 30);
		buttonAdd.setText("Add Power Source");

		buttonAnalyze = new Button(this, SWT.NONE);
		buttonAnalyze.setBounds(displayWidth / 4 + 8 * compBuffer, displayHeight / 2 + 2 * edgePaddingWidth + 20,
				displayWidth / 10, displayHeight / 30);
		buttonAnalyze.setText("Analyze");

		comboPowerOptions = new Combo(this, SWT.READ_ONLY);
		comboPowerOptions.setItems(powerOption);
		comboPowerOptions.setBounds(edgePaddingWidth + 163, (int) (0.53 * displayHeight), 94, 30);
		comboPowerOptions.select(0);
		setSubComposite();
		comboPowerOptions.setBackground(ColorPalette.CUSTOM_WHITE);

		comboBoxes = new Combo[] { comboPowerOptions };
	}

	// =====================Behavioral Code====================== //
	/**
	 * Resets the composite, setting the combo box to the first element in it.
	 */
	public void refreshView() {
		for (Combo c : comboBoxes)
			c.select(0);
		consoleScrolledComposite.clearConsole();
	}

	// =====================Setting up the subComposites========= //
	/**
	 * Creates the sub composites.
	 */
	public void setUpSubComps() {
		subComposites = new Composite[] { new SolarSubComposite(currentSubComposite, SWT.None),
				new WindSubComposite(currentSubComposite, SWT.None) };

	}

	// =====================Getters and Setter and Mcgeers======================
	/**
	 * Changes the sub composite.
	 */
	public void setSubComposite() {
		layout.topControl = subComposites[comboPowerOptions.getSelectionIndex()];
		currentSubComposite.layout();
	}

	/**
	 * @return Gets the button to add
	 */
	public Button getButtonAdd() {
		return buttonAdd;
	}

	/**
	 * @return Gets the button to analyze
	 */
	public Button getButtonAnalyze() {
		return buttonAnalyze;
	}

	/**
	 * @return Gets the combo box.
	 */
	public Combo getComboPowerOptions() {
		return comboPowerOptions;
	}

	/**
	 * @return Gets the table for solar inputs.
	 */
	public SolarTableComposite getSolarTable() {
		return this.inputData;
	}

	/**
	 * @return Gets the table for outputs.
	 */
	public OutputTableComposite getOutputTable() {
		return this.outputData;
	}

	/**
	 * @return Gets the table for wind outputs
	 */
	public WindTableComposite getWindTable() {
		return this.windInputData;
	}

	/**
	 * @return the console used for interacting with user.
	 */
	public Console getConsoleScrolledComposite() {
		return consoleScrolledComposite;
	}

	/**
	 * @return Gets the wind sub composite
	 */
	public WindSubComposite getWindSubComposite() {
		return (WindSubComposite) subComposites[1];
	}

	/**
	 * @return Gets the Solar sub composite
	 */
	public SolarSubComposite getSolarSubComposite() {
		return (SolarSubComposite) subComposites[0];
	}

}
