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

/**
 * SolarTableItem instance
 */
public class SolarTableItem{

	private TableEditor editor;
	private Button removeItemButton;
	private TableItem item;
	private Table solarTable;
	
	/**
	 * Solar table item instance
	 * 
	 * @param table
	 *            table this will belong to
	 * @param SWTargs
	 *            SWT arguments for the table item, Default pass SWT.NONE
	 */
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
	/**
	 * Frees all memory for this item and removes it from the table
	 */
	public void destroy(){
		removeItemButton.dispose();
		editor.dispose();
		item.dispose();
		solarTable.pack();
	}

	// SETTERS
	/**
	 * @param s set Longitude to s on the view
	 */
	public void setLongitude(String s){
		item.setText(0, s);
	}
	
	/**
	 * @param s set Latitude to s on the view
	 */
	public void setLatitude(String s){
		item.setText(1, s);
	}
	
	/**
	 * @param s set solar panel area to s on the view
	 */
	public void setArea(String s){
		item.setText(2, s);
	}

	/**
	 * @param s set the power loss coefficient to s on the view
	 */
	public void setPowerLoss(String s){
		item.setText(3, s);
	}
	
	/**
	 * @param s set efficiency to s on the view
	 */
	public void setEfficiency(String s){
		item.setText(4, s);
	}
	
	/**
	 * @param s set number of panels to s on the view
	 */
	public void setNumberOfPanels(String s){
		item.setText(5, s);
	}
	
	/**
	 * @param s set cost of a panel to s on the view
	 */
	public void setCostPerUnit(String s){
		item.setText(6, s);
	}
	
	//GETTERS
	/**
	 * @return items longitude
	 */
	public String getLatitude(){
		return item.getText(0);
	}

	/**
	 * @return items latitude
	 */
	public String getLongitude(){
		return item.getText(1);
	}
	
	/**
	 * @return items area
	 */
	public String getArea(){
		return item.getText(2);
	}

	/**
	 * @return items power loss coefficient
	 */
	public String getPowerLoss(){
		return item.getText(3);
	}
	
	/**
	 * @return items efficiency
	 */
	public String getEfficiency(){
		return item.getText(4);
	}
	
	/**
	 * @return items number of panels
	 */
	public String getNumberOfPanels(){
		return item.getText(5);
	}
	
	/**
	 * @return items cost per solar panel
	 */
	public String getCostPerUnit(){
		return item.getText(6);
	}

	/**
	 * @return button to remove item
	 */
	public Table getTable() {
		return solarTable;
	}

	/**
	 * @return Table the item belongs to
	 */
	public Button getRemoveButton() {
		return removeItemButton;
	}

}
