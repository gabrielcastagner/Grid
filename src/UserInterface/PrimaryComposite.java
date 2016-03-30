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
	
	//Things used for relative screen sizing.
	private int displayWidth, displayHeight;
	private final int edgePaddingWidth = 40;
	private final int edgePaddingHeight = 70;
	private final int compBuffer = 20;
	
	
	
	
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
		setBackground(ColorPalette.CUSTOM_BLACK);
		setForeground(ColorPalette.CUSTOM_BLUE);
		setLayout(null);
		//setBounds(0, 0, 1080, 720);
		displayHeight= arg0.getBounds().height;
		displayWidth = arg0.getBounds().width;
		setBounds(0, 0, displayWidth, displayHeight);
		System.out.println(displayHeight);
		System.out.println(displayWidth);
		setElementsToComposite();
	}

	private void setElementsToComposite() {
		// Console For interacting with user.
		consoleScrolledComposite = new Console(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL,
				ColorPalette.CUSTOM_WHITE, ColorPalette.CUSTOM_BLACK);
		consoleScrolledComposite.setBounds(edgePaddingWidth, (int) (displayHeight*0.75), displayWidth - 2*edgePaddingWidth, 150);

		// Temporary Console For Displaying Data
		dataDisplay = new Console(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		dataDisplay.setBounds((int) (displayWidth*0.5), edgePaddingHeight, displayWidth/2 -edgePaddingWidth, (int) (displayHeight*0.65));
		dataDisplay.clearConsole();
		dataDisplay.addToConsole("Temporary Data Display Console");

		inputData = new TableList(this, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL, ColorPalette.CUSTOM_WHITE,
				ColorPalette.CUSTOM_BLACK);
		inputData.setBounds(edgePaddingWidth, edgePaddingHeight, displayWidth/2 - 2*edgePaddingWidth, (int) (displayHeight*0.45));

		currentSubComposite.setLayout(layout);
		currentSubComposite.setBackground(ColorPalette.CUSTOM_BLACK);
		currentSubComposite.setForeground(ColorPalette.CUSTOM_BLUE);
		currentSubComposite.setBounds(edgePaddingWidth, displayHeight/2 + edgePaddingHeight+compBuffer, displayWidth/4 - 3*edgePaddingWidth, displayHeight/4 - edgePaddingHeight - compBuffer);

		Label lblPType = new Label(this, SWT.NONE);
		lblPType.setSize(163, 33);
		lblPType.setLocation(edgePaddingWidth, (int) (0.53*displayHeight));
		lblPType.setText("Power Type:");
		lblPType.setForeground(SWTResourceManager.getColor(52, 152, 219));
		lblPType.setFont(SWTResourceManager.getFont("Segoe UI", 10, SWT.NORMAL));
		lblPType.setBackground(ColorPalette.CUSTOM_BLACK);
		lblPType.setForeground(ColorPalette.CUSTOM_BLUE);

		buttonAdd = new Button(this, SWT.NONE);
		buttonAdd.setBounds(displayWidth/4, displayHeight/2 + edgePaddingWidth, 166, 25);
		buttonAdd.setText("Add Power Source");
		buttonAdd.addListener(SWT.Selection, event -> {
			consoleScrolledComposite
					.addToConsole(powerOption[comboPowerOptions.getSelectionIndex()] + " Power Source has been added");
		});

		buttonRemove = new Button(this, SWT.NONE);
		buttonRemove.setBounds(displayWidth/4, displayHeight/2 + 2*edgePaddingWidth, 166, 25);
		buttonRemove.setText("Remove Power Source");
		buttonRemove.addListener(SWT.Selection, event -> {
			consoleScrolledComposite.addToConsole("Power Source has been Removed");
		});

		buttonAnalyze = new Button(this, SWT.NONE);
		buttonAnalyze.setBounds(displayWidth/4, displayHeight/2 + 3*edgePaddingWidth, 166, 25);
		buttonAnalyze.setText("Analyze");
		buttonAnalyze.addListener(SWT.Selection, event -> {
			dataDisplay.addToConsole("Data Being Analyzed...");
		});

		comboPowerOptions = new Combo(this, SWT.NONE);
		comboPowerOptions.setItems(powerOption);
		comboPowerOptions.setBounds(edgePaddingWidth+163+edgePaddingWidth, (int) (0.53*displayHeight), 94, 30);
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
