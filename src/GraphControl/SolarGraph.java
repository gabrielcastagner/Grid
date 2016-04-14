package GraphControl;

import java.util.ArrayList;
import java.util.HashMap;

import Parser.DataParser;
import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;

public class SolarGraph {
	private final double topLat = 89, topLon = 179, bottomLat = -90, bottomLon = -180;
	private int V;
	private int E;
	private HashMap<Location, SolarDataNode> solar;
	private Location[] locations;
	private HashMap<Location, ArrayList<Location>> adj;

	public SolarGraph() {
		setupGraph();

		this.V = locations.length;
		this.E = 0;

		//Set up the graph
		for (Location l : locations) {
			adj.put(l, surroundingEdges(l));
		}
	}

	private void setupGraph() {
		solar = new HashMap<>();
		adj = new HashMap<>();

		solar = DataParser.getSolarData();

		locations = new Location[solar.keySet().size()];

		solar.keySet().toArray(locations);

		GenericMerge.sortMerge(locations, locations.length);
	}

	public int getNode() {
		return V;
	}

	public int getEdge() {
		return E;
	}

	public ArrayList<Location> getAdjEdges(Location l) {
		ArrayList<Location> printlist = new ArrayList<Location>();
		for (int i = 0; i < adj.get(l).size() - 1; i++) {
			printlist.add(adj.get(l).get(i));
		}

		return printlist;
	}

	public ArrayList<Location> BFS(Location l, double d) {
		ArrayList<Location> printlist = new ArrayList<Location>();
		ArrayList<Location> queue = new ArrayList<Location>();
		ArrayList<Location> visited = new ArrayList<Location>();

		queue.add(l); //first iteration
		printlist.add(l);
		visited.add(l);
		while (!queue.isEmpty()) {
			System.out.println(l.toString());
			Location current = queue.remove(0);
			visited.add(current);
			for (Location temp : adj.get(current)) {
				//for loop doing all the 8 surrounding edges
				//include this in the forloop as an iteration, ask Gabe
				if (l.Haversine(l.getLatitude(), l.getLongitude(), temp.getLatitude(), temp.getLongitude()) > d
						|| visited.contains(temp)) {
					//do nothing
				} else if (l.Haversine(l.getLatitude(), l.getLongitude(), temp.getLatitude(), temp.getLongitude()) <= d
						&& !visited.contains(temp)) {
					queue.add(temp);
					visited.add(temp);
					printlist.add(temp);
				} else {
					//do nothing
				}
			}
		}

		return printlist;
	}

	private ArrayList<Location> surroundingEdges(Location l) {
		int lat = (int) l.getLatitude() + 90;
		int lon = (int) l.getLongitude() + 180;
		int currentPosition = (lat) * 360 + (lon);
		ArrayList<Location> printlist = new ArrayList<Location>();
		//Up Down Left and Right
		printlist.add((lon != 359) ? locations[currentPosition + 1] : locations[currentPosition - 359]);
		printlist.add((lon != 0) ? locations[currentPosition - 1] : locations[currentPosition + 359]);
		printlist.add((lat != 179) ? locations[currentPosition + 359] : locations[lon]);
		printlist.add((lat != 0) ? locations[currentPosition - 359] : locations[locations.length - 360 + lon]);

		//UP and Right
		if (lon != 359) {
			if (lat != 179)
				printlist.add(locations[currentPosition + 360 + 1]);
			else
				printlist.add(locations[lon + 1]);
		} else {
			if (lat != 179)
				printlist.add(locations[currentPosition - lon]);
			else
				printlist.add(locations[0]);
		}

		//Up and Left
		if (lon != 0) {
			if (lat != 179)
				printlist.add(locations[currentPosition + 360 - 1]);
			else
				printlist.add(locations[locations.length - 360 + lon - 1]);
		} else {
			if (lat != 179)
				printlist.add(locations[currentPosition + 359 + 179]);
			else
				printlist.add(locations[179]);
		}

		//Down and Right
		if (lon != 359) {
			if (lat != 0)
				printlist.add(locations[currentPosition - 360 + 1]);
			else
				printlist.add(locations[locations.length - 360 + lon + 1]);
		} else {
			if (lat != 0)
				printlist.add(locations[currentPosition - 359 - lon]);
			else
				printlist.add(locations[locations.length - 360]);
		}

		//Down and Left
		if (lon != 0) {
			if (lat != 0)
				printlist.add(locations[currentPosition - 360 - 1]);
			else
				printlist.add(locations[locations.length - 360 + lon - 1]);
		} else {
			if (lat != 0)
				printlist.add(locations[currentPosition - 1]);
			else
				printlist.add(locations[locations.length - 1]);
		}

		return printlist;
	}

	private Location getSourceLocation(Location l) {
		for (Location loc : solar.keySet())
			if (loc.equals(l))
				return loc;
		return null;
	}

	public ArrayList<SolarDataNode> getInterferenceZone(Location l) {
		l = getSourceLocation(l);
		ArrayList<SolarDataNode> retval = new ArrayList<>();
		retval.add(solar.get(l));
		for (Location loc : BFS(l, 200)) {
			retval.add(solar.get(loc));
		}

		return retval;
	}
}
