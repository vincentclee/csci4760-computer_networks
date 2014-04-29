package pizza.message;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
/**
 * Tests the ConfirmationMessag sent by a PizzaServer
 */
public class ConfirmationMessageTest {

	@Test
	/**
	 * Test the representation of a single item specialty pie order
	 */
	public void testSpecialtyPieOrder() {
		ConfirmationMessage message = new ConfirmationMessage();
		message.addItem("1 Pizza Supremo SMALL", 15.60);
		ArrayList<String> encoding = message.encoding();
		assertEquals("encoding number of lines",6,encoding.size());
		assertEquals("encoding first line","4",encoding.get(0));
		assertEquals("encoding second line","Price:15.60, description:1 Pizza Supremo SMALL",
				 encoding.get(1));
		assertTrue("encoding third line empty",encoding.get(2).isEmpty());
		assertEquals("encoding fourth line","Sales tax:1.09", encoding.get(3));
		assertEquals("encoding fifth line","Order total amount:16.69", encoding.get(4));
		assertEquals("encoding sixth line","END", encoding.get(5));
	}
	
	@Test
	/**
	 * Test the representation of a single item slice order
	 */
	public void testSliceOrder() {
		ConfirmationMessage message = new ConfirmationMessage();
		message.addItem("2 Slice (stuffed) Anchovies Extra Cheese", 7.50);
		ArrayList<String> encoding = message.encoding();
		assertEquals("encoding number of lines",6,encoding.size());
		assertEquals("encoding first line","4",encoding.get(0));
		assertEquals("encoding second line",
				"Price:7.50, description:2 Slice (stuffed) Anchovies Extra Cheese",
				 encoding.get(1));
		assertTrue("Third line empty",encoding.get(2).isEmpty());
		assertEquals("encoding fourth line","Sales tax:0.52", encoding.get(3));
		assertEquals("encoding fifth line","Order total amount:8.02", encoding.get(4));
		assertEquals("encoding sixth line","END", encoding.get(5));
	}
	
	@Test
	/**
	 * Test the representation of an order with a specialty pie and two slices
	 */
	public void testMultipleItemOrder() {
		ConfirmationMessage message = new ConfirmationMessage();
		message.addItem("1 Pizza Supremo SMALL", 15.50);
		message.addItem("2 Slice (stuffed) Anchovies Extra Cheese", 7.50);
		message.addItem("1 Slice (thick) Green Pepper", 3.50);
		ArrayList<String> encoding = message.encoding();
		assertEquals("encoding number of lines",8,encoding.size());
		assertEquals("encoding first line","4",encoding.get(0));
		assertEquals("encoding second line","Price:15.50, description:1 Pizza Supremo SMALL",
				 encoding.get(1));
		assertEquals("encoding third line",
				"Price:7.50, description:2 Slice (stuffed) Anchovies Extra Cheese",
				 encoding.get(2));
		assertEquals("encoding fourth line",
				"Price:3.50, description:1 Slice (thick) Green Pepper",
				 encoding.get(3));
		assertTrue("fifth line empty",encoding.get(4).isEmpty());
		assertEquals("encoding sixth line","Sales tax:1.86", encoding.get(5));
		assertEquals("encoding seventh line","Order total amount:28.36", encoding.get(6));
		assertEquals("encoding eighth line","END", encoding.get(7));
	}

}
