package pizza.message;

/**
 * Itemized total bill for a pizza order, including a description and price of each item in the order plus sales tax and total.
 * The representation of this object according to PizzaProtocol is
 * 4 <Representation of first confirmation item>
 * ...
 * <Representation of last confirmation item>
 * (Empty line)
 * Sales tax: <sales tax amount>
 * Order total amount: <total amount>
 * END
 * See documentation of ConfirmationItem for its representation.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;

import pizza.endpoint.PizzaServer;

public class ConfirmationMessage extends PizzaMessage implements Serializable {
	private static final long serialVersionUID = 1;
	
	private ArrayList<ConfirmationItem> order;
	private double totalAmount;
	private double salesTax;
	
	/**
	 * Creates a new, empty ConfirmationMessage
	 */
	public ConfirmationMessage() {
		super(PizzaMessage.ORDER_RESPONSE);
		order = new ArrayList<ConfirmationItem>();
	}
	
	/**
	 * Add a new item, without changing salesTax or totalAmount
	 * @param itemDescription
	 * @param itemPrice
	 */
	public void addItem(String itemDescription, double itemPrice) {
		order.add(new ConfirmationItem(itemDescription, itemPrice));
	}
	
	/**
	 * Returns the list of items on this order.
	 * @return
	 */
	public ArrayList<ConfirmationItem> getItemList() {
		return order;
	}
	
	/**
	 * Returns the sales tax on this order
	 * @return
	 */
	public double getSalesTax() {
		return salesTax;
	}
	
	/**
	 * Returns the total amount including sales tax
	 * @return
	 */
	public double getTotalAmount() {
		return totalAmount;
	}
	
	/**
	 * Sets the sales tax on this order.
	 * @param salesTax
	 */
	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	
	/**
	 * Sets the total amount of this order
	 * @param totalAmount sum of the price of each item, plus sales tax
	 */
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	/**
	 * Generates the encoding of this object according to the PizzaProtocol encoding rules.
	 * @return
	 */
	public ArrayList<String> encoding() {
		ArrayList<String> str = new ArrayList<String>();
		DecimalFormat formatter = new DecimalFormat("0.00");
		
		str.add("4");
		
		if (salesTax == 0.0) {
		for (ConfirmationItem item : order) {
			//records price
			totalAmount += item.getItemPrice();
		}
		
		totalAmount += salesTax = totalAmount * PizzaServer.TAX_RATE;
		}
		
		for (ConfirmationItem item : order) {
			//adds one line of message to the array list
			str.add(item.encoding());
		}
		
		
		str.add("");
		str.add("Sales tax:" + formatter.format(salesTax));
		str.add("Order total amount:" + formatter.format(totalAmount));
		str.add("END");
		
		return str;
	}
}
