package bullet_hell.model.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bullet_hell.model.remote.Task;

public class Server implements Remote{

	public Server(){
		super();
	}

	public <T> T executeTask(Task<T> task) throws RemoteException{
		return task.execute();
	}

	public static void main(String[] args){
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "Server";
            Server server = new Server();
            Server stub =
                (Server) UnicastRemoteObject.exportObject(server, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("ComputeEngine bound");
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }

	}
}
