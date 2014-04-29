package pizza.message;

/**
 * This Message type contains no data, but simply requests a message
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;

public class MenuRequestMessage extends PizzaMessage implements Serializable {
	private static final long serialVersionUID = 1;

	/**
	 * Create a MenuRequestMessage by setting the message type of the underlying Message object
	 */
	public MenuRequestMessage() {
		super(PizzaMessage.MENU_REQUEST);
	}
}
