package Controller;

import java.util.UUID;
import org.eclipse.swt.widgets.Button;
import PowerModels.SolarModel;
import UserInterface.Elements.Table.OutputTableItem;
import UserInterface.Elements.Table.SolarTableItem;

public class SolarItemController implements IPowerItemController{
	
	private final SolarTableItem item;
	private final SolarModel model;
	private final UUID uuid;
	private final OutputTableItem output;
	
	public SolarItemController(SolarTableItem item, SolarModel model, UUID uuid, OutputTableItem output){
		this.item = item;
		this.model = model;
		this.uuid = uuid;
		this.output = output;
	}
	
	private void v2mSetEfficiency(){
		model.setYield(Double.valueOf(item.getEfficiency()));
	}
	
	private void v2mSetPowerLossCoeff(){
		model.setPlCoeff(Double.parseDouble(item.getPowerLoss()));
	}
	
	private void v2mSetArea(){
		model.setArea(Double.parseDouble(item.getArea()));
	}
	
	private void m2vSetEfficiency(){
		item.setEfficiency(Double.toString(model.getYield()));
	}
	
	private void m2vSetPowerLossCoeff(){
		item.setPowerLoss(Double.toString(model.getPlCoeff()));
	}
	
	private void m2vSetArea(){
		item.setArea(Double.toString(model.getArea()));
	}

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
		
		model.calculatePower();	
		
		item.getTable().layout();
		//item.getTable().pack();
	}

	@Override
	public void updateViewToModelState() {
		m2vSetArea();
		m2vSetEfficiency();
		m2vSetPowerLossCoeff();
		
		item.getTable().layout();
		output.getTable().layout();
	}
}
