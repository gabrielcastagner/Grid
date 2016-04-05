package Testing;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Parser.DataParser;
import PowerModels.Graph.Location;
import PowerModels.Graph.SolarDataNode;

public class ParserTest {
	private HashMap<Location,SolarDataNode> solarInfo;

	@Before
	public void setUp() throws Exception {
		DataParser.parse();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		
		
		
		
		
		fail("Not yet implemented");
	}

}
