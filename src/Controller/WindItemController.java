package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;

import PowerModels.WindModel;
import UserInterface.Elements.Table.OutputTableItem;
import PowerModels.Graph.Location;
import UserInterface.Elements.Table.WindTableItem;

public class WindItemController extends AbstractPowerItemController{

	private final WindTableItem item;
	private final WindModel model;
	private final UUID uuid;
	private OutputTableItem output;
	
	private boolean outputted = false;
	
	public WindItemController(WindTableItem item, WindModel model, UUID uuid){
		this.item = item;
		this.model = model;
		this.uuid = uuid;

	}
	
	private void v2mSetEfficiency(){
		model.setEffCoeff(Double.valueOf(item.getEfficiency()));
	}
	
	private void v2mSetRadius(){
		model.setRadius(Double.parseDouble(item.getRadius()));
	}
	
	private void v2mSetAirDensity(){
		model.setAirDensity(Double.parseDouble(item.getAirDensity()));
	}
	
	private void v2mSetNumberOfTurbines(){
		model.setQuantity(Double.parseDouble(item.getNumberOfTurbines()));
	}
	
	private void v2mSetCostPerUnit(){
		model.setCostPerUnit(Double.parseDouble(item.getCostPerTurbine()));
	}
	
	private void v2mSetLocation(){
		model.setLocation(new Location(
				Math.round(Double.parseDouble(item.getLatitude())),
				Math.round(Double.parseDouble(item.getLongitude()))));
	}
	
	private void m2vSetEfficiency(){
		item.setEfficiency(Double.toString(model.getEffCoeff()));
	}
	
	private void m2vSetAirDensity(){
		item.setAirDensity(Double.toString(model.getAirDensity()));
	}
	
	private void m2vSetRadius(){
		item.setRadius(Double.toString(model.getRadius()));
	}
	
	private void m2vSetNumberOfPanels(){
		item.setNumberOfTurbines(Double.toString(model.getQuantity()));
	}
	
	private void m2vSetCostPerUnit(){
		item.setCostPerTurbine(Double.toString(model.getCostPerUnit()));
	}
	
	private void m2vSetLocation(){
		item.setLatitude(Double.toString(model.getLocation().getLatitude()));
		item.setLongitude(Double.toString(model.getLocation().getLongitude()));
	}
	
	//*****************Output table stuff*****************************************//
	private void m2vOutputType(){
		output.setType(model.getType());
	}
	
	private void m2vOutputLatLong(){
		output.setLat(Double.toString(model.getLocation().getLatitude()));
		output.setLong(Double.toString(model.getLocation().getLongitude()));
	}
	
	private void m2vOutputPower(){
		output.setPowerOut(Double.toString(model.calculatePower()));
	}
	
	private void m2vOutputQty(){
		output.setNumberOf(Integer.toString(model.getQuantity()));
	}
	
	private void m2vOutputCostPerUnit(){
		output.setCostPer(Double.toString(model.getCostPerUnit()));
	}
	
	public void buildOutput(OutputTableItem output){
		this.output = output;
	}
	
	/**
	 * Returns whether or not an output has been made for item, to avoid duplication
	 * @return true if it's been outputted, false if it hasn't
	 */
	public boolean outputted(){
		return outputted;
	}
	
	@Override
	public void analyze() {
		model.calculatePower();	
		v2mSetAirDensity();
		v2mSetEfficiency();
		v2mSetRadius();
		v2mSetCostPerUnit();
		v2mSetLocation();
		v2mSetNumberOfTurbines();
		
		item.getTable().layout();
		item.getTable().pack();
	}

	@Override
	public UUID destroy() {
		this.item.destroy();
		this.output.destroy();
		return this.uuid;
	}
	
	/**
	 * Removes from output table
	 */
	public void destroyOutput(){
		this.output.destroy();
	}

	@Override
	public Button getRemoveButton() {
		return item.getRemoveButton();
	}

	/**
	 * Updates first table with data inputted by user
	 */
	@Override
	public void updateModelStateToView() {
		m2vSetRadius();
		m2vSetEfficiency();
		m2vSetAirDensity();
		m2vSetCostPerUnit();
		m2vSetLocation();
		m2vSetNumberOfPanels();
		
		item.getTable().layout();
	}
	
	/**
	 * Updates output table with relevant data
	 */
	@Override
	public void updateOutputTable(){
		m2vOutputType();
		m2vOutputLatLong();
		m2vOutputPower();
		m2vOutputQty();
		m2vOutputCostPerUnit();
		
		output.getTable().layout();
		
		outputted = true;
		
	}

	/**
	 * Compares total power output with another item (greatest to least)
	 * @param o item to compare against
	 * @return 1 for lesser than, 0 for equal, -1 for greater than
	 */
	@Override
	public double returnPower() {
		return model.calculatePower();
	}

	/**
	 * Compares total power output over total cost with another item (greatest to least)
	 * @param o item to compare against
	 * @return 1 for lesser than, 0 for equal, -1 for greater than
	 */
	public double returnPowerPerDollar() {
		return model.getPower()/(model.getCostPerUnit()*model.getQuantity());
	}
	
	@Override
	public int compareTo(AbstractPowerItemController o) {
		if(this.returnPower() < o.returnPower())
			return 1;
		else if(this.returnPower() == o.returnPower())
			return 0;
		else
			return -1;
	}

	@Override
	public int comparePerDollar(AbstractPowerItemController o) {
		if(this.returnPowerPerDollar() < o.returnPowerPerDollar())
			return 1;
		else if(this.returnPower() == o.returnPower())
			return 0;
		else
			return -1;
	}


	

}
