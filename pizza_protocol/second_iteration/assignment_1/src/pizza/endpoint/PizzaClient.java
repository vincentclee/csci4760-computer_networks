package pizza.endpoint;

/**
 * User agent for the Pizza protocol. Connects to a PizzaServer and requests a menu, then transmits the PizzaOrder message to the server and receives the PizzaConfirmationMessage response.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import pizza.message.ConfirmationMessage;
import pizza.message.MenuItem;
import pizza.message.MenuMessage;
import pizza.message.MenuRequestMessage;
import pizza.message.OrderMessage;
import pizza.message.PizzaMessage;
import pizza.message.SliceOrderItem;
import pizza.message.Topping;

public class PizzaClient {
	private Socket socket;
	
	/**
	 * Constructor initiates socket connection
	 * @param vm
	 */
	public PizzaClient(String vm) {
		try {
			InetAddress ip = InetAddress.getByName(vm);
			socket = new Socket(ip.getHostAddress(), 2014);
			System.out.println("Connected to: " + ip + " PizzaServer!");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public PizzaClient() {
		
	}
	
	/**
	 * Retrieves a menu from the remote server.
	 * @return 
	 */
	protected MenuMessage getMenu() {
		try {
			//Send
			OutputStream oStream = socket.getOutputStream();
			DataOutputStream dStream = new DataOutputStream(oStream);
			
			for (String line : new MenuRequestMessage().encoding()) {
				//sends one line of data to the server
				dStream.writeBytes(line + "\n");
			}
			
			//Receive
			InputStreamReader iStream = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(iStream);
			
			ArrayList<String> data = new ArrayList<String>();
			
			//Capture
			String message;
			do {
				//read one line of data from server & add it to array list
				message = reader.readLine();
				data.add(message);
			} while (!message.equalsIgnoreCase("END"));
			
			return (MenuMessage) PizzaMessage.reconstitute(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			//Send
			OutputStream oStream = socket.getOutputStream();
			DataOutputStream dStream = new DataOutputStream(oStream);
			
			for (String line : order.encoding()) {
				//sends one line of data to the server
				dStream.writeBytes(line + "\n");
			}
			
			//Receive
			InputStreamReader iStream = new InputStreamReader(socket.getInputStream());
			BufferedReader reader = new BufferedReader(iStream);
			
			ArrayList<String> data = new ArrayList<String>();
			
			//Capture
			String message;
			do {
				//read one line of data from server & add it to array list
				message = reader.readLine();
				data.add(message);
			} while (!message.equalsIgnoreCase("END"));
			
			return (ConfirmationMessage) PizzaMessage.reconstitute(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return new ConfirmationMessage();
	}
	
	/**
	 * Collects order information from the user
	 * @return the user's order packaged into an OrderMessage object
	 */
	protected OrderMessage collectUserOrder() {
		//not quite sure why this is here.
		return new OrderMessage();
	}
	
	/**
	 * Display a confirmation Message on a terminal
	 * @param confirmation
	 */
	protected void displayConfirmationMessage(ConfirmationMessage confirmation) {
		ArrayList<String> confirmationList = confirmation.encoding();
		if (confirmationList.size() > 2) {
			confirmationList.remove(0);
			confirmationList.remove(confirmationList.size()-1);
		}
		
		System.out.println();
		System.out.println("|||||||||||||||||||||||||||||||");
		System.out.println("||  Your Order Confirmation  ||");
		System.out.println("|||||||||||||||||||||||||||||||");
		
		for (String line : confirmationList) {
			//prints out one line of the confirmation message
			System.out.println(line);
		}
	}
	
	/**
	 * Display a menu on a terminal
	 * @param menu
	 */
	protected void displayMenu(MenuMessage menu) {
		ArrayList<String> menuList = menu.encoding();
		if (menuList.size() > 2) {
			menuList.remove(0);
			menuList.remove(menuList.size()-1);
		}
		
		System.out.println();
		System.out.println("||||||||||||");
		System.out.println("||  Menu  ||");
		System.out.println("||||||||||||");
		
		for (String line : menuList) {
			//prints out one line of the menu
			System.out.println(line);
		}
	}
	
	/**
	 * Connects to a remote PizzaServer and requests a menu which is displayed to the user. Collects user input and composes this into a PizzaOrder message. Finally, transmits the PizzaOrder message to the server and receives the PizzaConfirmationMessage response which is displayed to the user. 
	 * <br><b>Error behavior:</b> Quits with an error message on wrong number of args, hostname does not resolve to an IP address, or no running PizzaServer at remote host..
	 * @param args should have one element 'hostname', a host name that is recognized by the local DNS system.
	 */
	public static void main(String[] args) {
		try {
			//args
			if (args.length != 1) {
				System.out.println("Error: wrong number of args");
				System.exit(0);
			}
			
			//hostname
			try {
				InetAddress.getByName(args[0]);
			} catch (Exception f) {
				System.out.println("Error: hostname does not resolve to an IP address");
				System.exit(0);
			}
			
			//server
			try {
				InetAddress ip = InetAddress.getByName(args[0]);
				Socket testSocket = new Socket();
				testSocket.connect(new InetSocketAddress(ip.getHostAddress(), 2014), 1000);
				testSocket.close();
			} catch(Exception g) {
				System.out.println("Error: no running PizzaServer at remote host");
				System.exit(0);
			}
			
			System.out.println();
			
			System.out.print("Welcome, would you like to view our menu (Y/N)? ");
			Scanner kb = new Scanner(System.in);
			String option = kb.nextLine();
			option.trim();
			if (option.equalsIgnoreCase("y")) {
				MenuMessage menu = new PizzaClient(args[0]).getMenu();
				OrderMessage order = new OrderMessage();
				ArrayList<String> menu_id = new ArrayList<String>();
				ArrayList<String> topping_id = new ArrayList<String>();
				new PizzaClient().displayMenu(menu);
				for (MenuItem item : menu.getMenuItemList()) {
					//add id to arraylist
					menu_id.add(Integer.toString(item.getMenuItemId()));
				}
				for (Topping item : menu.getToppingList()) {
					//add id to arraylist
					topping_id.add(Integer.toString(item.getId()));
				}
				
				System.out.println();
				
				do {
				
				System.out.print("What item would you like, by Menu id (" + menu_id.get(0) + "-" + menu_id.get(menu_id.size()-1) + ")? ");
				option = kb.nextLine();
				option.trim();
				while (!menu_id.contains(option)) {
					//recheck options
					System.out.print("Invalid item, options " + menu_id.toString() + ": ");
					option = kb.nextLine();
					option = option.trim();
				}
				
				for (MenuItem item : menu.getMenuItemList()) {
					//creates one order
					if (option.equals(Integer.toString(item.getMenuItemId()))) {
						//slice
						if (item.getMenuItemType() == 1) {
							System.out.print("Enter multiple toppings by id (" + topping_id.get(0) + "-" + topping_id.get(topping_id.size()-1) + ")? ");
							option = kb.nextLine();
							option = option.trim();
							
							
							ArrayList<String> tokens = new ArrayList<String>();
							Scanner tokenize = new Scanner(option);
							while (tokenize.hasNext()) {
								//add new token to array list
							    tokens.add(tokenize.next());
							}
							tokenize.close();
							
							System.out.print("Quantity? ");
							option = kb.nextLine();
							option = option.trim();
							
							int quantity_number = 1;
							try {
								quantity_number = Integer.parseInt(option);
								if (quantity_number < 1)
									quantity_number = 1;
							} catch(Exception e) {}
							
							SliceOrderItem slice = new SliceOrderItem(item.getMenuItemId(),quantity_number);
							
							for (String toppings : tokens) {
								if (topping_id.contains(toppings)) {
									slice.addTopping(Integer.parseInt(toppings));
								}
							}
							
							order.addSlice(slice);
						} else {
							System.out.print("Size (small(1), medium(2), large(3))? ");
							option = kb.nextLine();
							option = option.trim();
							
							int size_number = 3;
							try {
								size_number = Integer.parseInt(option);
								if (size_number < 1 || size_number > 3)
									size_number = 3;
							} catch(Exception e) {}
							
							System.out.print("Quantity? ");
							option = kb.nextLine();
							option = option.trim();
							
							int quantity_number = 1;
							try {
								quantity_number = Integer.parseInt(option);
								if (quantity_number < 1)
									quantity_number = 1;
							} catch(Exception e) {}
							
							order.addSpecialtyPie(item.getMenuItemId(), quantity_number, size_number);
						}
						
						
						break;
					}
				}
				
				System.out.println();
				System.out.print("Order another (Y/N)? ");
				option = kb.nextLine();
				option = option.trim();
				} while(option.equalsIgnoreCase("y"));
				
				kb.close();
				//send order
				ConfirmationMessage cm = new PizzaClient(args[0]).sendOrder(order);
				new PizzaClient().displayConfirmationMessage(cm);
				
			} else {
				System.out.println("Goodbye!");
			}
		} catch(Exception e) {
			System.out.println("General error. please re-run");
		}
	}
}