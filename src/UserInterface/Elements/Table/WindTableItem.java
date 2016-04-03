package UserInterface.Elements.Table;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import Constants.FilePaths;

public class WindTableItem{

	private TableEditor editor;
	private Button removeItemButton;
	private Table table;
	private TableItem item;
	
	public WindTableItem(Table table, int SWTargs) {
		item = new TableItem(table, SWTargs);
		this.table = table;
		editor = new TableEditor(table);
		removeItemButton = new Button(table, SWT.NULL);

		editor.grabHorizontal = true;

		InputStream TrashInputStream = SolarTableComposite.class.getResourceAsStream(FilePaths.TRASH_ICON_PATH);

		Image trashIcon = new Image(item.getDisplay(), TrashInputStream);
		ImageData trashIconData = trashIcon.getImageData();
		trashIconData = trashIconData.scaledTo(20, 20);
		trashIcon = new Image(item.getDisplay(), trashIconData);
		removeItemButton.setImage(trashIcon);

		editor.setEditor(removeItemButton, item, 7);
	}
	
	//Behaviour
	public void destroy(){
		removeItemButton.dispose();
		editor.dispose();
		item.dispose();
	}
	
	//SETTERS
	//TODO Align these to their proper columns
	public void setLongitude(String s){
		item.setText(0, s);
	}

	public void setLatitude(String s){
		item.setText(1, s);
	}
	
	public void setRadius(String s){
		item.setText(2, s);
	}
	
	public void setAirDensity(String s){
		item.setText(3, s);
	}
	
	
	public void setEfficiency(String s){
		item.setText(4, s);
	}

	public void setNumberOfTurbines(String s){
		item.setText(5, s);
	}
	
	public void setCostPerTurbine(String s){
		item.setText(6, s);
	}
	
	//GETTERS
	public String getLongitude(){
		return item.getText(0);
	}

	public String getLatitude(){
		return item.getText(1);
	}
	
	public String getRadius(){
		return item.getText(2);
	}
	
	public String getAirDensity(){
		return item.getText(3);
	}
	
	
	public String getEfficiency(){
		return item.getText(4);
	}

	public String getNumberOfTurbines(){
		System.out.println(item.getText(5));
		return item.getText(5);
	}
	
	public String getCostPerTurbine(){
		return item.getText(6);
	}
	
	
	public Button getRemoveButton(){
		return this.removeItemButton;
	}

	public Table getTable() {
		return this.table;
	}
	
	
}
