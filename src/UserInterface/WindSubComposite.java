package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class WindSubComposite extends Composite {

	private Text bladeRadiusText;
	private Text airDensityText;
	private Text efficiencyText;
	private Text windSpeedText;
	private Text[] textBoxes;

	public WindSubComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		setElementsToComposite();
		setBackground(ColorPalette.CUSTOM_BLACK);
		setForeground(ColorPalette.CUSTOM_BLUE);
		setLayout(null);
		setBounds(0, 100, 540, 200);
	}

	private void setElementsToComposite() {

		Label lblRadius = new Label(this, SWT.NONE);
		lblRadius.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblRadius.setBounds(25, 0, 163, 35);
		lblRadius.setText("Blade Radius:");
		lblRadius.setBackground(ColorPalette.CUSTOM_BLACK);
		lblRadius.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblDensity = new Label(this, SWT.NONE);
		lblDensity.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblDensity.setBounds(25, 40, 163, 35);
		lblDensity.setText("Air Density:");
		lblDensity.setBackground(ColorPalette.CUSTOM_BLACK);
		lblDensity.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblWindSpeed = new Label(this, SWT.NONE);
		lblWindSpeed.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblWindSpeed.setBounds(25, 80, 163, 35);
		lblWindSpeed.setText("Wind Speed:");
		lblWindSpeed.setBackground(ColorPalette.CUSTOM_BLACK);
		lblWindSpeed.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblEfficiency = new Label(this, SWT.NONE);
		lblEfficiency.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblEfficiency.setBounds(25, 120, 163, 35);
		lblEfficiency.setText("Model Efficiency:");
		lblEfficiency.setBackground(ColorPalette.CUSTOM_BLACK);
		lblEfficiency.setForeground(ColorPalette.CUSTOM_BLUE);

		bladeRadiusText = new Text(this, SWT.BORDER);
		bladeRadiusText.setBounds(189, 0, 196, 35);

		airDensityText = new Text(this, SWT.BORDER);
		airDensityText.setBounds(189, 40, 196, 35);

		windSpeedText = new Text(this, SWT.BORDER);
		windSpeedText.setBounds(189, 80, 196, 35);

		efficiencyText = new Text(this, SWT.BORDER);
		efficiencyText.setBounds(189, 120, 196, 35);

		textBoxes = new Text[] { bladeRadiusText, airDensityText, windSpeedText, efficiencyText };

	}

	public void refreshView() {
		for (Text c : textBoxes)
			c.setText("");
	}

}
