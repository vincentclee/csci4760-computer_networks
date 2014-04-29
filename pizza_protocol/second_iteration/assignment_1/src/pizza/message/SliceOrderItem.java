package pizza.message;

/**
 * Represents a slice together with zero or more toppings. Also includes a quantity so that a customer can order more than one slice.
 * The representation of this object according to PizzaProtocol is
 * Item type:slice, Menu id:<menu id>, quantity:<quantity>, toppings:<first topping menu id> ... <last topping menu id>
 * -- all on a single line.
 * Topping ids are separated by a single space. If there are no toppings, the "toppings" keyword is still in rhe encoding, but is followed by a newline.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.util.ArrayList;

public class SliceOrderItem extends OrderItem implements Serializable {
	private static final long serialVersionUID = 1;
	
	private ArrayList<Integer> toppings;
	
	/**
	 * Creates a SliceOrderItem with no toppings
	 * @param menuItemId id of a MenuItem, which must be of type SliceOrderItem
	 * @param quantity
	 */
	public SliceOrderItem(int menuItemId, int quantity) {
		super(menuItemId, quantity);
		toppings = new ArrayList<Integer>();
	}
	
	/**
	 * Add a topping to this slice
	 * @param toppingId id of this topping from the menu
	 */
	public void addTopping(int toppingId) {
		toppings.add(toppingId);
	}
	
	/**
	 * Return topping list
	 * @return
	 */
	public ArrayList<Integer> getToppingList() {
		return toppings;
	}
	
	public String encoding() {
		String str = "Item type:slice, Menu id:" + getItemId() + ", quantity:" + getQuantity() + ", toppings:";
		
		for (Integer number : toppings)
			//add one topping to string
			str += Integer.toString(number) + " ";
		
		return str;
	}
}
