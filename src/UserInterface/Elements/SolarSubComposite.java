
package UserInterface.Elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class SolarSubComposite extends Composite {

	private Text areaText;
	private Text powerLossCoefficientText;
	private Text solarPowerEfficienyText;
	private Text exposureText;
	private Text[] textBoxes;
	private RowLayout layout = new RowLayout();
	private final int textBoxLength = 125;

	public SolarSubComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		addElementsToComposite();
//		setBackground(ColorPalette.CUSTOM_BLACK);
//		setForeground(ColorPalette.CUSTOM_BLUE);
		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackgroundImage(getBackgroundImage());
		setLayout(null);
		setBounds(0, 100, 540, 200);
	}

	private void addElementsToComposite() {

		Label lblAreaText = new Label(this, SWT.NONE);
		lblAreaText.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblAreaText.setBounds(0, 0, 163, 35);
		lblAreaText.setText("Panel Area:");
		//lblAreaText.setBackground(ColorPalette.CUSTOM_BLACK);
		lblAreaText.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblPowerloss = new Label(this, SWT.NONE);
		lblPowerloss.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPowerloss.setBounds(0, 40, 163, 35);
		lblPowerloss.setText("Powerloss Coefficient:");
		//lblPowerloss.setBackground(ColorPalette.CUSTOM_BLACK);
		lblPowerloss.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblPowerEfficiency = new Label(this, SWT.NONE);
		lblPowerEfficiency.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPowerEfficiency.setBounds(0, 80, 163, 35);
		lblPowerEfficiency.setText("Panel Efficiency:");
		//lblPowerEfficiency.setBackground(ColorPalette.CUSTOM_BLACK);
		lblPowerEfficiency.setForeground(ColorPalette.CUSTOM_BLACK);

		areaText = new Text(this, SWT.BORDER);
		areaText.setBackground(ColorPalette.CUSTOM_WHITE);
		areaText.setBounds(164, 0, textBoxLength, 30);

		powerLossCoefficientText = new Text(this, SWT.BORDER);
		powerLossCoefficientText.setBackground(ColorPalette.CUSTOM_WHITE);
		powerLossCoefficientText.setBounds(164, 40, textBoxLength, 30);

		solarPowerEfficienyText = new Text(this, SWT.BORDER);
		solarPowerEfficienyText.setBackground(ColorPalette.CUSTOM_WHITE);
		solarPowerEfficienyText.setBounds(164, 80, textBoxLength, 30);

		textBoxes = new Text[] { areaText, powerLossCoefficientText, solarPowerEfficienyText };
	}

	public void refreshView() {
		for (Text c : textBoxes)
			c.setText("");
	}


	
	// =====================Getters for text Boxes Code====================== //
	public Text getAreaText() {
		return areaText;
	}

	public Text getPowerLossCoefficientText() {
		return powerLossCoefficientText;
	}

	public Text getSolarPowerEfficienyText() {
		return solarPowerEfficienyText;
	}
}
