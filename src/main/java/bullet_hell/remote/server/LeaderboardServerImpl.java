package bullet_hell.remote.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bullet_hell.remote.server.LeaderboardServerImpl;
import bullet_hell.remote.pub.LeaderboardServer;
import bullet_hell.model.Leaderboard;

public class LeaderboardServerImpl implements LeaderboardServer{

	public LeaderboardServerImpl (){
		super();
	}

	public Leaderboard getLeaderboard(){
		System.out.println("Task recieved");
		return Leaderboard.fromJson("leaderboard.json");
	}

	public void addScore(String name, int score){
		Leaderboard leaderboard = Leaderboard.fromJson("leaderboard.json");
		leaderboard.addScore(name, score);
		leaderboard.toJson("leaderboard.json");
	}

	public static void main(String[] args){
        try {
			System.setProperty("java.rmi.server.hostname","nragis.com");
            String name = "LeaderboardServer";
            LeaderboardServer server = new LeaderboardServerImpl();
            LeaderboardServer stub =
                (LeaderboardServer) UnicastRemoteObject.exportObject(server, 1056);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("Server bound");
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
