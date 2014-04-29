package routing.dv.tests;

/**
 * Distance Vector Simulator Tester which checks for correct updates, messages, and route calculations using the DV algorithm
 * Testing a disconnected network
 * 
 * @author Vincent Lee
 * @since April 7, 2014
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

public class DVSimulatorTestDisconnected {
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step0Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step1Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step2Map;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DVSimulator.main(new String[] {"disconnected", "--debug"});
	}
	
	@Test
	public void step0() {
		//Node A
		assertEquals("NodeA-NeighborRoutes", step0Map.get("A").keySet().toString(), DVSimulator.nodeMap.get("A").routeMap.keySet().toString());
		assertEquals("NodeA-RowA", step0Map.get("A").get("A").toString(), DVSimulator.nodeMap.get("A").routeMap.get("A").toString());
		
		//Node B
		assertEquals("NodeB-NeighborRoutes", step0Map.get("B").keySet().toString(), DVSimulator.nodeMap.get("B").routeMap.keySet().toString());
		assertEquals("NodeB-RowB", step0Map.get("B").get("B").toString(), DVSimulator.nodeMap.get("B").routeMap.get("B").toString());
		
		//Node C
		assertEquals("NodeC-NeighborRoutes", step0Map.get("C").keySet().toString(), DVSimulator.nodeMap.get("C").routeMap.keySet().toString());
		assertEquals("NodeC-RowC", step0Map.get("C").get("C").toString(), DVSimulator.nodeMap.get("C").routeMap.get("C").toString());
		
		//Node D
		assertEquals("NodeD-NeighborRoutes", step0Map.get("D").keySet().toString(), DVSimulator.nodeMap.get("D").routeMap.keySet().toString());
		assertEquals("NodeD-RowD", step0Map.get("D").get("D").toString(), DVSimulator.nodeMap.get("D").routeMap.get("D").toString());
		
		//Node E
		assertEquals("NodeE-NeighborRoutes", step0Map.get("E").keySet().toString(), DVSimulator.nodeMap.get("E").routeMap.keySet().toString());
		assertEquals("NodeE-RowE", step0Map.get("E").get("E").toString(), DVSimulator.nodeMap.get("E").routeMap.get("E").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-0 Messages", 0, Node.messages);
		//Nodes that update
		assertEquals("Round-0 Updates", 0, Node.updates);
	}
	
	@Test
	public void step1() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node A
		assertEquals("NodeA-NeighborRoutes", step1Map.get("A").keySet().toString(), DVSimulator.nodeMap.get("A").routeMap.keySet().toString());
		assertEquals("NodeA-RowA", step1Map.get("A").get("A").toString(), DVSimulator.nodeMap.get("A").routeMap.get("A").toString());
		assertEquals("NodeA-RowB", step1Map.get("A").get("B").toString(), DVSimulator.nodeMap.get("A").routeMap.get("B").toString());
		
		//Node B
		assertEquals("NodeB-NeighborRoutes", step1Map.get("B").keySet().toString(), DVSimulator.nodeMap.get("B").routeMap.keySet().toString());
		assertEquals("NodeB-RowA", step1Map.get("B").get("A").toString(), DVSimulator.nodeMap.get("B").routeMap.get("A").toString());
		assertEquals("NodeB-RowB", step1Map.get("B").get("B").toString(), DVSimulator.nodeMap.get("B").routeMap.get("B").toString());
		assertEquals("NodeB-RowC", step1Map.get("B").get("C").toString(), DVSimulator.nodeMap.get("B").routeMap.get("C").toString());
		
		//Node C
		assertEquals("NodeC-NeighborRoutes", step1Map.get("C").keySet().toString(), DVSimulator.nodeMap.get("C").routeMap.keySet().toString());
		assertEquals("NodeC-RowB", step1Map.get("C").get("B").toString(), DVSimulator.nodeMap.get("C").routeMap.get("B").toString());
		assertEquals("NodeC-RowC", step1Map.get("C").get("C").toString(), DVSimulator.nodeMap.get("C").routeMap.get("C").toString());
		
		//Node D
		assertEquals("NodeD-NeighborRoutes", step1Map.get("D").keySet().toString(), DVSimulator.nodeMap.get("D").routeMap.keySet().toString());
		assertEquals("NodeD-RowD", step1Map.get("D").get("D").toString(), DVSimulator.nodeMap.get("D").routeMap.get("D").toString());
		assertEquals("NodeD-RowE", step1Map.get("D").get("E").toString(), DVSimulator.nodeMap.get("D").routeMap.get("E").toString());
		
		//Node E
		assertEquals("NodeE-NeighborRoutes", step1Map.get("E").keySet().toString(), DVSimulator.nodeMap.get("E").routeMap.keySet().toString());
		assertEquals("NodeE-RowD", step1Map.get("E").get("D").toString(), DVSimulator.nodeMap.get("E").routeMap.get("D").toString());
		assertEquals("NodeE-RowE", step1Map.get("E").get("E").toString(), DVSimulator.nodeMap.get("E").routeMap.get("E").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-1 Messages", 6, Node.messages);
		//Nodes that update
		assertEquals("Round-1 Updates", 5, Node.updates);
	}
	
	@Test
	public void step2() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node A
		assertEquals("NodeA-NeighborRoutes", step2Map.get("A").keySet().toString(), DVSimulator.nodeMap.get("A").routeMap.keySet().toString());
		assertEquals("NodeA-RowA", step2Map.get("A").get("A").toString(), DVSimulator.nodeMap.get("A").routeMap.get("A").toString());
		assertEquals("NodeA-RowB", step2Map.get("A").get("B").toString(), DVSimulator.nodeMap.get("A").routeMap.get("B").toString());
		
		//Node B
		assertEquals("NodeB-NeighborRoutes", step2Map.get("B").keySet().toString(), DVSimulator.nodeMap.get("B").routeMap.keySet().toString());
		assertEquals("NodeB-RowA", step2Map.get("B").get("A").toString(), DVSimulator.nodeMap.get("B").routeMap.get("A").toString());
		assertEquals("NodeB-RowB", step2Map.get("B").get("B").toString(), DVSimulator.nodeMap.get("B").routeMap.get("B").toString());
		assertEquals("NodeB-RowC", step2Map.get("B").get("C").toString(), DVSimulator.nodeMap.get("B").routeMap.get("C").toString());
		
		//Node C
		assertEquals("NodeC-NeighborRoutes", step2Map.get("C").keySet().toString(), DVSimulator.nodeMap.get("C").routeMap.keySet().toString());
		assertEquals("NodeC-RowB", step2Map.get("C").get("B").toString(), DVSimulator.nodeMap.get("C").routeMap.get("B").toString());
		assertEquals("NodeC-RowC", step2Map.get("C").get("C").toString(), DVSimulator.nodeMap.get("C").routeMap.get("C").toString());
		
		//Node D
		assertEquals("NodeD-NeighborRoutes", step2Map.get("D").keySet().toString(), DVSimulator.nodeMap.get("D").routeMap.keySet().toString());
		assertEquals("NodeD-RowD", step2Map.get("D").get("D").toString(), DVSimulator.nodeMap.get("D").routeMap.get("D").toString());
		assertEquals("NodeD-RowE", step2Map.get("D").get("E").toString(), DVSimulator.nodeMap.get("D").routeMap.get("E").toString());
		
		//Node E
		assertEquals("NodeE-NeighborRoutes", step2Map.get("E").keySet().toString(), DVSimulator.nodeMap.get("E").routeMap.keySet().toString());
		assertEquals("NodeE-RowD", step2Map.get("E").get("D").toString(), DVSimulator.nodeMap.get("E").routeMap.get("D").toString());
		assertEquals("NodeE-RowE", step2Map.get("E").get("E").toString(), DVSimulator.nodeMap.get("E").routeMap.get("E").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-2 Messages", 2, Node.messages);
		//Nodes that update
		assertEquals("Round-2 Updates", 1, Node.updates);
	}
	
	@Test
	public void step3() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Route messages sent to other nodes
		assertEquals("Round-3 Messages", 0, Node.messages);
		//Nodes that update
		assertEquals("Round-3 Updates", 0, Node.updates);
	}
	
	@Before
	public void setUp() {
		////////////
		// Step 0 //
		////////////
		step0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node A
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> a0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> aa0SubMap = new ConcurrentSkipListMap<String, Path>();
		aa0SubMap.put("A", new Path("A", 0.0));
		aa0SubMap.put("B", new Path("B", 1.2));
		a0Map.put("A", aa0SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> bb0SubMap = new ConcurrentSkipListMap<String, Path>();
		bb0SubMap.put("A", new Path("A", 1.2));
		bb0SubMap.put("B", new Path("B", 0.0));
		bb0SubMap.put("C", new Path("C", 3.8));
		b0Map.put("B", bb0SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> cc0SubMap = new ConcurrentSkipListMap<String, Path>();
		cc0SubMap.put("B", new Path("B", 3.8));
		cc0SubMap.put("C", new Path("C", 0.0));
		c0Map.put("C", cc0SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> dd0SubMap = new ConcurrentSkipListMap<String, Path>();
		dd0SubMap.put("D", new Path("D", 0.0));
		dd0SubMap.put("E", new Path("E", 1.7));
		d0Map.put("D", dd0SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ee0SubMap = new ConcurrentSkipListMap<String, Path>();
		ee0SubMap.put("D", new Path("D", 1.7));
		ee0SubMap.put("E", new Path("E", 0.0));
		e0Map.put("E", ee0SubMap);
		//Add to run's map
		step0Map.put("A", a0Map);
		step0Map.put("B", b0Map);
		step0Map.put("C", c0Map);
		step0Map.put("D", d0Map);
		step0Map.put("E", e0Map);
		////////////
		// Step 1 //
		////////////
		step1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node A
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> a1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> aa1SubMap = new ConcurrentSkipListMap<String, Path>();
		aa1SubMap.put("A", new Path("A", 0.0));
		aa1SubMap.put("B", new Path("B", 1.2));
		aa1SubMap.put("C", new Path("B", 5.0));
		ConcurrentNavigableMap<String, Path> ab1SubMap = new ConcurrentSkipListMap<String, Path>();
		ab1SubMap.put("A", new Path("A", 1.2));
		ab1SubMap.put("B", new Path("B", 0.0));
		ab1SubMap.put("C", new Path("C", 3.8));
		a1Map.put("A", aa1SubMap);
		a1Map.put("B", ab1SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ba1SubMap = new ConcurrentSkipListMap<String, Path>();
		ba1SubMap.put("A", new Path("A", 0.0));
		ba1SubMap.put("B", new Path("B", 1.2));
		ConcurrentNavigableMap<String, Path> bb1SubMap = new ConcurrentSkipListMap<String, Path>();
		bb1SubMap.put("A", new Path("A", 1.2));
		bb1SubMap.put("B", new Path("B", 0.0));
		bb1SubMap.put("C", new Path("C", 3.8));
		ConcurrentNavigableMap<String, Path> bc1SubMap = new ConcurrentSkipListMap<String, Path>();
		bc1SubMap.put("B", new Path("B", 3.8));
		bc1SubMap.put("C", new Path("C", 0.0));
		b1Map.put("A", ba1SubMap);
		b1Map.put("B", bb1SubMap);
		b1Map.put("C", bc1SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> cb1SubMap = new ConcurrentSkipListMap<String, Path>();
		cb1SubMap.put("A", new Path("A", 1.2));
		cb1SubMap.put("B", new Path("B", 0.0));
		cb1SubMap.put("C", new Path("C", 3.8));
		ConcurrentNavigableMap<String, Path> cc1SubMap = new ConcurrentSkipListMap<String, Path>();
		cc1SubMap.put("A", new Path("B", 5.0));
		cc1SubMap.put("B", new Path("B", 3.8));
		cc1SubMap.put("C", new Path("C", 0.0));
		c1Map.put("B", cb1SubMap);
		c1Map.put("C", cc1SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> dd1SubMap = new ConcurrentSkipListMap<String, Path>();
		dd1SubMap.put("D", new Path("D", 0.0));
		dd1SubMap.put("E", new Path("E", 1.7));
		ConcurrentNavigableMap<String, Path> de1SubMap = new ConcurrentSkipListMap<String, Path>();
		de1SubMap.put("D", new Path("D", 1.7));
		de1SubMap.put("E", new Path("E", 0.0));
		d1Map.put("D", dd1SubMap);
		d1Map.put("E", de1SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ed1SubMap = new ConcurrentSkipListMap<String, Path>();
		ed1SubMap.put("D", new Path("D", 0.0));
		ed1SubMap.put("E", new Path("E", 1.7));
		ConcurrentNavigableMap<String, Path> ee1SubMap = new ConcurrentSkipListMap<String, Path>();
		ee1SubMap.put("D", new Path("D", 1.7));
		ee1SubMap.put("E", new Path("E", 0.0));
		e1Map.put("D", ed1SubMap);
		e1Map.put("E", ee1SubMap);
		//Add to run's map
		step1Map.put("A", a1Map);
		step1Map.put("B", b1Map);
		step1Map.put("C", c1Map);
		step1Map.put("D", d1Map);
		step1Map.put("E", e1Map);
		////////////
		// Step 2 //
		////////////
		step2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node A
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> a2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> aa2SubMap = new ConcurrentSkipListMap<String, Path>();
		aa2SubMap.put("A", new Path("A", 0.0));
		aa2SubMap.put("B", new Path("B", 1.2));
		aa2SubMap.put("C", new Path("B", 5.0));
		ConcurrentNavigableMap<String, Path> ab2SubMap = new ConcurrentSkipListMap<String, Path>();
		ab2SubMap.put("A", new Path("A", 1.2));
		ab2SubMap.put("B", new Path("B", 0.0));
		ab2SubMap.put("C", new Path("C", 3.8));
		a2Map.put("A", aa2SubMap);
		a2Map.put("B", ab2SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ba2SubMap = new ConcurrentSkipListMap<String, Path>();
		ba2SubMap.put("A", new Path("A", 0.0));
		ba2SubMap.put("B", new Path("B", 1.2));
		ba2SubMap.put("C", new Path("B", 5.0));
		ConcurrentNavigableMap<String, Path> bb2SubMap = new ConcurrentSkipListMap<String, Path>();
		bb2SubMap.put("A", new Path("A", 1.2));
		bb2SubMap.put("B", new Path("B", 0.0));
		bb2SubMap.put("C", new Path("C", 3.8));
		ConcurrentNavigableMap<String, Path> bc2SubMap = new ConcurrentSkipListMap<String, Path>();
		bc2SubMap.put("A", new Path("B", 5.0));
		bc2SubMap.put("B", new Path("B", 3.8));
		bc2SubMap.put("C", new Path("C", 0.0));
		b2Map.put("A", ba2SubMap);
		b2Map.put("B", bb2SubMap);
		b2Map.put("C", bc2SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> cb2SubMap = new ConcurrentSkipListMap<String, Path>();
		cb2SubMap.put("A", new Path("A", 1.2));
		cb2SubMap.put("B", new Path("B", 0.0));
		cb2SubMap.put("C", new Path("C", 3.8));
		ConcurrentNavigableMap<String, Path> cc2SubMap = new ConcurrentSkipListMap<String, Path>();
		cc2SubMap.put("A", new Path("B", 5.0));
		cc2SubMap.put("B", new Path("B", 3.8));
		cc2SubMap.put("C", new Path("C", 0.0));
		c2Map.put("B", cb2SubMap);
		c2Map.put("C", cc2SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> dd2SubMap = new ConcurrentSkipListMap<String, Path>();
		dd2SubMap.put("D", new Path("D", 0.0));
		dd2SubMap.put("E", new Path("E", 1.7));
		ConcurrentNavigableMap<String, Path> de2SubMap = new ConcurrentSkipListMap<String, Path>();
		de2SubMap.put("D", new Path("D", 1.7));
		de2SubMap.put("E", new Path("E", 0.0));
		d2Map.put("D", dd2SubMap);
		d2Map.put("E", de2SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ed2SubMap = new ConcurrentSkipListMap<String, Path>();
		ed2SubMap.put("D", new Path("D", 0.0));
		ed2SubMap.put("E", new Path("E", 1.7));
		ConcurrentNavigableMap<String, Path> ee2SubMap = new ConcurrentSkipListMap<String, Path>();
		ee2SubMap.put("D", new Path("D", 1.7));
		ee2SubMap.put("E", new Path("E", 0.0));
		e2Map.put("D", ed2SubMap);
		e2Map.put("E", ee2SubMap);
		//Add to run's map
		step2Map.put("A", a2Map);
		step2Map.put("B", b2Map);
		step2Map.put("C", c2Map);
		step2Map.put("D", d2Map);
		step2Map.put("E", e2Map);
	}
}
