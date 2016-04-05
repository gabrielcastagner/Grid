package UserInterface.Elements.Table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class OutputTableComposite extends ScrolledComposite {

	private Table inputTable;
	private String[] columnHeaders = {"ID", "Type", "Longitude", "Latitude", "Power Output", "Quantity", "Cost Per Unit",
			"Power/Cost" };
	
	/**
	 * 
	 * @param arg0
	 *            parent composite
	 * @param arg1
	 *            style ints
	 * @param bg
	 *            background color
	 * @param fg
	 *            foreground color
	 */
	public OutputTableComposite(Composite arg0, int arg1, Color bg, Color fg) {

		super(arg0, arg1);
		int columnwidth = ((arg0.getBounds().width) / (columnHeaders.length));

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
			column.setWidth((i == columnHeaders.length - 1) ? 150 : columnwidth);
			column.setText(columnHeaders[i]);
		}
		setContent(inputTable);

	}

	/**
	 * @return adds a new item to the table.
	 */
	public OutputTableItem addNewItemtoTableSolar() {
		OutputTableItem w = new OutputTableItem(inputTable, SWT.NULL);
		pack();
		return w;
	}

	/**
	 * @param rowNumber
	 *            removes the desired table item
	 */
	public void removeItem(int rowNumber) {
		inputTable.remove(rowNumber);
		inputTable.pack();
	}

	// ======================= Getters =====================================//
	/**
	 * @return Gets the table of data
	 */
	public Table getTable() {
		return inputTable;
	}

	/**
	 * @param itemNumber
	 * @return gets the desired item
	 */
	public TableItem getTableItem(int itemNumber) {
		return inputTable.getItem(itemNumber);
	}

	// ==============================Destroy Function========================//

}
