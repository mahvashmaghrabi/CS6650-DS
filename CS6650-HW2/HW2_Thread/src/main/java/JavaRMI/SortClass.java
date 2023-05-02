// Package 1
package JavaRMI;
// Importing the ncessary java packages
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.List;

// Creating a class that extends the UnicastRemoteObject and implements the SortInterface
public class SortClass extends UnicastRemoteObject implements SortInterface {
// a constructor that only the class and it's subclasses can access
  protected SortClass() throws RemoteException {
    super();
  }
  @Override
// implementation of the sort method in the SortInterface
  public List<Integer> sort(List<Integer> numList, int order) throws RemoteException {
    // if the user enters one the list is sorted in ascending order
    if (order == 1) {
      Collections.sort(numList);
    // if the user enters 2 it is reversed that is descending order
    } else if (order == 2) {
      numList.sort(Collections.reverseOrder());
    }
    return numList;
  }
}




