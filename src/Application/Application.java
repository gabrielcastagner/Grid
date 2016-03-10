package Application;
import Controller.Controller;
import UserInterface.ApplicationView;

public class Application {
	public static void main(String args[]) {
		ApplicationView v = new ApplicationView();
		Controller gridController = new Controller(v);
		gridController.Run();
	}
}
