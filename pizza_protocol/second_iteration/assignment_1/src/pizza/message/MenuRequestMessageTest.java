package pizza.message;


import java.util.ArrayList;

import org.junit.Test;

import junit.framework.TestCase;
/**
 * Verify encoding of MenuRequestMessage
 * @author drdan
 *
 */
public class MenuRequestMessageTest extends TestCase {

	@Test
	/**
	 * Verify encoding
	 */
	public void testEncoding() {
		MenuRequestMessage message = new MenuRequestMessage();
		ArrayList<String> encoding = message.encoding();
		assertEquals("encoding number of lines",2,encoding.size());
		assertEquals("encoding first line","1",encoding.get(0));
		assertEquals("encoding second line","END", encoding.get(1));
	}

}
