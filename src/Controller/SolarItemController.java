package Controller;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.eclipse.swt.widgets.Item;

import PowerModels.SolarModel;
import UserInterface.Elements.Table.SolarTableItem;

public class SolarItemController {
	
	private final SolarTableItem item;
	private final SolarModel model;
	
	public SolarItemController(SolarTableItem item, SolarModel model){
		this.item = item;
		this.model = model;
	}
	
	
	private enum MatchTableFields{
		//TODO finish linking
		/*EFFICIENCY(SolarTableItem::setEfficiency, SolarModel::),
		AREA,
		POWER_LOSS,
		SOLAR_EXPOSURE;
		//COST
		
		private Consumer<String> tableSetter;
		private Consumer<String> modelSetter;
		private Supplier<Object> tableGetter;
		private Supplier<Object> modelGetter;
		
		private MatchTableFields(Consumer tableSetter, Consumer modelSetter, 
				Supplier tableGetter, Supplier modelGetter){
			this.tableSetter = tableSetter;
			this.tableGetter = tableGetter;
			this.modelSetter = modelSetter;
			this.modelGetter = modelGetter;
		}
		
		*/
	}
	
	
	
}
