package pizza.message;

/**
 * Base class for all of the message types exchanged during operations of the Pizza Protocol.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public abstract class PizzaMessage implements Serializable {
	private static final long serialVersionUID = 1;
	
	/**
	 * The four types of Messages
	 */
	public static final int MENU_REQUEST = 1;
	public static final int MENU_RESPONSE = 2;
	public static final int ORDER_REQUEST = 3;
	public static final int ORDER_RESPONSE = 4;
	
	/**
	 * Sets the message type
	 * @param theType
	 */
	public PizzaMessage(int theType) {
		this.theType = theType;
	}
	
	/**
	 * Returns the message type
	 * @return
	 */
	public int getMessageType() {
		return theType;
	}
	private int theType;
}
