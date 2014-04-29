package pizza.message;

import org.junit.Test;

import junit.framework.TestCase;
/**
 * Verify data content and encoding of  slice toppings
 */
public class ToppingTest extends TestCase {


	@Test
	/**
	 * Verifies data item contents
	 */
	public void testConstructor() {
		Topping topping1 = new Topping(1, "Pineapple",0.75);
		Topping topping2 = new Topping(2, "Pepperoni",1.25);
		Topping topping3 = new Topping(3, "Anchovies",1.25);
		Topping topping4 = new Topping(6, "Extra Cheese",0.75);
		
		assertEquals("Topping 1 id",1,topping1.getId());
		assertEquals("Topping 1 price",0.75,topping1.getPrice(),0.01);
		assertEquals("Topping 1 description","Pineapple",topping1.getDescription());

		assertEquals("Topping 2 id",2,topping2.getId());
		assertEquals("Topping 2 price",1.25,topping2.getPrice(),0.01);
		assertEquals("Topping 2 description","Pepperoni",topping2.getDescription());

		assertEquals("Topping 3 id",3,topping3.getId());
		assertEquals("Topping 3 price",1.25,topping3.getPrice(),0.01);
		assertEquals("Topping 3 description","Anchovies",topping3.getDescription());
		
		assertEquals("Topping 4 id",6,topping4.getId());
		assertEquals("Topping 4 price",0.75,topping4.getPrice(),0.01);
		assertEquals("Topping 4 description","Extra Cheese",topping4.getDescription());
	}
	
	@Test
	/**
	 * Verifies encodings
	 */
	public void testEncoding() {
		Topping topping1 = new Topping(1, "Pineapple",0.75);
		Topping topping2 = new Topping(2, "Pepperoni",1.25);
		Topping topping3 = new Topping(3, "Anchovies",1.25);
		Topping topping4 = new Topping(6, "Extra Cheese",0.75);
		
		assertEquals("Topping 1 encoding","topping id:1, description:Pineapple, price:0.75",
				topping1.encoding());
		assertEquals("Topping 2 encoding","topping id:2, description:Pepperoni, price:1.25",
				topping2.encoding());
		assertEquals("Topping 3 encoding","topping id:3, description:Anchovies, price:1.25",
				topping3.encoding());
		assertEquals("Topping 4 encoding","topping id:6, description:Extra Cheese, price:0.75",
				topping4.encoding());
	}

}