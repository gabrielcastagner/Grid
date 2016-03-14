package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableList extends ScrolledComposite {

	private Table inputTable;
	private TableItem input;
	private String[] columnHeaders = { "Type", "Input 1", "Input 2", "Input 3", "Input 4" };

	public TableList(Composite arg0, int arg1, Color bg, Color fg) {
		
		super(arg0, arg1);
		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
		inputTable = new Table(this, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		inputTable.setLinesVisible(true);
		inputTable.setHeaderVisible(true);
		setMinSize(inputTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		inputTable.setBackground(bg);
		inputTable.setForeground(fg);
		
		
		for (int i = 0; i < columnHeaders.length; i++) {
			TableColumn column = new TableColumn(inputTable, SWT.NONE);
			column.setResizable(true);
			column.setWidth(96);
			column.setText(columnHeaders[i]);
		}
		setContent(inputTable);

	}

}
