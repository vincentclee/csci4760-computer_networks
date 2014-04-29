package pizza.message;

/**
 * Represents a specialty pizza item on a restaurant menu
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public class SpecialtyPizzaMenuItem extends MenuItem implements Serializable {
	private static final long serialVersionUID = 1;
	
	private double smallPrice;
	private double mediumPrice;
	private double largePrice;
	
	/**
	 * Creates a menu item by specifying its content.
	 * @param description
	 * @param menuItemId
	 * @param smallPrice
	 * @param mediumPrice
	 * @param largePrice
	 */
	public SpecialtyPizzaMenuItem(String description, int menuItemId, double smallPrice, double mediumPrice, double largePrice) {
		super(MenuItem.SPECIALTY_PIE, menuItemId, description);
		this.smallPrice = smallPrice;
		this.mediumPrice = mediumPrice;
		this.largePrice = largePrice;
	}
	
	/**
	 * Returns a description of the menu item
	 * @return
	 */
	public String getDescription() {
		return super.getItemDescription();
	}
	
	/**
	 * Returns the price of a large pizza of this type.
	 * @return
	 */
	public double getLargePrice() {
		return largePrice;
	}
	
	/**
	 * Returns the price of a medium pizza of this type.
	 * @return
	 */
	public double getMediumPrice() {
		return mediumPrice;
	}
	
	/**
	 * Returns the price of a small pizza of this type.
	 * @return
	 */
	public double getSmallPrice() {
		return smallPrice;
	}
}
