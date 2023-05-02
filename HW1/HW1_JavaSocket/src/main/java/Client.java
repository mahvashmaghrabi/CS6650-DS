// Importing necessary libraries
import java.io.*;
import java.net.*;
import java.util.Scanner;

// Creating a class to handle the client
public class Client {
 public static void main(String args[]) throws IOException {
   Scanner scanner = new Scanner(System.in);

   // Taking IP address from the user
   System.out.print("Enter the IP address: ");
   String IpAddress = scanner.nextLine();

   // Taking port number from the user
   System.out.print("Enter the port number: ");
   int port = scanner.nextInt();
   if (!isValid(port)) {
     System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
     return;
   }
   // Asking the user to enter the string/text
   System.out.print("Enter the string: ");

// Creates a new socket connection to server
   Socket socket = new Socket(IpAddress,port);
// Writes data to the output stream of the socket
    DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
// Reads data from the input stream of the socket
    DataInputStream inputStream = new DataInputStream(socket.getInputStream());
// Reads text from an input stream
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    try{
      String str=reader.readLine();
// checks if the entered text matches the given regex
      if (!str.matches("^[a-zA-Z0-9\\s.,:;!?]*$")) {
        System.out.println("Invalid Input");
      }else {
// sends the string over to the server
        outputStream.writeUTF(str);
// gets the altered string from the server and prints it
        String alteredString = inputStream.readUTF();
        System.out.println("Response from server: " + alteredString);
        socket.close();
      }
    } catch(IOException e){
      System.out.println("Connection Closed");
    }
// Closes the socket connection
    socket.close();
  }
//  A method to check if the port number is within the range
  private static boolean isValid(int portNumber){
    return portNumber >= 0 && portNumber <= 65535;
  }
}

