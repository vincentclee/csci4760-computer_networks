package routing.dv.tests;

/**
 * Test file parser for common errors
 * 
 * @author Vincent Lee
 * @since March 31, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Before;

import routing.dv.Route;
import routing.dv.RouteInputStream;

public class RouteInputStreamTest {
	private List<Route> routes;
	
	/**
	 * Load actual route information
	 */
	@Before
	public void setUp() {
		routes = new ArrayList<Route>();
		routes.add(new Route("A", "B", 3.8));
		routes.add(new Route("A", "C", 1.2));
		routes.add(new Route("B", "D", 5.1));
		routes.add(new Route("C", "D", 4.2));
		routes.add(new Route("B", "E", 1.1));
		routes.add(new Route("D", "E", 3.2));
		routes.add(new Route("E", "F", 4.4));
	}
	
	/**
	 * Test Constructor
	 */
	@Test
	public void testConstructor() {
		assertNotNull(new RouteInputStream());
	}
	
	/**
	 * Test a perfect file
	 * 
	 * --------
	 * "A B 3.8"
	 * "A C 1.2"
	 * "B C 5.1"
	 * --------
	 */
	@Test
	public void readPerfectFile() {
		List<Route> routeList = new ArrayList<Route>();
		RouteInputStream instance = new RouteInputStream();
		
		//Open file
		assertTrue("File open error", instance.openFile("topology"));
		
		//Read file
		while (instance.next()) //add one route to List
			routeList.add(instance.nextRoute());
		
		//// Tests ////
		//# of Routes Test
		assertEquals("Invalid number of routes", routeList.size(), routes.size());
		
		//Route Integrity Test
		for (int i = 0; i < routeList.size(); i++) {
			//test a route's host-1, host-2, and distance values
			assertEquals("Host-1", routeList.get(i).getHost1(), routes.get(i).getHost1());
			assertEquals("Host-2", routeList.get(i).getHost2(), routes.get(i).getHost2());
			assertEquals("distance", routeList.get(i).getDistance(), routes.get(i).getDistance(), 0);
		}
	}
	
	/**
	 * Tests a file with extra blank lines
	 * 
	 * --------
	 * "A B 3.8"
	 * "A C 1.2"
	 * "B C 5.1"
	 * ""
	 * --------
	 */
	@Test
	public void readFileWithExtraBlankLines() {
		List<Route> routeList = new ArrayList<Route>();
		RouteInputStream instance = new RouteInputStream();
		
		//Open file
		assertTrue("File open error", instance.openFile("test_topology" + System.getProperty("file.separator") + "topology1"));
		
		//Read file
		while (instance.next()) //add one route to List
			routeList.add(instance.nextRoute());
		
		//// Tests ////
		//# of Routes Test
		assertEquals("Invalid number of routes", routeList.size(), routes.size());
		
		//Route Integrity Test
		for (int i = 0; i < routeList.size(); i++) {
			//test a route's host-1, host-2, and distance values
			assertEquals("Host-1", routeList.get(i).getHost1(), routes.get(i).getHost1());
			assertEquals("Host-2", routeList.get(i).getHost2(), routes.get(i).getHost2());
			assertEquals("distance", routeList.get(i).getDistance(), routes.get(i).getDistance(), 0);
		}
	}
	
	/**
	 * Test a file with extra lines containing spaces
	 * 
	 * --------
	 * "A B 3.8"
	 * "A C 1.2"
	 * "B C 5.1"
	 * ""
	 * "  "
	 * --------
	 */
	@Test
	public void readFileWithExtraLinesContainingSpaces() {
		List<Route> routeList = new ArrayList<Route>();
		RouteInputStream instance = new RouteInputStream();
		
		//Open file
		assertTrue("File open error", instance.openFile("test_topology" + System.getProperty("file.separator") + "topology2"));
		
		//Read file
		while (instance.next()) //add one route to List
			routeList.add(instance.nextRoute());
		
		//// Tests ////
		//# of Routes Test
		assertEquals("Invalid number of routes", routeList.size(), routes.size());
		
		//Route Integrity Test
		for (int i = 0; i < routeList.size(); i++) {
			//test a route's host-1, host-2, and distance values
			assertEquals("Host-1", routeList.get(i).getHost1(), routes.get(i).getHost1());
			assertEquals("Host-2", routeList.get(i).getHost2(), routes.get(i).getHost2());
			assertEquals("distance", routeList.get(i).getDistance(), routes.get(i).getDistance(), 0);
		}
	}
	
	/**
	 * Test a file with routes that contain before and after spaces
	 * 
	 * --------
	 * " A B 3.8"
	 * "A C 1.2 "
	 * " B C 5.1 "
	 * --------
	 */
	@Test
	public void readFileWithRoutesContainingBeforeAfterSpaces() {
		List<Route> routeList = new ArrayList<Route>();
		RouteInputStream instance = new RouteInputStream();
		
		//Open file
		assertTrue("File open error", instance.openFile("test_topology" + System.getProperty("file.separator") + "topology3"));
		
		//Read file
		while (instance.next()) //add one route to List
			routeList.add(instance.nextRoute());
		
		//// Tests ////
		//# of Routes Test
		assertEquals("Invalid number of routes", routeList.size(), routes.size());
		
		//Route Integrity Test
		for (int i = 0; i < routeList.size(); i++) {
			//test a route's host-1, host-2, and distance values
			assertEquals("Host-1", routeList.get(i).getHost1(), routes.get(i).getHost1());
			assertEquals("Host-2", routeList.get(i).getHost2(), routes.get(i).getHost2());
			assertEquals("distance", routeList.get(i).getDistance(), routes.get(i).getDistance(), 0);
		}
	}
}
