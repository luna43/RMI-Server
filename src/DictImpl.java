import java.time.format.DateTimeFormatter;

public class DictImpl extends java.rmi.server.UnicastRemoteObject implements Dict {
    /**
     * Constructor just inherits itself
     * @throws java.rmi.RemoteException
     */
    protected DictImpl() throws java.rmi.RemoteException {
        super();
    }

    /**
     * Used as a semaphore for mutual exclusion on resources
     */
    private int threadCount=0;
    /**
     * This function prints the current time the way I want
     * @return the date in a nice format
     */
    public String getTime(){
        return "(" + java.time.LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + ") ";
    }

    /**
     * This function checks the threads active in the database (HashMap)
     * and waits until there is no activity to go in. Waits in increments
     * of three seconds and prints to stderr
     */
    private void checkThreads(){
        while(threadCount>0){
            try {
                System.err.println(getTime() + Thread.currentThread().getName() + " is waiting 3 seconds..");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Remote Procedure call - PUT: Puts the key and value into the map if
     * it does not already exists. Does not overwrite.
     *
     * @param key The key you wish to save
     * @param value The value you wish to save
     */
    public void put(String key, String value) {
        checkThreads();
        threadCount++;

        //This block of code is used to test Multithreadedness
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (dict.containsKey(key)){
            System.out.println(getTime() + "PUT Command: Key Exists already. Please delete");
            System.out.println(getTime() + "PUT Command: Active thread count: " + Thread.activeCount());
            threadCount--;
            return;
        }

        dict.put(key,value);
        System.out.println(getTime() + "PUT Command: Added new key to dict: " + key);
        System.out.println(getTime() + "PUT Command: Active thread count: " + Thread.activeCount());
        threadCount--;
        return;
    }

    /**
     * Remote Procedure Call - GET: checks for the key you input, and
     * returns the key/value pair if found
     *
     * @param key the key you want to get
     */
    public void get(String key) {
        checkThreads();
        threadCount++;

        //This block of code is used to test Multithreadedness
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (dict.containsKey(key)){
            System.out.println(getTime() + "GET Command: Key: " + key + " Value: " + dict.get(key));
            System.out.println(getTime() + "GET Command: Active thread count: " + Thread.activeCount());
            threadCount--;
            return;
        } else {
            System.out.println(getTime() + "GET Command: No value found for Key: " + key);
            System.out.println(getTime() + "GET Command: Active thread count: " + Thread.activeCount());
            threadCount--;
            return;
        }
    }

    /**
     * Remote Procedure Call - DELETE: removes key from map, or returns an error
     * message that it was not found
     *
     * @param key the key you want to delete
     */
    public void delete(String key) {
        checkThreads();
        threadCount++;

        //This block of code is used to test Multithreadedness
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (dict.containsKey(key)){
            System.out.println(getTime() + "DELETE Command: Deleted Key: " + key + " Value: " + dict.get(key));
            System.out.println(getTime() + "DELETE Command: Active thread count: " + Thread.activeCount());
            dict.remove(key);
            threadCount--;
            return;
        } else {
            System.out.println(getTime() + "DELETE COMMAND: No Key found to delete, Key: " + key);
            System.out.println(getTime() + "DELETE Command: Active thread count: " + Thread.activeCount());
            threadCount--;
            return;
        }
    }
}
