package GraphControl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import Parser.DataParser;
import Parser.SolarDataSetParser;
import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;

public class SolarGraph {
	final static double topLat = 89,topLon =179,bottomLat = -90,bottomLon = -180;
	private int V; 
	private static int E;
	private  static HashMap<Location, SolarDataNode> solar;
	private static Set list = solar.keySet();
	private static ArrayList<Integer>[] adj;
	
	public SolarGraph(int V){
		this.V = V;
		this.E = 0;
		for(int v = 0;v<V;v++){
			adj[v] = new ArrayList<Integer>();
		}
	}
	public int getNode(){
		return V;
	}
	public int getEdge(){
		return E;
	}
	private static void addEdge(int v,int w){
		adj[v].add(w);
		adj[w].add(v);
		E++;
	}
	private static void setup(){
		DataParser.parse();
		solar = DataParser.getSolarData();
		solar.keySet();
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
	private static void findAdjEdge(Location l){
			//iteration of adding edges based on the position
		Location temp;
		int j = l.hashCode();
		Object[] solarList = solar.keySet().toArray();
			for(int i = 0;i<list.size()-1;i++){
				temp = (Location)solarList[i];
				if(l.adjacent(temp)){
					addEdge(solar.get(j).hashCode(),solar.get(i).hashCode());
				}
			}
	}
	public static void setEdges(){
		Object[] solarList = solar.keySet().toArray();
		for(int i = 0;i<solar.size();i++){
			findAdjEdge((Location)solarList[i]);
		}
	}
	
	public static void main(String [] args){
		setup();
		System.out.println(solar.size());
		//setEdges();
		//System.out.println(solar);
	}
}
