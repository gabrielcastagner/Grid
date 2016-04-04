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
	private ArrayList<Integer>[] adj;
	
	public SolarGraph(){
		setupGraph();
		this.V = locations.size();
		this.E = 0;
		for(int v = 0;v<V;v++){
			adj[v] = new ArrayList<Integer>();
		}
	}
	
	public void setupGraph(){
		solar = new HashMap<>();

		//TODO remove reparse on merge leave for testing
		DataParser.parse();
		solar = DataParser.getSolarData();
		locations = solar.keySet();
		System.out.println(solar.size());
		
	}
	
	public int getNode(){
		return V;
	}
	public int getEdge(){
		return E;
	}
	private void addEdge(int v,int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}

	private boolean isEdgeTopLat(Location l){
		 double lat = l.getLatitude();
			if(lat == topLat) return true;
			return false;
		}
	private boolean isEdgeBottomLat(Location l){
		 double lat = l.getLatitude();
			if(lat == bottomLat) return true;
			return false;
		}
	private boolean isEdgeBottomLon(Location l){
		 double lon = l.getLongitude();
			if(lon == bottomLon) return true;
			return false;
		}
	private void findAdjEdge(Location l){
			//iteration of adding edges based on the position
		Location temp;
		int j = l.hashCode();
		Object[] solarList = solar.keySet().toArray();
			for(int i = 0;i<locations.size()-1;i++){
				temp = (Location)solarList[i];
				if(l.adjacent(temp)){
					addEdge(solar.get(j).hashCode(),solar.get(i).hashCode());
				}
			}
	}
	public void setEdges(){
		Object[] solarList = solar.keySet().toArray();
		for(int i = 0;i<solar.size();i++){
			findAdjEdge((Location)solarList[i]);
		}
	}

	public static void main(String args[]){
		System.out.println(1);
		SolarGraph sg = new SolarGraph();
		
	}
}
