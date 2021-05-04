package bullet_hell.remote.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bullet_hell.remote.pub.LeaderboardServer;
import bullet_hell.model.Leaderboard;

public class RemoteLeaderboard{
	
	LeaderboardServer server;

	public RemoteLeaderboard(String ip){
		try {
		    Registry registry = LocateRegistry.getRegistry(ip);
		    this.server = (LeaderboardServer) registry.lookup("Server");
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}

	public Leaderboard getLeaderboard(){
		try{
			return this.server.getLeaderboard();	
		} catch( Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public void addScore(String name, int score){
		try{
			this.server.addScore(name, score);	
		} catch( Exception e ){
			e.printStackTrace();
		}
	}

}
