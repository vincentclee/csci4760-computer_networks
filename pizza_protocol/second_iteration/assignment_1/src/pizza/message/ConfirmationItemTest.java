package pizza.message;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Tests that a ConfirmationItem object correctly contains
 * and returns data, and that its representation is correct
 * @author drdan
 *
 */
public class ConfirmationItemTest {



	@Test
	/**
	 * Verify that a newly created ConfirmationItem contains
	 * the data supplied to the constructor
	 */
	public void testConstructor() {
		ConfirmationItem instance1 = new ConfirmationItem("1 Slice (Thin Crust) Anchovies",3.75);
		assertEquals("First item description","1 Slice (Thin Crust) Anchovies",instance1.getItemDescription());
		assertEquals("First item price",3.75,instance1.getItemPrice(),0.001);
		
		ConfirmationItem instance2 = new ConfirmationItem("2 Pizza Supremo SMALL",31.00);
		assertEquals("Second item description","2 Pizza Supremo SMALL",instance2.getItemDescription());
		assertEquals("Second item price",31.00,instance2.getItemPrice(),0.001);
		
	}
	@Test
	/**
	 * Verify that the representaion of ConfirmationItem conforms to the Pizza Protocol.
	 */
	public void testRepresentation() {
		ConfirmationItem instance1 = new ConfirmationItem("1 Slice (Thin Crust) Anchovies",3.75);
		assertEquals("First item encoding","Price:3.75, description:1 Slice (Thin Crust) Anchovies",instance1.encoding());
		
		ConfirmationItem instance2 = new ConfirmationItem("2 Pizza Supremo SMALL",31.00);
		assertEquals("Second item encoding","Price:31.00, description:2 Pizza Supremo SMALL",instance2.encoding());
		
	}

}
