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

public class SolarTableItem extends TableItem {

	private TableEditor editor;
	private Button removeItemButton;

	public SolarTableItem(Table table, int SWTargs) {
		super(table, SWTargs);
		editor = new TableEditor(table);
		removeItemButton = new Button(table, SWT.NULL);

		editor.grabHorizontal = true;

		InputStream TrashInputStream = SolarTableComposite.class.getResourceAsStream(FilePaths.TRASH_ICON_PATH);

		Image trashIcon = new Image(getDisplay(), TrashInputStream);
		ImageData trashIconData = trashIcon.getImageData();
		trashIconData = trashIconData.scaledTo(20, 20);
		trashIcon = new Image(this.getDisplay(), trashIconData);
		removeItemButton.setImage(trashIcon);

		editor.setEditor(removeItemButton, this, 6);
		//input.setText(0, new Button(inputTable, SWT.NONE));
		//setText(2, "Yes");
		//setText(3, "No");
		//setText(4, "A table item");
	}
	
	//Behaviour
	public void destroy(){
		removeItemButton.dispose();
		editor.dispose();
		this.dispose();
	}
	
	//SETTERS
	//TODO Align these to their proper columns
	public void setEfficiency(String s){
		setText(1, s);
	}
	
	public void setArea(String s){
		setText(2, s);
	}
	
	public void setRadius(String s){
		setText(3, s);
	}
	
	public void setPowerLoss(String s){
		setText(4, s);
	}
	
	public void getSolarExposure(String s){
		setText(5, s);
	}
	
	//GETTERS
	public String getEfficiency(){
		return getText(1);
	}
	
	public String getArea(){
		return getText(2);
	}
	
	public String getRadius(){
		return getText(3);
	}
	
	public String getPowerLoss(){
		return getText(4);
	}
	
	public String getSolarExposure(){
		return getText(5);
	}
	
	public Button getRemoveButton(){
		return this.removeItemButton;
	}
	
	
}
