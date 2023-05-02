/**
 * Importing the necessary java packages
 */

import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Creting a TCP client class that takes the hostname and the port number from the user
 */
public class TCPClient {

  /**
   * Main method
   * @param args tales input from the user
   * @throws IOException handles the error
   */
  public static void main(String[] args) throws IOException {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter hostname");
    String hostname = scanner.nextLine();
    System.out.print("Enter the port number: ");
    int port = Integer.parseInt(scanner.nextLine());

  /**
  * Checking the validity of the port number
  */
    if (!isValid(port)) {
      System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
      return;
    }
  /**
  * Creating a new TCP socket for communication
  * setting time out for when the server does not respond to the given request
  */
    try {
      Socket sckt = new Socket(hostname, port);
      sckt.setSoTimeout(30000);
      DataInputStream inputStream = new DataInputStream(sckt.getInputStream());
      DataOutputStream outputStream = new DataOutputStream(sckt.getOutputStream());

  /**
  * Initializing the put/get/delete counters
  * a while loop to check if the counters reach 5, which is the maximum number of requests allowed
  */
      int putCntr = 0;
      int getCntr = 0;
      int deleteCntr = 0;
      while (!(putCntr == 5 && getCntr == 5 && deleteCntr == 5)) {
        System.out.print("Enter your request in this format (PUT key value or GET key or DELETE key or exit): ");
        String rqst = scanner.nextLine();
        if (rqst.equals("exit")) {
          break;
        }
        outputStream.writeUTF(rqst);
        String response;
        try {
          response = inputStream.readUTF();
        } catch (EOFException e) {
          System.out.println("Server closed the connection.");
          break;
        }
  /**
  *  If/else blocks to check whether the requests starts from PUT/GET/DELETE
  */
        System.out.println("Server: " + response + " at " + java.time.LocalTime.now());
        if (rqst.startsWith("PUT")) {
          putCntr++;
          if (putCntr == 5) {
            System.out.println("Only 5 PUT requests are allowed.");
            continue;
          }
        } else if (rqst.startsWith("GET")) {
          getCntr++;
          if (getCntr == 5) {
            System.out.println("Only 5 GET requests are allowed.");
            continue;
          }
        } else if (rqst.startsWith("DELETE")) {
          deleteCntr++;
          if (deleteCntr == 5) {
            System.out.println("Only 5 DELETE requests are allowed.");
            continue;
          }
        }
      }
          sckt.close();
  /**
  * Handling the timed exception with an error message
  */
    } catch (SocketTimeoutException e) {
      System.out.println("Client: Timed out while waiting for a response from the server at " + java.time.LocalTime.now());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checking the port number validity
   * @param port number is withing the given range
   * @return port number
   */
  private static boolean isValid(int port) {
    return port >= 0 && port <= 65535;
  }
}

