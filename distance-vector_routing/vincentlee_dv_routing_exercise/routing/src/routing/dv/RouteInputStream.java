package routing.dv;

/**
 * Opens file and returns Routes
 * 
 * @author Vincent Lee
 * @since March 31, 2014
 * @version 1.0
 * 
 * Copyright 2014, SSLTunnel <ssltunnelnet@gmail.com>
 * All rights reserved.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RouteInputStream {
	private String line;
	private BufferedReader reader;
	
	/** Default Constructor */
	public RouteInputStream() {}
	
	/**
	 * Opens file stream
	 * @param filename
	 * @return if file can be opened
	 */
	public boolean openFile(String filename) {
		try {
			reader = new BufferedReader(new FileReader(filename));
			return true;
		} catch (Exception e) {
			System.out.println("error: openFile");
			return false;
		}
	}
	
	/**
	 * Checks if there is next line in route file
	 * @return if line in file contains valid route information
	 */
	public boolean next() {
		try {
			//read line
			line = reader.readLine();
			
			//if no line
			if (line == null)
				return false;
			//if line is blank
			else {
				line = line.trim();
				return !line.equals("");
			}
		} catch (Exception e) {
			System.out.println("error: next");
		}
		return false;
	}
	
	/**
	 * Returns next route as a Route Object
	 * @return Route Object
	 */
	public Route nextRoute() {
		try {
			//Stores items from line in file
			List<String> tokens = new ArrayList<String>();
					
			Scanner tokenize = new Scanner(line);
			while (tokenize.hasNext()) //Add's one chunk of data seperated by a space
			    tokens.add(tokenize.next());
			tokenize.close();
			
			//if line contains invalid route information
			if (tokens.size() != 3) throw new Exception();
			
			return new Route(tokens.get(0), tokens.get(1), Double.parseDouble(tokens.get(2)));
		} catch (Exception e) {
			System.out.println("error: nextRoute");
			return null;
		}
	}
}
