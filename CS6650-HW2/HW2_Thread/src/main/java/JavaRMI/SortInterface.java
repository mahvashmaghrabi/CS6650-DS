// Package 1
package JavaRMI;
// Importing the necessary packages
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

// the SortInterface extends the remote interface
// the remote interface shows that the objects can be invoked from a remote machine
public interface SortInterface extends Remote {
  List<Integer> sort(List<Integer> numList, int order) throws RemoteException;
}
