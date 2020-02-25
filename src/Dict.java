import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public interface Dict extends java.rmi.Remote{
    HashMap<String,String> dict = new HashMap<String,String>();

    void put(String key, String value) throws java.rmi.RemoteException;

    void get(String key) throws java.rmi.RemoteException;

    void delete(String key) throws java.rmi.RemoteException;

    String getTime() throws java.rmi.RemoteException;
}
