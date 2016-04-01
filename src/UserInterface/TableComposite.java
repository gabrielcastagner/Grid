package UserInterface;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

public class TableComposite extends ScrolledComposite {
	
	
	private final String trashIconPath = "resource\\trash.png";
	
	private Table inputTable;
	private TableItem input;
	private String[] columnHeaders = { "Type", "Radius", "Area", "Power Loss", "Exposure", "Efficiency", "" };

	public TableComposite(Composite arg0, int arg1, Color bg, Color fg) {

		super(arg0, arg1);
		int columnwidth = ((arg0.getBounds().width / 2 - 2 * 40) - 50) / (columnHeaders.length-1);

		setExpandHorizontal(true);
		setExpandVertical(true);
		setBackground(bg);
		setForeground(fg);
		inputTable = new Table(this, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION | SWT.CHECK);
		inputTable.setLinesVisible(true);
		inputTable.setHeaderVisible(true);
		inputTable.setTouchEnabled(true);
		setMinSize(inputTable.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		inputTable.setBackground(bg);
		inputTable.setForeground(fg);

		for (int i = 0; i < columnHeaders.length; i++) {
			TableColumn column = new TableColumn(inputTable, SWT.NONE);
			column.setResizable(false);
			column.setWidth((i == columnHeaders.length-1)? 50 :columnwidth);
			column.setText(columnHeaders[i]);
		}
		setContent(inputTable);
		
	}

	public void addItemtoTableWind(String efficiency, double bladeRadius, double cost, int colWidth) {
		TableEditor edits = new TableEditor(inputTable);
		edits.grabHorizontal = true;
		Image trashIcon = new Image(getDisplay(), trashIconPath);
		
		Button remove = new Button(inputTable, SWT.NULL);
		//remove.setText("X");
		remove.setImage(trashIcon);
		
		input = new TableItem(inputTable, SWT.NULL);
		edits.setEditor(remove, input, 6);
		input.setText(1,efficiency);
		//input.setText(0, new Button(inputTable, SWT.NONE));
		input.setText(2, "Yes");
		input.setText(3, "No");
		input.setText(4, "A table item");
	}

	public void addItemtoTableSolar(double area, double powerLossCoeff, double efficiency, double exposure,
			double cost) {

	}
	public void removeItems(int rowNumber){
		inputTable.remove(rowNumber);
		inputTable.pack();
	}
	public void modifyItem(TableItem itemToBeChanged, int column){
		//TODO//
	}
	
	//======================= Getters =====================================//
	public Table getTable(){
		return inputTable;
	}
	public TableItem getTableItem(int itemNumber){
		return inputTable.getItem(itemNumber);
	}
	
	//==============================Destroy Function========================//
	

}
