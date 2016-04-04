package UserInterface.Elements.Table;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class WindTableComposite extends ScrolledComposite {



	private Table inputTable;
	private String[] columnHeaders = { "Longitude", "Latitude", "Blade (m) ","Air Density", "Efficiency", "Quantity",
			"Cost Per Unit($)", "" };
	// private

	public WindTableComposite(Composite arg0, int arg1, Color bg, Color fg) {

		super(arg0, arg1);
		int columnwidth = ((arg0.getBounds().width - 80) / (columnHeaders.length - 1));

		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
		inputTable = new Table(this,
				SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.V_SCROLL | SWT.NO_SCROLL);
		inputTable.setLinesVisible(true);
		inputTable.setHeaderVisible(true);
		inputTable.setTouchEnabled(true);
		setMinSize(inputTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		inputTable.setBackground(bg);
		inputTable.setForeground(fg);
		inputTable.setBounds(arg0.getBounds());

		for (int i = 0; i < columnHeaders.length; i++) {
			TableColumn column = new TableColumn(inputTable, SWT.NONE);
			column.setResizable(false);
			column.setWidth((i == columnHeaders.length - 1) ? 50 : columnwidth);
			column.setText(columnHeaders[i]);
		}
		setContent(inputTable);

	}

	public WindTableItem addNewItemtoTableSolar() {
		WindTableItem w = new WindTableItem(inputTable, SWT.NULL);
		pack();
		return w;
	}

	public void removeItem(int rowNumber) {
		inputTable.remove(rowNumber);
		inputTable.pack();
	}

	// ======================= Getters =====================================//
	public Table getTable() {
		return inputTable;
	}

	public TableItem getTableItem(int itemNumber) {
		return inputTable.getItem(itemNumber);
	}

	// ==============================Destroy Function========================//

}
