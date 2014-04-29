package pizza.message;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;

public class SliceOrderItemTest extends TestCase {

	@Test
	/**
	 * Verifies data item contents
	 */
	public void testConstructor() {
		SliceOrderItem orderItem1 = new SliceOrderItem(3,2); //no toppings
		SliceOrderItem orderItem2 = new SliceOrderItem(4,2); //one topping
		orderItem2.addTopping(4);
		SliceOrderItem orderItem3 = new SliceOrderItem(5,1); //two toppings
		orderItem3.addTopping(2);
		orderItem3.addTopping(5);
		SliceOrderItem orderItem4 = new SliceOrderItem(9,2); //three toppings
		orderItem4.addTopping(2);
		orderItem4.addTopping(5);
		orderItem4.addTopping(7);
		
		assertEquals("order item 1 menu id",3,orderItem1.getItemId());
		assertEquals("order item 1 quantity",2,orderItem1.getQuantity());
		ArrayList<Integer> toppingList1 = orderItem1.getToppingList();
		assertTrue("orderItem 1 empty topping list",toppingList1.isEmpty());

		assertEquals("order item 2 menu id",4,orderItem2.getItemId());
		assertEquals("order item 2 quantity",2,orderItem2.getQuantity());
		ArrayList<Integer> toppingList2 = orderItem2.getToppingList();
		assertEquals("orderItem 2 number of toppings",1,toppingList2.size());
		assertEquals("order item 2 first topping",4,toppingList2.get(0).intValue());

		assertEquals("order item 3 menu id",5,orderItem3.getItemId());
		assertEquals("order item 3 quantity",1,orderItem3.getQuantity());
		ArrayList<Integer> toppingList3 = orderItem3.getToppingList();
		assertEquals("order item 3 number of toppings",2,toppingList3.size());
		assertEquals("order item 3 first topping",2,toppingList3.get(0).intValue());
		assertEquals("order item 3 second topping",5,toppingList3.get(1).intValue());

		assertEquals("order item 4 menu id",9,orderItem4.getItemId());
		assertEquals("order item 4 quantity",2,orderItem4.getQuantity());
		ArrayList<Integer> toppingList4 = orderItem4.getToppingList();
		assertEquals("order item 4 number of toppings",3,toppingList4.size());
		assertEquals("order item 4 first topping",2,toppingList4.get(0).intValue());
		assertEquals("order item 4 second topping",5,toppingList4.get(1).intValue());
		assertEquals("order item 4 third topping",7,toppingList4.get(2).intValue());
		
		
	}
	
	/**
	 * Verifies encodings
	 */
	public void testEncoding() {
		SliceOrderItem orderItem1 = new SliceOrderItem(3,2); //no toppings
		SliceOrderItem orderItem2 = new SliceOrderItem(4,2); //one topping
		orderItem2.addTopping(4);
		SliceOrderItem orderItem3 = new SliceOrderItem(5,1); //two toppings
		orderItem3.addTopping(2);
		orderItem3.addTopping(5);
		SliceOrderItem orderItem4 = new SliceOrderItem(9,2); //three toppings
		orderItem4.addTopping(2);
		orderItem4.addTopping(5);
		orderItem4.addTopping(7);
		
		assertEquals("order item 1 encoding","Item type:slice, Menu id:3, quantity:2, toppings:",
             orderItem1.encoding());
		assertEquals("order item 2 encoding","Item type:slice, Menu id:4, quantity:2, toppings:4",
	             orderItem2.encoding().trim());
		assertEquals("order item 3 encoding","Item type:slice, Menu id:5, quantity:1, toppings:2 5",
	             orderItem3.encoding().trim());
		assertEquals("order item 4 encoding","Item type:slice, Menu id:9, quantity:2, toppings:2 5 7",
	             orderItem4.encoding().trim());
		
	}
	
}
