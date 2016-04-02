
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
	private int lblLength, lblHeight;
	int xbound, ybound;

	public SolarSubComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		
		xbound = arg0.getBounds().width;
		ybound = arg0.getBounds().height;
		lblHeight = ybound/7;
		lblLength = 2*xbound/5;
		
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
		lblPowerloss.setBounds(0, lblHeight, lblLength, lblHeight);
		lblPowerloss.setText("Powerloss Coefficient:");
		lblPowerloss.setForeground(ColorPalette.CUSTOM_BLACK);

		Label lblPowerEfficiency = new Label(this, SWT.NONE);
		lblPowerEfficiency.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPowerEfficiency.setBounds(0, 2*lblHeight, lblLength, lblHeight);
		lblPowerEfficiency.setText("Panel Efficiency:");
		lblPowerEfficiency.setForeground(ColorPalette.CUSTOM_BLACK);
		
		Label lblNumber = new Label(this, SWT.NONE);
		lblNumber.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblNumber.setBounds(0, 3*lblHeight, lblLength, lblHeight);
		lblNumber.setText("Quantity #:");
		lblNumber.setForeground(ColorPalette.CUSTOM_BLACK);
		
		Label lblCost = new Label(this, SWT.NONE);
		lblCost.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblCost.setBounds(0, 4*lblHeight, lblLength, lblHeight);
		lblCost.setText("Cost/Unit:");
		lblCost.setForeground(ColorPalette.CUSTOM_BLACK);
		
		Label lblLong = new Label(this, SWT.NONE);
		lblLong.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLong.setBounds(0, 5*lblHeight, lblLength, lblHeight);
		lblLong.setText("Longitude:");
		lblLong.setForeground(ColorPalette.CUSTOM_BLACK);
		
		Label lblLat = new Label(this, SWT.NONE);
		lblLat.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblLat.setBounds(0, 6*lblHeight, lblLength, lblHeight);
		lblLat.setText("Latitude:");
		lblLat.setForeground(ColorPalette.CUSTOM_BLACK);

		areaText = new Text(this, SWT.BORDER);
		areaText.setBackground(ColorPalette.CUSTOM_WHITE);
		areaText.setBounds(lblLength, 0, lblLength, lblHeight);

		powerLossCoefficientText = new Text(this, SWT.BORDER);
		powerLossCoefficientText.setBackground(ColorPalette.CUSTOM_WHITE);
		powerLossCoefficientText.setBounds(lblLength, lblHeight, lblLength, lblHeight);

		solarPowerEfficienyText = new Text(this, SWT.BORDER);
		solarPowerEfficienyText.setBackground(ColorPalette.CUSTOM_WHITE);
		solarPowerEfficienyText.setBounds(lblLength, 2*lblHeight, lblLength, lblHeight);

		textBoxes = new Text[] { areaText, powerLossCoefficientText, solarPowerEfficienyText };
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
}
