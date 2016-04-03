package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;

public abstract class IPowerItemController implements Comparable<IPowerItemController>{
	
	public abstract void analyze();
	public abstract UUID destroy();
	public abstract Button getRemoveButton();
	public abstract void updateModelStateToView();
	public abstract void updateOutputTable();
	
	public abstract double returnPower();
}


