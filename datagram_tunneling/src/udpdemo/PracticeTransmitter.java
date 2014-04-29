package udpdemo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

import message.PracticeMessage;

/**
 * Transmits a message to a PracticeReceiver, relayed through a
 * reliable relay station. The message will be encapsulated in a
 * message.PracticeMessage object, which will be further 
 * encapsulated in a DatagramPacket.
 */
public class PracticeTransmitter {
	
	//IP and transport address of the relay service
	static int RELAY_PORT = 2025;
	static String RELAY_HOST ="172.17.152.122";
	InetAddress  relayIP;

	//Socket for sending a packet to the relay host
	DatagramSocket sendSocket;
	
	/**
	 * Stores relay IP address and stablishes a connection to the relay host
	 */
	public PracticeTransmitter() {
		try {
			sendSocket = new DatagramSocket();
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Encapsulates the message into a PracticeMessage object
	 */
	public PracticeMessage prepareMessage(String messagePayload, int destPort, String destIP){
		try {
			return new PracticeMessage(destIP, destPort, messagePayload);
		} catch (UnknownHostException e) {
			return null;
		}
	}
	/**
	 * Encapsulates the PracticeMessage object into a UDP datagram
	 */
	public DatagramPacket prepareDatagram(PracticeMessage message) {
		Charset charset = Charset.forName("UTF-8");
		CharBuffer charBuffer = CharBuffer.wrap(message.encoding());
		ByteBuffer byteBuffer = charset.encode(charBuffer);
		
		byte[] byteArray = new byte[byteBuffer.remaining()];
		byteBuffer.get(byteArray);
		
		try {
			return new DatagramPacket(byteArray, byteArray.length, InetAddress.getByName(RELAY_HOST), RELAY_PORT);
		} catch (UnknownHostException e) {
			return null;
		}
	}
	/**
	 * Transmits 'message' to port 'destinationPort' on
	 * host 'destinationIP.'
	 * @param args message, destination port, and destination IP
	 */
	public static void main(String[] args) {
		if (args.length != 3){
			System.out.println("Usage: PracticeTransmitter <message> <dest port no> <dest IP address>");
		}
			
        //TODO: compose, encapsulate, and send the message
		//TODO: handle UnknownHostException by printing a polite error message
	}

}
