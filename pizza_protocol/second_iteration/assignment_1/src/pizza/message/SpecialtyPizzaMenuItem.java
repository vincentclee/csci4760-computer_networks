package pizza.message;

/**
 * Represents a specialty pizza item on a restaurant menu.
 * The representation of this object according to PizzaProtocol is
 * Item type:specialty pie, Menu id:<menu id>, small:<smallPrice>, medium:<mediumPrice>, large:<largePrice>, description:<description>
 * -- all on a single line.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.text.DecimalFormat;

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
	
	public String encoding() {
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		return "Item type:specialty pie, Menu id:" + getMenuItemId() + ", small:" + formatter.format(smallPrice) + ", medium:" + formatter.format(mediumPrice) + ", large:" + formatter.format(largePrice) + ", description:" + getDescription();
	}
}
