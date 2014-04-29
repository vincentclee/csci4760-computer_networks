package nettest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	static Socket sendSock;
	
	public static void main(String[] args) {
		Client client = new Client(2010);
//		client.send("Hi mom!");
		client.send(new MessageObject("Hi mom!", 5));
		
		try {
			InputStreamReader iStream = new InputStreamReader(sendSock.getInputStream());
			BufferedReader reader = new BufferedReader(iStream);
			
			String message = reader.readLine();
			System.out.println("Client: received: " + message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Opens a connection to a server on the remote port
	 * @param portNo TCP port to which we will connect
	 */
	public Client(int portNo) {
		try {
			InetAddress ip = InetAddress.getByName("localhost");
			sendSock = new Socket(ip, portNo);
			
			System.out.println("Client: Connected to remote host");
			System.out.println("Client: local port = " + sendSock.getLocalPort());
			System.out.println("Client: remote port = " + sendSock.getPort());
		} catch(Exception e) {
			System.out.println(e.getClass().getName() + " opening connection: " + e.getMessage());
		}
	}
	
	public void send(String message) {
		System.out.println("Client: sending message: " + message);
		OutputStream oStream = null;
		try {
			oStream = sendSock.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//wrap the output stream in something that can write a String
		DataOutputStream dStream = new DataOutputStream(oStream);
		try {
			dStream.writeBytes(message + "\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(MessageObject message) {
		System.out.println("Client: sending message: " + message.item);
		ObjectOutputStream objectStream;
		try {
			objectStream = new ObjectOutputStream(sendSock.getOutputStream());
			objectStream.writeObject(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
