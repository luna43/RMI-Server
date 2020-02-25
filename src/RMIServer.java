import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) throws RemoteException {
        try {
            Registry registry = LocateRegistry.createRegistry(1300);
            Dict serverDict = new DictImpl();

            registry.bind("Dict", serverDict);
            System.out.println(serverDict.getTime() + "Server is ready");
        } catch (RemoteException | AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
