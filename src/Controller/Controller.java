package Controller;

import java.util.HashMap;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import Parser.DataParser;
import PowerModels.SolarModel;
import UserInterface.ApplicationView;
import UserInterface.PrimaryComposite;
import UserInterface.Elements.SolarSubComposite;
import UserInterface.Elements.Table.SolarTableItem;

public class Controller {

	private ApplicationView view;
	private Shell parentShell;
	private Display display;
	private PrimaryComposite primaryComposite;
	private Table solarTable;
	private static final Pattern invalidDouble = Pattern.compile("[^0-9\\.]+");
	
	
	private static HashMap<UUID, SolarItemController> solarTableItems;
	
	
	
	public Controller(ApplicationView v) {
		view = v;
		view.open();
		// initViewEventHandeling();

		try {
			display = view.getDisplay();
			parentShell = view.getParentShell();
			primaryComposite = view.getPrimaryComposite();
			solarTable = primaryComposite.getSolarTable().getTable();
			
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		solarTableItems = new HashMap<>();
		initController();
		
		//TODO Console stuff here
		System.out.println("PRINT TO CONSOLE \"Gathering Assets and Loading the Program...\"");
		DataParser.parse();
		System.out.println("PRINT TO CONSOLE \"Program Loaded.\"");
		
	}

	private void initController() {
		primaryComposite.getButtonAdd().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				UUID itemID = UUID.randomUUID();
				if(primaryComposite.getComboPowerOptions().getSelectionIndex() == 0){
					
					//Create The controller
					SolarModel model = new SolarModel();
					if(!inputsToSolarModel(model))
						return;
					
					SolarItemController c = new SolarItemController(
							new SolarTableItem(solarTable, SWT.NULL), 
							model, itemID);
					
					c.updateViewToModelState();
					//Keep reference to the controller
					solarTableItems.put(itemID, c);
					solarTableItems.get(itemID).getRemoveButton().addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							c.destroy();
							solarTableItems.remove(itemID);
						}
					});
				}
				else if(primaryComposite.getComboPowerOptions().getSelectionIndex() == 1){
					//TODO add WIND stuff here
					/*SolarItemController c = new SolarItemController(
							new SolarTableItem(solarTable, SWT.NULL), 
							new SolarModel(), itemID);
					
					solarTableItems.put(itemID, c);
					solarTableItems.get(itemID).getRemoveButton().addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							c.destroy();
							solarTableItems.remove(itemID);
						}
					});*/
				}
			}
		});
		
		primaryComposite.getButtonAnalyze().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//TODO Print to Console
				
				if(!solarTableItems.isEmpty())
					for(UUID id: solarTableItems.keySet())
						solarTableItems.get(id).analyze();
				
				//TODO do WIND DATA HERE
			}	
		});
		
		primaryComposite.getComboPowerOptions().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
			primaryComposite.setSubComposite();
			}
		});
		
		
	}
	
	public boolean inputsToSolarModel(SolarModel model){
		SolarSubComposite sc = primaryComposite.getSolarSubComposite();
		if(!(matchesDoubleCharSequence(sc.getAreaText()) &&
				matchesDoubleCharSequence(sc.getPowerLossCoefficientText()) &&
				matchesDoubleCharSequence(sc.getSolarPowerEfficienyText()))){
			//TODO other inputs
			return false;
		}
		
		
		model.setArea(Double.parseDouble(sc.getAreaText()));
		model.setPlCoeff(Double.parseDouble(sc.getPowerLossCoefficientText()));
		model.setYield(Double.parseDouble(sc.getSolarPowerEfficienyText()));
		return true;
	}
	
	
	public boolean matchesDoubleCharSequence(String s){
		s = s.trim();
		if(s.isEmpty())
			return false;
		Matcher m = invalidDouble.matcher(s);
		return !m.find();
		
	}
	
	/**
	 * Main Program run loop. Program closes when the display is disposed
	 */
	public void Run() {
		while (!parentShell.isDisposed())
			if (!display.readAndDispatch())
				display.sleep();
		// Free Memory
		display.dispose();
	}
	
}
