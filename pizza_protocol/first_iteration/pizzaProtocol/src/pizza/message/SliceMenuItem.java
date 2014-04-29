package pizza.message;

/**
 * Represents a slice on a pizza menu. The menu will have one SliceMenuItem for each different type of crust. The price of a slice will be the base price plus the sum of the prices of all toppings chosen
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public class SliceMenuItem extends MenuItem implements Serializable {
	private static final long serialVersionUID = 1;
	
	private double basePrice;
	
	/**
	 * Creates a menu item with specified id, crust, and base price
	 * @param itemId id of this menu item, unique among all menu item
	 * @param crust type of crust, such as "Thick", "Thin", "Sicilian", etc.
	 * @param basePrice price of this slice without any toppings added
	 */
	public SliceMenuItem(int itemId, String crust, double basePrice) {
		super(MenuItem.SLICE, itemId, crust);
		this.basePrice = basePrice;
	}
	
	/**
	 * Returns the price of this slice, without any toppings added.
	 * @return
	 */
	public double getBasePrice() {
		return basePrice;
	}
}
