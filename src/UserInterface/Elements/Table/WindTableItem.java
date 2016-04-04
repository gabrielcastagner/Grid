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
 * WindTableItem instance
 */
public class WindTableItem {

	private TableEditor editor;
	private Button removeItemButton;
	private Table table;
	private TableItem item;

	/**
	 * Wind table item instance
	 * 
	 * @param table
	 *            table this will belong to
	 * @param SWTargs
	 *            SWT arguments for the table item, Default pass SWT.NONE
	 */
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

	// Behaviour
	/**
	 * Frees all memory for this item and removes it from the table
	 */
	public void destroy() {
		removeItemButton.dispose();
		editor.dispose();
		item.dispose();
	}

	// SETTERS
	/**
	 * @param s set Longitude to s on the view
	 */
	public void setLongitude(String s) {
		item.setText(0, s);
	}
	
	/**
	 * @param s set Latitude to s on the view
	 */
	public void setLatitude(String s) {
		item.setText(1, s);
	}

	/**
	 * @param s set the blade radius to s on the view
	 */
	public void setRadius(String s) {
		item.setText(2, s);
	}

	/**
	 * @param s set Air density to s on the view
	 */
	public void setAirDensity(String s) {
		item.setText(3, s);
	}

	/**
	 * @param s set efficiency to s on the view
	 */
	public void setEfficiency(String s) {
		item.setText(4, s);
	}

	/**
	 * @param s set number of turbines to s on the view
	 */
	public void setNumberOfTurbines(String s) {
		item.setText(5, s);
	}

	/**
	 * @param s set cost of a turbine to s on the view
	 */
	public void setCostPerTurbine(String s) {
		item.setText(6, s);
	}

	// GETTERS
	/**
	 * @return items longitude
	 */
	public String getLongitude() {
		return item.getText(0);
	}

	/**
	 * @return items latitude
	 */
	public String getLatitude() {
		return item.getText(1);
	}

	/**
	 * @return items blade radius
	 */
	public String getRadius() {
		return item.getText(2);
	}

	/**
	 * @return items air density
	 */
	public String getAirDensity() {
		return item.getText(3);
	}

	/**
	 * @return items efficiency
	 */
	public String getEfficiency() {
		return item.getText(4);
	}

	/**
	 * @return items number of turbines
	 */
	public String getNumberOfTurbines() {
		return item.getText(5);
	}

	/**
	 * @return items cost per turbine
	 */
	public String getCostPerTurbine() {
		return item.getText(6);
	}

	/**
	 * @return button to remove item
	 */
	public Button getRemoveButton() {
		return this.removeItemButton;
	}

	/**
	 * @return Table the item belongs to
	 */
	public Table getTable() {
		return this.table;
	}

}
