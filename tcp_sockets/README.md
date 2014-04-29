#TCP Socket Programming

###CSCI 4760
**What is this?** The **Transmission Control Protocol (TCP)** transmits a sequence of bytes between two processes over the network. Normally we would expect these two processes to be running on different hosts, but for testing purposes we can send messages between two processes running on the same computer.

A **socket** is the programming abstraction (object) through which a program interacts with the TCP network software. Sockets are quite similar to File objects, which are programming abstractions through which a program can read and write bytes on a disk.

**Client-server paradigm:** It is not practical for both sides of the conversation to begin communicating at exactly the same time. In the **client-server** paradigm, the server side must begin listening on a known **TCP port** before the client side attempts to connect to that port.

**Virtual machine implementation:** You can either write the code on Eclipse or directly on your VM, but it must run on the VM. I prefer the former method, as the ease of auto-generating code and finding syntax errors on Eclipse outweighs the hassle of repeatedly uploading versions of the code.

You can move the code from your local machine in two ways:
