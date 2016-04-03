package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;

import PowerModels.WindModel;
import UserInterface.Elements.Table.OutputTableItem;
import PowerModels.Graph.Location;
import UserInterface.Elements.Table.WindTableItem;

public class WindItemController implements IPowerItemController{

	private final WindTableItem item;
	private final WindModel model;
	private final UUID uuid;
	private final OutputTableItem output;
	
	public WindItemController(WindTableItem item, WindModel model, UUID uuid, OutputTableItem output){
		this.item = item;
		this.model = model;
		this.uuid = uuid;
		this.output = output;

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
	
	//Output table
	private void m2vOutputType(){
		output.setType("Solar");
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
		return this.uuid;
	}

	@Override
	public Button getRemoveButton() {
		return item.getRemoveButton();
	}

	@Override
	public void updateViewToModelState() {
		m2vSetRadius();
		m2vSetEfficiency();
		m2vSetAirDensity();
		m2vSetCostPerUnit();
		m2vSetLocation();
		m2vSetNumberOfPanels();
		
		item.getTable().layout();
	}
	
	@Override
	public void updateOutputTable(){
		m2vOutputType();
		m2vOutputLatLong();
		m2vOutputPower();
		m2vOutputQty();
		m2vOutputCostPerUnit();
		
		output.getTable().layout();
	}
	

}
