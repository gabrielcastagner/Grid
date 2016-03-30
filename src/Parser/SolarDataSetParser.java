package Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;

public class SolarDataSetParser {
	private final static Pattern SOLAR_INTENSITY_PATTERN = Pattern
			.compile("([-0-9\\.]+)\\s([-0-9\\.]+)\\s((?:(?:[-0-9\\.]+)\\s?){13})\\s*$");

	private final static String solarIntensityDataPath = "data\\windSpeed.txt";
	
	private static HashMap<Location, SolarDataNode> data;
	
	/**
	 * Parse solarIntensityDataPath for all wind speed information
	 * @return HashMap matching Locations to Data
	 */
	protected static HashMap<Location, SolarDataNode> parseSolarDataSet(){
		data = new HashMap<>();
		try {
			byte[] temp = Files.readAllBytes(Paths.get(solarIntensityDataPath));
			String s = new String(temp, "UTF-8");
			String[] fileContents = s.split("\n");
			
			if (fileContents != null) {
				for (String line : fileContents) {
					//Read in a line from the data set, form of data is:
					//Lat Lon Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec Ann
					//Group 0 --> Whole line of Data
					//Group 1 --> Lat
					//Group 2 --> Long
					//Group 3 --> Month Data
					Matcher m = SOLAR_INTENSITY_PATTERN.matcher(line);
					//Find grouped pairs, push them to the graph
					if (m.find()) {
						data.put(new Location(m.group(1),  m.group(2)), 
								new SolarDataNode(m.group(3).split("\\s"))
								);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}
}
