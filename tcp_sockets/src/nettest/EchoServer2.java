package nettest;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class EchoServer2 {
	private final int PORT_NO = 2010;
	private final int TIMEOUT = 20000;
	
	/**
	 * Test of TCP socket programming.
	 * Version 0 opens a listener socket and times out.
	 */
	public EchoServer2() {
		
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
		
			//wrap the raw input stream in something that can read an object
			ObjectInputStream iStream = new ObjectInputStream(connection.getInputStream());
			MessageObject message = (MessageObject) iStream.readObject();
			System.out.println("Server: received object, item = " + message.getItem() + " count: " + message.getCount());
			
			
			OutputStream oStream = null;
			try {
				oStream = connection.getOutputStream();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//wrap the output stream in something that can write a String
			DataOutputStream dStream = new DataOutputStream(oStream);
			try {
				dStream.writeBytes(message.item + "\n");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			socket.close();
		} catch(SocketTimeoutException ste) {
			System.out.println("Timed out after " + TIMEOUT + "ms");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + " at sever: " + e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		new EchoServer2().listen();
	}
}
