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

**Implementation:** I urge you to give some thought to the object-oriented design of this problem.  You are strongly encouraged to use test-driven development. We won’t necessarily help debug code that does not follow TDD practice, if it’s possible to use TDD in your environment (which it almost certainly is).

**Submission:**  Please submit on eLC a Zip file with your source code and a README file. If you are using a language other than Java, C/C++, or Python, please give some hints as to how to run your code – we may call you in for a demo!

###Implementation Hints
 * Define the methods of your classes so that you can unit test them in reasonable chunks. For example, the first step is reading the config file, the second step is exchanging routing tables, etc. At each step, sit down with pencil and paper and predict what the result should be, then express this in your unit test code.
 * For splitting a String around multiple spaces, see [Parsing Strings with split](http://pages.cs.wisc.edu/~hasti/cs302/examples/Parsing/parseString.html)

###Grading Rubric

Awesome accomplishment | Generous credit | My Score
---------------------- | --------------- | --------
Correct loop comments | 20 | 20
Simulator reads config file, records network structure | 20 | 20
D-V algorithm  converges | 20 | 20
Displays correct table | 20 | 20
Usability  (readable README and output) | 20 | 20
TOTAL | 100 | 100
