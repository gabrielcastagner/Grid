package UserInterface.Elements.Table;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class OutputTableItem{

	private TableItem item;
	private Table outputTable;
	
	public OutputTableItem(Table table, int SWTargs) {
		outputTable = table;
		item = new TableItem(table, SWTargs);
	}
	
	//SETTERS
	
	public void setType(String s){
		item.setText(0, s);
	}
	
	public void setLong(String s){
		item.setText(1, s);
	}
	
	public void setLat(String s){
		item.setText(2, s);
	}
	
	public void setPowerOut(String s){
		item.setText(3, s);
	}
	
	public void setNumberOf(String s){
		item.setText(4, s);
	}
	public void setCostPer(String s){
		item.setText(5, s);
	}
	
	//Behaviour
	public void destroy(){
		item.dispose();
		outputTable.pack();
	}
	
	//"Type", "Longitude", "Latitude", "Power Output", "#", "Cost Per Unit"
	//GETTERS
	public String getType(){
		return item.getText(0);
	}
	
	public String getLong(){
		return item.getText(1);
	}
	
	public String getLat(){
		return item.getText(2);
	}
	
	public String getPowerOut(){
		return item.getText(3);
	}
	
	public String getNumberOf(){
		return item.getText(4);
	}
	public String getCostPer(){
		return item.getText(5);
	}
	
	public Table getTable(){
		return this.outputTable;
	}

}
