package transport;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;

import static org.junit.Assert.*;
import junit.framework.TestCase;

/**
 * Tests encoding and decoding operations for the ReliableTransport message class
 * @author drdan
 *
 */
public class ReliableTransportMessageTest extends TestCase {

	@Test
	/**
	 * Test left-padded integer representation
	 */
	public void testPaddedInt() {
		char[] expected1 = new String("  345").toCharArray();
		assertArrayEquals("encoding of 345",expected1,ReliableTransportMessage.leftPaddedInt(345, 5));
		
		try {
			ReliableTransportMessage.leftPaddedInt(-22, 5);
			fail("Should throw IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {}//OK, it is supposed to throw this Exception
		
		try {
			ReliableTransportMessage.leftPaddedInt(345, 2);
			fail("Should throw IllegalArgumentException");
		}
		catch (IllegalArgumentException e) {}//OK, it is supposed to throw this Exception
		
	}
	/**
	 * Test fields of the constructed object
	 * @throws UnknownHostException 
	 */
	public void testConstructor() throws UnknownHostException{
		InetAddress srcIP = InetAddress.getByName("172.17.152.122");
		InetAddress destIP = InetAddress.getByName("172.17.152.124");
		ReliableTransportMessage msg = new ReliableTransportMessage(
				srcIP,destIP,2200,2015,ReliableTransportMessage.DATA,1,"Hi mom!");
		assertEquals("source port: ",2200,msg.getSrcPort());
		assertEquals("dest port: ",2015,msg.getDestPort());
		assertEquals("op code",'D',msg.getOpCode());
		assertEquals("sequence no",1,msg.getSequenceNo());
		assertEquals("payload","Hi mom!",msg.getPayload());
	}
	/**
	 * Test ip encodings
	 * @throws UnknownHostException 
	 */
	public void testIPencoding() throws UnknownHostException{
		InetAddress srcIP = InetAddress.getByName("172.17.152.122");
		InetAddress destIP = InetAddress.getByName("172.17.152.124");
		assertEquals("src ip encoding"," 172.17.152.122",new String(ReliableTransportMessage.leftPaddedIP(srcIP, 15)));
		assertEquals("dest ip encoding"," 172.17.152.124",new String(ReliableTransportMessage.leftPaddedIP(destIP, 15)));	
	}
	/**
	 * Test text padding
	 */
	public void testTextPadding(){
		String payload= "Your mama wears cowboy boots";
		assertEquals("payload length",28,payload.length());
		char[] encoding = ReliableTransportMessage.rightPaddedString(payload);
		for (int i=0;i<28;i++)
			//check i-th char
			assertEquals("char "+i,payload.charAt(i),encoding[i]);
		assertEquals("char 28",' ',encoding[28]);
		assertEquals("char 29",' ',encoding[29]);
	}
	@Test
	/**
	 * Test entire encoding
	 */
	public void testEncoding() throws UnknownHostException {
	   String expectedHeader = " 172.17.152.12253200  172.17.152.17 2016D 1";
	   String payload        = "Hi Mom!";
	   String padding        =  "                       ";
	   String checksumStr    =  " 3352";
	   assertEquals("Header length",ReliableTransportMessage.HEADER_LEN, expectedHeader.length());
	   assertEquals("padding length",23, padding.length());
	   InetAddress srcIP = InetAddress.getByName("172.17.152.122");
	   InetAddress destIP = InetAddress.getByName("172.17.152.17");
	   ReliableTransportMessage msg = new ReliableTransportMessage(srcIP,destIP,53200,2016,
			   ReliableTransportMessage.DATA,1, payload);
	   assertEquals("source IP","/172.17.152.122",msg.getSourceIP().toString());
	   assertEquals("dest IP","/172.17.152.17",msg.getDestIP().toString());
	   msg.encode();
	   String expectedEncoding = expectedHeader+payload+padding+checksumStr;  
           assertEquals("Expected Encoding length",78,expectedEncoding.length());
           assertEquals("Checksum input length",73,ReliableTransportMessage.HEADER_LEN+ReliableTransportMessage.PAYLOAD_LEN);
           for (int i=0;i<73;i++)
             //check i-th term of checksum
             assertEquals("Checksum term "+i,(int)expectedEncoding.charAt(i),
             msg.getChecksumTerm(i));
	   String encodingStr = new String(msg.getBuffer());
           assertEquals("encoded length",78,encodingStr.length());
           assertEquals("computed checksum",3352,msg.getComputedChecksum());
	   assertEquals("encoding",expectedEncoding,encodingStr);  
	}
	/**
	 * Test reconstituting a message
	 */
	public void testReconstituteMessage(){
		String encoding1= 
		" 172.17.152.12253200  172.17.152.17 2017D 1TCP, the Queen of Protocols!   4587";
		char[] encodingChars = encoding1.toCharArray();
		byte[] encodingBytes = new byte[ encodingChars.length];
		for (int i=0;i<encodingChars.length;i++)
			//convert i-th char to byte
			encodingBytes[i] = (byte) encodingChars[i];
		assertEquals("encoding1 length",ReliableTransportMessage.BUFFER_LEN, encoding1.length());
		ReliableTransportMessage msg1 = ReliableTransportMessage.reconstitute(encodingBytes);
		assertNotNull("reconstituted message 1 is NULL", msg1);
		assertEquals("Source IP","/172.17.152.122",msg1.getSourceIP().toString());
		assertEquals("Dest IP","/172.17.152.17",msg1.getDestIP().toString());
		assertEquals("Source port",53200,msg1.getSrcPort());
		assertEquals("Dest port",2017,msg1.getDestPort());
		assertEquals("Op code",ReliableTransportMessage.DATA,msg1.getOpCode());
		assertEquals("seq no",1,msg1.getSequenceNo());
                assertEquals("Payload length",ReliableTransportMessage.PAYLOAD_LEN, msg1.getPayload().length());
		assertEquals("Payload","TCP, the Queen of Protocols!  ",msg1.getPayload());
		assertEquals("Computed checksum",4587,msg1.getComputedChecksum());
		assertEquals("Stored checksum",4587,msg1.getStoredChecksum());
		//encoding with garbled contents
		String encoding2= 
				" 172.17.152.12253200  172.17.152.17 2017D 1Drink Coca-cola!               4568";
		assertEquals("encoding2 length",ReliableTransportMessage.BUFFER_LEN, encoding2.length());
		encodingChars = encoding2.toCharArray();
		byte[] encodingBytes2 = new byte[ encodingChars.length];
		for (int i=0;i<encodingChars.length;i++)
			//convert i-th char to byte
			encodingBytes2[i] = (byte) encodingChars[i];
		ReliableTransportMessage msg2 = ReliableTransportMessage.reconstitute(encodingBytes2);
		assertNull("reconstituted message 2 is not NULL", msg2);
		
	}
}
