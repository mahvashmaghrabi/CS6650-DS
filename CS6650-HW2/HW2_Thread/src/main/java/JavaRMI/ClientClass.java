// Package 1
package JavaRMI;
// Importing the necessary java packages
import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Creating the Client class
public class ClientClass {
// Main method
  public static void main(String[] args) {
    try {
// The naming.lookup method is used to get reference to the remote object
      SortInterface si = (SortInterface) Naming.lookup("SortInterface");
// The numList is used to store the ten integers entered by the user in an array
      List<Integer> numList = new ArrayList<>();
// Scanner object takes input from the user
      Scanner scanner = new Scanner(System.in);
      System.out.println("Enter any ten integers");
// this for loop is used to get ten integers from the user
// the integers entered by the user are then stored in the numList
      for (int i = 0; i < 10; i++) {
        numList.add(scanner.nextInt());
      }
// The user is asked to enter the choice 1 or 2 for the order of sort to be performed
      System.out.println("Enter 1 for ascending order and 2 for descending order:");
      int order = scanner.nextInt();
// Validation to check if the user enters anything other than 1 or 2
      while (order != 1 && order != 2) {
        System.out.println("Please check the description enter the number 1 or 2 accordingly");
        order = scanner.nextInt();
      }
// the sort method is called which sorts the array of numbers entered by the user
// the sorted number list is then stored in the variable sortedNum
      List<Integer> sortedNum = si.sort(numList,order);
// The list is printed
      System.out.println("The sorted list: " + sortedNum);
// Catches the exception if any
    } catch (Exception e) {
      System.out.println("Exception encountered: " + e.getMessage());
    }
  }
}
