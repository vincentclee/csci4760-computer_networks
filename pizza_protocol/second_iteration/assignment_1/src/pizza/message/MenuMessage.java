package pizza.message;

/**
 * Represents a menu for a pizza restaurant. A client must be able to use the information in this object to display a human-readable menu to the user and to collect user input for a PizzaOrder object.
 * The representation of this object according to PizzaProtocol is
 * 2 <Representation of first menu item>
 * ...
 * <Representation of last menu item>
 * (Empty line)
 * <Representation of first topping>
 * ...
 * <Representation of last topping>
 * END
 * See the documentation of MenuItem and Topping classes for their representations
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.util.ArrayList;

public class MenuMessage extends PizzaMessage implements Serializable {
	private static final long serialVersionUID = 1;
	
	private ArrayList<MenuItem> menu;
	private ArrayList<Topping> toppings;
	
	/**
	 * Create a PizzaMenu message with empty lists
	 */
	public MenuMessage() {
		super(2);
		menu = new ArrayList<MenuItem>();
		toppings = new ArrayList<Topping>();
	}
	
	/**
	 * Add a specialty pizza or a slice menu item to the list of menu items.
	 * @param item
	 */
	public void addMenuItem(MenuItem item) {
		menu.add(item);
	}
	
	/**
	 * Add a topping to the list of toppings.
	 * @param topping
	 */
	public void addTopping(Topping topping) {
		toppings.add(topping);
	}
	
	/**
	 * Get the list of specialty pizzas and slices on this menu
	 * @return
	 */
	public ArrayList<MenuItem> getMenuItemList() {
		return menu;
	}
	
	/**
	 * Get the list of slice toppings on this menu
	 * @return
	 */
	public ArrayList<Topping> getToppingList() {
		return toppings;
	}
	
	/**
	 * Returns a human-readable encoding of this object.
	 * @return
	 */
	public ArrayList<String> encoding() {
		ArrayList<String> str = new ArrayList<String>();
		
		str.add("2");
		
		for (MenuItem item : menu)
			//add one line of message to array list
			str.add(item.encoding());
		
		str.add("");
		
		for (Topping topping : toppings)
			//add one line of message to array list
			str.add(topping.encoding());
		
		str.add("END");
		
		return str;
	}
}
