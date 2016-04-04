package Parser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import Constants.FilePaths;
import PowerModels.Graph.Location;
import PowerModels.Graph.WindDataNode;

/**
 * Static parser for Wind data NOT TO BE INSTANTIATED
 */
public class WindDataSetParser {
	private final static Pattern WIND_SPEED_PATTERN = Pattern
			.compile("([-0-9\\.]+)\\s([-0-9\\.]+)\\s((?:(?:[-0-9\\.]+)\\s?){13})\\s*$");


	
	private static HashMap<Location, WindDataNode> data;
	
	/**
	 * Parse windSpeedDataPath for all wind speed information
	 * @return HashMap matching Locations to Data
	 */
	protected static HashMap<Location, WindDataNode> parseWindDataSet(){
		data = new HashMap<>();
		try {
			byte[] temp = Files.readAllBytes(Paths.get(FilePaths.WIND_SPEED_DATA_PATH));
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
					Matcher m = WIND_SPEED_PATTERN.matcher(line);
					//Find grouped pairs, push them to the graph
					if (m.find()) {
						data.put(new Location(m.group(1), m.group(2)), 
								new WindDataNode(m.group(3).split("\\s"))
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
