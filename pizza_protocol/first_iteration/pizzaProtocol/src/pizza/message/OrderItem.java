package pizza.message;

/**
 * Represents an item in a PizzaOrder sent from a PizzaClient to a PizzaServer
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1;
	
	private int id;
	private int quantity;
	
	/**
	 * Creates an order item with a specified id and quantity, e.g. "two of item 37"
	 * @param id
	 * @param quantity
	 */
	public OrderItem(int id, int quantity) {
		this.id = id;
		this.quantity = quantity;
	}
	
	/**
	 * Returns the id of this item on the pizza menu.
	 * @return
	 */
	public int getItemId() {
		return id;
	}
	
	/**
	 * Returns the number of this item on this order
	 * @return
	 */
	public int getQuantity() {
		return quantity;
	}
}
