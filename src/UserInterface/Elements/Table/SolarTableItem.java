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

		editor.setEditor(removeItemButton, item, 6);
		//input.setText(0, new Button(inputTable, SWT.NONE));
		//setText(2, "Yes");
		//setText(3, "No");
		//setText(4, "A table item");
	}
	
	//Behaviour
	public void destroy(){
		removeItemButton.dispose();
		editor.dispose();
		item.dispose();
		solarTable.pack();
	}
	
	//SETTERS
	
	public void setEfficiency(String s){
		item.setText(1, s);
	}
	
	public void setArea(String s){
		item.setText(2, s);
	}
	
	public void setRadius(String s){
		item.setText(3, s);
	}
	
	public void setPowerLoss(String s){
		item.setText(4, s);
	}
	
	public void setSolarExposure(String s){
		item.setText(5, s);
	}
	
	
	//GETTERS
	public String getEfficiency(){
		return item.getText(1);
	}
	
	public String getArea(){
		return item.getText(2);
	}
	
	public String getRadius(){
		return item.getText(3);
	}
	
	public String getPowerLoss(){
		return item.getText(4);
	}
	
	public Button getRemoveButton(){
		return this.removeItemButton;
	}
	
	public Table getTable(){
		return this.solarTable;
	}

}
