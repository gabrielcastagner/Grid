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
import PowerModels.WindModel;
import UserInterface.ApplicationView;
import UserInterface.PrimaryComposite;
import UserInterface.Elements.SolarSubComposite;
import UserInterface.Elements.WindSubComposite;
import UserInterface.Elements.Table.SolarTableItem;
import UserInterface.Elements.Table.WindTableItem;

public class Controller {

	private ApplicationView view;
	private Shell parentShell;
	private Display display;
	private PrimaryComposite primaryComposite;
	private Table solarTable, windTable;
	private static final Pattern invalidDouble = Pattern.compile("[^0-9\\.]+");
	
	
	private static HashMap<UUID, SolarItemController> solarTableItems;
	private static HashMap<UUID, WindItemController> windTableItems;
	
	
	
	public Controller(ApplicationView v) {
		view = v;
		view.open();
		// initViewEventHandeling();

		try {
			//Link View to Controller Elements
			display = view.getDisplay();
			parentShell = view.getParentShell();
			primaryComposite = view.getPrimaryComposite();
			solarTable = primaryComposite.getSolarTable().getTable();
			windTable = primaryComposite.getWindTable().getTable();
			
		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//Init data structures
		solarTableItems = new HashMap<>();
		windTableItems = new HashMap<>();
		
		//Link Input actions to Elements
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
					//Create The controller
					WindModel model = new WindModel();
					if(!inputsToWindModel(model))
						return;
					
					WindItemController c = new WindItemController(
							new WindTableItem(windTable, SWT.NULL),
							model, itemID);
					
					c.updateViewToModelState();
					//Keep reference to the controller
					windTableItems.put(itemID, c);
					
					windTableItems.get(itemID).getRemoveButton().addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							c.destroy();
							windTableItems.remove(itemID);
						};
					});
				}
			}
		});
		
		primaryComposite.getButtonAnalyze().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//TODO Print to Console
				//Analyze Solar data
				if(!solarTableItems.isEmpty())
					for(UUID id: solarTableItems.keySet())
						solarTableItems.get(id).analyze();
				//Analyze Wind data
				if(!windTableItems.isEmpty())
					for(UUID id: windTableItems.keySet())
						windTableItems.get(id).analyze();
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
	
	private boolean inputsToWindModel(WindModel model) {
		WindSubComposite wc = primaryComposite.getWindSubComposite();
		
		if(!(matchesDoubleCharSequence(wc.getAirDensityText()) &&
				matchesDoubleCharSequence(wc.getBladeRadiusText()) &&
				matchesDoubleCharSequence(wc.getEfficiencyText()))){
			//TODO other inputs
			return false;
		}
		
		
		model.setAirDensity(Double.parseDouble(wc.getAirDensityText()));
		model.setRadius(Double.parseDouble(wc.getBladeRadiusText()));
		model.setEffCoeff(Double.parseDouble(wc.getEfficiencyText()));
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
