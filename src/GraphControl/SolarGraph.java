package GraphControl;

import java.util.ArrayList;
import java.util.HashMap;

import Parser.DataParser;
import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;

public class SolarGraph {
	final double topLat = 89, topLon = 179, bottomLat = -90, bottomLon = -180;
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

	public void setupGraph() {
		solar = new HashMap<>();
		adj = new HashMap<>();
		//TODO remove reparse on merge leave for testing
		DataParser.parse();
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

	public void buildGraph() {

	}

	public ArrayList<Location> BFS(Location l, double d) {
		ArrayList<Location> printlist = new ArrayList<Location>();
		ArrayList<Location> queue = new ArrayList<Location>();
		ArrayList<Location> visited = new ArrayList<Location>();

		queue.add(l); //first iteration
		printlist.add(l);
		visited.add(l);
		while (!queue.isEmpty()) {
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
	//public TernarryFunction<Integer, Location> up = (ts) -> { int x = ts[0]; int y = ts[1]; return (x != 359) ? locations[y + 1] : locations[y - 359];};
	//	public TernarryFunction<Integer, Location> down = (ts) -> { int x = ts[0]; int y = ts[1]; return (x != 0) ? locations[y - 1] : locations[y + 359];};
	//	public TernarryFunction<Integer, Location> right = (ts) -> { int w = ts[0]; int x = ts[1]; int y = ts[2]; return (w != 179) ? locations[y + 359] : locations[x];};

	public ArrayList<Location> surroundingEdges(Location l) {
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

	
	
	
	
	public Location getALoc(){
		
		return adj.keySet().iterator().next();
	}
	
	public static void main(String args[]) {
		System.out.println("Start");
		SolarGraph sg = new SolarGraph();
		System.out.println("parsed");
		System.out.println(sg.BFS(sg.getALoc(), 500).toString());
		System.out.println("Stop");

	}

}
