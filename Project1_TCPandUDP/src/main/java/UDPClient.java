/**
 *Importing the necessary java packages
 */
import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * Creating a UDP client class that takes the hostname and the port number from the user
 */
public class UDPClient {
  /**
   * Main method
   * @param args takes input from the user
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
 * Creating a new UDP datagram socket for communication
 * setting time out for when the server does not respond to the given request
 */

    try {
      DatagramSocket sckt = new DatagramSocket();
      sckt.setSoTimeout(30000);
      InetAddress address = InetAddress.getByName(hostname);
/**
 * Initializing the put/get/delete counters
 * a while loop to check if the counters reach 5, which is the maximum number of requests allowed
 */
      int putCntr = 0;
      int getCntr = 0;
      int deleteCntr = 0;
      while (!(putCntr == 5 && getCntr == 5 && deleteCntr == 5)) {
        System.out.print("Enter request to send to server (PUT key value, GET key, DELETE key, or exit): ");
        String request = scanner.nextLine();
        if (request.equals("exit")) {
          break;
        }

        byte[] sendBuffer = request.getBytes();
        DatagramPacket sendPacket =
            new DatagramPacket(sendBuffer, sendBuffer.length, address, port);
        sckt.send(sendPacket);

        System.out.println("Client: Request sent to server at " + java.time.LocalTime.now());

        byte[] receiveBuffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
        sckt.receive(receivePacket);
        String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

        System.out.println("Client: Received response from server: " + response + " at " + java.time.LocalTime.now());
        System.out.println("Server: " + response + " at " + java.time.LocalTime.now());
/**
 *  If/else blocks to check whether the requests starts from PUT/GET/DELETE
 */
        if (request.startsWith("PUT")) {
          putCntr++;
          if (putCntr == 5) {
            System.out.println("Only 5 PUT requests are allowed.");
            continue;
          }
        } else if (request.startsWith("GET")) {
          getCntr++;
          if (getCntr == 5) {
            System.out.println("Only 5 GET requests are allowed.");
            continue;
          }
        } else if (request.startsWith("DELETE")) {
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
      System.out.println("Client: Timed out while waiting for response from server at " + java.time.LocalTime.now() );
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Checking the port number validity
   * @param port number is withing the given range
   * @return port number
   */
  public static boolean isValid(int port) {
    return port >= 0 && port <= 65535;
  }
}


