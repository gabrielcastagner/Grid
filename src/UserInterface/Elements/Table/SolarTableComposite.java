package UserInterface.Elements.Table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class SolarTableComposite extends ScrolledComposite {

	private Table inputTable;
	private String[] columnHeaders = { "Longitude", "Latitude", "Area(m^2)", "Power Loss", "Efficiency", "Quantity",
			"Cost Per Unit($)", "" };
	// private

	/**
	 * 
	 * @param arg0
	 *            parent constructor
	 * @param arg1
	 *            composite styles
	 * @param bg
	 *            background colors
	 * @param fg
	 *            Foreground colors
	 */
	public SolarTableComposite(Composite arg0, int arg1, Color bg, Color fg) {

		super(arg0, arg1);
		int columnwidth = ((arg0.getBounds().width - 80) / (columnHeaders.length - 1));

		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
		inputTable = new Table(this,
				SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.NO_SCROLL);
		inputTable.setLinesVisible(true);
		inputTable.setHeaderVisible(true);
		inputTable.setTouchEnabled(true);
		setMinSize(inputTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		inputTable.setBackground(bg);
		inputTable.setForeground(fg);

		for (int i = 0; i < columnHeaders.length; i++) {
			TableColumn column = new TableColumn(inputTable, SWT.NONE);
			column.setResizable(false);
			column.setWidth((i == columnHeaders.length - 1) ? 50 : columnwidth);
			column.setText(columnHeaders[i]);
		}
		setContent(inputTable);

	}

	/**
	 * adds table item.
	 * 
	 * @return Solartableitem
	 */
	public SolarTableItem addNewItemtoTableSolar() {
		SolarTableItem w = new SolarTableItem(inputTable, SWT.NULL);
		pack();
		return w;
	}

	/**
	 * removes item
	 * 
	 * @param rowNumber
	 *            item number
	 */
	public void removeItem(int rowNumber) {
		inputTable.remove(rowNumber);
		inputTable.pack();
	}

	// ======================= Getters =====================================//
	/**
	 * Gets the table
	 * 
	 * @return input table
	 */
	public Table getTable() {
		return inputTable;
	}

	/**
	 * gets a table item
	 * 
	 * @param itemNumber
	 * @return table item
	 */
	public TableItem getTableItem(int itemNumber) {
		return inputTable.getItem(itemNumber);
	}
}
