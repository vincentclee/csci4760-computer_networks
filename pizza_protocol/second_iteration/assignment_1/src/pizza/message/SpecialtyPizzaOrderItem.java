package pizza.message;

/**
 * Represents an order item for one or more specialty pizzas of a specified size SMALL, MEDIUM, or LARGE.
 * The representation of this object according to PizzaProtocol is
 * Item type:specialty pie, Menu id:<menu id>, size:<size>, quantity:<quantity>
 * --all on a single line
 * Where size is 1 (SMALL), 2 (MEDIUM), or 3 (LARGE)
 * @author Vincent Lee
 * @version 1.0
 * @since January 15, 2014
 */

import java.io.Serializable;

public class SpecialtyPizzaOrderItem extends OrderItem implements Serializable {
	private static final long serialVersionUID = 1;
	
	/** Constant representing a small pizza, value is 1 */
	public static int SMALL = 1;
	/** Constant representing a medium pizza, value is 2 */
	public static int MEDIUM = 2;
	/** Constant representing a large pizza, value is 3 */
	public static int LARGE = 3;
	
	private int size;
	
	/**
	 * Creates an order item for one or more specialty pizzas of a specified size.
	 * @param menuId id of this item on the menu
	 * @param quantity
	 * @param size
	 */
	public SpecialtyPizzaOrderItem(int menuId, int quantity, int size) {
		super(menuId, quantity);
		this.size = size;
	}
	
	/**
	 * Returns the size of this pizza, which is one of SMALL, MEDIUM, or LARGE
	 * @return
	 */
	public int getPizzaSize() {
		return size;
	}
	
	public String encoding() {
		return "Item type:specialty pie, Menu id:" + getItemId() + ", size:" + size + ", quantity:" + getQuantity();
	}
}