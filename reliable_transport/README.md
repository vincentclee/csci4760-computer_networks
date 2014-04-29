#Reliable Transport Assignment

###CSCI 4760
**Due date:** End of Spring Break. Finish beforehand to enjoy a relaxing holiday!

**What is this?** In this assignment you will implement the details of reliably transmitting data across an unreliable network. The design principles are covered in the Transport Layer chapter of our text. We will use cumulative acknowledgement with NAKs.

In order to simulate an unreliable network, I will set up a relay station on my VM. UDP datagrams sent to my relay station will sometimes be forwarded correctly to their destination, sometimes corrupted and forwarded, and sometimes dropped entirely. For this assignment, datagrams will not be delivered out of order.

**Standard UDP ports to use:**  Unlike TCP, UDP allows only one socket to be bound to the same port on the same host. I suggest the following set of four port numbers:
