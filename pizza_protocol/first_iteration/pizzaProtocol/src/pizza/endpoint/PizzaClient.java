package pizza.endpoint;

/**
 * User agent for the Pizza protocol. Connects to a PizzaServer and requests a menu, then transmits the PizzaOrder message to the server and receives the PizzaConfirmationMessage response.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.InetAddress;
import java.net.Socket;

import pizza.message.ConfirmationMessage;
import pizza.message.MenuMessage;
import pizza.message.MenuRequestMessage;
import pizza.message.OrderMessage;

public class PizzaClient {
	Socket socket;
	
	public PizzaClient(String vm) {
		try {
			InetAddress ip = InetAddress.getByName(vm);
			socket = new Socket(ip, 2014);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Retrieves a menu from the remote server.
	 * @return 
	 */
	protected MenuMessage getMenu() {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(new MenuRequestMessage());
			
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			return (MenuMessage) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new MenuMessage();
	}
	
	/**
	 * Sends an order and receives the confirmation message from the server
	 * @param order
	 * @return
	 */
	protected ConfirmationMessage sendOrder(OrderMessage order) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(order);
			
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			
			return (ConfirmationMessage) objectInputStream.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ConfirmationMessage();
	}
	
	public void breakServer(Object tester) {
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectOutputStream.writeObject(tester);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Connects to a remote PizzaServer and requests a menu which is displayed to the user. Collects user input and composes this into a PizzaOrder message. Finally, transmits the PizzaOrder message to the server and receives the PizzaConfirmationMessage response which is displayed to the user. 
	 * <br><b>Error behavior:</b> Quits with an error message on wrong number of args, hostname does not resolve to an IP address, or no running PizzaServer at remote host..
	 * @param args should have one element 'hostname', a host name that is recognized by the local DNS system.
	 */
//	public static void main(String[] args) {
//		try {
//			  String sentence;
//			  String modifiedSentence;
////			  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
//			Socket clientSocket = new Socket("localhost", 2014);
//			ObjectOutputStream objectStream = new ObjectOutputStream(clientSocket.getOutputStream());
//			Messagerr objecter = new Messagerr();
//			objecter.test = "asdfadf";
//			objectStream.writeObject(objecter);
////			  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
//			  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
////			  sentence = inFromUser.readLine();
////			  outToServer.writeBytes(sentence + '\n');
//			  modifiedSentence = inFromServer.readLine();
//			  System.out.println("FROM SERVER: " + modifiedSentence);
//			  clientSocket.close();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}