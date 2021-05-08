package bullet_hell.remote.server;

import java.io.File;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bullet_hell.remote.server.LeaderboardServerImpl;
import bullet_hell.remote.pub.LeaderboardServer;
import bullet_hell.model.Leaderboard;

public class LeaderboardServerImpl implements LeaderboardServer{
	
	private static Registry registry;
	private Leaderboard leaderboard;

	public LeaderboardServerImpl () {
		super();

		File f = new File("leaderboard.json");
		if( f.exists() ){
			this.leaderboard = Leaderboard.fromJson("leaderboard.json");
		} else {
			this.leaderboard = new Leaderboard();
		}
	}

	public Leaderboard getLeaderboard(){
		return this.leaderboard;
	}

	public void addScore(String name, int score){
		this.leaderboard.addScore(name, score);
		this.leaderboard.toJson("leaderboard.json");
	}

	public static void main(String[] args) {
        try {
			System.setProperty("java.rmi.server.hostname","nragis.com");
            String name = "LeaderboardServer";
            LeaderboardServer server = new LeaderboardServerImpl();
            LeaderboardServer stub =
                (LeaderboardServer) UnicastRemoteObject.exportObject(server, 1056);
			registry = LocateRegistry.createRegistry(1099);
            registry.rebind(name, stub);
            System.out.println("Server bound");
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
