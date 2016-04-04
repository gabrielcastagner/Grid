package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;

import UserInterface.Elements.Table.OutputTableItem;

public abstract class IPowerItemController implements Comparable<IPowerItemController>{
	
	public abstract void analyze();
	public abstract UUID destroy();
	public abstract boolean outputted();
	public abstract Button getRemoveButton();
	public abstract void updateModelStateToView();
	public abstract void updateOutputTable();
	public abstract void buildOutput(OutputTableItem output);
	
	public abstract int compareTo(IPowerItemController o);
	public abstract int comparePerDollar(IPowerItemController o);
	
	public abstract double returnPower();
	public abstract double returnPowerPerDollar();
}


