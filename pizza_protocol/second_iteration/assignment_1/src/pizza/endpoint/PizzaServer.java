package pizza.endpoint;

/**
 * Listens on a TCP port and responds to request messages. An incoming MenuRequestMessage will receive a PizzaMenu reply, and an incoming OrderMessage will receive an OrderConfirmation reply. This class should be run as a service or a long-running process.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import pizza.message.ConfirmationMessage;
import pizza.message.MenuItem;
import pizza.message.MenuMessage;
import pizza.message.OrderMessage;
import pizza.message.PizzaMessage;
import pizza.message.SliceMenuItem;
import pizza.message.SliceOrderItem;
import pizza.message.SpecialtyPizzaMenuItem;
import pizza.message.SpecialtyPizzaOrderItem;
import pizza.message.Topping;

public class PizzaServer {
	/** The TCP port on which this server listens, currently 2014 */
	private static final int LISTENING_PORT = 2014;
	/** Sales tax rate, currently 7% */
	public static final double TAX_RATE = 0.07;
	/** Menu object */
	private MenuMessage menuMessage;
	
	/**
	 * Constructor creates menu
	 */
	public PizzaServer() {
		menuMessage = new MenuMessage();
		
		menuMessage.addMenuItem(new SpecialtyPizzaMenuItem("Veggie Lovers' pizza with green peppers, black olives, onions, mushrooms, and mozzarella cheese", 1, 12.50, 15.50, 18.50)); //Veggie pizza
		menuMessage.addMenuItem(new SpecialtyPizzaMenuItem("Buffalo Chicken pizza with onions, mushrooms, and mozzarella cheese", 2, 13.50, 16.50, 19.50)); //Buffalo chicken pizza
		menuMessage.addMenuItem(new SpecialtyPizzaMenuItem("Pizza Supreme -- this pie has it all! Beef and chorizo sausage, yak meat, tofu, green peppers, olives, and mozzarella cheese!", 3, 15.50, 20.50, 25.50)); //Pizza Supremo!
		
		menuMessage.addMenuItem(new SliceMenuItem(4, "Slice (Thin crust)", 2.50)); //Thin crust slice
		menuMessage.addMenuItem(new SliceMenuItem(5, "Slice (Thick crust)", 2.50)); //Thick crust slice
		menuMessage.addMenuItem(new SliceMenuItem(6, "Slice (Stuffed crust)", 3.50)); //Stuffed crust slice
		
		menuMessage.addTopping(new Topping(1, "Pineapple", 0.75)); //Pineapple topping
		menuMessage.addTopping(new Topping(2, "Pepperoni", 1.25)); //Pepperoni topping
		menuMessage.addTopping(new Topping(3, "Anchovies", 1.25)); //Anchovies topping
		menuMessage.addTopping(new Topping(4, "Bacon", 1.00)); //Bacon topping
		menuMessage.addTopping(new Topping(5, "Onion", 0.75)); //Onion topping
		menuMessage.addTopping(new Topping(6, "Extra Cheese", 0.75)); //Extra cheese topping
	}
	
	/**
	 * Generates a menu.
	 * @return
	 */
	protected MenuMessage getMenu() {
		return menuMessage;
	}
	
	/**
	 * Generates the confirmation message for an order
	 * @param order
	 * @return
	 */
	protected ConfirmationMessage generateConfirmation(OrderMessage order) {
		ConfirmationMessage confirmationMessage = new ConfirmationMessage();
		
		for (SpecialtyPizzaOrderItem specialtyPizza : order.getSpecialtyPieList()) {
			//creates one SpecialtyPizzaOrderItem ConfirmationItem & adds to ConfirmationMessage 
			for (MenuItem item : menuMessage.getMenuItemList()) {
				//looks for match from the order item number to the menu item number
				if (specialtyPizza.getItemId() == item.getMenuItemId()) {
					double itemPrice = 0;
					switch (specialtyPizza.getPizzaSize()) {
						case 1:
							itemPrice = ((SpecialtyPizzaMenuItem) item).getSmallPrice();
							break;
						case 2:
							itemPrice = ((SpecialtyPizzaMenuItem) item).getMediumPrice();
							break;
						case 3:
							itemPrice = ((SpecialtyPizzaMenuItem) item).getLargePrice();
							break;
						default:
							System.err.println("err");
					}
					
					//Generate short description
					ArrayList<String> tokens = new ArrayList<String>();
					Scanner tokenize = new Scanner(item.getItemDescription());
					while (tokenize.hasNext()) {
						//add new token to array list
					    tokens.add(tokenize.next());
					}
					tokenize.close();
					
					int quantity = specialtyPizza.getQuantity();
					
					String description = quantity + " " + tokens.remove(0) + " " + tokens.remove(0);
					
					switch (specialtyPizza.getPizzaSize()) {
						case 1:
							description += " SMALL";
							break;
						case 2:
							description += " MEDIUM";
							break;
						case 3:
							description += " LARGE";
							break;
						default:
							System.out.println("err");
					}
					
					confirmationMessage.addItem(description, itemPrice * quantity);
					break;
				}
			}
		}
		
		for (SliceOrderItem slice : order.getSliceList()) {
			//creates one SliceOrderItem ConfirmationItem & adds to ConfirmationMessage 
			for (MenuItem item : menuMessage.getMenuItemList()) {
				//looks for match from the order item number to the menu item number
				if (slice.getItemId() == item.getMenuItemId()) {
					double itemPrice = ((SliceMenuItem)item).getBasePrice();
					int quantity = slice.getQuantity();
					String description = quantity + " " + item.getItemDescription();
					
					for (Integer toppingID : slice.getToppingList()) {
						//adds a topping to the ConfirmationItem
						for (Topping topping : menuMessage.getToppingList()) {
							//looks for a match from order item topping id to menu topping id
							if (toppingID == topping.getId()) {
								description += " " + topping.getDescription();
								itemPrice += topping.getPrice();
								break;
							}
						}
					}
					
					confirmationMessage.addItem(description, itemPrice * quantity);
					break;
				}
			}
		}
		
		return confirmationMessage;
	}
	
	private static ServerSocket serverSocket;
	/**
	 * Repeatedly listens for incoming messages on TCP port LISTENING_PORT and responds. If incoming message is a MenuRequestMessage, respond with a PizzaMenu. If the incoming message is a OrderMessage, respond with a PizzaOrderConfirmation.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			serverSocket = new ServerSocket(LISTENING_PORT);
			PizzaServer pizzaServer = new PizzaServer();
			
			while (true) {
				//opens a connection and processes request and blocks
				Socket connectionSocket = serverSocket.accept();
				
				System.out.println(connectionSocket.getRemoteSocketAddress().toString().substring(1));
				
				InputStreamReader iStream = new InputStreamReader(connectionSocket.getInputStream());
				BufferedReader reader = new BufferedReader(iStream);
				
				ArrayList<String> data = new ArrayList<String>();
				
				//Capture
				try {
					String message;
					do {
						//gets one line of the request
						message = reader.readLine();
						data.add(message);
//						System.out.println(message);
					} while (!message.equalsIgnoreCase("END"));
				} catch(Exception e) {
					System.out.println("No Data");
					continue;
				}
				
				//Output
				OutputStream oStream = connectionSocket.getOutputStream();
				DataOutputStream dStream = new DataOutputStream(oStream);
				
				//Menu Request
				if (data.get(0).startsWith("1")) {
					for (String line : pizzaServer.getMenu().encoding()) {
						//send one line of the request
						dStream.writeBytes(line + "\n");
					}
				}
				//Order Confirmation
				else if (data.get(0).startsWith("3")) {
					OrderMessage order;
					
					try {
						order = (OrderMessage) PizzaMessage.reconstitute(data);
					} catch (Exception e) {
						order = new OrderMessage();
					}
					
					//send back to client
					for (String line : pizzaServer.generateConfirmation(order).encoding()) {
						//send one line of the request
						dStream.writeBytes(line + "\n");
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {}
		}
	}
}