package routing.dv.tests;

/**
 * Distance Vector Simulator Tester which checks for correct updates, messages, and route calculations using the DV algorithm
 * Chapter 4 Problem 26
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

public class DVSimulatorTestC4P26 {
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step0Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step1Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step2Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step3Map;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DVSimulator.main(new String[] {"c4p26", "--debug"});
	}
	
	@Test
	public void step0() {
		//Node U
		assertEquals("NodeU-NeighborRoutes", step0Map.get("U").keySet().toString(), DVSimulator.nodeMap.get("U").routeMap.keySet().toString());
		assertEquals("NodeU-RowU", step0Map.get("U").get("U").toString(), DVSimulator.nodeMap.get("U").routeMap.get("U").toString());
		
		//Node V
		assertEquals("NodeV-NeighborRoutes", step0Map.get("V").keySet().toString(), DVSimulator.nodeMap.get("V").routeMap.keySet().toString());
		assertEquals("NodeV-RowV", step0Map.get("V").get("V").toString(), DVSimulator.nodeMap.get("V").routeMap.get("V").toString());
		
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
		
		//Node U
		assertEquals("NodeU-NeighborRoutes", step1Map.get("U").keySet().toString(), DVSimulator.nodeMap.get("U").routeMap.keySet().toString());
		assertEquals("NodeU-RowU", step1Map.get("U").get("U").toString(), DVSimulator.nodeMap.get("U").routeMap.get("U").toString());
		assertEquals("NodeU-RowV", step1Map.get("U").get("V").toString(), DVSimulator.nodeMap.get("U").routeMap.get("V").toString());
		assertEquals("NodeU-RowY", step1Map.get("U").get("Y").toString(), DVSimulator.nodeMap.get("U").routeMap.get("Y").toString());
		
		//Node V
		assertEquals("NodeV-NeighborRoutes", step1Map.get("V").keySet().toString(), DVSimulator.nodeMap.get("V").routeMap.keySet().toString());
		assertEquals("NodeV-RowU", step1Map.get("V").get("U").toString(), DVSimulator.nodeMap.get("V").routeMap.get("U").toString());
		assertEquals("NodeV-RowV", step1Map.get("V").get("V").toString(), DVSimulator.nodeMap.get("V").routeMap.get("V").toString());
		assertEquals("NodeV-RowX", step1Map.get("V").get("X").toString(), DVSimulator.nodeMap.get("V").routeMap.get("X").toString());
		assertEquals("NodeV-RowZ", step1Map.get("V").get("Z").toString(), DVSimulator.nodeMap.get("V").routeMap.get("Z").toString());
		
		//Node X
		assertEquals("NodeX-NeighborRoutes", step1Map.get("X").keySet().toString(), DVSimulator.nodeMap.get("X").routeMap.keySet().toString());
		assertEquals("NodeX-RowV", step1Map.get("X").get("V").toString(), DVSimulator.nodeMap.get("X").routeMap.get("V").toString());
		assertEquals("NodeX-RowX", step1Map.get("X").get("X").toString(), DVSimulator.nodeMap.get("X").routeMap.get("X").toString());
		assertEquals("NodeX-RowY", step1Map.get("X").get("Y").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Y").toString());
		assertEquals("NodeX-RowZ", step1Map.get("X").get("Z").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Z").toString());
		
		//Node Y
		assertEquals("NodeY-NeighborRoutes", step1Map.get("Y").keySet().toString(), DVSimulator.nodeMap.get("Y").routeMap.keySet().toString());
		assertEquals("NodeY-RowU", step1Map.get("Y").get("U").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("U").toString());
		assertEquals("NodeY-RowX", step1Map.get("Y").get("X").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("X").toString());
		assertEquals("NodeY-RowY", step1Map.get("Y").get("Y").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("Y").toString());
		
		//Node Z
		assertEquals("NodeZ-NeighborRoutes", step1Map.get("Z").keySet().toString(), DVSimulator.nodeMap.get("Z").routeMap.keySet().toString());
		assertEquals("NodeZ-RowV", step1Map.get("Z").get("V").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("V").toString());
		assertEquals("NodeZ-RowX", step1Map.get("Z").get("X").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("X").toString());
		assertEquals("NodeZ-RowZ", step1Map.get("Z").get("Z").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("Z").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-1 Messages", 12, Node.messages);
		//Nodes that update
		assertEquals("Round-1 Updates", 5, Node.updates);
	}
	
	@Test
	public void step2() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node U
		assertEquals("NodeU-NeighborRoutes", step2Map.get("U").keySet().toString(), DVSimulator.nodeMap.get("U").routeMap.keySet().toString());
		assertEquals("NodeU-RowU", step2Map.get("U").get("U").toString(), DVSimulator.nodeMap.get("U").routeMap.get("U").toString());
		assertEquals("NodeU-RowV", step2Map.get("U").get("V").toString(), DVSimulator.nodeMap.get("U").routeMap.get("V").toString());
		assertEquals("NodeU-RowY", step2Map.get("U").get("Y").toString(), DVSimulator.nodeMap.get("U").routeMap.get("Y").toString());
		
		//Node V
		assertEquals("NodeV-NeighborRoutes", step2Map.get("V").keySet().toString(), DVSimulator.nodeMap.get("V").routeMap.keySet().toString());
		assertEquals("NodeV-RowU", step2Map.get("V").get("U").toString(), DVSimulator.nodeMap.get("V").routeMap.get("U").toString());
		assertEquals("NodeV-RowV", step2Map.get("V").get("V").toString(), DVSimulator.nodeMap.get("V").routeMap.get("V").toString());
		assertEquals("NodeV-RowX", step2Map.get("V").get("X").toString(), DVSimulator.nodeMap.get("V").routeMap.get("X").toString());
		assertEquals("NodeV-RowZ", step2Map.get("V").get("Z").toString(), DVSimulator.nodeMap.get("V").routeMap.get("Z").toString());
		
		//Node X
		assertEquals("NodeX-NeighborRoutes", step2Map.get("X").keySet().toString(), DVSimulator.nodeMap.get("X").routeMap.keySet().toString());
		assertEquals("NodeX-RowV", step2Map.get("X").get("V").toString(), DVSimulator.nodeMap.get("X").routeMap.get("V").toString());
		assertEquals("NodeX-RowX", step2Map.get("X").get("X").toString(), DVSimulator.nodeMap.get("X").routeMap.get("X").toString());
		assertEquals("NodeX-RowY", step2Map.get("X").get("Y").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Y").toString());
		assertEquals("NodeX-RowZ", step2Map.get("X").get("Z").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Z").toString());
		
		//Node Y
		assertEquals("NodeY-NeighborRoutes", step2Map.get("Y").keySet().toString(), DVSimulator.nodeMap.get("Y").routeMap.keySet().toString());
		assertEquals("NodeY-RowU", step2Map.get("Y").get("U").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("U").toString());
		assertEquals("NodeY-RowX", step2Map.get("Y").get("X").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("X").toString());
		assertEquals("NodeY-RowY", step2Map.get("Y").get("Y").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("Y").toString());
		
		//Node Z
		assertEquals("NodeZ-NeighborRoutes", step2Map.get("Z").keySet().toString(), DVSimulator.nodeMap.get("Z").routeMap.keySet().toString());
		assertEquals("NodeZ-RowV", step2Map.get("Z").get("V").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("V").toString());
		assertEquals("NodeZ-RowX", step2Map.get("Z").get("X").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("X").toString());
		assertEquals("NodeZ-RowZ", step2Map.get("Z").get("Z").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("Z").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-2 Messages", 12, Node.messages);
		//Nodes that update
		assertEquals("Round-2 Updates", 5, Node.updates);
	}
	
	@Test
	public void step3() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node U
		assertEquals("NodeU-NeighborRoutes", step3Map.get("U").keySet().toString(), DVSimulator.nodeMap.get("U").routeMap.keySet().toString());
		assertEquals("NodeU-RowU", step3Map.get("U").get("U").toString(), DVSimulator.nodeMap.get("U").routeMap.get("U").toString());
		assertEquals("NodeU-RowV", step3Map.get("U").get("V").toString(), DVSimulator.nodeMap.get("U").routeMap.get("V").toString());
		assertEquals("NodeU-RowY", step3Map.get("U").get("Y").toString(), DVSimulator.nodeMap.get("U").routeMap.get("Y").toString());
		
		//Node V
		assertEquals("NodeV-NeighborRoutes", step3Map.get("V").keySet().toString(), DVSimulator.nodeMap.get("V").routeMap.keySet().toString());
		assertEquals("NodeV-RowU", step3Map.get("V").get("U").toString(), DVSimulator.nodeMap.get("V").routeMap.get("U").toString());
		assertEquals("NodeV-RowV", step3Map.get("V").get("V").toString(), DVSimulator.nodeMap.get("V").routeMap.get("V").toString());
		assertEquals("NodeV-RowX", step3Map.get("V").get("X").toString(), DVSimulator.nodeMap.get("V").routeMap.get("X").toString());
		assertEquals("NodeV-RowZ", step3Map.get("V").get("Z").toString(), DVSimulator.nodeMap.get("V").routeMap.get("Z").toString());
		
		//Node X
		assertEquals("NodeX-NeighborRoutes", step3Map.get("X").keySet().toString(), DVSimulator.nodeMap.get("X").routeMap.keySet().toString());
		assertEquals("NodeX-RowV", step3Map.get("X").get("V").toString(), DVSimulator.nodeMap.get("X").routeMap.get("V").toString());
		assertEquals("NodeX-RowX", step3Map.get("X").get("X").toString(), DVSimulator.nodeMap.get("X").routeMap.get("X").toString());
		assertEquals("NodeX-RowY", step3Map.get("X").get("Y").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Y").toString());
		assertEquals("NodeX-RowZ", step3Map.get("X").get("Z").toString(), DVSimulator.nodeMap.get("X").routeMap.get("Z").toString());
		
		//Node Y
		assertEquals("NodeY-NeighborRoutes", step3Map.get("Y").keySet().toString(), DVSimulator.nodeMap.get("Y").routeMap.keySet().toString());
		assertEquals("NodeY-RowU", step3Map.get("Y").get("U").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("U").toString());
		assertEquals("NodeY-RowX", step3Map.get("Y").get("X").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("X").toString());
		assertEquals("NodeY-RowY", step3Map.get("Y").get("Y").toString(), DVSimulator.nodeMap.get("Y").routeMap.get("Y").toString());
		
		//Node Z
		assertEquals("NodeZ-NeighborRoutes", step3Map.get("Z").keySet().toString(), DVSimulator.nodeMap.get("Z").routeMap.keySet().toString());
		assertEquals("NodeZ-RowV", step3Map.get("Z").get("V").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("V").toString());
		assertEquals("NodeZ-RowX", step3Map.get("Z").get("X").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("X").toString());
		assertEquals("NodeZ-RowZ", step3Map.get("Z").get("Z").toString(), DVSimulator.nodeMap.get("Z").routeMap.get("Z").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-3 Messages", 4, Node.messages);
		//Nodes that update
		assertEquals("Round-3 Updates", 3, Node.updates);
	}
	
	@Test
	public void step4() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Route messages sent to other nodes
		assertEquals("Round-4 Messages", 0, Node.messages);
		//Nodes that update
		assertEquals("Round-4 Updates", 0, Node.updates);
	}
	
	@Before
	public void setUp() {
		////////////
		// Step 0 //
		////////////
		step0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node U
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> u0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> uu0SubMap = new ConcurrentSkipListMap<String, Path>();
		uu0SubMap.put("U", new Path("U", 0.0));
		uu0SubMap.put("V", new Path("V", 1.0));
		uu0SubMap.put("Y", new Path("Y", 2.0));
		u0Map.put("U", uu0SubMap);
		//Node V
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> v0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> vv0SubMap = new ConcurrentSkipListMap<String, Path>();
		vv0SubMap.put("U", new Path("U", 1.0));
		vv0SubMap.put("V", new Path("V", 0.0));
		vv0SubMap.put("X", new Path("X", 3.0));
		vv0SubMap.put("Z", new Path("Z", 6.0));
		v0Map.put("V", vv0SubMap);
		//Node X
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> x0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> xx0SubMap = new ConcurrentSkipListMap<String, Path>();
		xx0SubMap.put("V", new Path("V", 3.0));
		xx0SubMap.put("X", new Path("X", 0.0));
		xx0SubMap.put("Y", new Path("Y", 3.0));
		xx0SubMap.put("Z", new Path("Z", 2.0));
		x0Map.put("X", xx0SubMap);
		//Node Y
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> y0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> yy0SubMap = new ConcurrentSkipListMap<String, Path>();
		yy0SubMap.put("U", new Path("U", 2.0));
		yy0SubMap.put("X", new Path("X", 3.0));
		yy0SubMap.put("Y", new Path("Y", 0.0));
		y0Map.put("Y", yy0SubMap);
		//Node Z
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> z0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> zz0SubMap = new ConcurrentSkipListMap<String, Path>();
		zz0SubMap.put("V", new Path("V", 6.0));
		zz0SubMap.put("X", new Path("X", 2.0));
		zz0SubMap.put("Z", new Path("Z", 0.0));
		z0Map.put("Z", zz0SubMap);
		//Add to run's map
		step0Map.put("U", u0Map);
		step0Map.put("V", v0Map);
		step0Map.put("X", x0Map);
		step0Map.put("Y", y0Map);
		step0Map.put("Z", z0Map);
		////////////
		// Step 1 //
		////////////
		step1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node U
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> u1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> uu1SubMap = new ConcurrentSkipListMap<String, Path>();
		uu1SubMap.put("U", new Path("U", 0.0));
		uu1SubMap.put("V", new Path("V", 1.0));
		uu1SubMap.put("X", new Path("V", 4.0));
		uu1SubMap.put("Y", new Path("Y", 2.0));
		uu1SubMap.put("Z", new Path("V", 7.0));
		ConcurrentNavigableMap<String, Path> uv1SubMap = new ConcurrentSkipListMap<String, Path>();
		uv1SubMap.put("U", new Path("U", 1.0));
		uv1SubMap.put("V", new Path("V", 0.0));
		uv1SubMap.put("X", new Path("X", 3.0));
		uv1SubMap.put("Z", new Path("Z", 6.0));
		ConcurrentNavigableMap<String, Path> uy1SubMap = new ConcurrentSkipListMap<String, Path>();
		uy1SubMap.put("U", new Path("U", 2.0));
		uy1SubMap.put("X", new Path("X", 3.0));
		uy1SubMap.put("Y", new Path("Y", 0.0));
		u1Map.put("U", uu1SubMap);
		u1Map.put("V", uv1SubMap);
		u1Map.put("Y", uy1SubMap);
		//Node V
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> v1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> vu1SubMap = new ConcurrentSkipListMap<String, Path>();
		vu1SubMap.put("U", new Path("U", 0.0));
		vu1SubMap.put("V", new Path("V", 1.0));
		vu1SubMap.put("Y", new Path("Y", 2.0));
		ConcurrentNavigableMap<String, Path> vv1SubMap = new ConcurrentSkipListMap<String, Path>();
		vv1SubMap.put("U", new Path("U", 1.0));
		vv1SubMap.put("V", new Path("V", 0.0));
		vv1SubMap.put("X", new Path("X", 3.0));
		vv1SubMap.put("Y", new Path("U", 3.0));
		vv1SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> vx1SubMap = new ConcurrentSkipListMap<String, Path>();
		vx1SubMap.put("V", new Path("V", 3.0));
		vx1SubMap.put("X", new Path("X", 0.0));
		vx1SubMap.put("Y", new Path("Y", 3.0));
		vx1SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> vz1SubMap = new ConcurrentSkipListMap<String, Path>();
		vz1SubMap.put("V", new Path("V", 6.0));
		vz1SubMap.put("X", new Path("X", 2.0));
		vz1SubMap.put("Z", new Path("Z", 0.0));
		v1Map.put("U", vu1SubMap);
		v1Map.put("V", vv1SubMap);
		v1Map.put("X", vx1SubMap);
		v1Map.put("Z", vz1SubMap);
		//Node X
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> x1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> xv1SubMap = new ConcurrentSkipListMap<String, Path>();
		xv1SubMap.put("U", new Path("U", 1.0));
		xv1SubMap.put("V", new Path("V", 0.0));
		xv1SubMap.put("X", new Path("X", 3.0));
		xv1SubMap.put("Z", new Path("Z", 6.0));
		ConcurrentNavigableMap<String, Path> xx1SubMap = new ConcurrentSkipListMap<String, Path>();
		xx1SubMap.put("U", new Path("V", 4.0));
		xx1SubMap.put("V", new Path("V", 3.0));
		xx1SubMap.put("X", new Path("X", 0.0));
		xx1SubMap.put("Y", new Path("Y", 3.0));
		xx1SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> xy1SubMap = new ConcurrentSkipListMap<String, Path>();
		xy1SubMap.put("U", new Path("U", 2.0));
		xy1SubMap.put("X", new Path("X", 3.0));
		xy1SubMap.put("Y", new Path("Y", 0.0));
		ConcurrentNavigableMap<String, Path> xz1SubMap = new ConcurrentSkipListMap<String, Path>();
		xz1SubMap.put("V", new Path("V", 6.0));
		xz1SubMap.put("X", new Path("X", 2.0));
		xz1SubMap.put("Z", new Path("Z", 0.0));
		x1Map.put("V", xv1SubMap);
		x1Map.put("X", xx1SubMap);
		x1Map.put("Y", xy1SubMap);
		x1Map.put("Z", xz1SubMap);
		//Node Y
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> y1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> yu1SubMap = new ConcurrentSkipListMap<String, Path>();
		yu1SubMap.put("U", new Path("U", 0.0));
		yu1SubMap.put("V", new Path("V", 1.0));
		yu1SubMap.put("Y", new Path("Y", 2.0));
		ConcurrentNavigableMap<String, Path> yx1SubMap = new ConcurrentSkipListMap<String, Path>();
		yx1SubMap.put("V", new Path("V", 3.0));
		yx1SubMap.put("X", new Path("X", 0.0));
		yx1SubMap.put("Y", new Path("Y", 3.0));
		yx1SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> yy1SubMap = new ConcurrentSkipListMap<String, Path>();
		yy1SubMap.put("U", new Path("U", 2.0));
		yy1SubMap.put("V", new Path("U", 3.0));
		yy1SubMap.put("X", new Path("X", 3.0));
		yy1SubMap.put("Y", new Path("Y", 0.0));
		yy1SubMap.put("Z", new Path("X", 5.0));
		y1Map.put("U", yu1SubMap);
		y1Map.put("X", yx1SubMap);
		y1Map.put("Y", yy1SubMap);
		//Node Z
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> z1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> zv1SubMap = new ConcurrentSkipListMap<String, Path>();
		zv1SubMap.put("U", new Path("U", 1.0));
		zv1SubMap.put("V", new Path("V", 0.0));
		zv1SubMap.put("X", new Path("X", 3.0));
		zv1SubMap.put("Z", new Path("Z", 6.0));
		ConcurrentNavigableMap<String, Path> zx1SubMap = new ConcurrentSkipListMap<String, Path>();
		zx1SubMap.put("V", new Path("V", 3.0));
		zx1SubMap.put("X", new Path("X", 0.0));
		zx1SubMap.put("Y", new Path("Y", 3.0));
		zx1SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> zz1SubMap = new ConcurrentSkipListMap<String, Path>();
		zz1SubMap.put("U", new Path("V", 7.0));
		zz1SubMap.put("V", new Path("X", 5.0));
		zz1SubMap.put("X", new Path("X", 2.0));
		zz1SubMap.put("Y", new Path("X", 5.0));
		zz1SubMap.put("Z", new Path("Z", 0.0));
		z1Map.put("V", zv1SubMap);
		z1Map.put("X", zx1SubMap);
		z1Map.put("Z", zz1SubMap);
		//Add to run's map
		step1Map.put("U", u1Map);
		step1Map.put("V", v1Map);
		step1Map.put("X", x1Map);
		step1Map.put("Y", y1Map);
		step1Map.put("Z", z1Map);
		////////////
		// Step 2 //
		////////////
		step2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node U
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> u2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> uu2SubMap = new ConcurrentSkipListMap<String, Path>();
		uu2SubMap.put("U", new Path("U", 0.0));
		uu2SubMap.put("V", new Path("V", 1.0));
		uu2SubMap.put("X", new Path("V", 4.0));
		uu2SubMap.put("Y", new Path("Y", 2.0));
		uu2SubMap.put("Z", new Path("V", 6.0));
		ConcurrentNavigableMap<String, Path> uv2SubMap = new ConcurrentSkipListMap<String, Path>();
		uv2SubMap.put("U", new Path("U", 1.0));
		uv2SubMap.put("V", new Path("V", 0.0));
		uv2SubMap.put("X", new Path("X", 3.0));
		uv2SubMap.put("Y", new Path("U", 3.0));
		uv2SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> uy2SubMap = new ConcurrentSkipListMap<String, Path>();
		uy2SubMap.put("U", new Path("U", 2.0));
		uy2SubMap.put("V", new Path("U", 3.0));
		uy2SubMap.put("X", new Path("X", 3.0));
		uy2SubMap.put("Y", new Path("Y", 0.0));
		uy2SubMap.put("Z", new Path("X", 5.0));
		u2Map.put("U", uu2SubMap);
		u2Map.put("V", uv2SubMap);
		u2Map.put("Y", uy2SubMap);
		//Node V
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> v2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> vu2SubMap = new ConcurrentSkipListMap<String, Path>();
		vu2SubMap.put("U", new Path("U", 0.0));
		vu2SubMap.put("V", new Path("V", 1.0));
		vu2SubMap.put("X", new Path("V", 4.0));
		vu2SubMap.put("Y", new Path("Y", 2.0));
		vu2SubMap.put("Z", new Path("V", 7.0));
		ConcurrentNavigableMap<String, Path> vv2SubMap = new ConcurrentSkipListMap<String, Path>();
		vv2SubMap.put("U", new Path("U", 1.0));
		vv2SubMap.put("V", new Path("V", 0.0));
		vv2SubMap.put("X", new Path("X", 3.0));
		vv2SubMap.put("Y", new Path("U", 3.0));
		vv2SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> vx2SubMap = new ConcurrentSkipListMap<String, Path>();
		vx2SubMap.put("U", new Path("V", 4.0));
		vx2SubMap.put("V", new Path("V", 3.0));
		vx2SubMap.put("X", new Path("X", 0.0));
		vx2SubMap.put("Y", new Path("Y", 3.0));
		vx2SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> vz2SubMap = new ConcurrentSkipListMap<String, Path>();
		vz2SubMap.put("U", new Path("V", 7.0));
		vz2SubMap.put("V", new Path("X", 5.0));
		vz2SubMap.put("X", new Path("X", 2.0));
		vz2SubMap.put("Y", new Path("X", 5.0));
		vz2SubMap.put("Z", new Path("Z", 0.0));
		v2Map.put("U", vu2SubMap);
		v2Map.put("V", vv2SubMap);
		v2Map.put("X", vx2SubMap);
		v2Map.put("Z", vz2SubMap);
		//Node X
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> x2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> xv2SubMap = new ConcurrentSkipListMap<String, Path>();
		xv2SubMap.put("U", new Path("U", 1.0));
		xv2SubMap.put("V", new Path("V", 0.0));
		xv2SubMap.put("X", new Path("X", 3.0));
		xv2SubMap.put("Y", new Path("U", 3.0));
		xv2SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> xx2SubMap = new ConcurrentSkipListMap<String, Path>();
		xx2SubMap.put("U", new Path("V", 4.0));
		xx2SubMap.put("V", new Path("V", 3.0));
		xx2SubMap.put("X", new Path("X", 0.0));
		xx2SubMap.put("Y", new Path("Y", 3.0));
		xx2SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> xy2SubMap = new ConcurrentSkipListMap<String, Path>();
		xy2SubMap.put("U", new Path("U", 2.0));
		xy2SubMap.put("V", new Path("U", 3.0));
		xy2SubMap.put("X", new Path("X", 3.0));
		xy2SubMap.put("Y", new Path("Y", 0.0));
		xy2SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> xz2SubMap = new ConcurrentSkipListMap<String, Path>();
		xz2SubMap.put("U", new Path("V", 7.0));
		xz2SubMap.put("V", new Path("X", 5.0));
		xz2SubMap.put("X", new Path("X", 2.0));
		xz2SubMap.put("Y", new Path("X", 5.0));
		xz2SubMap.put("Z", new Path("Z", 0.0));
		x2Map.put("V", xv2SubMap);
		x2Map.put("X", xx2SubMap);
		x2Map.put("Y", xy2SubMap);
		x2Map.put("Z", xz2SubMap);
		//Node Y
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> y2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> yu2SubMap = new ConcurrentSkipListMap<String, Path>();
		yu2SubMap.put("U", new Path("U", 0.0));
		yu2SubMap.put("V", new Path("V", 1.0));
		yu2SubMap.put("X", new Path("V", 4.0));
		yu2SubMap.put("Y", new Path("Y", 2.0));
		yu2SubMap.put("Z", new Path("V", 7.0));
		ConcurrentNavigableMap<String, Path> yx2SubMap = new ConcurrentSkipListMap<String, Path>();
		yx2SubMap.put("U", new Path("V", 4.0));
		yx2SubMap.put("V", new Path("V", 3.0));
		yx2SubMap.put("X", new Path("X", 0.0));
		yx2SubMap.put("Y", new Path("Y", 3.0));
		yx2SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> yy2SubMap = new ConcurrentSkipListMap<String, Path>();
		yy2SubMap.put("U", new Path("U", 2.0));
		yy2SubMap.put("V", new Path("U", 3.0));
		yy2SubMap.put("X", new Path("X", 3.0));
		yy2SubMap.put("Y", new Path("Y", 0.0));
		yy2SubMap.put("Z", new Path("X", 5.0));
		y2Map.put("U", yu2SubMap);
		y2Map.put("X", yx2SubMap);
		y2Map.put("Y", yy2SubMap);
		//Node Z
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> z2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> zv2SubMap = new ConcurrentSkipListMap<String, Path>();
		zv2SubMap.put("U", new Path("U", 1.0));
		zv2SubMap.put("V", new Path("V", 0.0));
		zv2SubMap.put("X", new Path("X", 3.0));
		zv2SubMap.put("Y", new Path("U", 3.0));
		zv2SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> zx2SubMap = new ConcurrentSkipListMap<String, Path>();
		zx2SubMap.put("U", new Path("V", 4.0));
		zx2SubMap.put("V", new Path("V", 3.0));
		zx2SubMap.put("X", new Path("X", 0.0));
		zx2SubMap.put("Y", new Path("Y", 3.0));
		zx2SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> zz2SubMap = new ConcurrentSkipListMap<String, Path>();
		zz2SubMap.put("U", new Path("V", 6.0));
		zz2SubMap.put("V", new Path("X", 5.0));
		zz2SubMap.put("X", new Path("X", 2.0));
		zz2SubMap.put("Y", new Path("X", 5.0));
		zz2SubMap.put("Z", new Path("Z", 0.0));
		z2Map.put("V", zv2SubMap);
		z2Map.put("X", zx2SubMap);
		z2Map.put("Z", zz2SubMap);
		//Add to run's map
		step2Map.put("U", u2Map);
		step2Map.put("V", v2Map);
		step2Map.put("X", x2Map);
		step2Map.put("Y", y2Map);
		step2Map.put("Z", z2Map);
		////////////
		// Step 3 //
		////////////
		step3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node U
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> u3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> uu3SubMap = new ConcurrentSkipListMap<String, Path>();
		uu3SubMap.put("U", new Path("U", 0.0));
		uu3SubMap.put("V", new Path("V", 1.0));
		uu3SubMap.put("X", new Path("V", 4.0));
		uu3SubMap.put("Y", new Path("Y", 2.0));
		uu3SubMap.put("Z", new Path("V", 6.0));
		ConcurrentNavigableMap<String, Path> uv3SubMap = new ConcurrentSkipListMap<String, Path>();
		uv3SubMap.put("U", new Path("U", 1.0));
		uv3SubMap.put("V", new Path("V", 0.0));
		uv3SubMap.put("X", new Path("X", 3.0));
		uv3SubMap.put("Y", new Path("U", 3.0));
		uv3SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> uy3SubMap = new ConcurrentSkipListMap<String, Path>();
		uy3SubMap.put("U", new Path("U", 2.0));
		uy3SubMap.put("V", new Path("U", 3.0));
		uy3SubMap.put("X", new Path("X", 3.0));
		uy3SubMap.put("Y", new Path("Y", 0.0));
		uy3SubMap.put("Z", new Path("X", 5.0));
		u3Map.put("U", uu3SubMap);
		u3Map.put("V", uv3SubMap);
		u3Map.put("Y", uy3SubMap);
		//Node V
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> v3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> vu3SubMap = new ConcurrentSkipListMap<String, Path>();
		vu3SubMap.put("U", new Path("U", 0.0));
		vu3SubMap.put("V", new Path("V", 1.0));
		vu3SubMap.put("X", new Path("V", 4.0));
		vu3SubMap.put("Y", new Path("Y", 2.0));
		vu3SubMap.put("Z", new Path("V", 6.0));
		ConcurrentNavigableMap<String, Path> vv3SubMap = new ConcurrentSkipListMap<String, Path>();
		vv3SubMap.put("U", new Path("U", 1.0));
		vv3SubMap.put("V", new Path("V", 0.0));
		vv3SubMap.put("X", new Path("X", 3.0));
		vv3SubMap.put("Y", new Path("U", 3.0));
		vv3SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> vx3SubMap = new ConcurrentSkipListMap<String, Path>();
		vx3SubMap.put("U", new Path("V", 4.0));
		vx3SubMap.put("V", new Path("V", 3.0));
		vx3SubMap.put("X", new Path("X", 0.0));
		vx3SubMap.put("Y", new Path("Y", 3.0));
		vx3SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> vz3SubMap = new ConcurrentSkipListMap<String, Path>();
		vz3SubMap.put("U", new Path("V", 6.0));
		vz3SubMap.put("V", new Path("X", 5.0));
		vz3SubMap.put("X", new Path("X", 2.0));
		vz3SubMap.put("Y", new Path("X", 5.0));
		vz3SubMap.put("Z", new Path("Z", 0.0));
		v3Map.put("U", vu3SubMap);
		v3Map.put("V", vv3SubMap);
		v3Map.put("X", vx3SubMap);
		v3Map.put("Z", vz3SubMap);
		//Node X
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> x3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> xv3SubMap = new ConcurrentSkipListMap<String, Path>();
		xv3SubMap.put("U", new Path("U", 1.0));
		xv3SubMap.put("V", new Path("V", 0.0));
		xv3SubMap.put("X", new Path("X", 3.0));
		xv3SubMap.put("Y", new Path("U", 3.0));
		xv3SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> xx3SubMap = new ConcurrentSkipListMap<String, Path>();
		xx3SubMap.put("U", new Path("V", 4.0));
		xx3SubMap.put("V", new Path("V", 3.0));
		xx3SubMap.put("X", new Path("X", 0.0));
		xx3SubMap.put("Y", new Path("Y", 3.0));
		xx3SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> xy3SubMap = new ConcurrentSkipListMap<String, Path>();
		xy3SubMap.put("U", new Path("U", 2.0));
		xy3SubMap.put("V", new Path("U", 3.0));
		xy3SubMap.put("X", new Path("X", 3.0));
		xy3SubMap.put("Y", new Path("Y", 0.0));
		xy3SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> xz3SubMap = new ConcurrentSkipListMap<String, Path>();
		xz3SubMap.put("U", new Path("V", 6.0));
		xz3SubMap.put("V", new Path("X", 5.0));
		xz3SubMap.put("X", new Path("X", 2.0));
		xz3SubMap.put("Y", new Path("X", 5.0));
		xz3SubMap.put("Z", new Path("Z", 0.0));
		x3Map.put("V", xv3SubMap);
		x3Map.put("X", xx3SubMap);
		x3Map.put("Y", xy3SubMap);
		x3Map.put("Z", xz3SubMap);
		//Node Y
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> y3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> yu3SubMap = new ConcurrentSkipListMap<String, Path>();
		yu3SubMap.put("U", new Path("U", 0.0));
		yu3SubMap.put("V", new Path("V", 1.0));
		yu3SubMap.put("X", new Path("V", 4.0));
		yu3SubMap.put("Y", new Path("Y", 2.0));
		yu3SubMap.put("Z", new Path("V", 6.0));
		ConcurrentNavigableMap<String, Path> yx3SubMap = new ConcurrentSkipListMap<String, Path>();
		yx3SubMap.put("U", new Path("V", 4.0));
		yx3SubMap.put("V", new Path("V", 3.0));
		yx3SubMap.put("X", new Path("X", 0.0));
		yx3SubMap.put("Y", new Path("Y", 3.0));
		yx3SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> yy3SubMap = new ConcurrentSkipListMap<String, Path>();
		yy3SubMap.put("U", new Path("U", 2.0));
		yy3SubMap.put("V", new Path("U", 3.0));
		yy3SubMap.put("X", new Path("X", 3.0));
		yy3SubMap.put("Y", new Path("Y", 0.0));
		yy3SubMap.put("Z", new Path("X", 5.0));
		y3Map.put("U", yu3SubMap);
		y3Map.put("X", yx3SubMap);
		y3Map.put("Y", yy3SubMap);
		//Node Z
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> z3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> zv3SubMap = new ConcurrentSkipListMap<String, Path>();
		zv3SubMap.put("U", new Path("U", 1.0));
		zv3SubMap.put("V", new Path("V", 0.0));
		zv3SubMap.put("X", new Path("X", 3.0));
		zv3SubMap.put("Y", new Path("U", 3.0));
		zv3SubMap.put("Z", new Path("X", 5.0));
		ConcurrentNavigableMap<String, Path> zx3SubMap = new ConcurrentSkipListMap<String, Path>();
		zx3SubMap.put("U", new Path("V", 4.0));
		zx3SubMap.put("V", new Path("V", 3.0));
		zx3SubMap.put("X", new Path("X", 0.0));
		zx3SubMap.put("Y", new Path("Y", 3.0));
		zx3SubMap.put("Z", new Path("Z", 2.0));
		ConcurrentNavigableMap<String, Path> zz3SubMap = new ConcurrentSkipListMap<String, Path>();
		zz3SubMap.put("U", new Path("V", 6.0));
		zz3SubMap.put("V", new Path("X", 5.0));
		zz3SubMap.put("X", new Path("X", 2.0));
		zz3SubMap.put("Y", new Path("X", 5.0));
		zz3SubMap.put("Z", new Path("Z", 0.0));
		z3Map.put("V", zv3SubMap);
		z3Map.put("X", zx3SubMap);
		z3Map.put("Z", zz3SubMap);
		//Add to run's map
		step3Map.put("U", u3Map);
		step3Map.put("V", v3Map);
		step3Map.put("X", x3Map);
		step3Map.put("Y", y3Map);
		step3Map.put("Z", z3Map);
	}
}
