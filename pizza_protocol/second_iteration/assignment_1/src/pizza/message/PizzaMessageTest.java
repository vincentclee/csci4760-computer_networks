package pizza.message;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
/**
 * Test for the ability to reconstitute an array of Strings into an object
 * of a PizzaMessage subclas
 * @author drdan
 *
 */
public class PizzaMessageTest extends TestCase {

	@Test
	/**
	 * Test reconstituting a MenuRequestMessage
	 */
	public void testReconstituteMenuRequest() {
		ArrayList<String> representation = new ArrayList<String>();
		representation.add("1");
		representation.add("END");
		PizzaMessage msg = PizzaMessage.reconstitute(representation);
		assertTrue("reconstituted message is a Menu Request",msg instanceof MenuRequestMessage);
		
	}
	@Test
	/**
	 * Test reconstituting a MenuMessage
	 */
	public void testReconstituteMenu() {
		ArrayList<String> representation = new ArrayList<String>();
		representation.add("2");
		representation.add("Item type:slice, Menu id:1, description:Slice (thin crust), price:1.75");
		representation.add("Item type:slice, Menu id:2, description:Slice (thick crust), price:1.75");
		representation.add("Item type:slice, Menu id:3, description:Slice (Sicilian crust), price:2.25");
		representation.add("Item type:specialty pie, Menu id:4, small:12.50, medium:14.50, large:17.75, "+
                 "description:Tibetan delight with hallucenogenic mushrooms, yak butter, and turnips");
		representation.add("Item type:specialty pie, Menu id:5, small:15.50, medium:20.00, large:24.00, "+
                "description:Hawaiin luau pie with pineapple, pork meat, hibiscus flowers, and sea salt");
		representation.add(""); //empty line separating menu items from toppings
		representation.add("topping id:1, description:Extra Cheese, price:0.75");
		representation.add("topping id:2, description:Anchovies, price:1.25");
		representation.add("topping id:3, description:Pepperoni, price:1.00 ");
		representation.add("END");
		PizzaMessage msg = PizzaMessage.reconstitute(representation);
		assertTrue("reconstituted message is a Menu ",msg instanceof MenuMessage);
		MenuMessage menu = (MenuMessage) msg;
		ArrayList<MenuItem> menuItems = menu.getMenuItemList();
		assertEquals("number of menu items",5,menuItems.size());
		
		MenuItem item0 = menuItems.get(0);
		assertTrue("first menu item is a slice",item0 instanceof SliceMenuItem);
		SliceMenuItem slice0 = (SliceMenuItem) item0;
		assertEquals("first menu item id",1, slice0.getMenuItemId());
		assertEquals("first menu item description","Slice (thin crust)", slice0.getItemDescription());
		assertEquals("first menu item price",1.75, slice0.getBasePrice(),0.01);

		MenuItem item1 = menuItems.get(1);
		assertTrue("second menu item is a slice",item1 instanceof SliceMenuItem);
		SliceMenuItem slice1 = (SliceMenuItem) item1;
		assertEquals("second menu item id",2, slice1.getMenuItemId());
		assertEquals("second menu item description","Slice (thick crust)", slice1.getItemDescription());
		assertEquals("second menu item price",1.75, slice1.getBasePrice(),0.01);

		MenuItem item2 = menuItems.get(2);
		assertTrue("third menu item is a slice",item2 instanceof SliceMenuItem);
		SliceMenuItem slice2 = (SliceMenuItem) item2;
		assertEquals("third menu item id",3, slice2.getMenuItemId());
		assertEquals("third menu item description","Slice (Sicilian crust)", slice2.getItemDescription());
		assertEquals("third menu item price",2.25, slice2.getBasePrice(),0.01);

		MenuItem item3 = menuItems.get(3);
		assertTrue("fourth menu item is a specialty pie",item3 instanceof SpecialtyPizzaMenuItem);
		SpecialtyPizzaMenuItem pie1 = (SpecialtyPizzaMenuItem) item3;
		assertEquals("fourth menu item id",4, pie1.getMenuItemId());
		assertEquals("fourth menu item description",
				"Tibetan delight with hallucenogenic mushrooms, yak butter, and turnips", 
				pie1.getItemDescription());
		assertEquals("fourth menu item small price",12.50, pie1.getSmallPrice(),0.01);
		assertEquals("fourth menu item medium price",14.50, pie1.getMediumPrice(),0.01);
		assertEquals("fourth menu item large price",17.75, pie1.getLargePrice(),0.01);

		MenuItem item4 = menuItems.get(4);
		assertTrue("fifth menu item is a specialty pie",item4 instanceof SpecialtyPizzaMenuItem);
		SpecialtyPizzaMenuItem pie2 = (SpecialtyPizzaMenuItem) item4;
		assertEquals("fifth menu item id",5, pie2.getMenuItemId());
		assertEquals("fifth menu item description",
				"Hawaiin luau pie with pineapple, pork meat, hibiscus flowers, and sea salt", 
				pie2.getItemDescription());
		assertEquals("fifth menu item small price",15.50, pie2.getSmallPrice(),0.01);
		assertEquals("fifth menu item medium price",20.00, pie2.getMediumPrice(),0.01);
		assertEquals("fitth menu item large price",24.00, pie2.getLargePrice(),0.01);
		
		/*
		 * Check toppings
		 */
		ArrayList<Topping> toppings = menu.getToppingList();
		assertEquals("number of toppings",3,toppings.size());
		
		Topping topping0 = toppings.get(0);
		assertEquals("first topping id",1, topping0.getId());
		assertEquals("first topping description","Extra Cheese", topping0.getDescription());
		assertEquals("first topping price",0.75, topping0.getPrice(),0.01);

		Topping topping1 = toppings.get(1);
		assertEquals("second topping id",2, topping1.getId());
		assertEquals("second topping description","Anchovies", topping1.getDescription());
		assertEquals("second topping price",1.25, topping1.getPrice(),0.01);

		Topping topping2 = toppings.get(2);
		assertEquals("third topping id",3, topping2.getId());
		assertEquals("third topping description","Pepperoni", topping2.getDescription());
		assertEquals("third topping price",1.00, topping2.getPrice(),0.01);
	}
	
