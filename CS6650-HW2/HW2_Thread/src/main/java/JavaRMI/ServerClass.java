// Package 1
package JavaRMI;
// Importing the necessary java packages
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

// Creating a server class
public class ServerClass {
 // Main method
  public static void main(String[] args) {
// Scanner object to take input from the user
    Scanner scanner = new Scanner(System.in);

    // Taking port number from the user
    System.out.print("Enter the port number: ");
    int port = scanner.nextInt();
    // Checking if the port number is valid
    if (!isValid(port)) {
      System.out.println("Invalid port number, please enter a valid port number in the range 0 to 65535.");
      return;
    }
    try {
 // the method create registry is used to create a registry on the port entered by the user
      LocateRegistry.createRegistry(port);
// Creating a new object of the SortClass
      SortClass sc = new SortClass();
//the rebind method here binds the sortinterface and the sortclass object sc
      Naming.rebind("SortInterface", sc);
    } catch (Exception e) {
      System.out.println("Exception encountered" + e.getMessage());
    }
  }
  // the is the method that checks idf the port number is withing the acceptable range
  private static boolean isValid(int portNumber){
    return portNumber >= 0 && portNumber <= 65535;
  }
}

