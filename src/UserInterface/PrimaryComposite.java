package UserInterface;

import org.eclipse.swt.SWT;
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

	// Buttons
	private Button buttonCalculate;
	private Button buttonCalculateOptimalTrain;
	private Button buttonAllowWormDrives;

	// Text boxes
	private Text inputSpeedText;
	private Text boxDimensionXText;
	private Text outputSpeedText;
	private Text boxDimensionYText;
	private Text boxDimensionZText;
	private Text minGearSizeText;
	private Text maxGearSizeText;
	private Text[] textBoxes;

	// Labels
	private Label labelGearTrainName;

	// Scrolled Composites
	private Console consoleScrolledComposite;

	// Combo Boxes
	private final String[] speedUnitsOptions = { "RPS", "RPM" };
	private final String[] dimensionUnitsOptions = { "mm", "cm", "m", "in", "ft" };
	private Combo comboSpeedUnits;
	private Combo comboDimensionUnits;
	private Combo[] comboBoxes;

	public PrimaryComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		setElementsToComposite();
		setBackground(ColorPalette.CUSTOM_BLACK);
		setForeground(ColorPalette.CUSTOM_BLUE);
		setLayout(null);
		setBounds(0, 0, 1080, 720);
	}

	private void setElementsToComposite() {
		consoleScrolledComposite = new Console(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		consoleScrolledComposite.setBounds(25, 474, 1024, 150);

		
		
		Label lblInputSpeed = new Label(this, SWT.NONE);
		lblInputSpeed.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblInputSpeed.setBounds(25, 53, 163, 35);
		lblInputSpeed.setText("Input Speed:");
		lblInputSpeed.setBackground(ColorPalette.CUSTOM_BLACK);
		lblInputSpeed.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblBoxDimensionX = new Label(this, SWT.NONE);
		lblBoxDimensionX.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblBoxDimensionX.setBounds(25, 133, 158, 35);
		lblBoxDimensionX.setText("Box Dimension X:");
		lblBoxDimensionX.setBackground(ColorPalette.CUSTOM_BLACK);
		lblBoxDimensionX.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblBoxDimensionY = new Label(this, SWT.NONE);
		lblBoxDimensionY.setSize(158, 35);
		lblBoxDimensionY.setLocation(25, 174);
		lblBoxDimensionY.setText("Box Dimension Y:");
		lblBoxDimensionY.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblBoxDimensionY.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblBoxDimensionY.setBackground(ColorPalette.CUSTOM_BLACK);
		lblBoxDimensionY.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblBoxDimensionZ = new Label(this, SWT.NONE);
		lblBoxDimensionZ.setSize(158, 35);
		lblBoxDimensionZ.setLocation(25, 215);
		lblBoxDimensionZ.setText("Box Dimension Z:");
		lblBoxDimensionZ.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblBoxDimensionZ.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblBoxDimensionZ.setBackground(ColorPalette.CUSTOM_BLACK);
		lblBoxDimensionZ.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblOutputSpeed = new Label(this, SWT.NONE);
		lblOutputSpeed.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblOutputSpeed.setBounds(25, 392, 134, 35);
		lblOutputSpeed.setText("Output Speed:");
		lblOutputSpeed.setBackground(ColorPalette.CUSTOM_BLACK);
		lblOutputSpeed.setForeground(ColorPalette.CUSTOM_GREEN);

		Label lblAllowWormDrives = new Label(this, SWT.NONE);
		lblAllowWormDrives.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblAllowWormDrives.setBounds(25, 338, 188, 35);
		lblAllowWormDrives.setText("Allow Worm Drives:");
		lblAllowWormDrives.setBackground(ColorPalette.CUSTOM_BLACK);
		lblAllowWormDrives.setForeground(ColorPalette.CUSTOM_ORANGE);

		Label lblMinGearSize = new Label(this, SWT.NONE);
		lblMinGearSize.setSize(158, 35);
		lblMinGearSize.setLocation(25, 256);
		lblMinGearSize.setText("Min Gear Size:");
		lblMinGearSize.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblMinGearSize.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblMinGearSize.setBackground(ColorPalette.CUSTOM_BLACK);
		lblMinGearSize.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblMaxGearSize = new Label(this, SWT.NONE);
		lblMaxGearSize.setSize(158, 35);
		lblMaxGearSize.setLocation(25, 297);
		lblMaxGearSize.setText("Max Gear Size:");
		lblMaxGearSize.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblMaxGearSize.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblMaxGearSize.setBackground(ColorPalette.CUSTOM_BLACK);
		lblMaxGearSize.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblUnits = new Label(this, SWT.NONE);
		lblUnits.setSize(163, 33);
		lblUnits.setLocation(25, 94);
		lblUnits.setText("Units:");
		lblUnits.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblUnits.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblUnits.setBackground(ColorPalette.CUSTOM_BLACK);
		lblUnits.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblGearTrain = new Label(this, SWT.NONE);
		lblGearTrain.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblGearTrain.setBounds(10, 10, 121, 37);
		lblGearTrain.setText("Gear Train:");
		lblGearTrain.setBackground(ColorPalette.CUSTOM_BLACK);
		lblGearTrain.setForeground(ColorPalette.CUSTOM_PURPLE);

		Label lblElements = new Label(this, SWT.NONE);
		lblElements.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		lblElements.setBounds(609, 10, 228, 37);
		lblElements.setText("Gear Train Elements");
		lblElements.setBackground(ColorPalette.CUSTOM_BLACK);
		lblElements.setForeground(ColorPalette.CUSTOM_YELLOW);

		labelGearTrainName = new Label(this, SWT.NONE);
		labelGearTrainName.setBounds(131, 10, 259, 37);
		labelGearTrainName.setText("");
		labelGearTrainName.setBackground(ColorPalette.CUSTOM_BLACK);
		labelGearTrainName.setForeground(ColorPalette.CUSTOM_PURPLE);
		labelGearTrainName.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));

		inputSpeedText = new Text(this, SWT.BORDER);
		inputSpeedText.setBounds(189, 53, 196, 35);

		boxDimensionXText = new Text(this, SWT.BORDER);
		boxDimensionXText.setBounds(189, 133, 196, 35);

		outputSpeedText = new Text(this, SWT.BORDER);
		outputSpeedText.setBounds(189, 392, 196, 35);

		boxDimensionYText = new Text(this, SWT.BORDER);
		boxDimensionYText.setBounds(189, 174, 196, 35);

		boxDimensionZText = new Text(this, SWT.BORDER);
		boxDimensionZText.setBounds(189, 215, 196, 35);

		minGearSizeText = new Text(this, SWT.BORDER);
		minGearSizeText.setBounds(189, 256, 196, 35);

		maxGearSizeText = new Text(this, SWT.BORDER);
		maxGearSizeText.setBounds(189, 297, 196, 35);

		textBoxes = new Text[] { inputSpeedText, boxDimensionXText, outputSpeedText, boxDimensionYText,
				boxDimensionZText, minGearSizeText, maxGearSizeText };

		buttonCalculate = new Button(this, SWT.NONE);
		buttonCalculate.setBounds(219, 433, 166, 35);
		buttonCalculate.setText("Calculate");

		buttonCalculateOptimalTrain = new Button(this, SWT.NONE);
		buttonCalculateOptimalTrain.setBounds(25, 433, 188, 35);
		buttonCalculateOptimalTrain.setText("Calculate Optimal Train");

		buttonAllowWormDrives = new Button(this, SWT.CHECK);
		buttonAllowWormDrives.setSelection(true);
		buttonAllowWormDrives.setBounds(215, 337, 29, 32);
		buttonAllowWormDrives.setText("");
		buttonAllowWormDrives.setBackground(ColorPalette.CUSTOM_BLACK);
		buttonAllowWormDrives.setForeground(ColorPalette.CUSTOM_RED);

		comboSpeedUnits = new Combo(this, SWT.NONE);
		comboSpeedUnits.setItems(speedUnitsOptions);
		comboSpeedUnits.setBounds(189, 94, 94, 33);
		comboSpeedUnits.select(0);

		comboDimensionUnits = new Combo(this, SWT.NONE);
		comboDimensionUnits.setItems(dimensionUnitsOptions);
		comboDimensionUnits.setBounds(289, 94, 94, 33);
		comboDimensionUnits.select(0);

		comboBoxes = new Combo[] { comboSpeedUnits, comboDimensionUnits };
	}

	// =====================Behavioral Code====================== //
	public void refreshView() {
		for (Text t : textBoxes)
			t.setText("");
		for (Combo c : comboBoxes)
			c.select(0);
		consoleScrolledComposite.clearConsole();
		buttonAllowWormDrives.setSelection(true);
	}
	
}