	@Test
	/**
	 * Test reconstituting an OrderMessage
	 */
	public void testReconstituteOrder() {
		ArrayList<String> representation = new ArrayList<String>();
		representation.add("3");
		representation.add("Item type:slice, Menu id:3, quantity:2, toppings:1 3");
		representation.add("Item type:slice, Menu id:4, quantity:1, toppings:");//no toppings
		representation.add("Item type:specialty pie, Menu id:1, size:2, quantity:1");
		representation.add("END"); 
		PizzaMessage msg = PizzaMessage.reconstitute(representation);
		assertTrue("message is an order",msg instanceof OrderMessage);
		OrderMessage order = (OrderMessage) msg;
		ArrayList<SliceOrderItem> slices = order.getSliceList();
		assertEquals("number of slices",2,slices.size());
		SliceOrderItem slice0 = slices.get(0);
		assertEquals("Slice 0 menu id",3,slice0.getItemId());
		assertEquals("Slice 0 quantity",2,slice0.getQuantity());
		ArrayList<Integer> toppingIDs = slice0.getToppingList();
		assertEquals("Slice 0 number of toppings",2,toppingIDs.size());
		assertEquals("Slice 0 first topping id",1,toppingIDs.get(0).intValue());
		assertEquals("Slice 0 second topping id",3,toppingIDs.get(1).intValue());
		SliceOrderItem slice1 = slices.get(1);
		assertEquals("Slice 1 menu id",4,slice1.getItemId());
		assertEquals("Slice 1 quantity",1,slice1.getQuantity());
		 toppingIDs = slice1.getToppingList();
		assertEquals("Slice 1 number of toppings",0,toppingIDs.size());
		ArrayList<SpecialtyPizzaOrderItem> specialtyPies = order.getSpecialtyPieList();
		assertEquals("number of specialty pies",1,specialtyPies.size());
		SpecialtyPizzaOrderItem pie0 = specialtyPies.get(0);
		assertEquals("Specialty pie menu id",1,pie0.getItemId());
		assertEquals("Specialty pie quantity",1,pie0.getQuantity());
		assertEquals("Specialty pie size",2,pie0.getPizzaSize());
	}//testReconstituteOrder
	
	@Test
	/**
	 * Test reconstituting a ConfirmationMessage
	 */
	public void testReconstituteConfirmation() {
		ArrayList<String> representation = new ArrayList<String>();
		representation.add("4");
		representation.add("Price:3.75, description:1 Slice (Thin Crust) Anchovies");
		representation.add("Price:7.50, description:2 Slice (stuffed) Anchovies Extra Cheese");
		representation.add("Price:31.00, description:2 Pizza Supremo SMALL");
		representation.add("");
		representation.add("Sales tax:1.09");
		representation.add("Order total amount:43.34");
		representation.add("END");
		
		PizzaMessage msg = PizzaMessage.reconstitute(representation);
		assertTrue("message is a confirmation",msg instanceof ConfirmationMessage);
		ConfirmationMessage confirmation = (ConfirmationMessage) msg;
		ArrayList<ConfirmationItem> items = confirmation.getItemList();
		
		assertEquals("number of items",3,items.size());
		ConfirmationItem item0 = items.get(0);
		assertEquals("item0 price",3.75,item0.getItemPrice(),0.01);
		assertEquals("item0 description","1 Slice (Thin Crust) Anchovies" ,item0.getItemDescription());
		ConfirmationItem item1 = items.get(1);
		assertEquals("item1 price",7.50,item1.getItemPrice(),0.01);
		assertEquals("item1 description","2 Slice (stuffed) Anchovies Extra Cheese" ,item1.getItemDescription());
		ConfirmationItem item2 = items.get(2);
		assertEquals("item2 price",31.00,item2.getItemPrice(),0.01);
		assertEquals("item2 description","2 Pizza Supremo SMALL" ,item2.getItemDescription());
		
		assertEquals("Salses tax",1.09,confirmation.getSalesTax(),0.01);
		assertEquals("Order total amount",43.34,confirmation.getTotalAmount(),0.01);
	}
}
