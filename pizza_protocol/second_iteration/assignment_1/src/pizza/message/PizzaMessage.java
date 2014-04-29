package pizza.message;

/**
 * Base class for all of the message types exchanged during operations of the Pizza Protocol. Human-readable encoding of a PizzaMessage is defined as follows:
 * Message Type
 * <Specific content encoding of the message subclass >
 * Message type codes are:
 * 1 = Menu request
 * 2 = Menu message
 * 3 = Order message
 * 4 = Confirmation message
 * Consult the documentation of the subclasses for their specific encodings.Each specific encoding terminates with the word "END" on a line by itself.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public abstract class PizzaMessage implements Serializable {
	private static final long serialVersionUID = 1;
	
	/**
	 * The four types of Messages
	 */
	public static final int MENU_REQUEST = 1;
	public static final int MENU_RESPONSE = 2;
	public static final int ORDER_REQUEST = 3;
	public static final int ORDER_RESPONSE = 4;
	
	private int theType;
	
	/**
	 * Sets the message type
	 * @param theType
	 */
	public PizzaMessage(int theType) {
		this.theType = theType;
	}
	
	/**
	 * Returns the message type
	 * @return
	 */
	public int getMessageType() {
		return theType;
	}
	
	/**
	 * Returns a human-readable representation of this object.
	 * @return
	 */
	public abstract ArrayList<String> encoding();
	
	public static PizzaMessage reconstitute(ArrayList<String> representation) throws IllegalArgumentException {
		if (representation.get(0).startsWith("1")) {
			return new MenuRequestMessage();
		} else if (representation.get(0).startsWith("2")) {
			//Rebuild
			MenuMessage menu = new MenuMessage();
			
			for (String line : representation) {
				//converts one line into a Object
				if (line.startsWith("Item type:specialty pie")) {
					//do the description
					int descriptionIndex = line.indexOf("description:");
					String description = line.substring(descriptionIndex+12);
					
					//map parameters
					HashMap<String, String> map = new HashMap<String, String>();
					
					Scanner tokenize = new Scanner(line.substring(0, descriptionIndex));
					tokenize.useDelimiter(", ");
					while (tokenize.hasNext()) {
						//breaks up one parameter into key and value
						Scanner tokenizeParam = new Scanner(tokenize.next());
						tokenizeParam.useDelimiter(":");
						map.put(tokenizeParam.next(), tokenizeParam.next());
						tokenizeParam.close();
					}
					tokenize.close();
					
					//add item to menu
					menu.addMenuItem(new SpecialtyPizzaMenuItem(description, Integer.parseInt(map.get("Menu id")), Double.parseDouble(map.get("small")), Double.parseDouble(map.get("medium")), Double.parseDouble(map.get("large"))));
				} else if (line.startsWith("Item type:slice")) {
					HashMap<String, String> map = new HashMap<String, String>();
					
					Scanner tokenize = new Scanner(line);
					tokenize.useDelimiter(", ");
					while (tokenize.hasNext()) {
						//breaks up one parameter into key and value
						Scanner tokenizeParam = new Scanner(tokenize.next());
						tokenizeParam.useDelimiter(":");
						map.put(tokenizeParam.next(), tokenizeParam.next());
						tokenizeParam.close();
					}
					tokenize.close();
					
					//add item to menu
					menu.addMenuItem(new SliceMenuItem(Integer.parseInt(map.get("Menu id")), map.get("description"), Double.parseDouble(map.get("price"))));
				} else if (line.startsWith("topping id:")) {
					HashMap<String, String> map = new HashMap<String, String>();
					
					Scanner tokenize = new Scanner(line);
					tokenize.useDelimiter(", ");
					while (tokenize.hasNext()) {
						//breaks up one parameter into key and value
						Scanner tokenizeParam = new Scanner(tokenize.next());
						tokenizeParam.useDelimiter(":");
						map.put(tokenizeParam.next(), tokenizeParam.next());
						tokenizeParam.close();
					}
					tokenize.close();
					
					//add topping to menu
					menu.addTopping(new Topping(Integer.parseInt(map.get("topping id")), map.get("description"), Double.parseDouble(map.get("price"))));
				}
			}
			
			return menu;
		} else if (representation.get(0).startsWith("3")) {
			OrderMessage order = new OrderMessage();
			
			for (String line : representation) {
				//converts one line into a Object
				if (line.startsWith("Item type:slice")) {
					HashMap<String, String> map = new HashMap<String, String>();
					
					Scanner tokenize = new Scanner(line);
					tokenize.useDelimiter(", ");
					while (tokenize.hasNext()) {
						//breaks up one parameter into key and value
						Scanner tokenizeParam = new Scanner(tokenize.next());
						tokenizeParam.useDelimiter(":");
						
						try {
							map.put(tokenizeParam.next(), tokenizeParam.next());
						} catch (Exception e){}
						
						tokenizeParam.close();
					}
					tokenize.close();
					
					//Create a slice
					SliceOrderItem slice = new SliceOrderItem(Integer.parseInt(map.get("Menu id")), Integer.parseInt(map.get("quantity")));
					
					if (map.containsKey("toppings")) {
						tokenize = new Scanner(map.get("toppings"));
						while (tokenize.hasNext()) {
							//add a topping based on topping id
							slice.addTopping(Integer.parseInt(tokenize.next()));
						}
					}
					
					//add slice to order
					order.addSlice(slice);
				} else if (line.startsWith("Item type:specialty pie")) {
					HashMap<String, String> map = new HashMap<String, String>();
					
					Scanner tokenize = new Scanner(line);
					tokenize.useDelimiter(", ");
					while (tokenize.hasNext()) {
						//breaks up one parameter into key and value
						Scanner tokenizeParam = new Scanner(tokenize.next());
						tokenizeParam.useDelimiter(":");
						map.put(tokenizeParam.next(), tokenizeParam.next());
						tokenizeParam.close();
					}
					tokenize.close();
					
					//add Specialty Pie to order
					order.addSpecialtyPie(Integer.parseInt(map.get("Menu id")), Integer.parseInt(map.get("quantity")), Integer.parseInt(map.get("size")));
				}
			}
			
			return order;
		} else if (representation.get(0).startsWith("4")) {
			ConfirmationMessage confirmMessage = new ConfirmationMessage();
			
			for (String line : representation) {
				//convert one line into Object
				if (line.startsWith("Price:")) {
					HashMap<String, String> map = new HashMap<String, String>();
					
					Scanner tokenize = new Scanner(line);
					tokenize.useDelimiter(", ");
					while (tokenize.hasNext()) {
						//breaks up one parameter into key and value
						Scanner tokenizeParam = new Scanner(tokenize.next());
						tokenizeParam.useDelimiter(":");
						map.put(tokenizeParam.next(), tokenizeParam.next());
						tokenizeParam.close();
					}
					tokenize.close();
					
					//add item to confirmation message
					confirmMessage.addItem(map.get("description"), Double.parseDouble(map.get("Price")));
				} else if (line.startsWith("Sales tax:")) {
					ArrayList<String> tokens = new ArrayList<String>();
					
					Scanner tokenize = new Scanner(line);
					tokenize.useDelimiter(":");
					while (tokenize.hasNext()) {
						//add one token to the array list
						tokens.add(tokenize.next());
					}
					tokenize.close();
					
					//add item to confirmation message
					confirmMessage.setSalesTax(Double.parseDouble(tokens.get(1)));
				} else if (line.startsWith("Order total amount:")) {
					ArrayList<String> tokens = new ArrayList<String>();
					
					Scanner tokenize = new Scanner(line);
					tokenize.useDelimiter(":");
					while (tokenize.hasNext()) {
						//add one token to the array list
						tokens.add(tokenize.next());
					}
					tokenize.close();
					
					//add item to confirmation message
					confirmMessage.setTotalAmount(Double.parseDouble(tokens.get(1)));
				}
			}
			
			return confirmMessage;
		}
		
		throw new IllegalArgumentException();
	}
}
