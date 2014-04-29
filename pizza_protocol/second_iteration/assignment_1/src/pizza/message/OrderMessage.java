package pizza.message;

/**
 * Represents a pizza order transmitted from a PizzaClient to a PizzaServer, containing one or more specialty pies and slices with toppings.
 * The representation of this object according to PizzaProtocol is
 * 3 <Representation of first order item>
 * ...
 * <Representation of last order item>
 * END
 * See documentation of MenuItem and Topping classes for their representations
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.util.ArrayList;

public class OrderMessage extends PizzaMessage implements Serializable {
	private static final long serialVersionUID = 1;
	private ArrayList<SliceOrderItem> slice;
	private ArrayList<SpecialtyPizzaOrderItem> specialtyPizza;
	/**
	 * Creates a new, empty OrderMessage
	 */
	public OrderMessage() {
		super(PizzaMessage.ORDER_REQUEST);
		slice = new ArrayList<SliceOrderItem>();
		specialtyPizza = new ArrayList<SpecialtyPizzaOrderItem>();
	}
	
	/**
	 * Add a slice to the order
	 * @param item object representing the slice order item
	 */
	public void addSlice(SliceOrderItem item) {
		slice.add(item);
	}
	
	/**
	 * Add a specialty pie to the order
	 * @param menuId menu id of the specialty pier
	 * @param quantity number of pies wanted
	 * @param size SpecialtyPizzaMenuItem.SMALL, SpecialtyPizzaMenuItem.MEDIUM, or SpecialtyPizzaMenuItem.LARGE
	 */
	public void addSpecialtyPie(int menuId, int quantity, int size) {
		specialtyPizza.add(new SpecialtyPizzaOrderItem(menuId, quantity, size));
	}
	
	/**
	 * Get the list of slices in this OrderMessage
	 * @return
	 */
	public ArrayList<SliceOrderItem> getSliceList() {
		return slice;
	}
	
	/**
	 * Get the list of specialty pies in this OrderMessage.
	 * @return
	 */
	public ArrayList<SpecialtyPizzaOrderItem> getSpecialtyPieList() {
		return specialtyPizza;
	}
	
	/**
	 * Generates the encoding of this object according to the PizzaProtocol encoding rules.
	 * @return
	 */
	public ArrayList<String> encoding() {
		ArrayList<String> str = new ArrayList<String>();
		
		str.add("3");
		
		for (SliceOrderItem item : slice)
			//add one line of message to array list
			str.add(item.encoding());
		
		for (SpecialtyPizzaOrderItem pizza : specialtyPizza)
			//add one line of message to array list
			str.add(pizza.encoding());
		
		str.add("END");
		
		return str;
	}
}
