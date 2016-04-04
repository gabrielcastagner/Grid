package Controller;

import java.util.UUID;
import org.eclipse.swt.widgets.Button;
import PowerModels.SolarModel;
import PowerModels.Graph.Location;
import UserInterface.Elements.Table.SolarTableItem;

public class SolarItemController implements IPowerItemController{
	
	private final SolarTableItem item;
	private final SolarModel model;
	private final UUID uuid;
	
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
		model.setNumberOfPanels(Integer.parseInt(item.getNumberOfPanels()));
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
	
	
	//Functions controlling the model and View
	@Override
	public UUID destroy(){
		this.item.destroy();
		return this.uuid;
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

	@Override
	public void updateViewToModelState() {
		m2vSetArea();
		m2vSetEfficiency();
		m2vSetPowerLossCoeff();
		m2vSetNumberOfPanels();
		m2vSetCostPerUnit();
		m2vSetLocation();
		
		item.getTable().layout();
		
	}
}
