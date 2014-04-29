package pizza.message;

/**
 * This Message type contains no data, but simply requests a message. Human-readable encoding of a MenuRequestMessage is defined as simply:
 * 1 END
 * -- written on a separate line.
 * @author Vincent Lee
 * @version 1.0
 * @since January 10, 2014
 */

import java.io.Serializable;
import java.util.ArrayList;

public class MenuRequestMessage extends PizzaMessage implements Serializable {
	private static final long serialVersionUID = 1;

	/**
	 * Create a MenuRequestMessage by setting the message type of the underlying Message object
	 */
	public MenuRequestMessage() {
		super(PizzaMessage.MENU_REQUEST);
	}
	
	/**
	 * Generates the encoding of this object specified by the PizzaProtocol definition.
	 * @return a single line "END"
	 */
	public ArrayList<String> encoding() {
		ArrayList<String> str = new ArrayList<String>();
		str.add("1");
		str.add("END");
		
		return str;
	}
}
