/**
 * Importing necessary java packages
 */
import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Creating class TCP server
 * Hashmap stores the key value pairs
 */
public class TCPServer {
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
 * Creating a new TCP socket to handle client server communication
 * Getting the inet address and printing the address and the port in the client log
 * Calling the handleTCPClient method
 * Closing the server
 */
    System.out.println("Waiting for client request...");
    ServerSocket server = new ServerSocket(port);
    Socket client = server.accept();
    InetAddress clientAddress = client.getInetAddress();
    int clientPort = client.getPort();
    System.out.println("Received request from " + clientAddress + ":" + clientPort);
    handleTCPClient(client);
    server.close();
  }

  /**
   *
   * @param client sends requests to the server
   * @throws IOException handle the errors
   * the handleTCPClient method reads the client request
   * the client request is then split into an array of strings which splits the string into parts using space
   * The resulting array is then assigned to the "requestComponents" variable.
   * The requestComponents variable is then divide into parts for operation and key as well as values
   */
  private static void handleTCPClient(Socket client) throws IOException {
    DataInputStream inputStream = new DataInputStream(client.getInputStream());
    int rqstCount = 0;
    while (rqstCount < 15) {
      String request = inputStream.readUTF();
      System.out.println("Received request: " + request);

      String[] requestComponents = request.split(" ");
      String operation = requestComponents[0];
      String key = requestComponents[1];
      String value = requestComponents.length > 2 ? requestComponents[2] : null;

      DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
      String response = handleClientRequest(key, value, operation);
      outputStream.writeUTF(response);
      System.out.println("Sent response: " + response);
      rqstCount++;
    }
    client.close();
    System.out.println("NO more requests can be made. Only 5 PUT, 5 GET, 5 DELETE requests are allowed");
  }

  /**
   * Method to check id the port number is within the given range
   * @param portNumber should be withing the given range
   * @return portNumber
   */
  private static boolean isValid(int portNumber){
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
   * The DELETE takes in the operation and the key and removes the key along wit it's value.
   */
  public static String handleClientRequest(String key, String value, String operation) {
    switch (operation) {
      case "PUT":
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
        return "The request made is invalid. Please make a PUT/GET/DELETE request. Make sure that the letters for PUT/GET/DELETE are all caps";
    }
  }
}

