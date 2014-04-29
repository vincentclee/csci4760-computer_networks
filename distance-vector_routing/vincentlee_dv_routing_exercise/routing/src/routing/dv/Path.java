package routing.dv;

/**
 * Routing Path with first hop and distance
 * 
 * @author Vincent Lee
 * @since April 1, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

public class Path implements Cloneable {
	private String firstHop;
	private double cost;
	
	/**
	 * Constructor
	 * @param firstHop
	 * @param cost
	 */
	public Path(String firstHop, double cost) {
		this.firstHop = firstHop;
		this.cost = cost;
	}
	
	/**
	 * Copy Constructor
	 * @param path
	 */
	public Path(Path path) {
		this.firstHop = path.firstHop;
		this.cost = path.cost;
	}
	
	/** Shallow Copy */
	public Path clone() {try {return (Path) super.clone();} catch (Exception e) {return null;}}
	
	/** @return the firstHop */
	public synchronized String getFirstHop() {return firstHop;}
	
	/** @return the cost */
	public synchronized double getCost() {return cost;}
	
	/** @param firstHop the firstHop to set */
	public synchronized void setFirstHop(String firstHop) {this.firstHop = firstHop;}
	
	/** @param cost the cost to set */
	public synchronized void setCost(double cost) {this.cost = cost;}
	
	/** @return Path's simple representation */
	public String toString() {return "[via=" + firstHop + ", cost=" + ((cost == DistanceVector.INFINITY) ? DistanceVector.INFINITY_SYMBOL : cost) + "]";}
}
