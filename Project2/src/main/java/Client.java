/**
 * Importing the necessary java packages
 */

import java.net.Socket;
import java.rmi.*;
import java.util.Scanner;

/**
 * Client main class
 */

public class Client {
  private static KeyValueInt srvrObject;

  /**
   * Main method
   * @param args takes input from the user

   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Enter the hostname : ");
    String hostname = scanner.nextLine();
    if (hostname.isEmpty()) {
      hostname = "localhost";
    }
    System.out.print("Enter the port number :");
    int port = scanner.nextInt();
    scanner.nextLine(); // consume the newline character

    /**
     * Checking the validity of the port number
     */

    if (!isValid(port)) {
      System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
      return;
    }

    try {
      String registryURL = "rmi://" + hostname + ":" + port + "/KeyValueInt";
      srvrObject = (KeyValueInt) Naming.lookup(registryURL);

      /**
       * Initializing the put/get/delete counters
       * a while loop to check if the counters reach 5, which is the maximum number of requests allowed
       */

      int putCntr = 0;
      int getCntr = 0;
      int deleteCntr = 0;
      Socket socket = new Socket(hostname, port);
      socket.setSoTimeout(30000);
      while (true) {
        System.out.print("Enter your request in this format (PUT key value or GET key or DELETE key or EXIT): ");
        String[] request = scanner.nextLine().split(" ");
        if (request.length >= 2) {
          String operation = request[0].toUpperCase();
          String key = request[1];
          String value = null;
          if (request.length == 3) {
            value = request[2];
          }
          /**
           *  If/else blocks to check whether the operation is PUT, GET or DELETE
           */

          if (operation.equals("PUT")) {
            if (srvrObject.put(key, value)) {
              System.out.println("Key-value pair added successfully! at " + java.time.LocalTime.now());
              putCntr++;
              if (putCntr >= 5) {
                System.out.println("No more PUT requests can be made.");
              }
            } else {
              System.out.println("Failed to add key-value pair!" + java.time.LocalTime.now());
            }
          } else if (operation.equals("GET")) {
            String retrievedValue = srvrObject.get(key);
            if (retrievedValue != null) {
              System.out.println("Retrieved value: " + retrievedValue);
              getCntr++;
              if (getCntr >= 5) {
                System.out.println("No more GET requests can be made.");
              }
            } else {
              System.out.println("Key not found!");
            }
          } else if (operation.equals("DELETE")) {
            if (srvrObject.delete(key)) {
              System.out.println("Key-value pair deleted successfully! at " + java.time.LocalTime.now());
              deleteCntr++;
              if (deleteCntr >= 5) {
                System.out.println("No more DELETE requests can be made.");
              }
            } else {
              System.out.println("Failed to delete key-value pair!");
            }
          } else if (operation.equals("EXIT")) {
            break;
          } else {
            System.out.println("The request is not valid!");
          }
        } else if (request.length == 1 && request[0].equalsIgnoreCase("EXIT")) {
          break;
        } else {
          System.out.println("The request is not valid!");
        }
      }
      socket.close();
    } catch (Exception e) {
      System.err.println("Client exception: " + e.toString());
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


