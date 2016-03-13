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
	private Console consoleScrolledComposite;

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
		consoleScrolledComposite = new Console(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL,
				ColorPalette.CUSTOM_WHITE, ColorPalette.CUSTOM_BLACK);
		consoleScrolledComposite.setBounds(25, 474, 1024, 150);

		currentSubComposite.setLayout(layout);
		currentSubComposite.setBackground(ColorPalette.CUSTOM_BLACK);
		currentSubComposite.setForeground(ColorPalette.CUSTOM_BLUE);
		currentSubComposite.setBounds(0, 100, 520, 150);

		Label lblUnits = new Label(this, SWT.NONE);
		lblUnits.setSize(163, 33);
		lblUnits.setLocation(25, 30);
		lblUnits.setText("Power Type:");
		lblUnits.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblUnits.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblUnits.setBackground(ColorPalette.CUSTOM_BLACK);
		lblUnits.setForeground(ColorPalette.CUSTOM_BLUE);

		buttonAdd = new Button(this, SWT.NONE);
		buttonAdd.setBounds(136, 433, 166, 35);
		buttonAdd.setText("Add Power Source");
		buttonAdd.addListener(SWT.Selection, event -> {
			consoleScrolledComposite
					.addToConsole(powerOption[comboPowerOptions.getSelectionIndex()] + " Power Source has been added");
		});

		comboPowerOptions = new Combo(this, SWT.NONE);
		comboPowerOptions.setItems(powerOption);
		comboPowerOptions.setBounds(189, 30, 94, 33);
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
