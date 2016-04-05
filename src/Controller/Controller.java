package Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import GraphControl.SolarGraph;
import Parser.DataParser;
import PowerModels.SolarModel;
import PowerModels.WindModel;
import PowerModels.Graph.Location;
import PowerModels.Graph.Month;
import PowerModels.Graph.SolarDataNode;
import UserInterface.ApplicationView;
import UserInterface.PrimaryComposite;
import UserInterface.Elements.Console;
import UserInterface.Elements.SolarSubComposite;
import UserInterface.Elements.WindSubComposite;
import UserInterface.Elements.Graph.DataGraph;
import UserInterface.Elements.Table.OutputTableItem;
import UserInterface.Elements.Table.SolarTableItem;
import UserInterface.Elements.Table.WindTableItem;

/**
 * Controller for the program. Links Model elements to View elements
 */
public class Controller {
	//View Variables
	private ApplicationView view;
	private Shell parentShell;
	private Display display;
	private PrimaryComposite primaryComposite;
	private Table solarTable, windTable, outputTable;
	private Console console;

	private SolarGraph solarPoints;

	private DataGraph graph;

	//Regex's used for error handling 
	private static final Pattern invalidDouble = Pattern.compile("[^0-9\\.]+");
	private static final Pattern invalidInteger = Pattern.compile("[^0-9]+");

	//Data Holders
	private static ArrayList<SolarDataNode> solarNodes;
	private static HashMap<UUID, String> solarNames;
	private static HashMap<UUID, SolarItemController> solarTableItems;
	private static HashMap<UUID, WindItemController> windTableItems;
	private List<AbstractPowerItemController> combined;

