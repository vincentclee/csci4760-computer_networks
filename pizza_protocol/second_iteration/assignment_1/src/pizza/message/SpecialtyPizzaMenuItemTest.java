package pizza.message;

import static org.junit.Assert.*;

import org.junit.Test;
/**
 * Verifies the data values and encoding of a specialty pizza menu item
 */
public class SpecialtyPizzaMenuItemTest {

	@Test
	/**
	 * Verifies data item contents
	 */
	public void testConstructor() {
		SpecialtyPizzaMenuItem pie1 = new SpecialtyPizzaMenuItem("Veggie Lovers' pizza with green peppers, black olives, onions, mushrooms, and mozzarella cheese",1,12.50, 15.50, 18.50);
		SpecialtyPizzaMenuItem pie2 =  new SpecialtyPizzaMenuItem("Buffalo Chicken pizza with onions, mushrooms, and mozzarella cheese",2,13.50, 16.50, 19.50);
		SpecialtyPizzaMenuItem pie3 = new SpecialtyPizzaMenuItem("Pizza Supreme -- this pie has it all! Beef and chorizo sausage, yak meat, tofu, green peppers, olives, and mozzarella cheese!",3,15.50, 20.50, 25.50);
		
		assertEquals("First pie menu item id",1,pie1.getMenuItemId());
		assertEquals("First pie small price",12.50,pie1.getSmallPrice(),0.001);
		assertEquals("First pie medium price",15.50,pie1.getMediumPrice(),0.001);
		assertEquals("First pie large price",18.50,pie1.getLargePrice(),0.001);
		assertEquals("First pie description","Veggie Lovers' pizza with green peppers, black olives, onions, mushrooms, and mozzarella cheese",
				pie1.getDescription());
	
		assertEquals("Second pie menu item id",2,pie2.getMenuItemId());
		assertEquals("Second pie small price",13.50,pie2.getSmallPrice(),0.001);
		assertEquals("Second pie medium price",16.50,pie2.getMediumPrice(),0.001);
		assertEquals("Second pie large price",19.50,pie2.getLargePrice(),0.001);
		assertEquals("Second pie description","Buffalo Chicken pizza with onions, mushrooms, and mozzarella cheese",
				pie2.getDescription());
		
		assertEquals("Third pie menu item id",3,pie3.getMenuItemId());
		assertEquals("Third pie small price",15.50,pie3.getSmallPrice(),0.001);
		assertEquals("Third pie medium price",20.50,pie3.getMediumPrice(),0.001);
		assertEquals("Third pie large price",25.50,pie3.getLargePrice(),0.001);
		assertEquals("Third pie description","Pizza Supreme -- this pie has it all! Beef and chorizo sausage, yak meat, tofu, green peppers, olives, and mozzarella cheese!",
				pie3.getDescription());
	}
	@Test
	/**
	 * Verifies encoding of specialty pizza menu items
	 */
	public void testEncoding() {
		SpecialtyPizzaMenuItem pie1 = new SpecialtyPizzaMenuItem("Veggie Lovers' pizza with green peppers, black olives, onions, mushrooms, and mozzarella cheese",1,12.50, 15.50, 18.50);
		SpecialtyPizzaMenuItem pie2 =  new SpecialtyPizzaMenuItem("Buffalo Chicken pizza with onions, mushrooms, and mozzarella cheese",2,13.50, 16.50, 19.50);
		SpecialtyPizzaMenuItem pie3 = new SpecialtyPizzaMenuItem("Pizza Supreme -- this pie has it all! Beef and chorizo sausage, yak meat, tofu, green peppers, olives, and mozzarella cheese!",3,15.50, 20.50, 25.50);
		
		assertEquals("First pie encoding","Item type:specialty pie, Menu id:1, small:12.50, medium:15.50, large:18.50, description:Veggie Lovers' pizza with green peppers, black olives, onions, mushrooms, and mozzarella cheese",
				pie1.encoding());
		assertEquals("Second pie encoding","Item type:specialty pie, Menu id:2, small:13.50, medium:16.50, large:19.50, description:Buffalo Chicken pizza with onions, mushrooms, and mozzarella cheese",
				pie2.encoding());
		assertEquals("Third pie encoding","Item type:specialty pie, Menu id:3, small:15.50, medium:20.50, large:25.50, description:Pizza Supreme -- this pie has it all! Beef and chorizo sausage, yak meat, tofu, green peppers, olives, and mozzarella cheese!",
				pie3.encoding());
		
	}

}