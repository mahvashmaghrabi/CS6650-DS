// Package 2
package MultiThread;
// Importing the necessary packages
import java.io.*;
import java.net.*;
import java.util.Scanner;
// Creating a server class
public class Server {
  public static void main(String[] args) throws IOException {
// Scanner class to take input from user
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the port number: ");
    int port = scanner.nextInt();
// Checking if the port number is valid
    if (!isValid(port)) {
      System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
      return;
    }
// creates a new server socket on specified port
    ServerSocket server = new ServerSocket(port);
    System.out.println("Server started");
    while (true){
      Socket sckt = server.accept();
      System.out.println("Client is connected");
// Creating a MultiThread object
      MultiThreading mt = new MultiThreading(sckt);
// start method to start the thread execution
      mt.start();
    }
  }
//  A method to check if the port number is within the range
  private static boolean isValid(int portNumber){
    return portNumber >= 0 && portNumber <= 65535;
  }
}
 // the MultiThread class extends Thread
class MultiThreading extends Thread {
// constructor
  private Socket sckt;
  MultiThreading(Socket socket) {
    this.sckt = socket;
  }
  @Override
// calling the run method
  public void run() {
    try {
// Reads data from the input stream of the socket
      DataInputStream inputStream = new DataInputStream(sckt.getInputStream());
// Writes data to the output stream of the socket
      DataOutputStream outputStream = new DataOutputStream(sckt.getOutputStream());
      String msg = inputStream.readUTF();
// Prints message from the client
      System.out.println("Client: " + msg);
      outputStream.writeUTF("Message Received");
// closing the socket connection
      sckt.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
