package bullet_hell.remote.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bullet_hell.remote.server.Server;
import bullet_hell.model.Leaderboard;

public class RemoteLeaderboard{
	
	private final String ip;

	public RemoteLeaderboard(String ip){
		this.ip = ip;
	}

	public Leaderboard getLeaderboard(){
		/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        try {
            Registry registry = LocateRegistry.getRegistry(ip);
            Server server = (Server) registry.lookup("Server");
            GetGlobalLeaderboard task = new GetGlobalLeaderboard();
            return server.executeTask(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	public void addScore(String name, int score){
		/*if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }*/
        try {
            Registry registry = LocateRegistry.getRegistry(ip);
            Server server = (Server) registry.lookup("Server");
            AddScoreToGlobalLeaderboard task = new AddScoreToGlobalLeaderboard(name, score);
            server.executeTask(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}
