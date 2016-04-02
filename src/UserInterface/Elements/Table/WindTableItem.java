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

		editor.setEditor(removeItemButton, item, 5);
	}
	
	//Behaviour
	public void destroy(){
		removeItemButton.dispose();
		editor.dispose();
		item.dispose();
	}
	
	//SETTERS
	//TODO Align these to their proper columns
	public void setEfficiency(String s){
		item.setText(0, s);
	}
	
	public void setArea(String s){
		item.setText(1, s);
	}
	
	public void setRadius(String s){
		item.setText(2, s);
	}
	
	public void setAirDensity(String s){
		item.setText(3, s);
	}
	
	public void getSolarExposure(String s){
		//item.setText(4, s);
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
	
	public String getAirDensity(){
		return item.getText(4);
	}
	
	
	public Button getRemoveButton(){
		return this.removeItemButton;
	}

	public Table getTable() {
		return this.table;
	}
	
	
}
