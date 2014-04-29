package message;

import java.net.UnknownHostException;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Test encoding and decoding
 * @author drdan
 *
 */
public class PracticeMessageTest extends TestCase {

	@Test
	/**
	 * Test encoding a valid message.
	 */
	public void testGoodEncoding(){
	  try {
		PracticeMessage instance = new PracticeMessage("172.17.152.37", 2025,"UDP rocks!");
		assertEquals("encoding","172.17.152.37|2025|UDP rocks!",instance.encoding());
	  }catch (UnknownHostException e){
		  fail("Can't recognize host 172.17.12.37 -- are  you inside the UGA network?");
	  }
	}
	@Test
	/**
	 * Test encoding a message with an invalid IP address.
	 */
	public void testBadEncoding(){
	  try {
		PracticeMessage instance = new PracticeMessage("172.17.152.300", 2025,"UDP rocks!");
		fail("Should throw UnknownHostException");
	  }catch (UnknownHostException e){
		  
	  }
	}
	  @Test
      /**
       * Verify that  a message reconstituted from its encoding is non-null and has
       * the correct contents.
       */
	  public void testReconstitute(){
		  PracticeMessage instance = PracticeMessage.reconstitute("172.17.152.100|2025|UDP rocks!");
		  assertEquals("dest ip address", "/172.17.152.100", instance.getDestAddress().toString());
		  assertEquals("dest port", 2025, instance.getDestPort());
		  assertEquals("payload", "UDP rocks!", instance.getPayload());
	  }
}
