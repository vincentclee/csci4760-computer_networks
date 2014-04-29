package udpdemo;

import static org.junit.Assert.*;

import java.net.DatagramPacket;
import java.net.UnknownHostException;

import message.PracticeMessage;

import org.junit.Test;
/**
 * Verifies the encapsulation phases of PracticeTransmitter,
 * but not its actual ability to transmit messages
 *
 */
public class PracticeTransmitterTest {
    @Test
	/**
	 * Test loading relay IP and port addresses and connecting to  the relay host
	 */
	public void testConstructor() {
    	PracticeTransmitter instance = new PracticeTransmitter();
	}
    @Test
	/**
	 * Test encoding a message text into a PracticeMessage object
	 */
	public void testEncapsulateText() {
    	PracticeMessage instance = new PracticeTransmitter().prepareMessage("UDP rocks!", 2025, "172.17.152.37");
    	assertEquals("encoding","172.17.152.37|2025|UDP rocks!",instance.encoding());
	}
    @Test
   	/**
   	 * Test encapsulating a PracticeMessage object into a UDP datagram
   	 */
   	public void testEncapsulateMessage() {
    	try {
			DatagramPacket packet = new PracticeTransmitter().prepareDatagram(new PracticeMessage("172.17.152.37", 2025, "UDP rocks!"));
			assertEquals("ip address", PracticeTransmitter.RELAY_HOST, packet.getAddress().getHostAddress());
			assertEquals("port number", PracticeTransmitter.RELAY_PORT, packet.getPort());
			assertEquals("data length", packet.getLength(), packet.getData().length);
			
			String str = new String(packet.getData(), "UTF-8");
			PracticeMessage instance = PracticeMessage.reconstitute(str);
			assertEquals("dest ip address", "/172.17.152.37", instance.getDestAddress().toString());
			assertEquals("dest port", 2025, instance.getDestPort());
			assertEquals("payload", "UDP rocks!", instance.getPayload());
		} catch (Exception e) {
			fail("Practice Message error");
		}
   	}
}
