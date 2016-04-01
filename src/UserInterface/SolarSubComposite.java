package UserInterface;

import org.eclipse.swt.SWT;
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

	public SolarSubComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		addElementsToComposite();
		setBackground(ColorPalette.CUSTOM_BLACK);
		setForeground(ColorPalette.CUSTOM_BLUE);
		setLayout(null);
		setBounds(0, 100, 540, 200);
	}

	private void addElementsToComposite() {

		Label lblAreaText = new Label(this, SWT.NONE);
		lblAreaText.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblAreaText.setBounds(25, 0, 163, 35);
		lblAreaText.setText("Panel Area:");
		lblAreaText.setBackground(ColorPalette.CUSTOM_BLACK);
		lblAreaText.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblPowerloss = new Label(this, SWT.NONE);
		lblPowerloss.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPowerloss.setBounds(25, 40, 163, 35);
		lblPowerloss.setText("Powerloss Coeff:");
		lblPowerloss.setBackground(ColorPalette.CUSTOM_BLACK);
		lblPowerloss.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblPowerEfficiency = new Label(this, SWT.NONE);
		lblPowerEfficiency.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPowerEfficiency.setBounds(25, 80, 163, 35);
		lblPowerEfficiency.setText("Panel Efficiency:");
		lblPowerEfficiency.setBackground(ColorPalette.CUSTOM_BLACK);
		lblPowerEfficiency.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblExposure = new Label(this, SWT.NONE);
		lblExposure.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblExposure.setBounds(25, 120, 163, 35);
		lblExposure.setText("Panel Exposure:");
		lblExposure.setBackground(ColorPalette.CUSTOM_BLACK);
		lblExposure.setForeground(ColorPalette.CUSTOM_BLUE);

		areaText = new Text(this, SWT.BORDER);
		areaText.setBounds(189, 0, 196, 25);

		powerLossCoefficientText = new Text(this, SWT.BORDER);
		powerLossCoefficientText.setBounds(189, 40, 196, 25);

		solarPowerEfficienyText = new Text(this, SWT.BORDER);
		solarPowerEfficienyText.setBounds(189, 80, 196, 25);

		exposureText = new Text(this, SWT.BORDER);
		exposureText.setBounds(189, 120, 196, 25);

		textBoxes = new Text[] { areaText, powerLossCoefficientText, solarPowerEfficienyText, exposureText };
	}

	public void refreshView() {
		for (Text c : textBoxes)
			c.setText("");
	}

}
