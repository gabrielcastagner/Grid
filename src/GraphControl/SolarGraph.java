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
	private HashMap<Location, SolarDataNode> solar;
	private Set<Location> locations;
	private HashMap<Location ,ArrayList<Location>> adj;
	
	public SolarGraph(){
		setupGraph();
		this.V = locations.size();
		this.E = 0;
		//setEdges();
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
//	public ArrayList<Location> getAdjEdges(Location l){
//		ArrayList<Location> printlist = new ArrayList<Location>();
//		for(int i = 0;i<adj.get(l).size()-1;i++){
//			printlist.add(adj.get(l).get(i));
//		}
//		
//		return printlist;
//	}
//	private void addEdge(Location l1,Location l2 ){
//		if(adj.get(l1).contains(l2))
//			return;
//		adj.get(l1).add(l2);
//		E++;
//	}
//
//	private void findAdjEdge(Location l){
//			//iteration of adding edges based on the position
//
//			ééfor(Location temp : locations){
//				if(l.adjacent(temp)){
//					addEdge(l, temp);
//				}
//			}
//	}
//	/**
//	 * Set all adjacent edges for every node in the Graph
//	 */
//	public void setEdges(){
//		for(Location location: locations){
//			findAdjEdge(location);
//		}
//	}
	
	public ArrayList<Location> BFS(Location l, double d){
		ArrayList<Location> printlist = new ArrayList<Location>();
		ArrayList<Location> queue = new ArrayList<Location>();
		ArrayList<Location> visited = new ArrayList<Location>();
		
		queue.add(l);												//first iteration
		printlist.add(l);
		visited.add(l);
		while(!queue.isEmpty()){
			Location current = queue.remove(0);
			visited.add(current);
			//for loop doing all the 8 surrounding edges
			Location temp = current;//include this in the forloop as an iteration, ask Gabe
			if(l.Haversine(l.getLatitude(), l.getLongitude(), temp.getLatitude(), temp.getLongitude())> d
					&& visited.contains(temp)){
				//do nothing
			}else if(l.Haversine(l.getLatitude(), l.getLongitude(), temp.getLatitude(), temp.getLongitude())<= d
					&& !visited.contains(temp)){
				queue.add(temp);
				visited.add(temp);
				printlist.add(temp);
			}else{
				//do nothing
			}
		}
		return printlist;
	}
	
	public static void main(String args[]){
		System.out.println("Start");
		SolarGraph sg = new SolarGraph();
		System.out.println("Stop");
		
	}
}
