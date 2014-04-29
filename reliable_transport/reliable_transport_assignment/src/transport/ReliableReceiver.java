package transport;

/**
 * Receives packets sent by a ReliableSender and relayed by a Relayer
 * @author vincentlee
 * @version 1.0
 * @since February 18, 2014
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReliableReceiver {
	public static final int ACK_RECEIVE_PORT = 2016; //TODO:cummlative ack vs stop-wait & seqNo start number
	public static final int ACK_SEND_PORT = 2018;
	public static final int DATA_RECEIVE_PORT = 2017;
	public static final int PAYLOAD_LEN = 30;
	public static final String RELAY_HOST = "172.17.152.60";
	
	private String wholeMessage = "";
	private DatagramSocket receiveSocket, ackSendSocket;
	private InetAddress relayIP;
	private int relayPort;
	private InetAddress lastSource, lastDestination;
	private int seqNumber;
	
	/**
	 * Initialize sending socket'and ACK socket. ACK socket will send to relay host
	 * @throws SocketException
	 */
	public ReliableReceiver(int relayPort) throws SocketException, UnknownHostException {
		receiveSocket = new DatagramSocket(DATA_RECEIVE_PORT);
		ackSendSocket = new DatagramSocket(ACK_SEND_PORT);
		this.relayPort = relayPort;
		
		if (relayPort != 0)
			relayIP = InetAddress.getByName(RELAY_HOST);
		
		//used for unreliable
		lastSource = InetAddress.getByName("localhost");
		lastDestination = InetAddress.getByName("localhost");
		seqNumber = 0;
//		firstTime = true;
	}
	
	/**
	 * Receive packet, send ACK or NAK and print contents to stdout
	 * @return packet opCode
	 * @throws IOException
	 */
	public char receive() throws IOException {
		byte[] buffer = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
		receiveSocket.receive(packet);
		
		//convert data to message
		String str = new String(buffer);
		ReliableTransportMessage rtm = ReliableTransportMessage.reconstitute(buffer);
		
		//wrap sequence number
		seqNumber = seqNumber%100;
		
		//mangled packet, send NAK
		if (rtm == null) {
			//log mangled up packet
			errorLog(packet.getSocketAddress().toString().substring(1), str.substring(0, ReliableTransportMessage.BUFFER_LEN));
			
			//send NAK
			ReliableTransportMessage message = new ReliableTransportMessage(lastDestination, lastSource, ACK_SEND_PORT, ACK_RECEIVE_PORT, ReliableTransportMessage.NAK, seqNumber, "");
			message.encode();
			byte[] sendBuffer = message.getBuffer();
			ackSendSocket.send(new DatagramPacket(sendBuffer, sendBuffer.length, (relayPort == 0) ? packet.getAddress() : relayIP, (relayPort == 0) ? ACK_RECEIVE_PORT : relayPort));
			
			return ReliableTransportMessage.NAK;
		}
		
		//correct sequence number, send ACK
		if (seqNumber == rtm.getSequenceNo()) {
			//log good packet
			accessLog(packet.getSocketAddress().toString().substring(1), str.substring(0, ReliableTransportMessage.BUFFER_LEN));
			
			//send ACK
			ReliableTransportMessage message = new ReliableTransportMessage(rtm.getDestIP(), rtm.getSourceIP(), ACK_SEND_PORT, ACK_RECEIVE_PORT, ReliableTransportMessage.ACK, seqNumber, "");
			message.encode();
			byte[] sendBuffer = message.getBuffer();
			ackSendSocket.send(new DatagramPacket(sendBuffer, sendBuffer.length, (relayPort == 0) ? rtm.getSourceIP() : relayIP, (relayPort == 0) ? ACK_RECEIVE_PORT : relayPort));
			
			//add packet's payload to message
			wholeMessage += rtm.getPayload();
			
			//update sequence number
			seqNumber++;
			
			//for sending null NAK
			lastDestination = rtm.getDestIP();
			lastSource = rtm.getSourceIP();
			
			return rtm.getOpCode();
		}
		
		//re-send ACK
		ReliableTransportMessage message = new ReliableTransportMessage(rtm.getDestIP(), rtm.getSourceIP(), ACK_SEND_PORT, ACK_RECEIVE_PORT, ReliableTransportMessage.ACK, rtm.getSequenceNo(), "");
		message.encode();
		byte[] sendBuffer = message.getBuffer();
		ackSendSocket.send(new DatagramPacket(sendBuffer, sendBuffer.length, (relayPort == 0) ? rtm.getSourceIP() : relayIP, (relayPort == 0) ? ACK_RECEIVE_PORT : relayPort));
		
		return rtm.getOpCode();
	}
	
	/** Log good packets */
	public void accessLog(String ipAddress, String message) {log(ipAddress, message, "access");}
	
	/** Log mangled packets */
	public void errorLog(String ipAddress, String message) {log(ipAddress, message, "error");}
	
	/**
	 * Provides logging capabilities
	 * @param ipAddress
	 * @param message
	 * @param type
	 */
	public void log(String ipAddress, String message, String type) {
		try {
			SimpleDateFormat date = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat timestamp = new SimpleDateFormat(" [dd/MMM/yyyy hh:mm:ss.SSS a] ");
			
			File log = new File("log/" + type + ".log-" + date.format(new Date()));
			PrintWriter out = new PrintWriter(new FileWriter(log, true));
			out.append(ipAddress);
			out.append(timestamp.format(new Date()));
			out.append("\"" + message + "\"");
			out.append("\n");
		    out.close();
		} catch(Exception e) {
			System.err.println("error: " + type + ".log");
		}
	}
	
	/**
	 * 
	 * @param args (optional) relayPort
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			//up to one (1) argument
			if (args.length > 1) {
				System.out.println("error: too many arguments");
				return;
			}
			
			//valid port number
			int portNumber = 0;
			if (args.length == 1) {
				portNumber = Integer.parseInt(args[0]);
				if (portNumber < 2019 || portNumber > 2021)
					throw new NumberFormatException();
			}
			
			//start receiver
			ReliableReceiver receiver = new ReliableReceiver(portNumber);
			
			//receive data
			while (receiver.receive() != 'E') {};
			
			//display on standard output
			System.out.println(receiver.wholeMessage.trim());
		} catch(NumberFormatException nfe) {
			System.out.println("error: invalid port number");
		} catch(SocketException se) {
			System.out.println("error: socket already in use");
		} catch(Exception e) {
			System.out.println("error: general error, please re-run");
		}
	}
}