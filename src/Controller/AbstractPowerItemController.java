package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;

import UserInterface.Elements.Table.OutputTableItem;

public abstract class AbstractPowerItemController implements Comparable<AbstractPowerItemController> {

	public static int modelNumber;

	public abstract void analyze();

	public abstract UUID destroy();

	public abstract void destroyOutput();

	public abstract boolean outputted();

	public abstract Button getRemoveButton();

	public abstract void updateModelStateToView();

	public abstract void updateOutputTable();

	public abstract void buildOutput(OutputTableItem output);

	public abstract int compareTo(AbstractPowerItemController o);

	public abstract int comparePerDollar(AbstractPowerItemController o);

	public abstract double returnPower();

	public abstract double returnPowerPerDollar();
}
