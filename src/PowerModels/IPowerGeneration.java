package PowerModels;

/**
 * 
 * @author Gabriel Castagner
 * @author Ethan Chan
 * @author Siming Ma
 * @author Riley McGee
 * 
 *
 */
public interface IPowerGeneration {
	

	double calculatePower();
	
	double timeToPayOff();
	
	String getType();
}
