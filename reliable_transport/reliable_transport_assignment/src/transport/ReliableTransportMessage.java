package transport;

/**
 * Represents a transport-level message that will be delivered via a possibly unreliable transport service. Packet format is as follows (all fields should be human-readable): Bytes Content
 * 0-14 Source IP address, left-padded with blanks
 * 15-19 Source UDP port, 0-65535, left-padded with blanks
 * 20-34 Destination IP address, left -padded with blanks
 * 35-39 Destination UDP port, 0-65535, left-padded with blanks
 * 40-40 Operation code
 * 41-42 Sequence number, 0-99
 * 43-72 Payload, right-padded with blanks
 * 73-77 Checksum, left-padded with blanks
 * 
 * Opcodes are3 D=DATA, A=ACK, N=NAK, E=END.
 * The checksum is the sum of all the character codes of the first 73 chars.
 * @author vincentlee
 * @version 1.0
 * @since February 10, 2014
 */

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

public class ReliableTransportMessage {
	private static final boolean DEBUG = false;
	private static final boolean ERROR = false;
	
	public static final char DATA = 'D'; /** Operation code for a data message */
	public static final char ACK = 'A'; /** Operation code for a positive acknowledgment */
	public static final char NAK = 'N'; /** Operation code for a negative acknowledgment */
	public static final char END = 'E'; /** Operation code for end of transmission */
	public static final int HEADER_LEN = 43; /** Length of the header */
	public static final int PAYLOAD_LEN = 30; /** Maximum length of the text payload carried by this message. Shorter payloads will be right-padded with blanks */
	public static final int BUFFER_LEN = 78; /** Total length of this message */
	
	private InetAddress srcIP, destIP;
	private int srcPort, destPort, seqNo, checksum, storedChecksum;
	private char opCode;
	private String payload;
	private char[] message = new char[BUFFER_LEN];
	
	/**
	 * Load this message with all of its fields. Truncate 'payload' if it is longer than MAX_PAYLOAD_LEN; pad with blanks if it is shorter.
	 * @param srcIP Source IP
	 * @param destIP Destination IP
	 * @param srcPort Source Port Number
	 * @param destPort Destination Port Number
	 * @param opCode Operation Code
	 * @param seqNo Sequence Number
	 * @param payload Text Message
	 */
	public ReliableTransportMessage(InetAddress srcIP, InetAddress destIP, int srcPort, int destPort, char opCode, int seqNo, String payload) {
		this.srcIP = srcIP;
		this.destIP = destIP;
		this.srcPort = srcPort;
		this.destPort = destPort;
		this.opCode = opCode;
		this.seqNo = seqNo;
		this.payload = payload;
	}
	
	/**
	 * Encodes this ReliableProtocoMessage into its buffer
	 */
	public void encode() {
		System.arraycopy(leftPaddedIP(srcIP, 15), 0, message, 0, 15); //0-14 Source IP address, left-padded with blanks
		System.arraycopy(leftPaddedInt(srcPort, 5), 0, message, 15, 5); //15-19 Source UDP port, 0-65535, left-padded with blanks
		System.arraycopy(leftPaddedIP(destIP, 15), 0, message, 20, 15); //20-34 Destination IP address, left -padded with blanks
		System.arraycopy(leftPaddedInt(destPort, 5), 0, message, 35, 5); //35-39 Destination UDP port, 0-65535, left-padded with blanks
		message[40] = getOpCode(); //40-40 Operation code
		System.arraycopy(leftPaddedInt(getSequenceNo(), 2), 0, message, 41, 2); //41-42 Sequence number, 0-99
		System.arraycopy(rightPaddedString(getPayload()), 0, message, 43, 30); //43-72 Payload, right-padded with blanks
		
		for (int i = 0; i < HEADER_LEN+PAYLOAD_LEN; i++)
			//adds 1 value to the checksum
			checksum += message[i];
		if (DEBUG) System.out.println("Checksum: " + checksum);
		
		System.arraycopy(leftPaddedInt(getComputedChecksum(), 5), 0, message, 73, 5); //73-77 Checksum, left-padded with blanks
		
		if (DEBUG) System.out.println(":" + message.length + ":" + Arrays.toString(message));
	}
	
