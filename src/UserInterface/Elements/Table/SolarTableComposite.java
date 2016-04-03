package UserInterface.Elements.Table;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class SolarTableComposite extends ScrolledComposite {

	private final String TRASH_ICON_PATH = "/trash.png";

	private Table inputTable;
	private String[] columnHeaders = { "Longitude", "Latitude", "Area(m^2)", "Power Loss", "Efficiency", "#",
			"Cost Per Unit($)", "" };
	// private

	public SolarTableComposite(Composite arg0, int arg1, Color bg, Color fg) {

		super(arg0, arg1);
		int columnwidth = ((arg0.getBounds().width - 80) / (columnHeaders.length - 1));

		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
		inputTable = new Table(this, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK | SWT.V_SCROLL | SWT.NO_SCROLL );
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

	public SolarTableItem addNewItemtoTableSolar() {
		SolarTableItem w = new SolarTableItem(inputTable, SWT.NULL);
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
