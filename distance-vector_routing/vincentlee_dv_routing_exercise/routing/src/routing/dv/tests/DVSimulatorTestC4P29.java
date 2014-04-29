package routing.dv.tests;

/**
 * Distance Vector Simulator Tester which checks for correct updates, messages, and route calculations using the DV algorithm
 * Chapter 4 Problem 29
 * 
 * @author Vincent Lee
 * @since April 1, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

import static org.junit.Assert.*;

import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import routing.dv.DVSimulator;
import routing.dv.Node;
import routing.dv.Path;

public class DVSimulatorTestC4P29 {
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step0Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step1Map;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DVSimulator.main(new String[] {"c4p29", "--debug"});
	}
	
	@Test
	public void step0() {
		//Node X
		assertEquals("NodeX-NeighborRoutes", step0Map.get("X").keySet().toString(), DVSimulator.nodeMap.get("X").routeMap.keySet().toString());
		assertEquals("NodeX-RowX", step0Map.get("X").get("X").toString(), DVSimulator.nodeMap.get("X").routeMap.get("X").toString());
		
		//Node Y
		assertEquals("NodeY-NeighborRoutes", step0Map.get("Y").keySet().toString(), DVSimulator.nodeMap.get("Y").routeMap.keySet().toString());
		assertEquals("NodeY-RowY", step0Map.get("Y").get("Y").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("Y").toString());
		
		//Node Z
		assertEquals("NodeZ-NeighborRoutes", step0Map.get("Z").keySet().toString(), DVSimulator.nodeMap.get("Z").routeMap.keySet().toString());
		assertEquals("NodeZ-RowZ", step0Map.get("Z").get("Z").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("Z").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-0 Messages", 0, Node.messages);
		//Nodes that update
		assertEquals("Round-0 Updates", 0, Node.updates);
	}
	
	@Test
	public void step1() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node X
		assertEquals("NodeX-NeighborRoutes", step1Map.get("X").keySet().toString(), DVSimulator.nodeMap.get("X").routeMap.keySet().toString());
		assertEquals("NodeX-RowX", step1Map.get("X").get("X").toString(), DVSimulator.nodeMap.get("X").routeMap.get("X").toString());
		assertEquals("NodeX-RowY", step1Map.get("X").get("Y").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Y").toString());
		assertEquals("NodeX-RowZ", step1Map.get("X").get("Z").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Z").toString());
		
		//Node Y
		assertEquals("NodeY-NeighborRoutes", step1Map.get("Y").keySet().toString(), DVSimulator.nodeMap.get("Y").routeMap.keySet().toString());
		assertEquals("NodeY-RowX", step1Map.get("Y").get("X").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("X").toString());
		assertEquals("NodeY-RowY", step1Map.get("Y").get("Y").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("Y").toString());
		assertEquals("NodeY-RowZ", step1Map.get("Y").get("Z").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("Z").toString());
		
		//Node Z
		assertEquals("NodeZ-NeighborRoutes", step1Map.get("Z").keySet().toString(), DVSimulator.nodeMap.get("Z").routeMap.keySet().toString());
		assertEquals("NodeZ-RowX", step1Map.get("Z").get("X").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("X").toString());
		assertEquals("NodeZ-RowY", step1Map.get("Z").get("Y").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("Y").toString());
		assertEquals("NodeZ-RowZ", step1Map.get("Z").get("Z").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("Z").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-1 Messages", 6, Node.messages);
		//Nodes that update
		assertEquals("Round-1 Updates", 3, Node.updates);
	}
	
	@Test
	public void step2() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Route messages sent to other nodes
		assertEquals("Round-2 Messages", 0, Node.messages);
		//Nodes that update
		assertEquals("Round-2 Updates", 0, Node.updates);
	}
	
	@Before
	public void setUp() {
		////////////
		// Step 0 //
		////////////
		step0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node X
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> x0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> xx0SubMap = new ConcurrentSkipListMap<String, Path>();
		xx0SubMap.put("X", new Path("X", 0.0));
		xx0SubMap.put("Y", new Path("Y", 3.0));
		xx0SubMap.put("Z", new Path("Z", 4.0));
		x0Map.put("X", xx0SubMap);
		//Node Y
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> y0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> yy0SubMap = new ConcurrentSkipListMap<String, Path>();
		yy0SubMap.put("X", new Path("X", 3.0));
		yy0SubMap.put("Y", new Path("Y", 0.0));
		yy0SubMap.put("Z", new Path("Z", 6.0));
		y0Map.put("Y", yy0SubMap);
		//Node Z
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> z0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> zz0SubMap = new ConcurrentSkipListMap<String, Path>();
		zz0SubMap.put("X", new Path("X", 4.0));
		zz0SubMap.put("Y", new Path("Y", 6.0));
		zz0SubMap.put("Z", new Path("Z", 0.0));
		z0Map.put("Z", zz0SubMap);
		//Add to run's map
		step0Map.put("X", x0Map);
		step0Map.put("Y", y0Map);
		step0Map.put("Z", z0Map);
		////////////
		// Step 1 //
		////////////
		step1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node X
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> x1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> xx1SubMap = new ConcurrentSkipListMap<String, Path>();
		xx1SubMap.put("X", new Path("X", 0.0));
		xx1SubMap.put("Y", new Path("Y", 3.0));
		xx1SubMap.put("Z", new Path("Z", 4.0));
		ConcurrentNavigableMap<String, Path> xy1SubMap = new ConcurrentSkipListMap<String, Path>();
		xy1SubMap.put("X", new Path("X", 3.0));
		xy1SubMap.put("Y", new Path("Y", 0.0));
		xy1SubMap.put("Z", new Path("Z", 6.0));
		ConcurrentNavigableMap<String, Path> xz1SubMap = new ConcurrentSkipListMap<String, Path>();
		xz1SubMap.put("X", new Path("X", 4.0));
		xz1SubMap.put("Y", new Path("Y", 6.0));
		xz1SubMap.put("Z", new Path("Z", 0.0));
		x1Map.put("X", xx1SubMap);
		x1Map.put("Y", xy1SubMap);
		x1Map.put("Z", xz1SubMap);
		//Node Y
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> y1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> yx1SubMap = new ConcurrentSkipListMap<String, Path>();
		yx1SubMap.put("X", new Path("X", 0.0));
		yx1SubMap.put("Y", new Path("Y", 3.0));
		yx1SubMap.put("Z", new Path("Z", 4.0));
		ConcurrentNavigableMap<String, Path> yy1SubMap = new ConcurrentSkipListMap<String, Path>();
		yy1SubMap.put("X", new Path("X", 3.0));
		yy1SubMap.put("Y", new Path("Y", 0.0));
		yy1SubMap.put("Z", new Path("Z", 6.0));
		ConcurrentNavigableMap<String, Path> yz1SubMap = new ConcurrentSkipListMap<String, Path>();
		yz1SubMap.put("X", new Path("X", 4.0));
		yz1SubMap.put("Y", new Path("Y", 6.0));
		yz1SubMap.put("Z", new Path("Z", 0.0));
		y1Map.put("X", yx1SubMap);
		y1Map.put("Y", yy1SubMap);
		y1Map.put("Z", yz1SubMap);
		//Node Z
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> z1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> zx1SubMap = new ConcurrentSkipListMap<String, Path>();
		zx1SubMap.put("X", new Path("X", 0.0));
		zx1SubMap.put("Y", new Path("Y", 3.0));
		zx1SubMap.put("Z", new Path("Z", 4.0));
		ConcurrentNavigableMap<String, Path> zy1SubMap = new ConcurrentSkipListMap<String, Path>();
		zy1SubMap.put("X", new Path("X", 3.0));
		zy1SubMap.put("Y", new Path("Y", 0.0));
		zy1SubMap.put("Z", new Path("Z", 6.0));
		ConcurrentNavigableMap<String, Path> zz1SubMap = new ConcurrentSkipListMap<String, Path>();
		zz1SubMap.put("X", new Path("X", 4.0));
		zz1SubMap.put("Y", new Path("Y", 6.0));
		zz1SubMap.put("Z", new Path("Z", 0.0));
		z1Map.put("X", zx1SubMap);
		z1Map.put("Y", zy1SubMap);
		z1Map.put("Z", zz1SubMap);
		//Add to run's map
		step1Map.put("X", x1Map);
		step1Map.put("Y", y1Map);
		step1Map.put("Z", z1Map);
	}
}
