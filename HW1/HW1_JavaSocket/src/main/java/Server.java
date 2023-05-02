// Importing necessary libraries
import java.io.*;
import java.net.*;
import java.util.Scanner;

// Creating a class to handle the server
public class Server {
  public static void main(String args[]) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the port number: ");
    int port = scanner.nextInt();
    if (!isValid(port)) {
      System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
      return;
    }
// creates a server socket on port 32000
    ServerSocket server = new ServerSocket(port);
// the accept method will wait till the client connects to the server
    Socket socket = server.accept();
    System.out.println("Connected");
// Reads data from the input stream of the socket
    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
// Writes data to the output stream of the socket
    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
    try {
// reads message from the client and prints it
      String string = inputStream.readUTF();
      System.out.println("Client :" + string);
// reverses the string and converts lowercase to uppercase and vice-versa
      char[] reversedLetters = new char[string.length()];
      int reversedInd = 0;
      for (int i = string.length()-1; i >= 0; i--) {
        char character = string.charAt(i);
        if (Character.isUpperCase(character)) {
          reversedLetters[reversedInd++] = Character.toLowerCase(character);
        } else {
          reversedLetters[reversedInd++] = Character.toUpperCase(character);
        }
      }
      String reversedStr = new String(reversedLetters);
// writes back the output stream back to the socket which is read by the input stream
      outputStream.writeUTF(reversedStr); // writes back
      socket.close();
    }catch(IOException e){
      System.out.println("Connection Closed");
// closing the socket connection
      socket.close();
    }
  }
  //  A method to check if the port number is within the range
  private static boolean isValid(int portNumber){
    return portNumber >= 0 && portNumber <= 65535;
  }
}
