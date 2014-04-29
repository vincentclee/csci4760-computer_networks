package pizza.endpoint;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PizzaClientTest.class, PizzaServerTest.class })
public class AllTests {}
