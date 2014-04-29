package routing.dv;

/**
 * Route Information retrieved from input file
 * 
 * @author Vincent Lee
 * @since March 31, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

public class Route {
	private String host1, host2;
	private double distance;
	
	/**
	 * Default Constructor
	 * @param host1
	 * @param host2
	 * @param distance
	 */
	public Route(String host1, String host2, double distance) {
		this.host1 = host1;
		this.host2 = host2;
		this.distance = distance;
	}
	
	/** @return the host1 */
	public String getHost1() {return host1;}
	
	/** @return the host2 */
	public String getHost2() {return host2;}
	
	/** @return the distance */
	public double getDistance() {return distance;}
	
	/** @return Routes host to host representation */
	public String toString() {return "Route [host1=" + host1 + ", host2=" + host2 + ", distance=" + distance + "]";}
}
