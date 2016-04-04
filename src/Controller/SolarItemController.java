package Controller;

import java.util.UUID;
import org.eclipse.swt.widgets.Button;
import PowerModels.SolarModel;
import PowerModels.Graph.Location;
import UserInterface.Elements.Table.OutputTableItem;
import UserInterface.Elements.Table.SolarTableItem;

public class SolarItemController extends AbstractPowerItemController implements Comparable<AbstractPowerItemController>{
	
	private final SolarTableItem item;
	private final SolarModel model;
	private final UUID uuid;
	private OutputTableItem output;
	
	private boolean outputted = false;
	
	public SolarItemController(SolarTableItem item, SolarModel model, UUID uuid){
		this.item = item;
		this.model = model;
		this.uuid = uuid;
	}
	
	//View to Model link
	private void v2mSetEfficiency(){
		model.setYield(Double.valueOf(item.getEfficiency()));
	}
	
	private void v2mSetPowerLossCoeff(){
		model.setPlCoeff(Double.parseDouble(item.getPowerLoss()));
	}
	
	private void v2mSetArea(){
		model.setArea(Double.parseDouble(item.getArea()));
	}
	
	private void v2mSetNumberOfPanels(){
		model.setNumberOfPanels(Double.parseDouble(item.getNumberOfPanels()));
	}
	
	private void v2mSetCostPerUnit(){
		model.setCostPerUnit(Double.parseDouble(item.getCostPerUnit()));
	}
	
	private void v2mSetLocation(){
		model.setLocation(new Location(
				Math.round(Double.parseDouble(item.getLatitude())),
				Math.round(Double.parseDouble(item.getLongitude()))));
	}
	
	//Model to View link
	private void m2vSetEfficiency(){
		item.setEfficiency(Double.toString(model.getYield()));
	}
	
	private void m2vSetPowerLossCoeff(){
		item.setPowerLoss(Double.toString(model.getPlCoeff()));
	}
	
	private void m2vSetArea(){
		item.setArea(Double.toString(model.getArea()));
	}

	private void m2vSetNumberOfPanels(){
		item.setNumberOfPanels(Double.toString(model.getNumberOfPanels()));
	}
	
	private void m2vSetCostPerUnit(){
		item.setCostPerUnit(Double.toString(model.getCostPerUnit()));
	}
	
	private void m2vSetLocation(){
		item.setLatitude(Double.toString(model.getLocation().getLatitude()));
		item.setLongitude(Double.toString(model.getLocation().getLongitude()));
	}
	
	//*****************Output table stuff*****************************************//
	public void buildOutput(OutputTableItem output){
		this.output = output;
	}
	
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
		output.setNumberOf(Integer.toString(model.getNumberOfPanels()));
	}
	
	private void m2vOutputCostPerUnit(){
		output.setCostPer(Double.toString(model.getCostPerUnit()));
	}
	
	/**
	 * Returns whether or not an output has been made for item, to avoid duplication
	 * @return true if it's been outputted, false if it hasn't
	 */
	public boolean outputted(){
		return outputted;
	}
	
	//Functions controlling the model and View
	@Override
	public UUID destroy(){
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
	
	//Pass the button to the main controller
	@Override
	public Button getRemoveButton(){
		return item.getRemoveButton();
	}
	
	@Override
	public void analyze() {
		v2mSetArea();
		v2mSetEfficiency();
		v2mSetPowerLossCoeff();
		v2mSetNumberOfPanels();
		v2mSetCostPerUnit();
		v2mSetLocation();
		
		model.calculatePower();	
		item.getTable().layout();
		//item.getTable().pack();
	
	}

	/**
	 * Updates first table with data inputted by user
	 */
	@Override
	public void updateModelStateToView() {
		m2vSetArea();
		m2vSetEfficiency();
		m2vSetPowerLossCoeff();
		m2vSetNumberOfPanels();
		m2vSetCostPerUnit();
		m2vSetLocation();
		
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
	public int compareTo(AbstractPowerItemController o) {
		if(this.returnPower() < o.returnPower())
			return 1;
		else if(this.returnPower() == o.returnPower())
			return 0;
		else
			return -1;
	}
	
	/**
	 * Compares total power output over total cost with another item (greatest to least)
	 * @param o item to compare against
	 * @return 1 for lesser than, 0 for equal, -1 for greater than
	 */
	public int comparePerDollar(AbstractPowerItemController o){
		if(this.returnPowerPerDollar() < o.returnPowerPerDollar())
			return 1;
		else if(this.returnPower() == o.returnPower())
			return 0;
		else
			return -1;
	}

	@Override
	public double returnPower() {
		return model.getPower();
	}

	@Override
	public double returnPowerPerDollar() {
		return model.getPower()/(model.getCostPerUnit()*model.getNumberOfPanels());
	}

}
