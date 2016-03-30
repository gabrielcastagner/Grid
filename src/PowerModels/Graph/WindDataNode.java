package PowerModels.Graph;

import java.util.HashMap;

public class WindDataNode {
	private HashMap<Month, Double> annualDataAvg;
	

	/**
	 * 
	 * @param group Formatted String array of numbers for Wind Speed Averages
	 * 		"Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec Ann"
	 */
	public WindDataNode(String[] data) {
		annualDataAvg = new HashMap<>();
		//Loop through the Month enum in order of Months, this is important not to change
		int ind = 0;
		for (Month m : Month.values())
			annualDataAvg.put(m, Double.parseDouble(data[ind++]));
	}
}
