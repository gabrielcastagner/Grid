package UserInterface.Elements.Table;

import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class OutputTableItem {

	private TableItem item;
	private Table outputTable;

	/**
	 * 
	 * @param table
	 *            Parent table
	 * @param SWTargs
	 */
	public OutputTableItem(Table table, int SWTargs) {
		outputTable = table;
		item = new TableItem(table, SWTargs);
	}

	// SETTERS
	/**
	 * Set the type
	 * 
	 * @param s
	 *            content
	 */
	public void setType(String s) {
		item.setText(0, s);
	}

	/**
	 * Set the Longitude
	 * 
	 * @param s
	 *            content
	 */
	public void setLong(String s) {
		item.setText(1, s);
	}

	/**
	 * Set the Latitude
	 * 
	 * @param s
	 *            content
	 */
	public void setLat(String s) {
		item.setText(2, s);
	}

	/**
	 * Set the PowerOut
	 * 
	 * @param s
	 *            content
	 */
	public void setPowerOut(String s) {
		item.setText(3, s);
	}

	/**
	 * Set the Quantity
	 * 
	 * @param s
	 *            content
	 */
	public void setNumberOf(String s) {
		item.setText(4, s);
	}

	/**
	 * Set the Costper
	 * 
	 * @param s
	 *            content
	 */
	public void setCostPer(String s) {
		item.setText(5, s);
	}

	/**
	 * Set the Cost to Power
	 * 
	 * @param s
	 *            content
	 */
	public void setPowerPerDollar(String s) {
		item.setText(6, s);
	}

	// Behaviour
	/**
	 * Destroys the items
	 */
	public void destroy() {
		item.dispose();
		outputTable.pack();
	}

	// "Type", "Longitude", "Latitude", "Power Output", "#", "Cost Per Unit"
	// GETTERS
	/**
	 * @return Gets Type
	 */
	public String getType() {
		return item.getText(0);
	}

	/**
	 * @return Gets Type
	 */
	public String getLong() {
		return item.getText(1);
	}

	/**
	 * @return Gets Type
	 */
	public String getLat() {
		return item.getText(2);
	}

	/**
	 * @return Gets Type
	 */
	public String getPowerOut() {
		return item.getText(3);
	}

	/**
	 * @return Gets Type
	 */
	public String getNumberOf() {
		return item.getText(4);
	}

	/**
	 * @return Gets Type
	 */
	public String getCostPer() {
		return item.getText(5);
	}

	/**
	 * @return Gets Cost/unit
	 */
	public String getPowerPerDollar() {
		return item.getText(6);
	}

	/**
	 * @return Gets the output table
	 */
	public Table getTable() {
		return this.outputTable;
	}

}
