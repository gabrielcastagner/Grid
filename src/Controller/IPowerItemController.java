package Controller;

import java.util.UUID;

import org.eclipse.swt.widgets.Button;

public interface IPowerItemController {
	
	public void analyze();
	public UUID destroy();
	public Button getRemoveButton();
	public void updateViewToModelState();
}


