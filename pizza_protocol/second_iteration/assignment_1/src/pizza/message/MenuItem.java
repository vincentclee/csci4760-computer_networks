package pizza.message;

/**
 * Represents a menu item on a pizza restaurant menu. This will either be a specialty pizza or a slice with toppings.
 * See subclass documentation for specific representation of each subclass.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public abstract class MenuItem implements Serializable {
	private static final long serialVersionUID = 1;
	
	public static final int SLICE = 1;
	public static final int SPECIALTY_PIE = 2;
	
	private int type;
	private int itemID;
	private String description;
	
	/**
	 * Creates a new MenuItem with specified type, id, and description
	 * @param type SPECIALTY_PIE, SLICE, etc.
	 * @param itemID id of this item; must be unique among all menu items
	 * @param description human-readable description of the item
	 */
	public MenuItem(int type, int itemID, String description) {
		this.type = type;
		this.itemID = itemID;
		this.description = description;
	}
	
	/**
	 * Returns the id of this menu item, which must be distinct from any other menu item id
	 * @return
	 */
	public int getMenuItemId() {
		return itemID;
	}
	
	/**
	 * Returns the item type of this menu, with is either SPECIALTY_PIE or SLICE
	 * @return
	 */
	public int getMenuItemType() {
		return type;
	}
	
	/**
	 * Returns a human-readable description of this menu item
	 * @return
	 */
	public String getItemDescription() {
		return description;
	}
	
	/**
	 * Returns the encoding of this object according to the PizzaProtocol rules.
	 * @return
	 */
	public abstract String encoding();
}
