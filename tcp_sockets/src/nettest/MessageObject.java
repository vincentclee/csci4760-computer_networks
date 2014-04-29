package nettest;

import java.io.Serializable;

public class MessageObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String item;
	int count;
	
	/**
	 * @param item
	 * @param count
	 */
	public MessageObject(String item, int count) {
		this.item = item;
		this.count = count;
	}
	
	/**
	 * @return the item
	 */
	public String getItem() {
		return item;
	}
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}
}
