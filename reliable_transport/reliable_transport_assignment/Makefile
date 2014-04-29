compile:
	javac src/transport/ReliableTransportMessage.java src/transport/ReliableSender.java src/transport/ReliableReceiver.java
localhost: compile
	java -classpath src transport.ReliableReceiver > output/localhost &
	java -classpath src transport.ReliableSender file1 localhost localhost
2019: compile
	java -classpath src transport.ReliableReceiver 2019 > output/2019 &
	java -classpath src transport.ReliableSender file1 172.17.152.28 172.17.152.28 2019
2020: compile
	java -classpath src transport.ReliableReceiver 2020 > output/2020 &
	java -classpath src transport.ReliableSender file1 172.17.152.28 172.17.152.28 2020
2021: compile
	java -classpath src transport.ReliableReceiver 2021 > output/2021 &
	java -classpath src transport.ReliableSender file1 172.17.152.28 172.17.152.28 2021

