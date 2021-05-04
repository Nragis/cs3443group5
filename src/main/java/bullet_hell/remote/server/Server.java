package bullet_hell.remote.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bullet_hell.remote.both.Task;
import bullet_hell.remote.both.ServerInterface;

public class Server implements ServerInterface{

	public Server(){
		super();
	}

	public <T> T executeTask(Task<T> task) {
		System.out.println("Task recieved");
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
