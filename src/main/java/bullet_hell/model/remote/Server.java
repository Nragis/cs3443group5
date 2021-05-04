package bullet_hell.model.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bullet_hell.model.remote.Task;

public class Server implements ServerInterface{

	public Server(){
		super();
	}

	public <T> T executeTask(Task<T> task) {
		return task.execute();
	}

	public static void main(String[] args){
		/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        try {
			System.setProperty("java.rmi.server.hostname","nragis.com");
            String name = "Server";
            ServerInterface server = new Server();
            ServerInterface stub =
                (ServerInterface) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("Server bound");
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
