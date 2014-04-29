package nettest;

import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class EchoServer1 {
	private final int PORT_NO = 2010;
	private final int TIMEOUT = 20000;
	
	/**
	 * Test of TCP socket programming.
	 * Version 0 opens a listener socket and times out.
	 */
	public EchoServer1() {
		
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
			Socket connection = socket.accept();
			System.out.println("Server: Received connection from port " + connection.getPort());
			socket.close();
		} catch(SocketTimeoutException ste) {
			System.out.println("Timed out after " + TIMEOUT + "ms");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + " at sever: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new EchoServer1().listen();
	}
}
