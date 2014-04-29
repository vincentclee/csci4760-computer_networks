package nettest;

import java.net.ServerSocket;
import java.net.SocketTimeoutException;

public class EchoServer0 {
	private final int PORT_NO = 2010;
	private final int TIMEOUT = 20000;
	
	/**
	 * Test of TCP socket programming.
	 * Version 0 opens a listener socket and times out.
	 */
	public EchoServer0() {
		
	}
	
	/**
	 * Sets up a listening TCP socket
	 */
	public void listen() {
		ServerSocket socket;
		
		try {
			socket = new ServerSocket(PORT_NO);
			System.out.println("Opened server socket");
			socket.setSoTimeout(TIMEOUT);
			socket.accept();
			socket.close();
		} catch(SocketTimeoutException ste) {
			System.out.println("Timed out after " + TIMEOUT + "ms");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + " at sever: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new EchoServer0().listen();
	}
}
