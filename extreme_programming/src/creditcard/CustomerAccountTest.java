package creditcard;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerAccountTest {
	/**
	 * Verifies that a newly created CustomerAccount contains the supplied initial data.
	 */
	@Test
	public void testConstructor() {
		CustomerAccount instance1 = new CustomerAccount(1, "Gordon Gecko", "1 Wall Street, NYC", 10000000.0);
		assertEquals("Instance 1 account number", 1, instance1.getAccountNo());
		assertEquals("Instance 1 customer name", "Gordon Gecko", instance1.getCustName());
		assertEquals("Instance 1 customer address", "1 Wall Street, NYC", instance1.getCustAddress());
		assertEquals("Instance 1 unpaid balance", 0.0, instance1.getUnpaidBalance(),0.001);
		assertEquals("Instance 1 credit limit", 10000000.0, instance1.getCreditLimit(),0.001);
		assertEquals("Instance 1 image URL", "generic.png", instance1.getImageUrl());
		
		CustomerAccount instance2 = new CustomerAccount(2, "Dan Everett", "ComputerScience Department, UGA", 50.0);
		assertEquals("Instance 2 account number", 2, instance2.getAccountNo());
		assertEquals("Instance 2 customer name", "Dan Everett", instance2.getCustName());
		assertEquals("Instance 2 customer address", "ComputerScience Department, UGA", instance2.getCustAddress());
		assertEquals("Instance 2 unpaid balance", 0.0, instance2.getUnpaidBalance(),0.001);
		assertEquals("Instance 2 credit limit", 50.0, instance2.getCreditLimit(),0.001);
		assertEquals("Instance 2 image URL", "generic.png", instance2.getImageUrl());
		
		CustomerAccount instance3 = new CustomerAccount(3, "Fred Flintstone", "Bedrock");
		assertEquals("Instance 3 account number", 3, instance3.getAccountNo());
		assertEquals("Instance 3 customer name", "Fred Flintstone", instance3.getCustName());
		assertEquals("Instance 3 customer address", "Bedrock", instance3.getCustAddress());
		assertEquals("Instance 3 unpaid balance", 0.0, instance3.getUnpaidBalance(),0.001);
		assertEquals("Instance 3 credit limit", 1000.0, instance3.getCreditLimit(),0.001);
		assertEquals("Instance 3 image URL", "generic.png", instance3.getImageUrl());
	}
}
