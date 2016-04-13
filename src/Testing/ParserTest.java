package Testing;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import GraphControl.GenericMerge;
import GraphControl.SolarGraph;
import Parser.DataParser;
import PowerModels.SolarModel;
import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;

public class ParserTest {
	private HashMap<Location,SolarDataNode> solar;
	private Location[] locations;
	private Location desired;
	private Location e;
	private SolarGraph graph;
	private double distance;
	private double desireddistance;
	private SolarModel testSolarPanel;
	@Before
	public void setUp() throws Exception {
		DataParser.parse();
		solar = DataParser.getSolarData();
		locations = new Location[solar.keySet().size()];
		solar.keySet().toArray(locations);
		desired = new Location(12,23);
		desireddistance= 500;
		testSolarPanel = new SolarModel();
		
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void HaversineTest() {
		
		for(Location e: locations){
		distance = e.Haversine(e.getLatitude(), e.getLongitude(), desired.getLatitude(), desired.getLongitude());
		assertTrue(distance == e.Haversine(e.getLatitude(), e.getLongitude(), desired.getLatitude(), desired.getLongitude()));
		}
		
		
	}
	@Test
	public void BFSTest() {
		
		ArrayList<Location> testlist = graph.BFS(desired,desireddistance);
		assertTrue(testlist.equals(graph.BFS(desired, desireddistance)));
	}
	@Test
	public void MergeTest(){
		int length = 5;
		Location [] testlist = new Location[length];
		testlist[0]= new Location(20,48);
		testlist[1]= new Location(42,3);
		testlist[2]= new Location(13,42);
		testlist[3]= new Location(41,28);
		testlist[4]= new Location(39,50);
		GenericMerge.sortMerge(testlist, testlist.length);
		Location [] comparelist = new Location[length];
		comparelist[4]= new Location(42,3);
		comparelist[3]= new Location(41,28);
		comparelist[2]= new Location(39,50);
		comparelist[1]= new Location(20,48);
		comparelist[0]= new Location(13,42);
		for(int i = 0;i<length-1;i++){
			assertTrue(testlist[i].equals(comparelist[i]));
		}
	}
	@Test
	public void SolarCostTest(){
		
		testSolarPanel.setArea(4);
		testSolarPanel.setCostPerUnit(300);
		testSolarPanel.setNumberOfPanels(3);
		testSolarPanel.setYield(4);
		testSolarPanel.setPlCoeff(1);
		testSolarPanel.setLocation(desired);
		System.out.println(testSolarPanel.getPower());
		//assertTrue(testSolarPanel.getPower()== 144000);
	}
	

}
