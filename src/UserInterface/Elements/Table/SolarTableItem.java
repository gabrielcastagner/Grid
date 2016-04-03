package UserInterface.Elements.Table;

import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

import Constants.FilePaths;

public class SolarTableItem{

	private TableEditor editor;
	private Button removeItemButton;
	private TableItem item;
	private Table solarTable;
	
	public SolarTableItem(Table table, int SWTargs) {
		solarTable = table;
		item = new TableItem(table, SWTargs);
		
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
		solarTable.pack();
	}
	
	//SETTERS
	
	public void setLatitude(String s){
		item.setText(0, s);
	}

	public void setLongitude(String s){
		item.setText(1, s);
	}
	
	public void setArea(String s){
		item.setText(2, s);
	}

	public void setPowerLoss(String s){
		item.setText(3, s);
	}
	
	public void setEfficiency(String s){
		item.setText(4, s);
	}
	
	public void setNumberOfPanels(String s){
		item.setText(5, s);
	}
	
	public void setCostPerUnit(String s){
		item.setText(6, s);
	}
	
	//GETTERS
	public String getLatitude(){
		return item.getText(0);
	}

	public String getLongitude(){
		return item.getText(1);
	}
	
	public String getArea(){
		return item.getText(2);
	}

	public String getPowerLoss(){
		return item.getText(3);
	}
	
	public String getEfficiency(){
		return item.getText(4);
	}
	
	public String getNumberOfPanels(){
		return item.getText(5);
	}
	
	public String getCostPerUnit(){
		return item.getText(6);
	}

	public Table getTable() {
		return solarTable;
	}

	public Button getRemoveButton() {
		return removeItemButton;
	}

}
