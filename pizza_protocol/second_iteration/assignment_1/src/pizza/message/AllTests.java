package pizza.message;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ConfirmationItemTest.class, ConfirmationMessageTest.class,
		MenuRequestMessageTest.class, PizzaMessageTest.class,
		SliceOrderItemTest.class, SpecialtyPizzaMenuItemTest.class,
		ToppingTest.class })
public class AllTests {}
