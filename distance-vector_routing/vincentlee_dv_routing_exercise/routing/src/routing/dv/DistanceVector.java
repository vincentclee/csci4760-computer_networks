package routing.dv;

/**
 * Distance Vector Initializer
 * 
 * @author Vincent Lee
 * @since April 1, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

import java.util.Iterator;
import java.util.NavigableSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class DistanceVector {
	private static final boolean DEBUG = false;
	protected static final double INFINITY = 99.9;
	protected static final double NODEtoNODE = 0.0;
	protected static final String INFINITY_SYMBOL = "\u221e";
	private RouteInputStream routeStream;
	private ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> topologyMap;
	
	/**
	 * Default Constructor sets up Distance Vector
	 * @param routeStream
	 */
	public DistanceVector(RouteInputStream routeStream) {
		this.routeStream = routeStream;
		this.topologyMap = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
	}
	
	/**
	 * Initialize the Distance Vector Bellman-Ford Algorithm
	 */
	public void initialize() {
		while (routeStream.next()) {
			//Load one(1) route from file into topologyMap
			
			//Get next route from stream
			Route route = routeStream.nextRoute();
			
			//Host-1 -> Host-2
			if (!topologyMap.containsKey(route.getHost1()))
				topologyMap.put(route.getHost1(), new ConcurrentSkipListMap<String, Path>());
			topologyMap.get(route.getHost1()).put(route.getHost2(), new Path(route.getHost2(), route.getDistance()));
			
			//Host-2 -> Host-1
			if (!topologyMap.containsKey(route.getHost2()))
				topologyMap.put(route.getHost2(), new ConcurrentSkipListMap<String, Path>());
			topologyMap.get(route.getHost2()).put(route.getHost1(), new Path(route.getHost1(), route.getDistance()));
			
			//Host-1 -> Host-1
			topologyMap.get(route.getHost1()).put(route.getHost1(), new Path(route.getHost1(), NODEtoNODE));
			//Host-2 -> Host-2
			topologyMap.get(route.getHost2()).put(route.getHost2(), new Path(route.getHost2(), NODEtoNODE));
		}
		
		//initial topology from file
		if (DEBUG) {System.out.println("==Initial Topology Map=="); nestedMapPrinter(topologyMap);}
	}
	
	/** @return the topologyMap */
	public ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> getTopologyMap() {return topologyMap;}
	
	/**
	 * Prints out a user friendly view of the network routes
	 * @param map
	 * @param navigableSet 
	 */
	public void routerRoutesPrinter(ConcurrentNavigableMap<String, Path> map, NavigableSet<String> nodeSet) {
		for (String node: nodeSet) {
			//Print out one(1) route "node [id] via [id] @cost [double]"
			
			//no route to node, prints out ? and INFINITY Symbol
			if (!map.containsKey(node)) System.out.println("node " + node + " via ? @cost " + INFINITY_SYMBOL);
			//route to node, prints out node id and cost
			else System.out.println("node " + node + " via " + map.get(node).getFirstHop() + " @cost " + map.get(node).getCost());
		}
	}
	
	/**
	 * Prints out a nested ConcurrentNavigableMap
	 * @param map
	 * @param nodeSet 
	 */
	public void nestedMapPrinter(ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> map) {
		//Iterator over first Map
		Iterator<ConcurrentNavigableMap.Entry<String, ConcurrentNavigableMap<String, Path>>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			//Prints out one(1) single line representation of a inner nested map
			ConcurrentNavigableMap.Entry<String, ConcurrentNavigableMap<String, Path>> entry = entries.next();
			System.out.println(entry.getKey() + "=" + entry.getValue());
		}
		System.out.println();
	}
	
	/**
	 * Prints out a nested ConcurrentNavigableMap with missing routes
	 * @param map
	 * @param nodeSet 
	 */
	public void nestedMapPrinter(ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> map, NavigableSet<String> nodeSet) {
		//Iterator over first Map
		Iterator<ConcurrentNavigableMap.Entry<String, ConcurrentNavigableMap<String, Path>>> entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			//Prints out one(1) line of the internal representation of a map
			ConcurrentNavigableMap.Entry<String, ConcurrentNavigableMap<String, Path>> entry = entries.next();
			ConcurrentNavigableMap<String, Path> innerEntry = entry.getValue();
			
			//Row Label
			System.out.print(entry.getKey() + "={");
			
			for (String node: nodeSet) {
				//Prints out one of a Node's routes
				System.out.print(node + "=");
				
				//no route to node, print infinity
				if (!innerEntry.containsKey(node)) System.out.print(new Path("?", INFINITY));
				//has route print out first hop and cost
				else System.out.print(innerEntry.get(node));
				
				//print out comma to seperate listings, except for last
				if (!node.equals(nodeSet.last())) System.out.print(", ");
			}
			System.out.println("}");
		}
		System.out.println();
	}
	
	/**
	 * Prints out a Routing Table with FROM and TO routes with only costs
	 * 
	 * F
	 * R
	 * O	     COST TO
	 * M |	A	B	C	D	E
	 * -------------------------------------------
	 * A |	0.0	1.2	5.0	∞	∞
	 * B |	1.2	0.0	3.8	∞	∞
	 * C |	5.0	3.8	0.0	∞	∞
	 * D |	∞	∞	∞	0.0	1.7
	 * E |	∞	∞	∞	1.7	0.0
	 * 
	 * @param nodeMap
	 * @param nodeSet
	 */
	public void routingTablePrinter(ConcurrentNavigableMap<String, Node> nodeMap, NavigableSet<String> nodeSet) {
		//Vertical "FROM" and Horizontal "COST TO"
		System.out.print("F\nR\nO\t     COST TO\nM |");
		
		
		for (String node: nodeSet) //Prints out one column header
			System.out.print("\t" + node);
		
		//Row Label initial spacing
		System.out.print("\n---");
		
		for (@SuppressWarnings("unused") String node: nodeSet) //Print out a single line spacing for a node
			System.out.print("--------");
		
		//Gogo next line
		System.out.println();
		
		for (String node: nodeSet) {
			//Print out one(1) row of table information
			
			//Node Row Label
			System.out.print(node + " |");
			
			for (String innerNode: nodeSet) {
				//Print out one cost associated with a route
				
				//Route without path, print out INFINITY
				if (!nodeMap.get(node).routeMap.get(node).containsKey(innerNode)) System.out.print("\t" + INFINITY_SYMBOL);
				//Route with path, print out double
				else System.out.print("\t" + nodeMap.get(node).routeMap.get(node).get(innerNode).getCost());
			}
			System.out.println();
		}
		System.out.println();
	}
}
