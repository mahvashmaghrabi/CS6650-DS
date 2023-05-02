/**
 * Importing necessary java packages
 */
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Creating class UDP server
 * Hashmap stores the key value pairs
 */

public class UDPServer {
  private static HashMap<String, String> kvStore = new HashMap<>();
  /**
   * Main method
   * @param args asks the user for port number
   * Preloading the hashmap with some values
   * @throws IOException to handle errors
   */
  public static void main(String[] args) throws IOException {
    kvStore.put("1", "apple");
    kvStore.put("2", "orange");
    kvStore.put("3", "grapes");

    Scanner scanner = new Scanner(System.in);
    System.out.print("Enter the port number: ");
    int port = scanner.nextInt();
/**
 * checking if the port number is valid or not
 */
    if (!isValid(port)) {
      System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
      return;
    }
/**
 * Creating a new UDP datagram socket to handle client server communication
 * Calling the handleUDPClient method
 * Closing the server
 */
    System.out.println("Waiting for client request...");
    handleUDPClient(port);
  }

  /**
   * a datagram socket is set up on the specified port and it listens to incoming client request sin the form of datagram packets
   * @param port specified port
   * @throws IOException handles errors
   * * the client request is then split into an array of strings which splits the string into parts using space
   *    * The resulting array is then assigned to the "requestComponents" variable.
   *    * The requestComponents variable is then divide into parts for operation and key as well as values
   */

  private static void handleUDPClient(int port) throws IOException {
    DatagramSocket socket = new DatagramSocket(port);
    byte[] receiveData = new byte[1024];
    byte[] sendData;
    int requestCount = 0;
    int maxRequests = 15;
    while (requestCount < maxRequests) {
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      socket.receive(receivePacket);
      InetAddress clientAddress = receivePacket.getAddress();
      int clientPort = receivePacket.getPort();
      System.out.println("Received request from " + clientAddress + ":" + clientPort);

      String request = new String(receivePacket.getData());
      request = request.trim();
      System.out.println("Received request: " + request);
      String[] requestComponents = request.split(" ");
      if (requestComponents.length < 2) {
        System.err.println("Received malformed request of length " + request.length() + " from " + clientAddress + ":" + clientPort);
        continue;
      }
      String operation = requestComponents[0];
      String key = requestComponents[1];
      String value = requestComponents.length > 2 ? requestComponents[2] : null;

      String response = handleClientRequest(key, value, operation);
      sendData = response.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
      socket.send(sendPacket);
      System.out.println("Sent response: " + response);
      requestCount++;
    }
    socket.close();
  }
  /**
   * Method to check id the port number is within the given range
   * @param portNumber should be withing the given range
   * @return portNumber
   */
  private static boolean isValid(int portNumber) {
    return portNumber >= 0 && portNumber <= 65535;
  }
  /**
   *
   * @param key takes the key of the key-value pair
   * @param value takes the value of the key-value pair
   * @param operation performs the PUT/GET/Delete operation
   * @return the requested client request
   * The PUT operation takes in all three parameters the operation, key and value
   * The GET operation only takes in the operation and the key and give s the value of the key or return not found if it does not exist
   * The DELETE tales in the operation adn the ket and removes the key along wit it's value.
   */
  public static String handleClientRequest(String key, String value, String operation){
    switch (operation) {
    case "PUT":
      value = value.trim();
      kvStore.put(key, value);
      return "Key-value pair added: (" + key + ", " + value + ")";
    case "GET":
      String answer = kvStore.get(key);
      if (answer == null) {
        return "Key not found.";
      } else {
        return "Value for key '" + key + "': " + answer;
      }
    case "DELETE":
      kvStore.remove(key);
      return "Key-value pair deleted: (" + key + ", " + value + ")";
    default:
      return "Invalid request. Please make a PUT/GET/DELETE request";
  }
}
}


