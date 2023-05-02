// Package 2
package MultiThread;
// Importing necessary java pacakges
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
// Creating a client class
public class Client {
// Main method
  public static void main(String[] args) throws IOException {
// Scanner class to take input from user
    Scanner scanner = new Scanner(System.in);
// Taking port number from the user
    System.out.print("Enter the port number: ");
    int port = scanner.nextInt();
    scanner.nextLine();
// Checking if the entered port number is valid
    if (!isValid(port)) {
      System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
      return;
    }
// creates a new server socket on the entered port number
    Socket sckt = new Socket("localhost",port);
// Reads data from the input stream of the socket
    DataInputStream inputStream = new DataInputStream(sckt.getInputStream());
// Writes data to the output stream of the socket
    DataOutputStream outputStream = new DataOutputStream(sckt.getOutputStream());
// Ask the client to enter a message
    System.out.print("Enter message to send to the server: ");
    String msg = scanner.nextLine();
// writes back to the output stream
    outputStream.writeUTF(msg);
// reads message from the server and prints it
    msg = inputStream.readUTF();
    System.out.println("Message from server: " + msg);
    sckt.close();
  }
  //  A method to check if the port number is within the range
  private static boolean isValid(int portNumber){
    return portNumber >= 0 && portNumber <= 65535;
  }

}
