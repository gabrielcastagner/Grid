package GraphControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Parser.DataParser;
import Parser.SolarDataSetParser;
import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;

public class SolarGraph {
	final double topLat = 89,topLon =179,bottomLat = -90,bottomLon = -180;
	private int V; 
	private int E;
	//private HashMap<> solarlist;
	private HashMap<Location, SolarDataNode> solar;
	private Set<Location> locations;
	private HashMap<Location ,ArrayList<Location>> adj;
	
	public SolarGraph(){
		setupGraph();
		this.V = locations.size();
		this.E = 0;
		setEdges();
	}
	
	public void setupGraph(){
		solar = new HashMap<>();
		adj = new HashMap<>();
		//TODO remove reparse on merge leave for testing
		DataParser.parse();
		solar = DataParser.getSolarData();
		locations = solar.keySet();
		//Set up HashMap
		for(Location l : locations)
			adj.put(l, new ArrayList<>());	
	}
	
	public int getNode(){
		return V;
	}
	public int getEdge(){
		return E;
	}
	private void addEdge(Location l1,Location l2 ){
		if(adj.get(l1).contains(l2))
			return;
		adj.get(l1).add(l2);
		E++;
	}

	private void findAdjEdge(Location l){
			//iteration of adding edges based on the position

			for(Location temp : locations){
				if(l.adjacent(temp)){
					//System.out.println(l.toString() + "     " + temp.toString() );
					addEdge(l, temp);
				}
			}
	}
	/**
	 * Set all adjacent edges for every node in the Graph
	 */
	public void setEdges(){
		for(Location location: locations){
			findAdjEdge(location);
		}
	}

	public static void main(String args[]){
		System.out.println("Start");
		SolarGraph sg = new SolarGraph();
		System.out.println("Stop");
		
	}
}
