#Distance-Vector Routing Exercise

###CSCI 4760
**What is this?** What this is not, is the programming problem in Chapter 4 of the Kurose-Ross book, 5th ed. If you can get access to the materials needed to do that problem instead of this one, go ahead!

However, this assignment does get at the same underlying problem, which is the Distance-Vector routing problem.  You are to write a program in any language of your choice that does the following:

1. Reads in a text file describing a network
2. Runs the Distance-Vector algorithm  until it converges
3. For each node in the network, prints out the distance and first hop to each other node in the network

The text file contains lines of the form 
`Host-1   Host-2  distance`

--where Host-1 and Host-2 are Strings with no spaces and distance is a double value.  A file describing the network shown in the picture above is shown at right. All links are symmetric, i.e. the distance from B to A equals the distance from A to B. However, the network is not guaranteed to be connected.
