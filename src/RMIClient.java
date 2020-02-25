import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.LinkedList;

public class RMIClient {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry(1300);
            Dict localdict = (Dict) registry.lookup("Dict");

            //puts
            localdict.put("testkey0","testvalue0");
            localdict.put("testkey1","testvalue1");
            localdict.put("testkey2","testvalue2");
            localdict.put("testkey3","testvalue3");
            localdict.put("tesykey4","testvalue4"); // never used
            localdict.put("testkey0","badvalue0");
            localdict.put("testkey1","badvalue1");

//            //five gets
//            localdict.get("testkey0");
//            localdict.get("testkey1");
//            localdict.get("testkey2");
//            localdict.get("testkey3");
//            localdict.get("fake key");
//
//            //five deletes
//            localdict.delete("testkey0");
//            localdict.delete("testkey1");
//            localdict.delete("testkey2");
//            localdict.delete("testkey3");
//            localdict.delete("fake key");
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
