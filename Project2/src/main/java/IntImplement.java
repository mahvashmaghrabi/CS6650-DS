/**
 * Importing the required java packages
 */

import java.rmi.*;
import java.rmi.server.*;
import java.util.HashMap;
import java.util.concurrent.locks.*;

/**
 * The class extends the UnicastRemoteObject
 * It also implements the KeyValuInt interface
 * It has two variables a hashmap and a ReentrantReadWriteLock
 */
public class IntImplement extends UnicastRemoteObject implements KeyValueInt {
  private HashMap<String, String> kvStore;
  private ReentrantReadWriteLock readWriteLock;

  /**
   * a constructor that hs the hashmap and the lock
   * @throws RemoteException will be thrown if there is a problem with exporting the remote object.
   * the hashmap is prepopulated with three values
   */
  public IntImplement() throws RemoteException {
    super();
    kvStore = new HashMap<String, String>();
    readWriteLock = new ReentrantReadWriteLock();
    kvStore.put("1", "apple");
    kvStore.put("2", "orange");
    kvStore.put("3", "grapes");
  }

  /**
   * the put adds a new key, value pair to the hashmap
   * @param key for the hashmap
   * @param value for the key in th hashmap
   * @return true or false for if the value exists in the hashmap already
   * The writeLock() method is used to obtain a write lock, which blocks other threads from acquiring the lock.
   * It checks for the key value pair and adds to hashmap if does not exist
   * the writeLock() is released using the unlock() method.
   * @throws RemoteException is thrown if problem ocuurs for adding the key value pair
   */
  public boolean put(String key, String value) throws RemoteException {
    Lock writeLock = readWriteLock.writeLock();
    writeLock.lock();
    try {
      if (kvStore.containsKey(key)) {
        return false;
      }
      kvStore.put(key, value);
      return true;
    } finally {
      writeLock.unlock();
    }
  }

  /**
   *
   * @param key whose value needs to be retierved
   * @return the value for the key mentioned
   * a read lock is acquired using the readLock() method.
   * It allows multiple threads to read the HashMap concurrently
   * It makes sure that no thread can be modified while it is being read.
   * @throws RemoteException will be thrown if problem occurs with getting the key
   */

  public String get(String key) throws RemoteException {
    Lock readLock = readWriteLock.readLock();
    readLock.lock();
    try {
      return kvStore.get(key);
    } finally {
      readLock.unlock();
    }
  }

  /**
   *
   * @param key whose value has to be deleted
   * @return boolean calue indicatiing whether the given key value pair is deleted or not
   * A write lock is aquired and checks if the key exists in the hashmap and delete s it if it exists
   * Then it releases the write lock to allow other threads to access the map.
   * @throws RemoteException is thrown if problem occurs while removing the key value pair
   */
  public boolean delete(String key) throws RemoteException {
    Lock writeLock = readWriteLock.writeLock();
    writeLock.lock();
    try {
      if (!kvStore.containsKey(key)) {
        return false;
      }
      kvStore.remove(key);
      return true;
    } finally {
      writeLock.unlock();
    }
  }
}
