package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;

import PowerModels.WindModel;
import UserInterface.Elements.Table.WindTableItem;

public class WindItemController implements IPowerItemController{

	private final WindTableItem item;
	private final WindModel model;
	private final UUID uuid;
	
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
		model.setAirDensity(Double.parseDouble(item.getArea()));
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
	
	@Override
	public void analyze() {
		model.calculatePower();	
		v2mSetAirDensity();
		v2mSetEfficiency();
		v2mSetRadius();
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
		
		item.getTable().layout();
		
	}
	
	

}
