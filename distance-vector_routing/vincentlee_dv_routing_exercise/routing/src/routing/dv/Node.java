package routing.dv;

/**
 * Router Object which sends and receives route updates
 * 
 * @author Vincent Lee
 * @since April 1, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

import java.math.BigDecimal;
import java.util.List;
import java.util.Map.Entry;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Node implements Runnable {
	private static boolean DEBUG = false;
	public static int messages = 0; //ALL node's messages sent
	public static int updates = 0; //ALL node's updates preformed
	
	private String name;
	public ConcurrentNavigableMap<String, ConcurrentNavigableMap<String, Path>> routeMap;
	protected List<Node> neighbors;
	private boolean sendUpdate, newRoutes;
	
	/**
	 * Constructor sets up identity, neighbors, and initial routes
	 * @param name
	 * @param neighbors
	 * @param initialMap
	 */
	public Node(String name, List<Node> neighbors, ConcurrentNavigableMap<String, Path> initialMap) {
		this.name = name;
		this.neighbors = neighbors;
		this.sendUpdate = true;
		this.newRoutes = false;
		
		//Perform Semi-Deep Copy
		this.routeMap = new ConcurrentSkipListMap<String, ConcurrentNavigableMap<String, Path>>();
		this.routeMap.put(name, new ConcurrentSkipListMap<String, Path>());
		for (ConcurrentNavigableMap.Entry<String, Path> entry : initialMap.entrySet()) //Add one route to this node's routing table
			this.routeMap.get(name).put(entry.getKey(), entry.getValue().clone());
		
		if (DEBUG) System.out.println("Node" + name + this.routeMap.toString());
	}
	
	/**
	 * Node-to-node communication of routing table vectors
	 * @param fromNode
	 * @param fromRoutesMap
	 */
	public synchronized void sendRoutes(String fromNode, ConcurrentNavigableMap<String, Path> fromRoutesMap) {
		if (DEBUG) System.out.println(Thread.currentThread().getName() + " -> " + name);
		messages++;
		newRoutes = true;
		
		//Add neighbors routes to own
		if (!routeMap.containsKey(fromNode))
			routeMap.put(fromNode, new ConcurrentSkipListMap<String, Path>());
		
		//Perform Semi-Deep Copy
		for (ConcurrentNavigableMap.Entry<String, Path> entry : fromRoutesMap.entrySet()) //Add one route to this node's routing table
			routeMap.get(fromNode).put(entry.getKey(), entry.getValue().clone());
	}
	
	/**
	 * Preforms the Distance Vector Bellman–Ford routing algorithm 
	 */
	public synchronized void update() {
		updates++;
		
		//Get all possible nodes from different routes
		SortedSet<String> nodeSet = new TreeSet<String>();
		for (Entry<String, ConcurrentNavigableMap<String, Path>> entry : routeMap.entrySet()) //Add all neighbors of one(1) Node to set
			nodeSet.addAll(entry.getValue().keySet());
		
		for (String rowKey : nodeSet) {
			//Calculate FirstHop and Cost of one(1) path for this Node
			
			//Node to itself requires no updating
			if (name.equals(rowKey)) continue;
			
			//Temporary storage of lowest value
			double minimum = DistanceVector.INFINITY;
			String fasterHop = "";
			
			//Calculate DV algorithm for one path
			for (String columnKey : nodeSet) {
				//Computes one route's shortest cost
				
				if (!routeMap.get(name).containsKey(columnKey)) continue; //This node route to Node
				if (!routeMap.containsKey(columnKey)) continue; //Other node's vector
				if (!routeMap.get(columnKey).containsKey(rowKey)) continue; //Other node's vector's route
				
				//RowRoute + ColumRoute
				double temp = routeMap.get(name).get(columnKey).getCost() + routeMap.get(columnKey).get(rowKey).getCost();
				
				//Check if number is the new lowest
				if (temp < minimum) {
					minimum = temp;
					fasterHop = columnKey;
				}
			}
			
			//Set the new lowest number
			if (minimum != DistanceVector.INFINITY) {
				//Set double percision at .5 round up at (1) decimal place
				BigDecimal bd = new BigDecimal(minimum).setScale(1, BigDecimal.ROUND_HALF_UP);
				
				//If route does not exists add it
				if (!routeMap.get(name).containsKey(rowKey)) {
					routeMap.get(name).put(rowKey, new Path(fasterHop, bd.doubleValue()));
					sendUpdate = true;
				} 
				//Route already exists
				else {
					//If no cost-value is added to the firm, do not update route
					if (bd.doubleValue() == routeMap.get(name).get(rowKey).getCost()) continue;
					
					//Set faster route
					routeMap.get(name).get(rowKey).setCost(bd.doubleValue());
					routeMap.get(name).get(rowKey).setFirstHop(fasterHop);
					sendUpdate = true;
				}
			}
		}
	}
	
	/** Return's Router's Identity */
	public String toString() {return name;}
	
	/** Flips the boolean setUpdate */
	public synchronized void setSendUpdate() {sendUpdate = !sendUpdate;}
	
	/** Flips the boolean newRoutes */
	public synchronized void setNewRoutes() {newRoutes = !newRoutes;}
	
	/**
	 * Runnable Method which starts a new router and sends routing messages
	 */
	public void run() {
		try {
			//Set thread's cosmetic name
			Thread.currentThread().setName("Thread-" + name);
			
			//Advertise routes to neighbors
			if (sendUpdate) {
				for (Node instance : neighbors) //Advertise this node's vector to one(1) neighbor
					instance.sendRoutes(name, routeMap.get(name));
				setSendUpdate();
			}
			
			//Wait to get all routes before updating
			Thread.sleep(100);
			
			//Update routes if received new ones
			if (newRoutes) {
				update();
				setNewRoutes();
			}
		} catch (Exception e) {
			System.out.println("Router-" + name +" hardware faliure");
		}
	}
}
