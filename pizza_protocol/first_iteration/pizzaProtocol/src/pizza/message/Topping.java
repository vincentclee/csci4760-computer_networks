package pizza.message;

/**
 * Represents a topping that can be ordered for a pizza slice. Since toppings are not MenuItems, the id of a topping may duplicate the id of a MenuItem (but must not duplicate the id of another topping).
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public class Topping implements Serializable {
	private static final long serialVersionUID = 1;
	
	private int toppingId;
	private String description;
	private double price;
	
	/**
	 * Define a topping by specifying its topping id, description, and price
	 * @param toppingId
	 * @param description
	 * @param price
	 */
	public Topping(int toppingId, String description, double price) {
		this.toppingId = toppingId;
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Returns the id of this topping, which must be different from from the id of any other topping but may match the id of a menu item.
	 * @return
	 */
	public int getId() {
		return toppingId;
	}
	
	/**
	 * Returns a description of this topping.
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Returns the price of this topping.
	 * @return
	 */
	public double getPrice() {
		return price;
	}
}
