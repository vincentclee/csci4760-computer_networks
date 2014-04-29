package pizza.message;

/**
 * Represents one line item on an order confirmation, including a description of the item and its price
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public class ConfirmationItem implements Serializable {
	private static final long serialVersionUID = 1;
	
	private String description;
	private double price;
	
	/**
	 * Creates a new ConfirmationItem.
	 * @param description
	 * @param price
	 */
	public ConfirmationItem(String description, double price) {
		this.description = description;
		this.price = price;
	}
	
	/**
	 * Returns the price of the item
	 * @return
	 */
	public double getItemPrice() {
		return price;
	}
	
	/**
	 * Returns a description of the item.
	 * @return
	 */
	public String getItemDescription() {
		return description;
	}
}