	/**
	 * Returns the String representation of 'value', left-padded with blanks to a total size of 'width' characters.
	 * @param value a non-negative integer to be encoded
	 * @param width width of the resulting char array
	 * @return
	 * @throws IllegalArgumentException on overflow ('value' cannot be represented in 'width' chars)
	 */
	protected static char[] leftPaddedInt(int value, int width) throws IllegalArgumentException {
		//detect negative numbers
		if(value < 0)
			throw new IllegalArgumentException();
		
		//value to character array
		char[] valueArray = String.valueOf(value).toCharArray();
		
		//detect overflow
		if(valueArray.length > width)
			throw new IllegalArgumentException();
		
		//result buffer
		char[] result = new char[width];
		
		//fill empty with ' '
		Arrays.fill(result, 0, width-valueArray.length, ' ');
		
		//copy valueArray to end of result array
		System.arraycopy(valueArray, 0, result, width-valueArray.length, valueArray.length);
		
		if (DEBUG) System.out.println(Arrays.toString(result));
		
		return result;
	}
	
	/**
	 * Returns the String representation of 'value', left-padded with blanks to a total size of 'width' characters.
	 * @param value an IP address
	 * @param width width of the resulting char array
	 * @return
	 */
	public static char[] leftPaddedIP(InetAddress value, int width) {
		//value to character array
		char[] valueArray = String.valueOf(value.getHostAddress()).toCharArray();
		
		//result buffer
		char[] result = new char[width];
		
		//fill empty with ' '
		Arrays.fill(result, 0, width-valueArray.length, ' ');
		
		//copy valueArray to end of result array
		System.arraycopy(valueArray, 0, result, width-valueArray.length, valueArray.length);
		
		if (DEBUG) System.out.println(Arrays.toString(result));
		
		return result;
	}
	
	/**
	 * Truncate 'payload' if it is longer than PAYLOAD_LEN; pad with blanks if it is shorter.
	 * @param payload
	 * @return
	 */
	protected static char[] rightPaddedString(String payload) {
		//result buffer
		char[] result = new char[PAYLOAD_LEN];
		
		//payload to character array
		char[] payloadArray = String.valueOf(payload).toCharArray();
		
		//fill empty with ' '
		Arrays.fill(result, ' ');
		
		//copy payloadArray to end of result array
		System.arraycopy(payloadArray, 0, result, 0, (payloadArray.length > PAYLOAD_LEN) ? PAYLOAD_LEN : payloadArray.length);
		
		if (DEBUG) System.out.println(Arrays.toString(result));
		
		//truncate warning
		if (payload.length() > result.length)
			if (ERROR) System.err.println("truncated: " + payload + " -> " + new String(result));
		
		return result;
	}
	
	/**
	 * Returns a message reconstituted from 'buffer', or null if any error occurs
	 * @param buffer
	 * @return
	 */
	public static ReliableTransportMessage reconstitute(byte[] buffer) {
		try {
			String str = new String(buffer);
			
			InetAddress sourceIP = InetAddress.getByName(str.substring(0, 15).trim()); //0-14 Source IP address, left-padded with blanks
			InetAddress destinationIP = InetAddress.getByName(str.substring(20, 35).trim()); //20-34 Destination IP address, left -padded with blanks
			int sourcePort = Integer.parseInt(str.substring(15, 20).trim()); //15-19 Source UDP port, 0-65535, left-padded with blanks
			int destinationPort = Integer.parseInt(str.substring(35, 40).trim()); //35-39 Destination UDP port, 0-65535, left-padded with blanks
			char operation = str.charAt(40); //40-40 Operation code
			int sequenceNO = Integer.parseInt(str.substring(41, 43).trim()); //41-42 Sequence number, 0-99
			String payload = str.substring(43, 73); //43-72 Payload, right-padded with blanks
			
			//check for non-negative numbers & invalid port numbers
			if (sourcePort < 0 || sourcePort > 65535 || destinationPort < 0 || destinationPort > 65535 || sequenceNO < 0)
				throw new NumberFormatException();
			
			//invalid operation code
			if (operation != DATA && operation != ACK && operation != NAK && operation != END)
				throw new IllegalArgumentException();
			
			ReliableTransportMessage rtm = new ReliableTransportMessage(sourceIP, destinationIP, sourcePort, destinationPort, operation, sequenceNO, payload);
			
			//stored checksum
			rtm.storedChecksum = Integer.parseInt(str.substring(73, 78).trim()); //73-77 Checksum, left-padded with blanks
			
			for (int i = 0; i < HEADER_LEN+PAYLOAD_LEN; i++)
				//adds 1 value to the checksum
				rtm.checksum += str.charAt(i);
			if (DEBUG) System.out.println("Checksum: " + rtm.checksum);
			
			//if checksums don't match return null
			if (rtm.storedChecksum != rtm.checksum) {
				if (ERROR) System.err.println("reconstitute error: checksum");
				return null;
			}
			
			return rtm;
		} catch (UnknownHostException uhe) {
			if (ERROR) System.err.println("reconstitute error: IP");
			return null;
		} catch(NumberFormatException nfe) {
			if (ERROR) System.err.println("reconstitute error: number");
			return null;
		} catch(IllegalArgumentException iae) {
			if (ERROR) System.err.println("reconstitute error: character");
			return null;
		} catch (Exception e) {
			if (ERROR) System.err.println("reconstitute error");
			return null;
		}
	}
	
