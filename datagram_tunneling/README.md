#UDP Tunnelling Exercise

####CSCI 4760
**What is this?**  Normally, protocol messages at one layer are encapsulated into messages at a lower level. For example, TCP and UDP datagrams are encapsulated in IP packets, as shown at right.

**Tunnelling** is when a message at one layer is encapsulated into a message at the same layer. For example, you can connect to hosts inside the UGA network from outside the UGA network only be encapsulating your IP packet inside another IP packet which carries them through the UGA firewall. Once inside the firewall, your IP packet is released to find its destination inside the UGA network.

In this exercise, our  custom transport-layer messages will be tunneled inside UDP datagrams, which will be bounced off a relay host to get to their destination.

You can set up both ends of the conversation on your own laptop (if you are inside the UGA network) or on a VM. I will assume you are using a local computer and working on Eclipse.

1. Launch Eclipse. Start up a project named **Networking Code** or **Transport Examples**, depending on how much stuff you plan to put in here.

###The Message  class

2. Create a new class in package **message** with class name **PracticeMessage**. Copy the starter PracticeMessage code into this class.
3. Create a JUnit test class PracticeMessageTest. Copy the starter PracticeMessateTest into this class.
4. Complete the PracticeMessageTest code.. Remember the catch clause!
5. “Test the PracticeMessageClass into existence” by improving its code until the tests pass. In the reconstitute method, we have a slight issue with splitting the message about the vertical bar character, because this character is used as a metacharacter in regular expressions! <br> To use the split method, represent the vertical bar char with the escaped expression `\\|`.

###Encapsulating and Transmitting the Message
6. Read up on the **DatagramPacket** class, either via the online Javadocs or your text (I prefer the online docs). In particular, this constructor allows us to create a DatagramPacket addressed to a particular destination:

–Notice that the constructor requires a byte array  as a buffer, plus an InetAddress object (which represents the IP address, identifying the host to which our message will be delivered.

7. Define a class **PracticeTransmitter** in package  **udpdemo**. This object will send a PracticeMessage to the relay host, encapsulated in a UDP datagram. Copy the starter code from eLC into this class.
8. Define the JUnitTest class **PracticeTransmitterTest** in package u**dpdemo**. Copy the starter code from eLC into this class.
9. Check out the **DatagramSocket** class. In particular, note that a DatagramSocket can be connected to a specified remote host InetAddress and port, and that the class has getter methods that can tell us what the local and remote port and IP address are.
10. Complete the **PracticeTransmitterTest** code:
  1. In cases where the test operation might throw an Exception, wrap the test inside a try-catch structure;
  2. In testConstructor, use the getter methods to verify the remote port and InetAddress values. To what host will it be connected?
  3. n each case where you create a PracticeMessage or DatagramPacket object,  verify correctness by writing assetEquals on all the class members that can be accessed via getter methods. This is where you must think about the destination port numbers and IP addresses! Hint: the PracticeMessage object must be addressed to its eventual d estination, while the DatagramPacket should be addressed to the relay host.
11. Begin implementing PracticeTransmitter. Remember that you have already implemented the logic to incode a message, so call this existing method rather than rewriting the code.
12. **UnknownHostException** is an error that might occur if the user types in a bad IP address. Since the user’s input is not under our control, we can’t make this error go away by fixing our code. Instead, the correct behavior is to print a polite, informative error message and close the application. As you see from the TODO comments, this should be done in the main method.  (In general, I recommend having only the main method communicate directly with the user, unless there is a huge amount of communication to be done.) <br> Here is some template code for managing the Exceptions. Do you see how I have identified a couple of  Exception types that might occur? Think about how these Exceptions might occur so that you can write helpful  errorr messages. Complete the development of PracticeTransmitter. You will run into another possible Exception sending the packet. How should you handle this?

###Receiving and displaying the message
13. Finally, you must receive and display the message after it comes back from the relayer. Start by reviewing the DatagramSocket.receive method. You will notice that in order to receive a packet, you must already have a DatagramPacket!

In order to create a packet, you need a buffer. Since we don’t know how long the incoming message payload will be, I suggest allocating a large-ish buffer, and building a DatagramPacket around it:

14. When you do receive a packet, you must convert its buffer from bytes to characters and assemble them into a String. I suggest the StringBuffer class for this job. Converting each individual byte to a char can be easily accomplished by class casting.

If the relaying does not work, check with me to see if my relayer is working. Good luck!