	/**
	 * Creates a controller instance with no models initially
	 * 
	 * @param v
	 *            ApplicationView to be linked to the controller
	 */
	public Controller(ApplicationView v) {
		view = v;
		view.open();

		try {
			//Link View to Controller Elements
			display = view.getDisplay();
			parentShell = view.getParentShell();
			primaryComposite = view.getPrimaryComposite();
			solarTable = primaryComposite.getSolarTable().getTable();
			windTable = primaryComposite.getWindTable().getTable();
			outputTable = primaryComposite.getOutputTable().getTable();
			console = primaryComposite.getConsoleScrolledComposite();

		} catch (SecurityException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		//Init data structures
		solarTableItems = new HashMap<>();
		windTableItems = new HashMap<>();
		graph = new DataGraph();
		combined = new ArrayList<AbstractPowerItemController>();
		solarNames = new HashMap<>();

		//Link Input actions to Elements
		initController();

		console.addToConsole("Gathering Assets and Loading the Program...", false);
		//TODO Generate Graph Here
		DataParser.parse();
		console.addToConsole("Program Loaded.", false);

		solarPoints = new SolarGraph();
	}

	/**
	 * Sets up controls for the user to interact with the program
	 */
	private void initController() {
		primaryComposite.getButtonAdd().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				UUID itemID = UUID.randomUUID();
				if (primaryComposite.getComboPowerOptions().getSelectionIndex() == 0) {

					//Create The controller
					SolarModel model = new SolarModel();
					if (!inputsToSolarModel(model))
						return;

					SolarItemController c = new SolarItemController(new SolarTableItem(solarTable, SWT.NULL), model,
							itemID);

					c.updateModelStateToView();
					//Keep reference to the controller
					solarTableItems.put(itemID, c);
					solarTableItems.get(itemID).getRemoveButton().addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							c.destroy();
							solarTableItems.remove(itemID);
							updateGraph();
							updateGraph();
							if (combined.contains(c)) {
								DataGraph.removePlot(c.getDisplayID());
								combined.remove(c);
							}
							updateGraph();
							setOutputTable();

						}
					});
					console.addToConsole("New Solar Panel Model Added.", false);
				} else if (primaryComposite.getComboPowerOptions().getSelectionIndex() == 1) {
					//Create The controller
					WindModel model = new WindModel();
					if (!inputsToWindModel(model))
						return;

					WindItemController c = new WindItemController(new WindTableItem(windTable, SWT.NULL), model,
							itemID);

					c.updateModelStateToView();
					//Keep reference to the controller
					windTableItems.put(itemID, c);

					windTableItems.get(itemID).getRemoveButton().addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent arg0) {
							c.destroy();
							windTableItems.remove(itemID);
							updateGraph();
							if (combined.contains(c)) {
								DataGraph.removePlot(c.getDisplayID());
								combined.remove(c);
							}
							updateGraph();
							setOutputTable();
						};
					});

					console.addToConsole("New Wind Turbine Model Added.", false);
				}
				//Add new conditional for any new power generation scheme
			}
		});

		primaryComposite.getButtonAnalyze().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				console.addToConsole("Analyzing Input Data...", false);
				//Check to see if inputs exist
				if (solarTableItems.isEmpty() && windTableItems.isEmpty()) {
					console.addToConsole("Error: No inputs specified", true);
					return;
				}
				//Analyze Solar data
				if (!solarTableItems.isEmpty()) {
					for (UUID id : solarTableItems.keySet()) {
						solarTableItems.get(id).analyze();
					}
				}

				//Analyze Wind data
				if (!windTableItems.isEmpty()) {
					for (UUID id : windTableItems.keySet()) {
						windTableItems.get(id).analyze();
					}
				}

				sortTable(new ArrayList<AbstractPowerItemController>(solarTableItems.values()),
						new ArrayList<AbstractPowerItemController>(windTableItems.values()), 1);

				setOutputTable();

				updateGraph();
				
				setOutputTable();

				console.addToConsole("Data Inputs Analyzed, All Outputs in the Righthand Table", false);
			}
		});

		primaryComposite.getComboPowerOptions().addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				primaryComposite.setSubComposite();
			}
		});
	}

	private void setOutputTable() {
		//updates output table one item at a time
		for (AbstractPowerItemController i : combined) {
			if (i.outputted())
				i.destroyOutput(); //in case the item placement moves up or down

			i.buildOutput(new OutputTableItem(outputTable, SWT.NULL));
			i.updateOutputTable();

			//Graphing stuff

		}
	}

	private void updateGraph() {
		graph.refreshPlot();

		double[] yValues = new double[13];

		for (AbstractPowerItemController i : combined) {

			if (i.returnType().equals("Solar")) {

				int counter = 0;
				double avg = 0;
				solarNodes = solarPoints.getInterferenceZone(i.getLocation());

				for (Month m : Month.values()) {

					for (SolarDataNode s : solarNodes) {
						avg += s.getMonthlyAverageSolarIntensity(m);
					}
					avg /= 12;

					i.setMonthlyVar(avg);

					yValues[counter++] = i.returnPower();
				}

				i.setMonthlyVar(solarNodes.get(0).getMonthlyAverageSolarIntensity(Month.ANN));
				yValues[12] = i.returnPower();
				graph.addSeries(yValues, i.getDisplayID());

				solarNames.put(i.getID(), i.getDisplayID());
			}
		}
		graph.replotCurrentData();
	}

	/**
	 * Sets up a solar model instance based on the user inputs
	 */
	public boolean inputsToSolarModel(SolarModel model) {
		SolarSubComposite sc = primaryComposite.getSolarSubComposite();
		//Check values against proper formats
		if (!(matchesDoubleCharSequence(sc.getAreaText()) && matchesDoubleCharSequence(sc.getPowerLossCoefficientText())
				&& matchesDoubleCharSequence(sc.getSolarPowerEfficienyText())
				&& matchesDoubleCharSequence(sc.getCostText()) && matchesDoubleCharSequence(sc.getLatText())
				&& matchesDoubleCharSequence(sc.getLongText()) && matchesDoubleCharSequence(sc.getNumberText()))) {

			console.addToConsole("Error: Some or all inputs are incomplete or non numerical in form!", true);
			return false;
		}

		if (!matchesIntegerCharSequence(sc.getNumberText())) {
			console.addToConsole("Error: Number of Solar Panels requires a whole number input", true);
			return false;
		}

		//No errors thus construct the instance
		model.setArea(Double.parseDouble(sc.getAreaText()));
		model.setPlCoeff(Double.parseDouble(sc.getPowerLossCoefficientText()));
		model.setYield(Double.parseDouble(sc.getSolarPowerEfficienyText()));
		model.setCostPerUnit(Double.parseDouble(sc.getCostText()));
		model.setNumberOfPanels(Double.parseDouble(sc.getNumberText()));
		model.setLocation(new Location(Double.parseDouble(sc.getLatText()), Double.parseDouble(sc.getLongText())));
		return true;
	}

	/**
	 * Sets up a wind model instance based on the user inputs
	 */
	private boolean inputsToWindModel(WindModel model) {
		WindSubComposite wc = primaryComposite.getWindSubComposite();
		//Error Check
		if (!(matchesDoubleCharSequence(wc.getAirDensityText()) && matchesDoubleCharSequence(wc.getBladeRadiusText())
				&& matchesDoubleCharSequence(wc.getEfficiencyText()) && matchesDoubleCharSequence(wc.getCostText())
				&& matchesDoubleCharSequence(wc.getLatText()) && matchesDoubleCharSequence(wc.getLongText())
				&& matchesDoubleCharSequence(wc.getNumberText()))) {
			console.addToConsole("Error: Some or all inputs are incomplete or non numerical in form!", true);
			return false;
		}

		if (!matchesIntegerCharSequence(wc.getNumberText())) {
			console.addToConsole("Error: Number of Wind Turbines requires a whole number input", true);
			return false;
		}

		//Construct the model
		model.setAirDensity(Double.parseDouble(wc.getAirDensityText()));
		model.setRadius(Double.parseDouble(wc.getBladeRadiusText()));
		model.setEffCoeff(Double.parseDouble(wc.getEfficiencyText()));
		model.setCostPerUnit(Double.parseDouble(wc.getCostText()));
		model.setQuantity(Integer.parseInt(wc.getNumberText()));
		model.setLocation(new Location(Double.parseDouble(wc.getLatText()), Double.parseDouble(wc.getLongText())));
		return true;
	}

	/**
	 * Matches a string to see if it represents a double
	 * 
	 * @param s
	 *            String to be matched
	 * @return true if it matches a double else return false
	 */
	public boolean matchesDoubleCharSequence(String s) {
		s = s.trim();
		if (s.isEmpty())
			return false;
		Matcher m = invalidDouble.matcher(s);
		return !m.find();

	}

	/**
	 * Matches a string to see if it represents an int
	 * 
	 * @param s
	 *            String to be matched
	 * @return true if it matches an int else return false
	 */
	public boolean matchesIntegerCharSequence(String s) {
		s = s.trim();
		if (s.isEmpty())
			return false;
		Matcher m = invalidInteger.matcher(s);
		return !m.find();

	}

	/**
	 * Takes in lists of table items, merges them, then sorts them based on
	 * selection int
	 * 
	 * @param originalS
	 *            List of solar items
	 * @param originalW
	 *            List of wind items
	 * @param select
	 *            0 for total power, 1 for total power/total cost
	 */
	public void sortTable(List<AbstractPowerItemController> originalS, List<AbstractPowerItemController> originalW,
			int select) {

		combined = originalS;
		combined.addAll(originalW);

		Mergesort.sort(combined, select);

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
