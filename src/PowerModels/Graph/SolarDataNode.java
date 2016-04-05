package PowerModels.Graph;

import java.util.HashMap;

/**
 * Solar Data ADT containing 1:1 mappings of Months to Average
 * Solar Intensity
 */
public class SolarDataNode {
	private HashMap<Month, Double> avgSolarIntensityData;

	/**
	 * Creates a SolarDataNode instance
	 * 
	 * @param group
	 *            Formatted String array of numbers for Wind Speed Averages
	 *            "Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec Ann"
	 */
	public SolarDataNode(String[] data) {
		avgSolarIntensityData = new HashMap<>();
		// Loop through the Month enum in order of Months, this is important not
		// to change
		int ind = 0;
		for (Month m : Month.values())
			avgSolarIntensityData.put(m, Double.parseDouble(data[ind++]));
	}

	/**
	 * @return a HashMap of Months to average solar intensity in w/m^2
	 */
	public HashMap<Month, Double> getAnnualWindSpeedAvg() {
		return this.avgSolarIntensityData;
	}

	/**
	 * Gets a single Average solar Intesnity from the node
	 * 
	 * @param m
	 *            -Month of desired parse
	 * @return Average solar intensity in w/m^2
	 */
	public Double getMonthlyAverageSolarIntensity(Month m) {
		return this.avgSolarIntensityData.get(m);
	}
	/**
	 * @return the position of where the object is in the has map
	 */
}
