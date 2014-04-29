package pizza.endpoint;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import pizza.message.*;


/**
 * Verifies the client against Dr. Dan's server on VM122
 */
public class PizzaClientTest {
	@Test
	/**
	 * Retrieves a menu from dr. dan's server and verifies its contents
	 */
	public void testGetMenu(){
		PizzaClient instance = null;
		try {
//	    instance = new PizzaClient("172.17.152.122");
//	    instance = new PizzaClient("172.17.152.28");
		instance = new PizzaClient("localhost");
		}
		catch (Exception e){
    		fail(e.getClass().getName() +" Connecting to server\n"+e.getMessage());
    	}
		MenuMessage menu = instance.getMenu();
		assertNotNull("Menu is not null", menu);
		ArrayList<MenuItem> menuItems = menu.getMenuItemList();
		assertEquals("Number of menu items",6,menuItems.size());
		
		//Veggie pizza
		SpecialtyPizzaMenuItem item0 = (SpecialtyPizzaMenuItem) menuItems.get(0);
		assertEquals("item 0 id",1,item0.getMenuItemId());
		assertEquals("item 0 description","Veggie Lovers' pizza with green peppers, black olives, onions, mushrooms, and mozzarella cheese",item0.getDescription());
		assertEquals("Veggie small price", 12.50, item0.getSmallPrice(),0.001);
		assertEquals("Veggie medium price", 15.50, item0.getMediumPrice(),0.001);
		assertEquals("Veggie large price", 18.50, item0.getLargePrice(),0.001);
		
		//Buffalo chicken pizza
		SpecialtyPizzaMenuItem item1 = (SpecialtyPizzaMenuItem) menuItems.get(1);
		assertEquals("item 1 id",2,item1.getMenuItemId());
		assertEquals("item 1 description","Buffalo Chicken pizza with onions, mushrooms, and mozzarella cheese",item1.getDescription());
		assertEquals("Buffalo chicken small price", 13.50, item1.getSmallPrice(),0.001);
		assertEquals("Buffalo chicken medium price", 16.50, item1.getMediumPrice(),0.001);
		assertEquals("Buffalo chicken large price", 19.50, item1.getLargePrice(),0.001);

		//Pizza Supremo!
		SpecialtyPizzaMenuItem item2 = (SpecialtyPizzaMenuItem) menuItems.get(2);
		assertEquals("item 2 id",3,item2.getMenuItemId());
		assertEquals("item 2 description","Pizza Supreme -- this pie has it all! Beef and chorizo sausage, yak meat, tofu, green peppers, olives, and mozzarella cheese!",item2.getDescription());
		assertEquals("Pizza Supreme small price", 15.50, item2.getSmallPrice(),0.001);
		assertEquals("Pizza Supreme  medium price", 20.50, item2.getMediumPrice(),0.001);
		assertEquals("Pizza Supreme large price", 25.50, item2.getLargePrice(),0.001);

		//Thin crust slice
		SliceMenuItem item3 = (SliceMenuItem) menuItems.get(3);
		assertEquals("item 3 id",4,item3.getMenuItemId());
		assertEquals("item 3 description","Slice (Thin crust)",item3.getItemDescription());
		assertEquals("Thin crust slice base price", 2.50, item3.getBasePrice(),0.001);
		
		//Thick crust slice
		SliceMenuItem item4 = (SliceMenuItem) menuItems.get(4);
		assertEquals("item 4 id",5,item4.getMenuItemId());
		assertEquals("item 4 description","Slice (Thick crust)",item4.getItemDescription());
		assertEquals("Thick crust slice base price", 2.50, item4.getBasePrice(),0.001);
		
		//Stuffed crust slice
		SliceMenuItem item5 = (SliceMenuItem) menuItems.get(5);
		assertEquals("item 5 id",6,item5.getMenuItemId());
		assertEquals("item 5 description","Slice (Stuffed crust)",item5.getItemDescription());
		assertEquals("Stuffed crust slice base price", 3.50, item5.getBasePrice(),0.001);
				
		ArrayList<Topping> toppings = menu.getToppingList();
		assertEquals("Number of toppings",6,toppings.size());
		
		//Pineapple topping
		Topping topping0 = toppings.get(0);
		assertEquals("topping 0 description","Pineapple",topping0.getDescription());
		assertEquals("topping 0 price",0.75,topping0.getPrice(),0.001);
		
		//Pepperoni topping
		Topping topping1 = toppings.get(1);
		assertEquals("topping 1 description","Pepperoni",topping1.getDescription());
		assertEquals("topping 1 price",1.25,topping1.getPrice(),0.001);
		
		//Anchovies topping
		Topping topping2 = toppings.get(2);
		assertEquals("topping 2 description","Anchovies",topping2.getDescription());
		assertEquals("topping 2 price",1.25,topping2.getPrice(),0.001);
		
		//Bacon topping
		Topping topping3 = toppings.get(3);
		assertEquals("topping 3 description","Bacon",topping3.getDescription());
		assertEquals("topping 3 price",1.00,topping3.getPrice(),0.001);
		
		//Onion topping
		Topping topping4 = toppings.get(4);
		assertEquals("topping 4 description","Onion",topping4.getDescription());
		assertEquals("topping 4 price",0.75,topping4.getPrice(),0.001);
		
		//Extra cheese topping
		Topping topping5 = toppings.get(5);
		assertEquals("topping 5 description","Extra Cheese",topping5.getDescription());
		assertEquals("topping 5 price",0.75,topping5.getPrice(),0.001);
	}
	
