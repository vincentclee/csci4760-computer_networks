package pizza.message;

/**
 * Represents one line item on an order confirmation, including a short description of the item, the quantity ordered, and its price.
 * The representation of this object according to PizzaProtocol is
 * Price:<itemPrice>, description:<quantity> <itemDescription>
 * -- all on a single line.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.text.DecimalFormat;

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
	
	/**
	 * Return an encoding of this object according to the rules of the Pizza Protocol.
	 * @return
	 */
	public String encoding() {
		DecimalFormat formatter = new DecimalFormat("0.00");
		//formatter.setRoundingMode(RoundingMode.HALF_UP);
		return "Price:" + formatter.format(price) + ", description:" + description;
	}
}
