package message;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Represents  a message sent to a receiver via a relay host, 
 * encapsulated in a UDP datagram.	Encoded as <br><br>
 * &lt;dest IP address&gt; | &lt;dest UDP port &gt;  &lt;payload&gt;
 *
 */
public class PracticeMessage {
	/** The one-character string used to separate the fields of the message */
	public static final String SEPARATOR = "|";
	private int destPort;
	private String payload;
	private InetAddress destAddress;
	
	/**
	 * Creates a message with specified destination IP address,
	 * port, and message payload.
	 */
	public PracticeMessage(String ipAddr, int port, String payload) throws UnknownHostException {
		this.destAddress = InetAddress.getByName(ipAddr);
		this.destPort = port;
		this.payload = payload;
	}
	
	/**
	 * Returns the destination IP address of the eventual receiver
	 */
	public InetAddress getDestAddress() {
		return destAddress;
	}
	
    /**
      * Returns the destination UDP port  of the eventual receiver
      */
	public int getDestPort() {
		return destPort;
	}
	/**
	 * Returns the text content of this message
	 * @return
	 */
	public String getPayload() {
		return payload;
	}

	/**
	 * Returns a representation "dest-IP|dest-Port|payload"
	 */
	public String encoding() {
		return destAddress.getHostAddress() + SEPARATOR + destPort + SEPARATOR + payload;
	}
	
	/**
	 * Constructs a message from the given encoding
	 */
	public static PracticeMessage reconstitute(String encoding) {
		List<String> tokens = new ArrayList<String>();
		Scanner tokenize = new Scanner(encoding);
		tokenize.useDelimiter("\\|");
		while (tokenize.hasNext())
		    tokens.add(tokenize.next());
		System.out.println(tokens);
		
		try {
			return new PracticeMessage(tokens.get(0), Integer.parseInt(tokens.get(1)), tokens.get(2));
		} catch (NumberFormatException | UnknownHostException e) {
			return null;
		}
	}
}