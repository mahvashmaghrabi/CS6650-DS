//Package 3
package SingleThread;
// Importing the necessary java packages
import java.io.*;
import java.net.*;
import java.util.Scanner;
// Creating a server class
public class SingleServer {
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
// creates a server socket on specified port
    ServerSocket server = new ServerSocket(port);
    Socket sckt = server.accept();
// Reads data from the input stream of the socket
    DataInputStream inputStream = new DataInputStream(sckt.getInputStream());
// Writes data to the output stream of the socket
    DataOutputStream outputStream = new DataOutputStream(sckt.getOutputStream());
    System.out.println("Client is connected");
// reads message from the client and prints it
    String message = inputStream.readUTF();
    System.out.println("Client: " + message);
// reads message from the client and prints it
    outputStream.writeUTF("Message Received");
// closing the socket connection
    sckt.close();
    server.close();
  }
  //  A method to check if the port number is within the range
    private static boolean isValid(int portNumber){
    return portNumber >= 0 && portNumber <= 65535;
  }
}
