package PowerModels.Graph;

import java.util.HashMap;

/**
 * ADT containing average Monthly and annual data for Wind Speed
 */
public class WindDataNode {
	private HashMap<Month, Double> avgWindSpeedData;

	/**
	 * Creates a WindDataNode instance
	 * 
	 * @param group
	 *            Formatted String array of numbers for Wind Speed Averages
	 *            "Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec Ann"
	 */
	public WindDataNode(String[] data) {
		avgWindSpeedData = new HashMap<>();
		//Loop through the Month enum in order of Months, this is important not to change
		int ind = 0;
		for (Month m : Month.values())
			avgWindSpeedData.put(m, Double.parseDouble(data[ind++]));
	}

	/**
	 * @return a HashMap of Months to average wind speeds in m/s
	 */
	public HashMap<Month, Double> getAnnualWindSpeedAvg() {
		return this.avgWindSpeedData;
	}

	/**
	 * Gets a single Average wind speed from the node
	 * 
	 * @param m
	 *            -Month of desired parse
	 * @return Average wind speed for m
	 */
	public Double getMonthlyAverageWindSpeed(Month m) {
		return this.avgWindSpeedData.get(m);
	}
}
