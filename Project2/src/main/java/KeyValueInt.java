import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * This interface is a remote interface for the server side object
 * It handles three methods put, get and delete
 * put takes the key value pairs
 * get retrieve the key value pair
 * delete removes the key value pair specified
 */
interface KeyValueInt extends Remote {
  boolean put(String key, String value) throws RemoteException;

  String get(String key) throws RemoteException;

  boolean delete(String key) throws RemoteException;
}
