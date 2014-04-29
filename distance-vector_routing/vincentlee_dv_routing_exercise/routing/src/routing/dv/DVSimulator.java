package routing.dv;

/**
 * Distance Vector Simulator
 * 
 * @author Vincent Lee
 * @since April 1, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class DVSimulator {
	public static boolean DEBUG = false;
	//Stores runnable concurrent Node objects
	public static ConcurrentNavigableMap<String, Node> nodeMap = new ConcurrentSkipListMap<String, Node>();
	
	public static void main(String[] args) {
		//number of arguments
		if (args.length != 1 && !args[1].equals("--debug")) {
			System.out.println("error: Invalid number of arguments"); return;
		}
		
		//Parse topology file, and open stream
		RouteInputStream routeStream = new RouteInputStream();
		if (!routeStream.openFile(args[0])) {
			System.out.println("error: invalid file"); return;
		}
		
		//Initialize Distance-Vector (DV) Algorithm
		DistanceVector distanceVector = new DistanceVector(routeStream);
		distanceVector.initialize();
		
		//Stores node-to-neighbor object relationships
		ConcurrentNavigableMap<String, List<Node>> neighborMap = new ConcurrentSkipListMap<String, List<Node>>();
		
		//Initialize neighbors & Create runnable Node objeects
		Iterator<String> iterator = distanceVector.getTopologyMap().keySet().iterator();
		while(iterator.hasNext()) {
			//Add a neighbor to list, and create a new node and map it
	        String node = iterator.next(); //Get node element
			neighborMap.put(node,  new ArrayList<Node>());
			nodeMap.put(node, new Node(node, neighborMap.get(node), distanceVector.getTopologyMap().get(node)));
		}
		
		//Add neighbors to shared memory in new Node instances
		//(can't add neighbors before new Node() is called)
		iterator = neighborMap.keySet().iterator();
		while(iterator.hasNext()) {
			//Set up one(1) node's initial routes and neighbors
			
	        String node = iterator.next(); //Get node element
	        
	        //Get's Node's initial routes
	        Map<String, Path> initialRouteMap = distanceVector.getTopologyMap().get(node);
	        
	        //Add's neighbor node objects to node neighbor list
			Iterator<Map.Entry<String, Path>> entries = initialRouteMap.entrySet().iterator();
			while (entries.hasNext()) {
				//Adds one neighbor except itself to itself
				
				String key = entries.next().getKey(); //Get next entry's key only
		        
				//Ignore neighbor to itself
				if (node.equals(key)) continue;
				
				neighborMap.get(node).add(nodeMap.get(key));
			}
		}
		
		//Neighbors Map
		if (DEBUG) System.out.println("==Neighbor Map==" + "\n" + neighborMap.toString());
		
		//Step by Step Run
		if (args.length == 2) return;
		
		if (DEBUG) System.out.println("\n" + "==Message Rounds==");
		
		//Main Run
		int counter = 1;
		try {
			do {
				//One step of the DV algorithm
				
				//Set back to default
				Node.messages = 0;
				Node.updates = 0;
				
				//Create & Start threads
				Thread[] thread = new Thread[nodeMap.size()];
				
				Iterator<Map.Entry<String, Node>> entries = nodeMap.entrySet().iterator();
				for (int i = 0; i < thread.length; i++) {
					//Create and start one(1) thread of Node's
					thread[i] = new Thread(entries.next().getValue());
					Thread.sleep(5);
					thread[i].start();
				}
				
				//Join threads
				for (int i = 0; i < thread.length; i++) //Join one(1) thread
					thread[i].join();
				
				if (DEBUG) System.out.println("Round-" + counter + " Messages: " + Node.messages + " Updates: " + Node.updates);
				
				counter++;
			} while (Node.messages != 0);
		} catch (Exception e) {
			System.out.println("DV algorithm encountered error");
		}
		
		//Print out table with columns and rows
		if (!DEBUG) distanceVector.routingTablePrinter(nodeMap, nodeMap.keySet());
		
		//Print out routes
		if (DEBUG) System.out.println("\n" + "==Final Routing Maps==");
		iterator = nodeMap.keySet().iterator();
		while(iterator.hasNext()) {
			//Prints out one(1) node's routes
			String node = iterator.next();
			if (DEBUG) {
				//map.toString() Representation
				System.out.println(node);
				distanceVector.nestedMapPrinter(nodeMap.get(node).routeMap, nodeMap.keySet());
			} else {
				//Router-A
				//node A via A @cost 0.0
				//Representation
				System.out.println("Router-" + node);
				distanceVector.routerRoutesPrinter(nodeMap.get(node).routeMap.get(node), nodeMap.keySet());
				System.out.println();
			}
		}
	}
	
	/**
	 * Distance Vector one step at a time
	 */
	public static void stepByStepRun() {
		try {
			//Set back to default
			Node.messages = 0;
			Node.updates = 0;
			
			//Create & Start threads
			Thread[] thread = new Thread[nodeMap.size()];
			
			Iterator<Map.Entry<String, Node>> entries = nodeMap.entrySet().iterator();
			for (int i = 0; i < thread.length; i++) {
				//Create and start one(1) thread of Node's
				thread[i] = new Thread(entries.next().getValue());
				Thread.sleep(5);
				thread[i].start();
			}
			
			//Join threads
			for (int i = 0; i < thread.length; i++) //Join one(1) thread
				thread[i].join();
		} catch (Exception e) {
			System.out.println("DV algorithm encountered error");
		}
	}
}