	@Test
	/**
	 * Verifies the confirmation message when two Buffalo Chicken pizzas are ordered
	 */
    public void testSingleSpecialtyPieOrder(){
    	ConfirmationMessage response = null;
    	try {
//	    	PizzaClient client = new PizzaClient("172.17.152.122");
//	    	PizzaClient client = new PizzaClient("172.17.152.28");
    		PizzaClient client = new PizzaClient("localhost");
			OrderMessage order = new OrderMessage();
			order.addSpecialtyPie(2, 2, SpecialtyPizzaOrderItem.MEDIUM);
		    response = client.sendOrder(order);
    	}
    	catch (Exception e){
    		fail(e.getClass().getName() +" Sending order message\n"+e.getMessage());
    	}
    	try{
			assertNotNull("Confirmation message",response);
			ArrayList<ConfirmationItem> items = response.getItemList();
			assertEquals("Number of confirmation items",1,items.size());
			ConfirmationItem item0 = items.get(0);
			assertEquals("Item short description","2 Buffalo Chicken MEDIUM",item0.getItemDescription());
			assertEquals("Item price",33.0,item0.getItemPrice(),0.001);
			assertEquals("Sales tax",2.31,response.getSalesTax(),0.001);
			assertEquals("Total amount",35.31,response.getTotalAmount(),0.001);
    	}//try
    	catch (Exception e){
    		fail(e.getClass().getName() +" Reading confirmation message\n"+e.getMessage());
    	}
	}
    
    @Test
	/**
	 * Verifies the confirmation message when a slice with anchovies is ordered
	 */
    public void testSliceOrder(){
		try {
			PizzaClient instance = new PizzaClient("localhost");
			
			OrderMessage order = new OrderMessage();
			SliceOrderItem slice = new SliceOrderItem(4,1);
			slice.addTopping(3);
			
			order.addSlice(slice);
			ConfirmationMessage response = instance.sendOrder(order);
			assertNotNull("Confirmation message",response);
			ArrayList<ConfirmationItem> items = response.getItemList();
			assertEquals("Number of confirmation items",1,items.size());
			ConfirmationItem item0 = items.get(0);
			assertEquals("Item short description","1 Slice (Thin crust) Anchovies",item0.getItemDescription());
			assertEquals("Item price",3.75,item0.getItemPrice(),0.001);
			assertEquals("Sales tax",0.2625,response.getSalesTax(),0.001);
			assertEquals("Total amount",4.01,response.getTotalAmount(),0.01);
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
    
    @Test
    /**
	 * Verifies the confirmation message when one pizza supreme and two stuffed slices with 
	 * onions and extra cheese are ordered
	 */
    public void testCombinedOrder(){
    	try {
    		PizzaClient instance = new PizzaClient("localhost");
    		OrderMessage order = new OrderMessage();
    		order.addSpecialtyPie(3, 1, SpecialtyPizzaOrderItem.LARGE);
    		//now add the slice
    		SliceOrderItem slice = new SliceOrderItem(6,2);//Stuffed slice
    		slice.addTopping(5);//Onion
    		slice.addTopping(6);//Extra cheese
    		order.addSlice(slice);
    		ConfirmationMessage response = instance.sendOrder(order);
    		assertNotNull("Confirmation message",response);
    		ArrayList<ConfirmationItem> items = response.getItemList();
    		assertEquals("Number of confirmation items",2,items.size());
    		ConfirmationItem item0 = items.get(0);
    		assertEquals("Item 0 short description","1 Pizza Supreme LARGE",item0.getItemDescription());
    		assertEquals("Item 0 price",25.50,item0.getItemPrice(),0.001);
    		
    		ConfirmationItem item1 = items.get(1);
    		assertEquals("Item 1 short description","2 Slice (Stuffed crust) Onion Extra Cheese",item1.getItemDescription());
    		assertEquals("Item price",10,item1.getItemPrice(),0.001);
    		
    		assertEquals("Sales tax",2.485,response.getSalesTax(),0.001);
    		assertEquals("Total amount",37.985,response.getTotalAmount(),0.01);	
    	} catch(Exception e) {
    		System.out.println(e.getMessage());
    	}
	}
}
