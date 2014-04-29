package routing.dv.tests;

/**
 * Distance Vector Simulator Tester which checks for correct updates, messages, and route calculations using the DV algorithm
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

public class DVSimulatorTest {
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step0Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step1Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step2Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step3Map;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>> step4Map;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		DVSimulator.main(new String[] {"topology", "--debug"});
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
		
		//Node F
		assertEquals("NodeF-NeighborRoutes", step0Map.get("F").keySet().toString(), DVSimulator.nodeMap.get("F").routeMap.keySet().toString());
		assertEquals("NodeF-RowF", step0Map.get("F").get("F").toString(), DVSimulator.nodeMap.get("F").routeMap.get("F").toString());
		
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
		assertEquals("NodeA-RowC", step1Map.get("A").get("C").toString(), DVSimulator.nodeMap.get("A").routeMap.get("C").toString());
		
		//Node B
		assertEquals("NodeB-NeighborRoutes", step1Map.get("B").keySet().toString(), DVSimulator.nodeMap.get("B").routeMap.keySet().toString());
		assertEquals("NodeB-RowA", step1Map.get("B").get("A").toString(), DVSimulator.nodeMap.get("B").routeMap.get("A").toString());
		assertEquals("NodeB-RowB", step1Map.get("B").get("B").toString(), DVSimulator.nodeMap.get("B").routeMap.get("B").toString());
		assertEquals("NodeB-RowD", step1Map.get("B").get("D").toString(), DVSimulator.nodeMap.get("B").routeMap.get("D").toString());
		assertEquals("NodeB-RowE", step1Map.get("B").get("E").toString(), DVSimulator.nodeMap.get("B").routeMap.get("E").toString());
		
		//Node C
		assertEquals("NodeC-NeighborRoutes", step1Map.get("C").keySet().toString(), DVSimulator.nodeMap.get("C").routeMap.keySet().toString());
		assertEquals("NodeC-RowA", step1Map.get("C").get("A").toString(), DVSimulator.nodeMap.get("C").routeMap.get("A").toString());
		assertEquals("NodeC-RowC", step1Map.get("C").get("C").toString(), DVSimulator.nodeMap.get("C").routeMap.get("C").toString());
		assertEquals("NodeC-RowD", step1Map.get("C").get("D").toString(), DVSimulator.nodeMap.get("C").routeMap.get("D").toString());
		
		//Node D
		assertEquals("NodeD-NeighborRoutes", step1Map.get("D").keySet().toString(), DVSimulator.nodeMap.get("D").routeMap.keySet().toString());
		assertEquals("NodeD-RowB", step1Map.get("D").get("B").toString(), DVSimulator.nodeMap.get("D").routeMap.get("B").toString());
		assertEquals("NodeD-RowC", step1Map.get("D").get("C").toString(), DVSimulator.nodeMap.get("D").routeMap.get("C").toString());
		assertEquals("NodeD-RowD", step1Map.get("D").get("D").toString(), DVSimulator.nodeMap.get("D").routeMap.get("D").toString());
		assertEquals("NodeD-RowE", step1Map.get("D").get("E").toString(), DVSimulator.nodeMap.get("D").routeMap.get("E").toString());
		
		//Node E
		assertEquals("NodeE-NeighborRoutes", step1Map.get("E").keySet().toString(), DVSimulator.nodeMap.get("E").routeMap.keySet().toString());
		assertEquals("NodeE-RowB", step1Map.get("E").get("B").toString(), DVSimulator.nodeMap.get("E").routeMap.get("B").toString());
		assertEquals("NodeE-RowD", step1Map.get("E").get("D").toString(), DVSimulator.nodeMap.get("E").routeMap.get("D").toString());
		assertEquals("NodeE-RowE", step1Map.get("E").get("E").toString(), DVSimulator.nodeMap.get("E").routeMap.get("E").toString());
		assertEquals("NodeE-RowF", step1Map.get("E").get("F").toString(), DVSimulator.nodeMap.get("E").routeMap.get("F").toString());
		
		//Node F
		assertEquals("NodeF-NeighborRoutes", step1Map.get("F").keySet().toString(), DVSimulator.nodeMap.get("F").routeMap.keySet().toString());
		assertEquals("NodeF-RowE", step1Map.get("F").get("E").toString(), DVSimulator.nodeMap.get("F").routeMap.get("E").toString());
		assertEquals("NodeF-RowF", step1Map.get("F").get("F").toString(), DVSimulator.nodeMap.get("F").routeMap.get("F").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-1 Messages", 14, Node.messages);
		//Nodes that update
		assertEquals("Round-1 Updates", 6, Node.updates);
	}
	
	@Test
	public void step2() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node A
		assertEquals("NodeA-NeighborRoutes", step2Map.get("A").keySet().toString(), DVSimulator.nodeMap.get("A").routeMap.keySet().toString());
		assertEquals("NodeA-RowA", step2Map.get("A").get("A").toString(), DVSimulator.nodeMap.get("A").routeMap.get("A").toString());
		assertEquals("NodeA-RowB", step2Map.get("A").get("B").toString(), DVSimulator.nodeMap.get("A").routeMap.get("B").toString());
		assertEquals("NodeA-RowC", step2Map.get("A").get("C").toString(), DVSimulator.nodeMap.get("A").routeMap.get("C").toString());
		
		//Node B
		assertEquals("NodeB-NeighborRoutes", step2Map.get("B").keySet().toString(), DVSimulator.nodeMap.get("B").routeMap.keySet().toString());
		assertEquals("NodeB-RowA", step2Map.get("B").get("A").toString(), DVSimulator.nodeMap.get("B").routeMap.get("A").toString());
		assertEquals("NodeB-RowB", step2Map.get("B").get("B").toString(), DVSimulator.nodeMap.get("B").routeMap.get("B").toString());
		assertEquals("NodeB-RowD", step2Map.get("B").get("D").toString(), DVSimulator.nodeMap.get("B").routeMap.get("D").toString());
		assertEquals("NodeB-RowE", step2Map.get("B").get("E").toString(), DVSimulator.nodeMap.get("B").routeMap.get("E").toString());
		
		//Node C
		assertEquals("NodeC-NeighborRoutes", step2Map.get("C").keySet().toString(), DVSimulator.nodeMap.get("C").routeMap.keySet().toString());
		assertEquals("NodeC-RowA", step2Map.get("C").get("A").toString(), DVSimulator.nodeMap.get("C").routeMap.get("A").toString());
		assertEquals("NodeC-RowC", step2Map.get("C").get("C").toString(), DVSimulator.nodeMap.get("C").routeMap.get("C").toString());
		assertEquals("NodeC-RowD", step2Map.get("C").get("D").toString(), DVSimulator.nodeMap.get("C").routeMap.get("D").toString());
		
		//Node D
		assertEquals("NodeD-NeighborRoutes", step2Map.get("D").keySet().toString(), DVSimulator.nodeMap.get("D").routeMap.keySet().toString());
		assertEquals("NodeD-RowB", step2Map.get("D").get("B").toString(), DVSimulator.nodeMap.get("D").routeMap.get("B").toString());
		assertEquals("NodeD-RowC", step2Map.get("D").get("C").toString(), DVSimulator.nodeMap.get("D").routeMap.get("C").toString());
		assertEquals("NodeD-RowD", step2Map.get("D").get("D").toString(), DVSimulator.nodeMap.get("D").routeMap.get("D").toString());
		assertEquals("NodeD-RowE", step2Map.get("D").get("E").toString(), DVSimulator.nodeMap.get("D").routeMap.get("E").toString());
		
		//Node E
		assertEquals("NodeE-NeighborRoutes", step2Map.get("E").keySet().toString(), DVSimulator.nodeMap.get("E").routeMap.keySet().toString());
		assertEquals("NodeE-RowB", step2Map.get("E").get("B").toString(), DVSimulator.nodeMap.get("E").routeMap.get("B").toString());
		assertEquals("NodeE-RowD", step2Map.get("E").get("D").toString(), DVSimulator.nodeMap.get("E").routeMap.get("D").toString());
		assertEquals("NodeE-RowE", step2Map.get("E").get("E").toString(), DVSimulator.nodeMap.get("E").routeMap.get("E").toString());
		assertEquals("NodeE-RowF", step2Map.get("E").get("F").toString(), DVSimulator.nodeMap.get("E").routeMap.get("F").toString());
		
		//Node F
		assertEquals("NodeF-NeighborRoutes", step2Map.get("F").keySet().toString(), DVSimulator.nodeMap.get("F").routeMap.keySet().toString());
		assertEquals("NodeF-RowE", step2Map.get("F").get("E").toString(), DVSimulator.nodeMap.get("F").routeMap.get("E").toString());
		assertEquals("NodeF-RowF", step2Map.get("F").get("F").toString(), DVSimulator.nodeMap.get("F").routeMap.get("F").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-2 Messages", 14, Node.messages);
		//Nodes that update
		assertEquals("Round-2 Updates", 6, Node.updates);
	}
	
	@Test
	public void step3() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node A
		assertEquals("NodeA-NeighborRoutes", step3Map.get("A").keySet().toString(), DVSimulator.nodeMap.get("A").routeMap.keySet().toString());
		assertEquals("NodeA-RowA", step3Map.get("A").get("A").toString(), DVSimulator.nodeMap.get("A").routeMap.get("A").toString());
		assertEquals("NodeA-RowB", step3Map.get("A").get("B").toString(), DVSimulator.nodeMap.get("A").routeMap.get("B").toString());
		assertEquals("NodeA-RowC", step3Map.get("A").get("C").toString(), DVSimulator.nodeMap.get("A").routeMap.get("C").toString());
		
		//Node B
		assertEquals("NodeB-NeighborRoutes", step3Map.get("B").keySet().toString(), DVSimulator.nodeMap.get("B").routeMap.keySet().toString());
		assertEquals("NodeB-RowA", step3Map.get("B").get("A").toString(), DVSimulator.nodeMap.get("B").routeMap.get("A").toString());
		assertEquals("NodeB-RowB", step3Map.get("B").get("B").toString(), DVSimulator.nodeMap.get("B").routeMap.get("B").toString());
		assertEquals("NodeB-RowD", step3Map.get("B").get("D").toString(), DVSimulator.nodeMap.get("B").routeMap.get("D").toString());
		assertEquals("NodeB-RowE", step3Map.get("B").get("E").toString(), DVSimulator.nodeMap.get("B").routeMap.get("E").toString());
		
		//Node C
		assertEquals("NodeC-NeighborRoutes", step3Map.get("C").keySet().toString(), DVSimulator.nodeMap.get("C").routeMap.keySet().toString());
		assertEquals("NodeC-RowA", step3Map.get("C").get("A").toString(), DVSimulator.nodeMap.get("C").routeMap.get("A").toString());
		assertEquals("NodeC-RowC", step3Map.get("C").get("C").toString(), DVSimulator.nodeMap.get("C").routeMap.get("C").toString());
		assertEquals("NodeC-RowD", step3Map.get("C").get("D").toString(), DVSimulator.nodeMap.get("C").routeMap.get("D").toString());
		
		//Node D
		assertEquals("NodeD-NeighborRoutes", step3Map.get("D").keySet().toString(), DVSimulator.nodeMap.get("D").routeMap.keySet().toString());
		assertEquals("NodeD-RowB", step3Map.get("D").get("B").toString(), DVSimulator.nodeMap.get("D").routeMap.get("B").toString());
		assertEquals("NodeD-RowC", step3Map.get("D").get("C").toString(), DVSimulator.nodeMap.get("D").routeMap.get("C").toString());
		assertEquals("NodeD-RowD", step3Map.get("D").get("D").toString(), DVSimulator.nodeMap.get("D").routeMap.get("D").toString());
		assertEquals("NodeD-RowE", step3Map.get("D").get("E").toString(), DVSimulator.nodeMap.get("D").routeMap.get("E").toString());
		
		//Node E
		assertEquals("NodeE-NeighborRoutes", step3Map.get("E").keySet().toString(), DVSimulator.nodeMap.get("E").routeMap.keySet().toString());
		assertEquals("NodeE-RowB", step3Map.get("E").get("B").toString(), DVSimulator.nodeMap.get("E").routeMap.get("B").toString());
		assertEquals("NodeE-RowD", step3Map.get("E").get("D").toString(), DVSimulator.nodeMap.get("E").routeMap.get("D").toString());
		assertEquals("NodeE-RowE", step3Map.get("E").get("E").toString(), DVSimulator.nodeMap.get("E").routeMap.get("E").toString());
		assertEquals("NodeE-RowF", step3Map.get("E").get("F").toString(), DVSimulator.nodeMap.get("E").routeMap.get("F").toString());
		
		//Node F
		assertEquals("NodeF-NeighborRoutes", step3Map.get("F").keySet().toString(), DVSimulator.nodeMap.get("F").routeMap.keySet().toString());
		assertEquals("NodeF-RowE", step3Map.get("F").get("E").toString(), DVSimulator.nodeMap.get("F").routeMap.get("E").toString());
		assertEquals("NodeF-RowF", step3Map.get("F").get("F").toString(), DVSimulator.nodeMap.get("F").routeMap.get("F").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-3 Messages", 8, Node.messages);
		//Nodes that update
		assertEquals("Round-3 Updates", 6, Node.updates);
	}
	
	@Test
	public void step4() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Node A
		assertEquals("NodeA-NeighborRoutes", step4Map.get("A").keySet().toString(), DVSimulator.nodeMap.get("A").routeMap.keySet().toString());
		assertEquals("NodeA-RowA", step4Map.get("A").get("A").toString(), DVSimulator.nodeMap.get("A").routeMap.get("A").toString());
		assertEquals("NodeA-RowB", step4Map.get("A").get("B").toString(), DVSimulator.nodeMap.get("A").routeMap.get("B").toString());
		assertEquals("NodeA-RowC", step4Map.get("A").get("C").toString(), DVSimulator.nodeMap.get("A").routeMap.get("C").toString());
		
		//Node B
		assertEquals("NodeB-NeighborRoutes", step4Map.get("B").keySet().toString(), DVSimulator.nodeMap.get("B").routeMap.keySet().toString());
		assertEquals("NodeB-RowA", step4Map.get("B").get("A").toString(), DVSimulator.nodeMap.get("B").routeMap.get("A").toString());
		assertEquals("NodeB-RowB", step4Map.get("B").get("B").toString(), DVSimulator.nodeMap.get("B").routeMap.get("B").toString());
		assertEquals("NodeB-RowD", step4Map.get("B").get("D").toString(), DVSimulator.nodeMap.get("B").routeMap.get("D").toString());
		assertEquals("NodeB-RowE", step4Map.get("B").get("E").toString(), DVSimulator.nodeMap.get("B").routeMap.get("E").toString());
		
		//Node C
		assertEquals("NodeC-NeighborRoutes", step4Map.get("C").keySet().toString(), DVSimulator.nodeMap.get("C").routeMap.keySet().toString());
		assertEquals("NodeC-RowA", step4Map.get("C").get("A").toString(), DVSimulator.nodeMap.get("C").routeMap.get("A").toString());
		assertEquals("NodeC-RowC", step4Map.get("C").get("C").toString(), DVSimulator.nodeMap.get("C").routeMap.get("C").toString());
		assertEquals("NodeC-RowD", step4Map.get("C").get("D").toString(), DVSimulator.nodeMap.get("C").routeMap.get("D").toString());
		
		//Node D
		assertEquals("NodeD-NeighborRoutes", step4Map.get("D").keySet().toString(), DVSimulator.nodeMap.get("D").routeMap.keySet().toString());
		assertEquals("NodeD-RowB", step4Map.get("D").get("B").toString(), DVSimulator.nodeMap.get("D").routeMap.get("B").toString());
		assertEquals("NodeD-RowC", step4Map.get("D").get("C").toString(), DVSimulator.nodeMap.get("D").routeMap.get("C").toString());
		assertEquals("NodeD-RowD", step4Map.get("D").get("D").toString(), DVSimulator.nodeMap.get("D").routeMap.get("D").toString());
		assertEquals("NodeD-RowE", step4Map.get("D").get("E").toString(), DVSimulator.nodeMap.get("D").routeMap.get("E").toString());
		
		//Node E
		assertEquals("NodeE-NeighborRoutes", step4Map.get("E").keySet().toString(), DVSimulator.nodeMap.get("E").routeMap.keySet().toString());
		assertEquals("NodeE-RowB", step4Map.get("E").get("B").toString(), DVSimulator.nodeMap.get("E").routeMap.get("B").toString());
		assertEquals("NodeE-RowD", step4Map.get("E").get("D").toString(), DVSimulator.nodeMap.get("E").routeMap.get("D").toString());
		assertEquals("NodeE-RowE", step4Map.get("E").get("E").toString(), DVSimulator.nodeMap.get("E").routeMap.get("E").toString());
		assertEquals("NodeE-RowF", step4Map.get("E").get("F").toString(), DVSimulator.nodeMap.get("E").routeMap.get("F").toString());
		
		//Node F
		assertEquals("NodeF-NeighborRoutes", step4Map.get("F").keySet().toString(), DVSimulator.nodeMap.get("F").routeMap.keySet().toString());
		assertEquals("NodeF-RowE", step4Map.get("F").get("E").toString(), DVSimulator.nodeMap.get("F").routeMap.get("E").toString());
		assertEquals("NodeF-RowF", step4Map.get("F").get("F").toString(), DVSimulator.nodeMap.get("F").routeMap.get("F").toString());
		
		//Route messages sent to other nodes
		assertEquals("Round-4 Messages", 3, Node.messages);
		//Nodes that update
		assertEquals("Round-4 Updates", 3, Node.updates);
	}
	
	@Test
	public void step5() {
		//Run Program
		DVSimulator.stepByStepRun();
		
		//Route messages sent to other nodes
		assertEquals("Round-5 Messages", 0, Node.messages);
		//Nodes that update
		assertEquals("Round-5 Updates", 0, Node.updates);
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
		aa0SubMap.put("B", new Path("B", 3.8));
		aa0SubMap.put("C", new Path("C", 1.2));
		a0Map.put("A", aa0SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> bb0SubMap = new ConcurrentSkipListMap<String, Path>();
		bb0SubMap.put("A", new Path("A", 3.8));
		bb0SubMap.put("B", new Path("B", 0.0));
		bb0SubMap.put("D", new Path("D", 5.1));
		bb0SubMap.put("E", new Path("E", 1.1));
		b0Map.put("B", bb0SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> cc0SubMap = new ConcurrentSkipListMap<String, Path>();
		cc0SubMap.put("A", new Path("A", 1.2));
		cc0SubMap.put("C", new Path("C", 0.0));
		cc0SubMap.put("D", new Path("D", 4.2));
		c0Map.put("C", cc0SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> dd0SubMap = new ConcurrentSkipListMap<String, Path>();
		dd0SubMap.put("B", new Path("B", 5.1));
		dd0SubMap.put("C", new Path("C", 4.2));
		dd0SubMap.put("D", new Path("D", 0.0));
		dd0SubMap.put("E", new Path("E", 3.2));
		d0Map.put("D", dd0SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ee0SubMap = new ConcurrentSkipListMap<String, Path>();
		ee0SubMap.put("B", new Path("B", 1.1));
		ee0SubMap.put("D", new Path("D", 3.2));
		ee0SubMap.put("E", new Path("E", 0.0));
		ee0SubMap.put("F", new Path("F", 4.4));
		e0Map.put("E", ee0SubMap);
		//Node F
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> f0Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ff0SubMap = new ConcurrentSkipListMap<String, Path>();
		ff0SubMap.put("E", new Path("E", 4.4));
		ff0SubMap.put("F", new Path("F", 0.0));
		f0Map.put("F", ff0SubMap);
		//Add to run's map
		step0Map.put("A", a0Map);
		step0Map.put("B", b0Map);
		step0Map.put("C", c0Map);
		step0Map.put("D", d0Map);
		step0Map.put("E", e0Map);
		step0Map.put("F", f0Map);
		////////////
		// Step 1 //
		////////////
		step1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node A
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> a1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> aa1SubMap = new ConcurrentSkipListMap<String, Path>();
		aa1SubMap.put("A", new Path("A", 0.0));
		aa1SubMap.put("B", new Path("B", 3.8));
		aa1SubMap.put("C", new Path("C", 1.2));
		aa1SubMap.put("D", new Path("C", 5.4));
		aa1SubMap.put("E", new Path("B", 4.9));
		ConcurrentNavigableMap<String, Path> ab1SubMap = new ConcurrentSkipListMap<String, Path>();
		ab1SubMap.put("A", new Path("A", 3.8));
		ab1SubMap.put("B", new Path("B", 0.0));
		ab1SubMap.put("D", new Path("D", 5.1));
		ab1SubMap.put("E", new Path("E", 1.1));
		ConcurrentNavigableMap<String, Path> ac1SubMap = new ConcurrentSkipListMap<String, Path>();
		ac1SubMap.put("A", new Path("A", 1.2));
		ac1SubMap.put("C", new Path("C", 0.0));
		ac1SubMap.put("D", new Path("D", 4.2));
		a1Map.put("A", aa1SubMap);
		a1Map.put("B", ab1SubMap);
		a1Map.put("C", ac1SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ba1SubMap = new ConcurrentSkipListMap<String, Path>();
		ba1SubMap.put("A", new Path("A", 0.0));
		ba1SubMap.put("B", new Path("B", 3.8));
		ba1SubMap.put("C", new Path("C", 1.2));
		ConcurrentNavigableMap<String, Path> bb1SubMap = new ConcurrentSkipListMap<String, Path>();
		bb1SubMap.put("A", new Path("A", 3.8));
		bb1SubMap.put("B", new Path("B", 0.0));
		bb1SubMap.put("C", new Path("A", 5.0));
		bb1SubMap.put("D", new Path("E", 4.3));
		bb1SubMap.put("E", new Path("E", 1.1));
		bb1SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> bd1SubMap = new ConcurrentSkipListMap<String, Path>();
		bd1SubMap.put("B", new Path("B", 5.1));
		bd1SubMap.put("C", new Path("C", 4.2));
		bd1SubMap.put("D", new Path("D", 0.0));
		bd1SubMap.put("E", new Path("E", 3.2));
		ConcurrentNavigableMap<String, Path> be1SubMap = new ConcurrentSkipListMap<String, Path>();
		be1SubMap.put("B", new Path("B", 1.1));
		be1SubMap.put("D", new Path("D", 3.2));
		be1SubMap.put("E", new Path("E", 0.0));
		be1SubMap.put("F", new Path("F", 4.4));
		b1Map.put("A", ba1SubMap);
		b1Map.put("B", bb1SubMap);
		b1Map.put("D", bd1SubMap);
		b1Map.put("E", be1SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ca1SubMap = new ConcurrentSkipListMap<String, Path>();
		ca1SubMap.put("A", new Path("A", 0.0));
		ca1SubMap.put("B", new Path("B", 3.8));
		ca1SubMap.put("C", new Path("C", 1.2));
		ConcurrentNavigableMap<String, Path> cc1SubMap = new ConcurrentSkipListMap<String, Path>();
		cc1SubMap.put("A", new Path("A", 1.2));
		cc1SubMap.put("B", new Path("A", 5.0));
		cc1SubMap.put("C", new Path("C", 0.0));
		cc1SubMap.put("D", new Path("D", 4.2));
		cc1SubMap.put("E", new Path("D", 7.4));
		ConcurrentNavigableMap<String, Path> cd1SubMap = new ConcurrentSkipListMap<String, Path>();
		cd1SubMap.put("B", new Path("B", 5.1));
		cd1SubMap.put("C", new Path("C", 4.2));
		cd1SubMap.put("D", new Path("D", 0.0));
		cd1SubMap.put("E", new Path("E", 3.2));
		c1Map.put("A", ca1SubMap);
		c1Map.put("C", cc1SubMap);
		c1Map.put("D", cd1SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> db1SubMap = new ConcurrentSkipListMap<String, Path>();
		db1SubMap.put("A", new Path("A", 3.8));
		db1SubMap.put("B", new Path("B", 0.0));
		db1SubMap.put("D", new Path("D", 5.1));
		db1SubMap.put("E", new Path("E", 1.1));
		ConcurrentNavigableMap<String, Path> dc1SubMap = new ConcurrentSkipListMap<String, Path>();
		dc1SubMap.put("A", new Path("A", 1.2));
		dc1SubMap.put("C", new Path("C", 0.0));
		dc1SubMap.put("D", new Path("D", 4.2));
		ConcurrentNavigableMap<String, Path> dd1SubMap = new ConcurrentSkipListMap<String, Path>();
		dd1SubMap.put("A", new Path("C", 5.4));
		dd1SubMap.put("B", new Path("E", 4.3));
		dd1SubMap.put("C", new Path("C", 4.2));
		dd1SubMap.put("D", new Path("D", 0.0));
		dd1SubMap.put("E", new Path("E", 3.2));
		dd1SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> de1SubMap = new ConcurrentSkipListMap<String, Path>();
		de1SubMap.put("B", new Path("B", 1.1));
		de1SubMap.put("D", new Path("D", 3.2));
		de1SubMap.put("E", new Path("E", 0.0));
		de1SubMap.put("F", new Path("F", 4.4));
		d1Map.put("B", db1SubMap);
		d1Map.put("C", dc1SubMap);
		d1Map.put("D", dd1SubMap);
		d1Map.put("E", de1SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> eb1SubMap = new ConcurrentSkipListMap<String, Path>();
		eb1SubMap.put("A", new Path("A", 3.8));
		eb1SubMap.put("B", new Path("B", 0.0));
		eb1SubMap.put("D", new Path("D", 5.1));
		eb1SubMap.put("E", new Path("E", 1.1));
		ConcurrentNavigableMap<String, Path> ed1SubMap = new ConcurrentSkipListMap<String, Path>();
		ed1SubMap.put("B", new Path("B", 5.1));
		ed1SubMap.put("C", new Path("C", 4.2));
		ed1SubMap.put("D", new Path("D", 0.0));
		ed1SubMap.put("E", new Path("E", 3.2));
		ConcurrentNavigableMap<String, Path> ee1SubMap = new ConcurrentSkipListMap<String, Path>();
		ee1SubMap.put("A", new Path("B", 4.9));
		ee1SubMap.put("B", new Path("B", 1.1));
		ee1SubMap.put("C", new Path("D", 7.4));
		ee1SubMap.put("D", new Path("D", 3.2));
		ee1SubMap.put("E", new Path("E", 0.0));
		ee1SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ef1SubMap = new ConcurrentSkipListMap<String, Path>();
		ef1SubMap.put("E", new Path("E", 4.4));
		ef1SubMap.put("F", new Path("F", 0.0));
		e1Map.put("B", eb1SubMap);
		e1Map.put("D", ed1SubMap);
		e1Map.put("E", ee1SubMap);
		e1Map.put("F", ef1SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> f1Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> fe1SubMap = new ConcurrentSkipListMap<String, Path>();
		fe1SubMap.put("B", new Path("B", 1.1));
		fe1SubMap.put("D", new Path("D", 3.2));
		fe1SubMap.put("E", new Path("E", 0.0));
		fe1SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ff1SubMap = new ConcurrentSkipListMap<String, Path>();
		ff1SubMap.put("B", new Path("E", 5.5));
		ff1SubMap.put("D", new Path("E", 7.6));
		ff1SubMap.put("E", new Path("E", 4.4));
		ff1SubMap.put("F", new Path("F", 0.0));
		f1Map.put("E", fe1SubMap);
		f1Map.put("F", ff1SubMap);
		//Add to run's map
		step1Map.put("A", a1Map);
		step1Map.put("B", b1Map);
		step1Map.put("C", c1Map);
		step1Map.put("D", d1Map);
		step1Map.put("E", e1Map);
		step1Map.put("F", f1Map);
		////////////
		// Step 2 //
		////////////
		step2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node A
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> a2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> aa2SubMap = new ConcurrentSkipListMap<String, Path>();
		aa2SubMap.put("A", new Path("A", 0.0));
		aa2SubMap.put("B", new Path("B", 3.8));
		aa2SubMap.put("C", new Path("C", 1.2));
		aa2SubMap.put("D", new Path("C", 5.4));
		aa2SubMap.put("E", new Path("B", 4.9));
		aa2SubMap.put("F", new Path("B", 9.3));
		ConcurrentNavigableMap<String, Path> ab2SubMap = new ConcurrentSkipListMap<String, Path>();
		ab2SubMap.put("A", new Path("A", 3.8));
		ab2SubMap.put("B", new Path("B", 0.0));
		ab2SubMap.put("C", new Path("A", 5.0));
		ab2SubMap.put("D", new Path("E", 4.3));
		ab2SubMap.put("E", new Path("E", 1.1));
		ab2SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> ac2SubMap = new ConcurrentSkipListMap<String, Path>();
		ac2SubMap.put("A", new Path("A", 1.2));
		ac2SubMap.put("B", new Path("A", 5.0));
		ac2SubMap.put("C", new Path("C", 0.0));
		ac2SubMap.put("D", new Path("D", 4.2));
		ac2SubMap.put("E", new Path("D", 7.4));
		a2Map.put("A", aa2SubMap);
		a2Map.put("B", ab2SubMap);
		a2Map.put("C", ac2SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ba2SubMap = new ConcurrentSkipListMap<String, Path>();
		ba2SubMap.put("A", new Path("A", 0.0));
		ba2SubMap.put("B", new Path("B", 3.8));
		ba2SubMap.put("C", new Path("C", 1.2));
		ba2SubMap.put("D", new Path("C", 5.4));
		ba2SubMap.put("E", new Path("B", 4.9));
		ConcurrentNavigableMap<String, Path> bb2SubMap = new ConcurrentSkipListMap<String, Path>();
		bb2SubMap.put("A", new Path("A", 3.8));
		bb2SubMap.put("B", new Path("B", 0.0));
		bb2SubMap.put("C", new Path("A", 5.0));
		bb2SubMap.put("D", new Path("E", 4.3));
		bb2SubMap.put("E", new Path("E", 1.1));
		bb2SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> bd2SubMap = new ConcurrentSkipListMap<String, Path>();
		bd2SubMap.put("A", new Path("C", 5.4));
		bd2SubMap.put("B", new Path("E", 4.3));
		bd2SubMap.put("C", new Path("C", 4.2));
		bd2SubMap.put("D", new Path("D", 0.0));
		bd2SubMap.put("E", new Path("E", 3.2));
		bd2SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> be2SubMap = new ConcurrentSkipListMap<String, Path>();
		be2SubMap.put("A", new Path("B", 4.9));
		be2SubMap.put("B", new Path("B", 1.1));
		be2SubMap.put("C", new Path("D", 7.4));
		be2SubMap.put("D", new Path("D", 3.2));
		be2SubMap.put("E", new Path("E", 0.0));
		be2SubMap.put("F", new Path("F", 4.4));
		b2Map.put("A", ba2SubMap);
		b2Map.put("B", bb2SubMap);
		b2Map.put("D", bd2SubMap);
		b2Map.put("E", be2SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ca2SubMap = new ConcurrentSkipListMap<String, Path>();
		ca2SubMap.put("A", new Path("A", 0.0));
		ca2SubMap.put("B", new Path("B", 3.8));
		ca2SubMap.put("C", new Path("C", 1.2));
		ca2SubMap.put("D", new Path("C", 5.4));
		ca2SubMap.put("E", new Path("B", 4.9));
		ConcurrentNavigableMap<String, Path> cc2SubMap = new ConcurrentSkipListMap<String, Path>();
		cc2SubMap.put("A", new Path("A", 1.2));
		cc2SubMap.put("B", new Path("A", 5.0));
		cc2SubMap.put("C", new Path("C", 0.0));
		cc2SubMap.put("D", new Path("D", 4.2));
		cc2SubMap.put("E", new Path("A", 6.1));
		cc2SubMap.put("F", new Path("D", 11.8));
		ConcurrentNavigableMap<String, Path> cd2SubMap = new ConcurrentSkipListMap<String, Path>();
		cd2SubMap.put("A", new Path("C", 5.4));
		cd2SubMap.put("B", new Path("E", 4.3));
		cd2SubMap.put("C", new Path("C", 4.2));
		cd2SubMap.put("D", new Path("D", 0.0));
		cd2SubMap.put("E", new Path("E", 3.2));
		cd2SubMap.put("F", new Path("E", 7.6));
		c2Map.put("A", ca2SubMap);
		c2Map.put("C", cc2SubMap);
		c2Map.put("D", cd2SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> db2SubMap = new ConcurrentSkipListMap<String, Path>();
		db2SubMap.put("A", new Path("A", 3.8));
		db2SubMap.put("B", new Path("B", 0.0));
		db2SubMap.put("C", new Path("A", 5.0));
		db2SubMap.put("D", new Path("E", 4.3));
		db2SubMap.put("E", new Path("E", 1.1));
		db2SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> dc2SubMap = new ConcurrentSkipListMap<String, Path>();
		dc2SubMap.put("A", new Path("A", 1.2));
		dc2SubMap.put("B", new Path("A", 5.0));
		dc2SubMap.put("C", new Path("C", 0.0));
		dc2SubMap.put("D", new Path("D", 4.2));
		dc2SubMap.put("E", new Path("D", 7.4));
		ConcurrentNavigableMap<String, Path> dd2SubMap = new ConcurrentSkipListMap<String, Path>();
		dd2SubMap.put("A", new Path("C", 5.4));
		dd2SubMap.put("B", new Path("E", 4.3));
		dd2SubMap.put("C", new Path("C", 4.2));
		dd2SubMap.put("D", new Path("D", 0.0));
		dd2SubMap.put("E", new Path("E", 3.2));
		dd2SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> de2SubMap = new ConcurrentSkipListMap<String, Path>();
		de2SubMap.put("A", new Path("B", 4.9));
		de2SubMap.put("B", new Path("B", 1.1));
		de2SubMap.put("C", new Path("D", 7.4));
		de2SubMap.put("D", new Path("D", 3.2));
		de2SubMap.put("E", new Path("E", 0.0));
		de2SubMap.put("F", new Path("F", 4.4));
		d2Map.put("B", db2SubMap);
		d2Map.put("C", dc2SubMap);
		d2Map.put("D", dd2SubMap);
		d2Map.put("E", de2SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> eb2SubMap = new ConcurrentSkipListMap<String, Path>();
		eb2SubMap.put("A", new Path("A", 3.8));
		eb2SubMap.put("B", new Path("B", 0.0));
		eb2SubMap.put("C", new Path("A", 5.0));
		eb2SubMap.put("D", new Path("E", 4.3));
		eb2SubMap.put("E", new Path("E", 1.1));
		eb2SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> ed2SubMap = new ConcurrentSkipListMap<String, Path>();
		ed2SubMap.put("A", new Path("C", 5.4));
		ed2SubMap.put("B", new Path("E", 4.3));
		ed2SubMap.put("C", new Path("C", 4.2));
		ed2SubMap.put("D", new Path("D", 0.0));
		ed2SubMap.put("E", new Path("E", 3.2));
		ed2SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> ee2SubMap = new ConcurrentSkipListMap<String, Path>();
		ee2SubMap.put("A", new Path("B", 4.9));
		ee2SubMap.put("B", new Path("B", 1.1));
		ee2SubMap.put("C", new Path("B", 6.1));
		ee2SubMap.put("D", new Path("D", 3.2));
		ee2SubMap.put("E", new Path("E", 0.0));
		ee2SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ef2SubMap = new ConcurrentSkipListMap<String, Path>();
		ef2SubMap.put("B", new Path("E", 5.5));
		ef2SubMap.put("D", new Path("E", 7.6));
		ef2SubMap.put("E", new Path("E", 4.4));
		ef2SubMap.put("F", new Path("F", 0.0));
		e2Map.put("B", eb2SubMap);
		e2Map.put("D", ed2SubMap);
		e2Map.put("E", ee2SubMap);
		e2Map.put("F", ef2SubMap);
		//Node F
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> f2Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> fe2SubMap = new ConcurrentSkipListMap<String, Path>();
		fe2SubMap.put("A", new Path("B", 4.9));
		fe2SubMap.put("B", new Path("B", 1.1));
		fe2SubMap.put("C", new Path("D", 7.4));
		fe2SubMap.put("D", new Path("D", 3.2));
		fe2SubMap.put("E", new Path("E", 0.0));
		fe2SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ff2SubMap = new ConcurrentSkipListMap<String, Path>();
		ff2SubMap.put("A", new Path("E", 9.3));
		ff2SubMap.put("B", new Path("E", 5.5));
		ff2SubMap.put("C", new Path("E", 11.8));
		ff2SubMap.put("D", new Path("E", 7.6));
		ff2SubMap.put("E", new Path("E", 4.4));
		ff2SubMap.put("F", new Path("F", 0.0));
		f2Map.put("E", fe2SubMap);
		f2Map.put("F", ff2SubMap);
		//Add to run's map
		step2Map.put("A", a2Map);
		step2Map.put("B", b2Map);
		step2Map.put("C", c2Map);
		step2Map.put("D", d2Map);
		step2Map.put("E", e2Map);
		step2Map.put("F", f2Map);
		////////////
		// Step 3 //
		////////////
		step3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node A
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> a3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> aa3SubMap = new ConcurrentSkipListMap<String, Path>();
		aa3SubMap.put("A", new Path("A", 0.0));
		aa3SubMap.put("B", new Path("B", 3.8));
		aa3SubMap.put("C", new Path("C", 1.2));
		aa3SubMap.put("D", new Path("C", 5.4));
		aa3SubMap.put("E", new Path("B", 4.9));
		aa3SubMap.put("F", new Path("B", 9.3));
		ConcurrentNavigableMap<String, Path> ab3SubMap = new ConcurrentSkipListMap<String, Path>();
		ab3SubMap.put("A", new Path("A", 3.8));
		ab3SubMap.put("B", new Path("B", 0.0));
		ab3SubMap.put("C", new Path("A", 5.0));
		ab3SubMap.put("D", new Path("E", 4.3));
		ab3SubMap.put("E", new Path("E", 1.1));
		ab3SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> ac3SubMap = new ConcurrentSkipListMap<String, Path>();
		ac3SubMap.put("A", new Path("A", 1.2));
		ac3SubMap.put("B", new Path("A", 5.0));
		ac3SubMap.put("C", new Path("C", 0.0));
		ac3SubMap.put("D", new Path("D", 4.2));
		ac3SubMap.put("E", new Path("A", 6.1));
		ac3SubMap.put("F", new Path("D", 11.8));
		a3Map.put("A", aa3SubMap);
		a3Map.put("B", ab3SubMap);
		a3Map.put("C", ac3SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ba3SubMap = new ConcurrentSkipListMap<String, Path>();
		ba3SubMap.put("A", new Path("A", 0.0));
		ba3SubMap.put("B", new Path("B", 3.8));
		ba3SubMap.put("C", new Path("C", 1.2));
		ba3SubMap.put("D", new Path("C", 5.4));
		ba3SubMap.put("E", new Path("B", 4.9));
		ba3SubMap.put("F", new Path("B", 9.3));
		ConcurrentNavigableMap<String, Path> bb3SubMap = new ConcurrentSkipListMap<String, Path>();
		bb3SubMap.put("A", new Path("A", 3.8));
		bb3SubMap.put("B", new Path("B", 0.0));
		bb3SubMap.put("C", new Path("A", 5.0));
		bb3SubMap.put("D", new Path("E", 4.3));
		bb3SubMap.put("E", new Path("E", 1.1));
		bb3SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> bd3SubMap = new ConcurrentSkipListMap<String, Path>();
		bd3SubMap.put("A", new Path("C", 5.4));
		bd3SubMap.put("B", new Path("E", 4.3));
		bd3SubMap.put("C", new Path("C", 4.2));
		bd3SubMap.put("D", new Path("D", 0.0));
		bd3SubMap.put("E", new Path("E", 3.2));
		bd3SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> be3SubMap = new ConcurrentSkipListMap<String, Path>();
		be3SubMap.put("A", new Path("B", 4.9));
		be3SubMap.put("B", new Path("B", 1.1));
		be3SubMap.put("C", new Path("B", 6.1));
		be3SubMap.put("D", new Path("D", 3.2));
		be3SubMap.put("E", new Path("E", 0.0));
		be3SubMap.put("F", new Path("F", 4.4));
		b3Map.put("A", ba3SubMap);
		b3Map.put("B", bb3SubMap);
		b3Map.put("D", bd3SubMap);
		b3Map.put("E", be3SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ca3SubMap = new ConcurrentSkipListMap<String, Path>();
		ca3SubMap.put("A", new Path("A", 0.0));
		ca3SubMap.put("B", new Path("B", 3.8));
		ca3SubMap.put("C", new Path("C", 1.2));
		ca3SubMap.put("D", new Path("C", 5.4));
		ca3SubMap.put("E", new Path("B", 4.9));
		ca3SubMap.put("F", new Path("B", 9.3));
		ConcurrentNavigableMap<String, Path> cc3SubMap = new ConcurrentSkipListMap<String, Path>();
		cc3SubMap.put("A", new Path("A", 1.2));
		cc3SubMap.put("B", new Path("A", 5.0));
		cc3SubMap.put("C", new Path("C", 0.0));
		cc3SubMap.put("D", new Path("D", 4.2));
		cc3SubMap.put("E", new Path("A", 6.1));
		cc3SubMap.put("F", new Path("A", 10.5));
		ConcurrentNavigableMap<String, Path> cd3SubMap = new ConcurrentSkipListMap<String, Path>();
		cd3SubMap.put("A", new Path("C", 5.4));
		cd3SubMap.put("B", new Path("E", 4.3));
		cd3SubMap.put("C", new Path("C", 4.2));
		cd3SubMap.put("D", new Path("D", 0.0));
		cd3SubMap.put("E", new Path("E", 3.2));
		cd3SubMap.put("F", new Path("E", 7.6));
		c3Map.put("A", ca3SubMap);
		c3Map.put("C", cc3SubMap);
		c3Map.put("D", cd3SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> db3SubMap = new ConcurrentSkipListMap<String, Path>();
		db3SubMap.put("A", new Path("A", 3.8));
		db3SubMap.put("B", new Path("B", 0.0));
		db3SubMap.put("C", new Path("A", 5.0));
		db3SubMap.put("D", new Path("E", 4.3));
		db3SubMap.put("E", new Path("E", 1.1));
		db3SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> dc3SubMap = new ConcurrentSkipListMap<String, Path>();
		dc3SubMap.put("A", new Path("A", 1.2));
		dc3SubMap.put("B", new Path("A", 5.0));
		dc3SubMap.put("C", new Path("C", 0.0));
		dc3SubMap.put("D", new Path("D", 4.2));
		dc3SubMap.put("E", new Path("A", 6.1));
		dc3SubMap.put("F", new Path("D", 11.8));
		ConcurrentNavigableMap<String, Path> dd3SubMap = new ConcurrentSkipListMap<String, Path>();
		dd3SubMap.put("A", new Path("C", 5.4));
		dd3SubMap.put("B", new Path("E", 4.3));
		dd3SubMap.put("C", new Path("C", 4.2));
		dd3SubMap.put("D", new Path("D", 0.0));
		dd3SubMap.put("E", new Path("E", 3.2));
		dd3SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> de3SubMap = new ConcurrentSkipListMap<String, Path>();
		de3SubMap.put("A", new Path("B", 4.9));
		de3SubMap.put("B", new Path("B", 1.1));
		de3SubMap.put("C", new Path("B", 6.1));
		de3SubMap.put("D", new Path("D", 3.2));
		de3SubMap.put("E", new Path("E", 0.0));
		de3SubMap.put("F", new Path("F", 4.4));
		d3Map.put("B", db3SubMap);
		d3Map.put("C", dc3SubMap);
		d3Map.put("D", dd3SubMap);
		d3Map.put("E", de3SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> eb3SubMap = new ConcurrentSkipListMap<String, Path>();
		eb3SubMap.put("A", new Path("A", 3.8));
		eb3SubMap.put("B", new Path("B", 0.0));
		eb3SubMap.put("C", new Path("A", 5.0));
		eb3SubMap.put("D", new Path("E", 4.3));
		eb3SubMap.put("E", new Path("E", 1.1));
		eb3SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> ed3SubMap = new ConcurrentSkipListMap<String, Path>();
		ed3SubMap.put("A", new Path("C", 5.4));
		ed3SubMap.put("B", new Path("E", 4.3));
		ed3SubMap.put("C", new Path("C", 4.2));
		ed3SubMap.put("D", new Path("D", 0.0));
		ed3SubMap.put("E", new Path("E", 3.2));
		ed3SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> ee3SubMap = new ConcurrentSkipListMap<String, Path>();
		ee3SubMap.put("A", new Path("B", 4.9));
		ee3SubMap.put("B", new Path("B", 1.1));
		ee3SubMap.put("C", new Path("B", 6.1));
		ee3SubMap.put("D", new Path("D", 3.2));
		ee3SubMap.put("E", new Path("E", 0.0));
		ee3SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ef3SubMap = new ConcurrentSkipListMap<String, Path>();
		ef3SubMap.put("A", new Path("E", 9.3));
		ef3SubMap.put("B", new Path("E", 5.5));
		ef3SubMap.put("C", new Path("E", 11.8));
		ef3SubMap.put("D", new Path("E", 7.6));
		ef3SubMap.put("E", new Path("E", 4.4));
		ef3SubMap.put("F", new Path("F", 0.0));
		e3Map.put("B", eb3SubMap);
		e3Map.put("D", ed3SubMap);
		e3Map.put("E", ee3SubMap);
		e3Map.put("F", ef3SubMap);
		//Node F
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> f3Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> fe3SubMap = new ConcurrentSkipListMap<String, Path>();
		fe3SubMap.put("A", new Path("B", 4.9));
		fe3SubMap.put("B", new Path("B", 1.1));
		fe3SubMap.put("C", new Path("B", 6.1));
		fe3SubMap.put("D", new Path("D", 3.2));
		fe3SubMap.put("E", new Path("E", 0.0));
		fe3SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ff3SubMap = new ConcurrentSkipListMap<String, Path>();
		ff3SubMap.put("A", new Path("E", 9.3));
		ff3SubMap.put("B", new Path("E", 5.5));
		ff3SubMap.put("C", new Path("E", 10.5));
		ff3SubMap.put("D", new Path("E", 7.6));
		ff3SubMap.put("E", new Path("E", 4.4));
		ff3SubMap.put("F", new Path("F", 0.0));
		f3Map.put("E", fe3SubMap);
		f3Map.put("F", ff3SubMap);
		//Add to run's map
		step3Map.put("A", a3Map);
		step3Map.put("B", b3Map);
		step3Map.put("C", c3Map);
		step3Map.put("D", d3Map);
		step3Map.put("E", e3Map);
		step3Map.put("F", f3Map);
		////////////
		// Step 4 //
		////////////
		step4Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>>>();
		//Node A
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> a4Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> aa4SubMap = new ConcurrentSkipListMap<String, Path>();
		aa4SubMap.put("A", new Path("A", 0.0));
		aa4SubMap.put("B", new Path("B", 3.8));
		aa4SubMap.put("C", new Path("C", 1.2));
		aa4SubMap.put("D", new Path("C", 5.4));
		aa4SubMap.put("E", new Path("B", 4.9));
		aa4SubMap.put("F", new Path("B", 9.3));
		ConcurrentNavigableMap<String, Path> ab4SubMap = new ConcurrentSkipListMap<String, Path>();
		ab4SubMap.put("A", new Path("A", 3.8));
		ab4SubMap.put("B", new Path("B", 0.0));
		ab4SubMap.put("C", new Path("A", 5.0));
		ab4SubMap.put("D", new Path("E", 4.3));
		ab4SubMap.put("E", new Path("E", 1.1));
		ab4SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> ac4SubMap = new ConcurrentSkipListMap<String, Path>();
		ac4SubMap.put("A", new Path("A", 1.2));
		ac4SubMap.put("B", new Path("A", 5.0));
		ac4SubMap.put("C", new Path("C", 0.0));
		ac4SubMap.put("D", new Path("D", 4.2));
		ac4SubMap.put("E", new Path("A", 6.1));
		ac4SubMap.put("F", new Path("A", 10.5));
		a4Map.put("A", aa4SubMap);
		a4Map.put("B", ab4SubMap);
		a4Map.put("C", ac4SubMap);
		//Node B
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> b4Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ba4SubMap = new ConcurrentSkipListMap<String, Path>();
		ba4SubMap.put("A", new Path("A", 0.0));
		ba4SubMap.put("B", new Path("B", 3.8));
		ba4SubMap.put("C", new Path("C", 1.2));
		ba4SubMap.put("D", new Path("C", 5.4));
		ba4SubMap.put("E", new Path("B", 4.9));
		ba4SubMap.put("F", new Path("B", 9.3));
		ConcurrentNavigableMap<String, Path> bb4SubMap = new ConcurrentSkipListMap<String, Path>();
		bb4SubMap.put("A", new Path("A", 3.8));
		bb4SubMap.put("B", new Path("B", 0.0));
		bb4SubMap.put("C", new Path("A", 5.0));
		bb4SubMap.put("D", new Path("E", 4.3));
		bb4SubMap.put("E", new Path("E", 1.1));
		bb4SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> bd4SubMap = new ConcurrentSkipListMap<String, Path>();
		bd4SubMap.put("A", new Path("C", 5.4));
		bd4SubMap.put("B", new Path("E", 4.3));
		bd4SubMap.put("C", new Path("C", 4.2));
		bd4SubMap.put("D", new Path("D", 0.0));
		bd4SubMap.put("E", new Path("E", 3.2));
		bd4SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> be4SubMap = new ConcurrentSkipListMap<String, Path>();
		be4SubMap.put("A", new Path("B", 4.9));
		be4SubMap.put("B", new Path("B", 1.1));
		be4SubMap.put("C", new Path("B", 6.1));
		be4SubMap.put("D", new Path("D", 3.2));
		be4SubMap.put("E", new Path("E", 0.0));
		be4SubMap.put("F", new Path("F", 4.4));
		b4Map.put("A", ba4SubMap);
		b4Map.put("B", bb4SubMap);
		b4Map.put("D", bd4SubMap);
		b4Map.put("E", be4SubMap);
		//Node C
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> c4Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> ca4SubMap = new ConcurrentSkipListMap<String, Path>();
		ca4SubMap.put("A", new Path("A", 0.0));
		ca4SubMap.put("B", new Path("B", 3.8));
		ca4SubMap.put("C", new Path("C", 1.2));
		ca4SubMap.put("D", new Path("C", 5.4));
		ca4SubMap.put("E", new Path("B", 4.9));
		ca4SubMap.put("F", new Path("B", 9.3));
		ConcurrentNavigableMap<String, Path> cc4SubMap = new ConcurrentSkipListMap<String, Path>();
		cc4SubMap.put("A", new Path("A", 1.2));
		cc4SubMap.put("B", new Path("A", 5.0));
		cc4SubMap.put("C", new Path("C", 0.0));
		cc4SubMap.put("D", new Path("D", 4.2));
		cc4SubMap.put("E", new Path("A", 6.1));
		cc4SubMap.put("F", new Path("A", 10.5));
		ConcurrentNavigableMap<String, Path> cd4SubMap = new ConcurrentSkipListMap<String, Path>();
		cd4SubMap.put("A", new Path("C", 5.4));
		cd4SubMap.put("B", new Path("E", 4.3));
		cd4SubMap.put("C", new Path("C", 4.2));
		cd4SubMap.put("D", new Path("D", 0.0));
		cd4SubMap.put("E", new Path("E", 3.2));
		cd4SubMap.put("F", new Path("E", 7.6));
		c4Map.put("A", ca4SubMap);
		c4Map.put("C", cc4SubMap);
		c4Map.put("D", cd4SubMap);
		//Node D
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> d4Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> db4SubMap = new ConcurrentSkipListMap<String, Path>();
		db4SubMap.put("A", new Path("A", 3.8));
		db4SubMap.put("B", new Path("B", 0.0));
		db4SubMap.put("C", new Path("A", 5.0));
		db4SubMap.put("D", new Path("E", 4.3));
		db4SubMap.put("E", new Path("E", 1.1));
		db4SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> dc4SubMap = new ConcurrentSkipListMap<String, Path>();
		dc4SubMap.put("A", new Path("A", 1.2));
		dc4SubMap.put("B", new Path("A", 5.0));
		dc4SubMap.put("C", new Path("C", 0.0));
		dc4SubMap.put("D", new Path("D", 4.2));
		dc4SubMap.put("E", new Path("A", 6.1));
		dc4SubMap.put("F", new Path("A", 10.5));
		ConcurrentNavigableMap<String, Path> dd4SubMap = new ConcurrentSkipListMap<String, Path>();
		dd4SubMap.put("A", new Path("C", 5.4));
		dd4SubMap.put("B", new Path("E", 4.3));
		dd4SubMap.put("C", new Path("C", 4.2));
		dd4SubMap.put("D", new Path("D", 0.0));
		dd4SubMap.put("E", new Path("E", 3.2));
		dd4SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> de4SubMap = new ConcurrentSkipListMap<String, Path>();
		de4SubMap.put("A", new Path("B", 4.9));
		de4SubMap.put("B", new Path("B", 1.1));
		de4SubMap.put("C", new Path("B", 6.1));
		de4SubMap.put("D", new Path("D", 3.2));
		de4SubMap.put("E", new Path("E", 0.0));
		de4SubMap.put("F", new Path("F", 4.4));
		d4Map.put("B", db4SubMap);
		d4Map.put("C", dc4SubMap);
		d4Map.put("D", dd4SubMap);
		d4Map.put("E", de4SubMap);
		//Node E
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> e4Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> eb4SubMap = new ConcurrentSkipListMap<String, Path>();
		eb4SubMap.put("A", new Path("A", 3.8));
		eb4SubMap.put("B", new Path("B", 0.0));
		eb4SubMap.put("C", new Path("A", 5.0));
		eb4SubMap.put("D", new Path("E", 4.3));
		eb4SubMap.put("E", new Path("E", 1.1));
		eb4SubMap.put("F", new Path("E", 5.5));
		ConcurrentNavigableMap<String, Path> ed4SubMap = new ConcurrentSkipListMap<String, Path>();
		ed4SubMap.put("A", new Path("C", 5.4));
		ed4SubMap.put("B", new Path("E", 4.3));
		ed4SubMap.put("C", new Path("C", 4.2));
		ed4SubMap.put("D", new Path("D", 0.0));
		ed4SubMap.put("E", new Path("E", 3.2));
		ed4SubMap.put("F", new Path("E", 7.6));
		ConcurrentNavigableMap<String, Path> ee4SubMap = new ConcurrentSkipListMap<String, Path>();
		ee4SubMap.put("A", new Path("B", 4.9));
		ee4SubMap.put("B", new Path("B", 1.1));
		ee4SubMap.put("C", new Path("B", 6.1));
		ee4SubMap.put("D", new Path("D", 3.2));
		ee4SubMap.put("E", new Path("E", 0.0));
		ee4SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ef4SubMap = new ConcurrentSkipListMap<String, Path>();
		ef4SubMap.put("A", new Path("E", 9.3));
		ef4SubMap.put("B", new Path("E", 5.5));
		ef4SubMap.put("C", new Path("E", 10.5));
		ef4SubMap.put("D", new Path("E", 7.6));
		ef4SubMap.put("E", new Path("E", 4.4));
		ef4SubMap.put("F", new Path("F", 0.0));
		e4Map.put("B", eb4SubMap);
		e4Map.put("D", ed4SubMap);
		e4Map.put("E", ee4SubMap);
		e4Map.put("F", ef4SubMap);
		//Node F
		ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> f4Map = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		ConcurrentNavigableMap<String, Path> fe4SubMap = new ConcurrentSkipListMap<String, Path>();
		fe4SubMap.put("A", new Path("B", 4.9));
		fe4SubMap.put("B", new Path("B", 1.1));
		fe4SubMap.put("C", new Path("B", 6.1));
		fe4SubMap.put("D", new Path("D", 3.2));
		fe4SubMap.put("E", new Path("E", 0.0));
		fe4SubMap.put("F", new Path("F", 4.4));
		ConcurrentNavigableMap<String, Path> ff4SubMap = new ConcurrentSkipListMap<String, Path>();
		ff4SubMap.put("A", new Path("E", 9.3));
		ff4SubMap.put("B", new Path("E", 5.5));
		ff4SubMap.put("C", new Path("E", 10.5));
		ff4SubMap.put("D", new Path("E", 7.6));
		ff4SubMap.put("E", new Path("E", 4.4));
		ff4SubMap.put("F", new Path("F", 0.0));
		f4Map.put("E", fe4SubMap);
		f4Map.put("F", ff4SubMap);
		//Add to run's map
		step4Map.put("A", a4Map);
		step4Map.put("B", b4Map);
		step4Map.put("C", c4Map);
		step4Map.put("D", d4Map);
		step4Map.put("E", e4Map);
		step4Map.put("F", f4Map);
	}
}