	/** Returns the entire contents of this message as a byte array. */
	public byte[] getBuffer() {return new String(message).getBytes();}
	
	/** Returns the checksum stored in the trailer of the message. */
	protected int getStoredChecksum() {return storedChecksum;}
	
	/** Returns the sum of the first HEADER_LEN+PAYLOAD_LEN chars of buffer */
	protected int getComputedChecksum() {return checksum;}
	
	/** Returns the int value of the index-th byte of this message */
	protected int getChecksumTerm(int index) {return message[index];}
	
	/** Return the op code. 1=DATA, 2=ACK, 3=NAK, 4=END */
	public char getOpCode() {return opCode;}
	
	/** Return the sequence number. */
	public int getSequenceNo() {return seqNo;}
	
	/** Returns the source IP address */
	public InetAddress getSourceIP() {return srcIP;}
	
	/** Returns the destination IP address */
	public InetAddress getDestIP() {return destIP;}
	
	/** Returns the source port number */
	public int getSrcPort() {return srcPort;}
	
	/** Returns the destination port number */
	public int getDestPort() {return destPort;}
	
	/** Returns the payload of this message */
	public String getPayload() {return payload;}
	
	/** Replaces the initial part of the payload with a new payload, without changing the checksum */
	public void setPayload(String newPayload) {this.payload = newPayload;}
	
	/**
	 * Create and print out a Message
	 * @param args
	 * @throws UnknownHostException
	 */
	public static void main(String[] args) throws UnknownHostException {
		InetAddress srcIP = InetAddress.getByName("localhost");
		InetAddress destIP = InetAddress.getByName("localhost");
		int srcPort = 2015;
		int destPort = 2017;
		char opCode = DATA;
		int seqNo = 0;
		String payload = "Hello World Test Message";
		
		ReliableTransportMessage rtm = new ReliableTransportMessage(srcIP, destIP, srcPort, destPort, opCode, seqNo, payload);
		rtm.encode();
		
		System.out.print("\"");
		for (int i = 0; i < rtm.message.length; i++)
			//print one character of message
			System.out.print(rtm.message[i]);
		System.out.println("\"");
		System.out.println();
		
		//message parts
		System.out.println("--------Mesage Components--------");
		System.out.println("source IP: \t\t" + rtm.getSourceIP().getHostAddress());
		System.out.println("destination IP: \t" + rtm.getDestIP().getHostAddress());
		System.out.println("source port: \t\t" + rtm.getSrcPort());
		System.out.println("destination port: \t" + rtm.getDestPort());
		System.out.println("operation code: \t" + rtm.getOpCode());
		System.out.println("sequence number: \t" + rtm.getSequenceNo());
		System.out.println("payload: \t\t" + rtm.getPayload());
		System.out.println("checksum: \t\t" + rtm.getComputedChecksum());
	}
}
