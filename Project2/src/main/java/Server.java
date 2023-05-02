/**
 * Importing necessary java packages
 */

import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.*;
import java.rmi.registry.*;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Server main class
 */
public class Server {
  public static void main(String[] args) throws RemoteException {
    try {
      Scanner scanner = new Scanner(System.in);

      System.out.print("Enter RMI registry port number (Eg :1099) :");
      int remotePort = scanner.nextInt();

      KeyValueInt serverObject = new IntImplement();
      LocateRegistry.createRegistry(remotePort);
      Naming.rebind("//localhost/KeyValueInt", serverObject);

      System.out.println("Server started at " + java.time.LocalTime.now());

      System.out.print("Enter server port number: ");
      int sPort = scanner.nextInt();

      /**
       * checking if the port number is valid or not
       */

      if (!isValid(remotePort) || !isValid(sPort)) {
        System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
        return;
      }

      ServerSocket ss = new ServerSocket(sPort);

      ExecutorService executorService = Executors.newFixedThreadPool(10);

      while (true) {
        Socket socket = ss.accept();
        Runnable work = new RqstHandle(socket, serverObject);
        executorService.execute(work);
      }

    } catch (Exception e) {
      System.err.println("Server exception: " + e.toString());
      e.printStackTrace();
    }
  }

  /**
   * Method to check id the port number is within the given range
   * @param portNumber should be withing the given range
   * @return portNumber
   */

  private static boolean isValid(int portNumber) {
    return portNumber >= 0 && portNumber <= 65535;
  }

}

/**
 * Implementing the runnable interface
 */

class RqstHandle implements Runnable {
  private Socket s;
  private KeyValueInt serverObject;

  public RqstHandle(Socket s, KeyValueInt serverObject) {
    this.s = s;
    this.serverObject = serverObject;
  }

  /**
   * Run method gets executed when a thread is using an instance of class
   */

  public void run() {
    try {
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

