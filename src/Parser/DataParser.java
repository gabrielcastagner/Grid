package Parser;

import java.util.HashMap;

import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;
import PowerModels.Graph.WindDataNode;
/**
 * Top Level Parser Class, builds all parsed structures
 * DataParser.parse(); must be called prior to use.
 */
public class DataParser {

	private static HashMap<Location, SolarDataNode> solarData;
	private static HashMap<Location, WindDataNode> windData;

	/**
	 * Parses all DataSets, MUST be called prior to use of data
	 */
	public static void parse(){
		//Parse Data Sets
		solarData = SolarDataSetParser.parseSolarDataSet();
		windData = WindDataSetParser.parseWindDataSet();
	}

	/**
	 * @return Hashed Location SolarData pairs 
	 */
	public static HashMap<Location, SolarDataNode> getSolarData() {
		return solarData;
	}
	
	/**
	 * @return Hashed Location WindData pairs 
	 */
	public static HashMap<Location, WindDataNode> getWindData() {
		return windData;
	}

	
}
