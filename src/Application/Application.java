package Application;
import Controller.Controller;
import UserInterface.ApplicationView;

/**
 * Class used as the program entry point
 *
 */
public class Application {
	/**
	 * Program entry point
	 * @param args UNUSED
	 */
	public static void main(String args[]) {
		ApplicationView v = new ApplicationView();
		Controller gridController = new Controller(v);
		gridController.Run();
	}
}
