package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
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
	
	private final int textBoxLength = 125;

	public WindSubComposite(Composite arg0, int arg1) {
		super(arg0, arg1);
		setElementsToComposite();
		setBackground(ColorPalette.CUSTOM_BLACK);
		setForeground(ColorPalette.CUSTOM_BLUE);
		setLayout(null);
		setBounds(25, 100, 540, 200);
	}

	private void setElementsToComposite() {

		Label lblRadius = new Label(this, SWT.NONE);
		lblRadius.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblRadius.setBounds(0, 0, 163, 35);
		lblRadius.setText("Blade Radius:");
		lblRadius.setBackground(ColorPalette.CUSTOM_BLACK);
		lblRadius.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblDensity = new Label(this, SWT.NONE);
		lblDensity.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblDensity.setBounds(0, 40, 163, 35);
		lblDensity.setText("Air Density:");
		lblDensity.setBackground(ColorPalette.CUSTOM_BLACK);
		lblDensity.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblWindSpeed = new Label(this, SWT.NONE);
		lblWindSpeed.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblWindSpeed.setBounds(0, 80, 163, 35);
		lblWindSpeed.setText("Wind Speed:");
		lblWindSpeed.setBackground(ColorPalette.CUSTOM_BLACK);
		lblWindSpeed.setForeground(ColorPalette.CUSTOM_BLUE);

		Label lblEfficiency = new Label(this, SWT.NONE);
		lblEfficiency.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblEfficiency.setBounds(0, 120, 163, 35);
		lblEfficiency.setText("Model Efficiency:");
		lblEfficiency.setBackground(ColorPalette.CUSTOM_BLACK);
		lblEfficiency.setForeground(ColorPalette.CUSTOM_BLUE);

		bladeRadiusText = new Text(this, SWT.BORDER);
		bladeRadiusText.setBounds(164, 0, textBoxLength, 25);

		airDensityText = new Text(this, SWT.BORDER);
		airDensityText.setBounds(164, 40, textBoxLength, 25);

		windSpeedText = new Text(this, SWT.BORDER);
		windSpeedText.setBounds(164, 80, textBoxLength, 25);

		efficiencyText = new Text(this, SWT.BORDER);
		efficiencyText.setBounds(164, 120, textBoxLength, 25);

		textBoxes = new Text[] { bladeRadiusText, airDensityText, windSpeedText, efficiencyText };

	}

	public void refreshView() {
		for (Text c : textBoxes)
			c.setText("");
	}

}
