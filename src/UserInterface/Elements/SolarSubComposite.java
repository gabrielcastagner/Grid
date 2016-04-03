
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
	private Text numberText;
	private Text costText;
	private Text longText;
	private Text latText;

	private Text[] textBoxes;
	private RowLayout layout = new RowLayout();
	private int lblLength, lblHeight;
	private int xbound, ybound, padding;

	public SolarSubComposite(Composite arg0, int arg1) {
		super(arg0, arg1);

		xbound = arg0.getBounds().width;
		ybound = arg0.getBounds().height;
		lblHeight = ybound / 7;
		lblLength = 3 * xbound / 8;
		padding = xbound / 100;

		addElementsToComposite();
		setBackground(null);

		setBackgroundMode(SWT.INHERIT_FORCE);
		setBackgroundImage(getBackgroundImage());
		setLayout(null);
	}

	private void addElementsToComposite() {

		Label lblAreaText = new Label(this, SWT.NONE);
		lblAreaText.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblAreaText.setBounds(0, 0, lblLength, lblHeight);
		lblAreaText.setText("Panel Area:");
		lblAreaText.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblPowerloss = new Label(this, SWT.NONE);
		lblPowerloss.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPowerloss.setBounds(0, (lblHeight + padding), lblLength, lblHeight);
		lblPowerloss.setText("Powerloss Coefficient:");
		lblPowerloss.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblPowerEfficiency = new Label(this, SWT.NONE);
		lblPowerEfficiency.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPowerEfficiency.setBounds(0, 2 * (lblHeight + padding), lblLength, lblHeight);
		lblPowerEfficiency.setText("Panel Efficiency:");
		lblPowerEfficiency.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblNumber = new Label(this, SWT.NONE);
		lblNumber.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNumber.setBounds(0, 3 * (lblHeight + padding), lblLength, lblHeight);
		lblNumber.setText("Quantity #:");
		lblNumber.setForeground(ColorPalette.CUSTOM_BLACK);

		// Right 3
		Label lblCost = new Label(this, SWT.NONE);
		lblCost.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblCost.setBounds(3 * lblLength / 2 + padding, 0, 5 * lblLength / 9, lblHeight);
		lblCost.setText("Cost/Unit:");
		lblCost.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblLong = new Label(this, SWT.NONE);
		lblLong.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLong.setBounds(3 * lblLength / 2 + padding, lblHeight + padding, 5 * lblLength / 9, lblHeight);
		lblLong.setText("Longitude:");
		lblLong.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblLat = new Label(this, SWT.NONE);
		lblLat.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLat.setBounds(3 * lblLength / 2 + padding, 2 * lblHeight + 2 * padding, 5 * lblLength / 9, lblHeight);
		lblLat.setText("Latitude:");
		lblLat.setForeground(ColorPalette.CUSTOM_BLACK);

		// Left 4
		areaText = new Text(this, SWT.BORDER);
		areaText.setBackground(ColorPalette.CUSTOM_WHITE);
		areaText.setBounds(lblLength, 0, lblLength / 2, lblHeight);

		powerLossCoefficientText = new Text(this, SWT.BORDER);
		powerLossCoefficientText.setBackground(ColorPalette.CUSTOM_WHITE);
		powerLossCoefficientText.setBounds(lblLength, (lblHeight + padding), lblLength / 2, lblHeight);

		solarPowerEfficienyText = new Text(this, SWT.BORDER);
		solarPowerEfficienyText.setBackground(ColorPalette.CUSTOM_WHITE);
		solarPowerEfficienyText.setBounds(lblLength, 2 * (lblHeight + padding), lblLength / 2, lblHeight);

		numberText = new Text(this, SWT.BORDER);
		numberText.setBackground(ColorPalette.CUSTOM_WHITE);
		numberText.setBounds(lblLength, 3 * (lblHeight + padding), lblLength / 2, lblHeight);

		// Right 3
		costText = new Text(this, SWT.BORDER);
		costText.setBackground(ColorPalette.CUSTOM_WHITE);
		costText.setBounds(5 * lblLength / 9 + lblLength / 2 + padding + lblLength, 0, lblLength / 2, lblHeight);

		longText = new Text(this, SWT.BORDER);
		longText.setBackground(ColorPalette.CUSTOM_WHITE);
		longText.setBounds(5 * lblLength / 9 + lblLength / 2 + padding + lblLength, (lblHeight + padding),
				lblLength / 2, lblHeight);

		latText = new Text(this, SWT.BORDER);
		latText.setBackground(ColorPalette.CUSTOM_WHITE);
		latText.setBounds(5 * lblLength / 9 + lblLength / 2 + padding + lblLength, 2 * (lblHeight + padding),
				lblLength / 2, lblHeight);

		textBoxes = new Text[] { areaText, powerLossCoefficientText, solarPowerEfficienyText, numberText, costText,
				longText, latText };
	}

	public void refreshView() {
		for (Text c : textBoxes)
			c.setText("");
	}

	// =====================Getters for text Boxes Code====================== //
	public String getAreaText() {
		return areaText.getText();
	}

	public String getPowerLossCoefficientText() {
		return powerLossCoefficientText.getText();
	}

	public String getSolarPowerEfficienyText() {
		return solarPowerEfficienyText.getText();
	}

	public String getNumberText() {
		return numberText.getText();
	}

	public String getCostText() {
		return costText.getText();

	}

	public String getLongText() {
		return longText.getText();

	}

	public String getLatText() {
		return latText.getText();

	}

}
