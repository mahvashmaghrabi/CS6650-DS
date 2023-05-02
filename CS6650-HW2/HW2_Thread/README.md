## There are three packages in this folder
## Package 1 - Java RMI
## This package has four java classes as follows
## ServerClass
## The server class needs to start first. 
## It asks for the port number which has to be entered in the command line interface
## It checks idf the given port is within the acceptable range.
## ClientClass
## Next the client class needs to be started.
## The client class asks the user to enter ten integers. After entering each integer the enter button has to pressed.
## Once the ten integers have been entered the program asks if the sorting need to be done in ascending or descending order
## If the user needs ascending order press 1 and if descending order is needed press 2
## If anything other than 1 or 2 is entered it throws an error and mentions to check the value entered
## SortClass
## The sort class basically provides the remote service(RMI).
## It sorts the integers in ascending or descending order
## SortInterface
## This interface class extends the remote interface class.
## The remote interface class should be implemented for Java RMI
## This is a remote service that can be accessed by remote clients.
## Package 2 - Multi Thread
## This package has two java classes as follows
## Server
## The server class needs to be started first
## The program asks for the user to enter the port number.
## Once the user entered the port number it can now start the client side of the program.
## Client
## The client class has the main method which is the entry point of the program
## Once the client is started it asks the user to enter a port number
## Then asks for the message to be delivered to the server.
## MultiThreading
## This class extends the Thread class.
## The thread class is a built-in Java class that manages and also creates threads in java
## The start() is a method of the thread class which starts the execution of the new thread and calls the run() method

## Package 3 - Single Thread
## This package has two java classes as follows
## SingleServer
## The main method of the server class is to be started.
# It runs and asks you to enter the port number in the command line interface Eg: 4444
## It also checks if the entered port is valid or not
## SingleClient
## Now start the main method of the client class
## It then asks the user to enter a message to send over to the server
## The message is displayed in the server and the client window shows that the message has been received
