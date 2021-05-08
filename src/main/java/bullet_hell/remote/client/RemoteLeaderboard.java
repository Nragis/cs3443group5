package bullet_hell.remote.client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import bullet_hell.remote.pub.LeaderboardServer;
import bullet_hell.model.Leaderboard;

public class RemoteLeaderboard{
	
	private LeaderboardServer server;

	public RemoteLeaderboard(String ip){
		try {
		    Registry registry = LocateRegistry.getRegistry(ip);
		    this.server = (LeaderboardServer) registry.lookup("LeaderboardServer");
		} catch (Exception e) {
			System.out.println("Failed to atatch to remote leaderboard");
		}
	}

	public Leaderboard getLeaderboard() throws RemoteException{
		if(this.server != null){
			try{
				return this.server.getLeaderboard();	
			} catch( Exception e){
				System.out.println("Failed to get leaderboard from remote leaderboard");
			}
		}
		return null;
	}

	public void addScore(String name, int score) throws RemoteException{
		if(this.server != null){
			try{
				this.server.addScore(name, score);	
			} catch( Exception e ){
				System.out.println("Failed to add score to remote leaderboard");
			}
		}
	}

}
