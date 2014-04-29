package transport;

/**
 * Transmits a file via a Relayer to a remote ReliableReceiver, using a stop-and-wait protocol with error checking.
 * @author vincentlee
 * @version 1.0
 * @since February 18, 2014
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ReliableSender {
	private static final boolean DEBUG = false;
	
	public static final int ACK_RECEIVE_PORT = 2016;
	public static final int DATA_RECEIVE_PORT = 2017; //TODO: 
	public static final int DATA_TRANSMIT_PORT = 2015;
	public static final int PAYLOAD_LEN = 30;
	public static final int RELAY_PORT = 2019;
	public static final String RELAY_HOST = "172.17.152.60";
	
	//Socket for sending a packet to the relay host
	private DatagramSocket sendSocket, ackReceiveSocket;
	private InetAddress srcIP, destIP, relayIP;
	private int relayPort;
	
	/**
	 * Initialize sending socket'
	 * @param destIP
	 */
	public ReliableSender(InetAddress srcIP, InetAddress destIP, int relayPort) throws SocketException, UnknownHostException {
		sendSocket = new DatagramSocket(DATA_TRANSMIT_PORT);
		ackReceiveSocket = new DatagramSocket(ACK_RECEIVE_PORT);
		this.srcIP = srcIP;
		this.destIP = destIP;
		this.relayPort = relayPort;
		
		if (relayPort != 0) relayIP = InetAddress.getByName(RELAY_HOST);
		
		//set ack receive socket timeout
		ackReceiveSocket.setSoTimeout(500);
	}
	
	/**
	 * Send a single packet with sequence number 'seqNo' and receive ACK
	 * @param payload
	 * @param seqNo
	 * @throws IOException
	 */
	public void singleSend(String payload, int seqNo) throws IOException {
		ReliableTransportMessage message = new ReliableTransportMessage(srcIP, destIP, DATA_TRANSMIT_PORT, DATA_RECEIVE_PORT, ReliableTransportMessage.DATA, seqNo, payload);
		message.encode();
		
		//send message
		byte[] buffer = message.getBuffer();
		sendSocket.send(new DatagramPacket(buffer, buffer.length, (relayPort == 0) ? destIP : relayIP, (relayPort == 0) ? DATA_RECEIVE_PORT : relayPort));
		if (DEBUG) System.out.println("sent: " + seqNo);
		
		//receive ack
		byte[] ackBuffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(ackBuffer, ackBuffer.length);
		try {
			ackReceiveSocket.receive(packet);
			ReliableTransportMessage rtm = ReliableTransportMessage.reconstitute(ackBuffer);
			
			//if null, re-send
			if (rtm == null)
				throw new SocketTimeoutException();
			//if NAK, re-send
			if (rtm.getOpCode() == ReliableTransportMessage.NAK)
				throw new SocketTimeoutException();
			//if missing sequence number, re-send
			if (rtm.getSequenceNo() != seqNo)
				throw new SocketTimeoutException();
			if (rtm.getOpCode() == ReliableTransportMessage.ACK && seqNo == rtm.getSequenceNo())
				if (DEBUG) System.out.println("ack");
		} catch(SocketTimeoutException ste) {
			singleSend(payload, seqNo); //if timeout, re-send
		}
	}
	
	/**
	 * Send a single packet with opcode END
	 * @throws IOException
	 */
	public void close(int seqNo, int endTimer) throws IOException {
		//END Timer
		if (endTimer != 0)
			endTimer--;
		else
			return;
		
		//END message
		ReliableTransportMessage message = new ReliableTransportMessage(srcIP, destIP, DATA_TRANSMIT_PORT, DATA_RECEIVE_PORT, ReliableTransportMessage.END, seqNo, "");
		message.encode();
		
		//send message
		byte[] buffer = message.getBuffer();
		sendSocket.send(new DatagramPacket(buffer, buffer.length, (relayPort == 0) ? destIP : relayIP, (relayPort == 0) ? DATA_RECEIVE_PORT : relayPort));
		if (DEBUG) System.out.println("sent: e");
		
		//receive ack
		byte[] ackBuffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(ackBuffer, ackBuffer.length);
		try {
			ackReceiveSocket.receive(packet);
			ReliableTransportMessage rtm = ReliableTransportMessage.reconstitute(ackBuffer);
			
			//if null, re-send
			if (rtm == null)
				throw new SocketTimeoutException();
			if (rtm.getSequenceNo() == seqNo && rtm.getOpCode() == ReliableTransportMessage.ACK)
				if (DEBUG) System.out.println("ack");
		} catch(SocketTimeoutException ste) {
			close(seqNo, endTimer); //if timeout, re-send
		}
	}
	
	/**
	 * Opens file and sends data
	 * @param filename
	 */
	public void sendFile(String filename) {
		try {
			Scanner file = new Scanner(new FileReader(filename));
			String everything = "";
			
			//load entire file into string
			while(file.hasNext()) {
				//load one line of text into string
				everything += file.nextLine() + "\n";
			}
			file.close();
			
			//find out how many packets need to be sent
			int packets = (int) Math.ceil(everything.length()/30.0);
			
			if (DEBUG) System.out.println("Message length: " + everything.length());
			if (DEBUG) System.out.println("Packets: " + packets);
			
			//send file
			int sequenceNumber = 0;
			for(int i = 0; i < packets; i++) {
				//calculates sequence number, and sends single packet
				sequenceNumber = i%100;
				int end = (30*(i+1) > everything.length()) ? everything.length() : 30*(i+1);
				singleSend(everything.substring(30*i, end), sequenceNumber);
			}
			
			//send end of file
			sequenceNumber++;
			sequenceNumber %= 100;
			int closeTimer = 10;
			close(sequenceNumber, closeTimer);
		} catch(FileNotFoundException fnfe) {
			System.err.println("error: file not found");
		} catch(Exception e) {
			System.err.println("error: sending error, please try");
		}
	}
	
	/**
	 * Open file and send to remote receiver
	 * @param args filename, sourceIP, destinationIP, (optional) relayPort
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			//program needs 3 or 4 arguments
			if (args.length > 4) {
				System.out.println("error: too many arguments");
				return;
			}
			if (args.length < 3) {
				System.out.println("error: too few arguments");
				return;
			}
			
			//filename
			File file = new File(args[0]); 
			if (!file.exists()) {
				System.err.println("error: file not found");
				return;
			}
			
			//sourceIP
			try {
				InetAddress.getByName(args[1]);
			} catch (Exception f) {
				System.out.println("error: invalid source IP address");
				return;
			}
			
			//destinationIP
			try {
				InetAddress.getByName(args[2]);
			} catch (Exception g) {
				System.out.println("error: invalid source IP address");
				return;
			}
			
			//valid port number
			int portNumber = 0;
			if (args.length == 4) {
				portNumber = Integer.parseInt(args[3]);
				if (portNumber < 2019 || portNumber > 2021)
					throw new NumberFormatException();
			}
			
			//start the sender
			ReliableSender sender = new ReliableSender(InetAddress.getByName(args[1]), InetAddress.getByName(args[2]), portNumber);
			
			//send the file
			sender.sendFile(args[0]);
			
		} catch(NumberFormatException nfe) {
			System.out.println("error: invalid port number");
		} catch(SocketException se) {
			System.out.println("error: socket already in use");
		} catch(Exception e) {
			System.out.println("error: general error, please re-run");
		}
	}
}
